package data;

import java.io.FileInputStream;
import java.io.IOException;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.effect.Light.Point;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 *
 * @author ai_to
 */
public class Rover {
    
    public ImageView roverView;
    private Point ubicacion;
    private double orientacion = 0; //En el intervalo [-pi/2, 3pi/2)
    private double roverAncho = 200;
    private double roverAlto = 200;
    

    public Rover(){
        try(FileInputStream f = new FileInputStream(constantes.constantes.robotFileName)){
                roverView = new ImageView(new Image(f));
                roverView.setFitWidth(roverAncho);
                roverView.setFitHeight(roverAlto);
        }catch (IOException e){
            System.out.println("¡Algo salió mal al cargar el robot!");
        }
        roverView.setTranslateX(-roverAncho/2);
        roverView.setTranslateY(-roverAlto/2);
        roverView.setX(roverAncho/2);
        roverView.setY(roverAlto/2);
        this.ubicacion = new Point(roverView.getX(), roverView.getY(), 0, Color.ALICEBLUE);
        
    }
    public void avanzar(double distancia){
        
        Point destino = new Point(ubicacion.getX() + distancia*Math.cos(orientacion), ubicacion.getY() + distancia*Math.sin(orientacion),0,Color.ALICEBLUE);
        Thread t = new Thread(new movimiento(destino));
        t.start();
    }
    public void girar(double grados){
        Thread t = new Thread(new movimiento(grados));
        t.start();
    }
    public void dirigirse(double x, double y){
        Point destino = new Point(x, y, 0, Color.ALICEBLUE);
        Thread t = new Thread(new movimiento(destino));
        t.start();
    }
    public String sensar(){return "Hola";}
    
    public double getX(){
        return ubicacion.getX();
    }
    
    public double getY(){
        return ubicacion.getY();
    }
    
    public double getOrientacion() {
        return orientacion;
    }
    
    class movimiento implements Runnable{
        double x;
        double y;
        double distanciaFaltante;
        double anguloFaltante;
        double direccion;
        boolean sentidoMasCorto = true;
        private TextArea comandosList;

        public movimiento(Point destino) {
            this.x = destino.getX();
            this.y = destino.getY();
            distanciaFaltante = Math.sqrt(Math.pow(ubicacion.getX()-x, 2)+ Math.pow(ubicacion.getY()-y, 2));
            
            double recorridoX = x-ubicacion.getX();
            double recorridoY = y-ubicacion.getY();
            if (recorridoX > 0) direccion = Math.atan(recorridoY/recorridoX);
            else if (recorridoX < 0) direccion = Math.atan(recorridoY/recorridoX) + Math.PI;
            else direccion = Math.signum(recorridoY) * Math.PI/2;
            
            
        }
        
        public movimiento (double cantidadGrados){
            direccion = orientacion - cantidadGrados*Math.PI/180;
            x = ubicacion.getX();
            y = ubicacion.getY();
            distanciaFaltante = 0;
            sentidoMasCorto = false;
            
        }
        
        public void run(){
            rotacion();
            traslacion();
        }
        
        public void rotacion(){
            
            double difAngular = direccion - orientacion;
            if (sentidoMasCorto){
                if (Math.abs(orientacion - direccion) <= Math.PI) anguloFaltante = difAngular;
                else anguloFaltante = difAngular - Math.signum(difAngular)*2*Math.PI;
            }else anguloFaltante = difAngular;
            
            boolean escapa = 0 > x || x > constantes.constantes.mapAncho || 0 > y || y > constantes.constantes.mapAlto;
            while (Math.abs(anguloFaltante) > constantes.constantes.dTheta && !escapa){
                
                orientacion += Math.signum(anguloFaltante)*constantes.constantes.dTheta;
                roverView.setRotate(orientacion*180/Math.PI);
                
                anguloFaltante -= Math.signum(anguloFaltante)*constantes.constantes.dTheta;
                try{
                    Thread.sleep(constantes.constantes.dt);
                } catch(InterruptedException e){
                    System.out.println("F en el sleep :(");
                }
            }
            if (!escapa){
                orientacion = direccion;
                roverView.setRotate(orientacion*180/Math.PI);
                while (orientacion < -Math.PI/2) orientacion += 2*Math.PI;
                while (orientacion >= 3*Math.PI/2) orientacion -= 2*Math.PI;
            }
        }
        
        public void traslacion(){
            double newX = ubicacion.getX() + constantes.constantes.dx*Math.cos(direccion);
            double newY = ubicacion.getY() + constantes.constantes.dx*Math.sin(direccion);
            boolean escapa = 0 > x || x > constantes.constantes.mapAncho || 0 > y || y > constantes.constantes.mapAlto;
            System.out.println(escapa);
            while (distanciaFaltante > constantes.constantes.dx && (!escapa)){
                ubicacion.setX(newX);
                ubicacion.setY(newY);
                roverView.setX(newX);
                roverView.setY(newY);
                
                distanciaFaltante -= constantes.constantes.dx;
                try{
                    Thread.sleep(constantes.constantes.dt);
                } catch(InterruptedException e){
                    System.out.println("F en el sleep :(");
                }
                newX = ubicacion.getX() + constantes.constantes.dx*Math.cos(direccion);
                newY = ubicacion.getY() + constantes.constantes.dx*Math.sin(direccion);
                
            }
            if (!escapa){
                ubicacion.setX(x);
                ubicacion.setY(y);
                roverView.setX(x);
                roverView.setY(y);
            }
        }
    }
}