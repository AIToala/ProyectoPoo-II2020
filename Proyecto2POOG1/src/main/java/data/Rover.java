package data;

import java.io.FileInputStream;
import java.io.IOException;
import javafx.scene.control.TextArea;
import javafx.scene.effect.Light.Point;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 * Clase Rover, objeto movil que se encarga de sensar crateres.
 * @author ai_to
 */
public class Rover {
    
    public ImageView roverView;
    private Point ubicacion;
    private double orientacion = 0; //En el intervalo [-pi/2, 3pi/2)
    private double roverAncho = 10;
    private double roverAlto = 10;
    
    /**
     * Constructor de Rover
     * Carga informacion del rover.
     */
    public Rover(){
        try(FileInputStream f = new FileInputStream(constantes.constantes.robotFileName)){
                roverView = new ImageView(new Image(f,50,50,false,false));
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
    /**
     * Metodo avanzar del rover que avanza cierta distancia con respecto a su orientacion.
     * @param distancia distancia a moverse de acuerdo a su orientacion.
     */
    public void avanzar(double distancia){
        
        Point destino = new Point(ubicacion.getX() + distancia*Math.cos(orientacion), ubicacion.getY() + distancia*Math.sin(orientacion),0,Color.ALICEBLUE);
        Thread t = new Thread(new movimiento(destino));
        t.start();
    }
    /**
     * Metodo girar del rover que modifica la orientacion del rover. 
     * @param grados Grados de giro
     */
    public void girar(double grados){
        Thread t = new Thread(new movimiento(grados));
        t.start();
    }
    /**
     * Metodo dirigirse del rover que hace que el rover se posicione en las coordenadas
     * pasadas por parametro.
     * @param x Posicion en eje x del rover
     * @param y Posicion en eje y del rover
     */
    public void dirigirse(double x, double y){
        Point destino = new Point(x, y, 0, Color.ALICEBLUE);
        Thread t = new Thread(new movimiento(destino));
        t.start();
    }
    /**
     * Metodo sensar del rover, retorna coordenadas del rover usadas para sensar crater en la posicion
     * @return String con sus coordenadas
     */
    public String sensar(){        
        return getX()+","+getY(); 
    }
    /**
     * Metodo que retorna la ubicacion en X con respecto al nodo que se encuentra el rover
     * @return ubicacion en x (double)
     */
    public double getX(){
        return ubicacion.getX();
    }
    /**
     * Metodo que retorna la ubicacion en Y con respecto al nodo que se encuentra el rover
     * @return ubicacion en y (double)
     */
    public double getY(){
        return ubicacion.getY();
    }
    /**
     * Metodo que retorna la orientacion en grados del rover.
     * @return orientacion del rover.
     */
    public double getOrientacion() {
        return orientacion;
    }
    /**
     * Clase anonima de movimiento que permite el movimiento y las interacciones del rover.
     */
    class movimiento implements Runnable{
        double x;
        double y;
        double distanciaFaltante;
        double anguloFaltante;
        double direccion;
        boolean sentidoMasCorto = true;
        private TextArea comandosList;
        /**
         * Constructor de movimiento.
         * Usado para metodo avanzar del rover
         * @param destino Punto de destino
         */
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
        /**
         * Constructor de movimiento
         * Usado para metodo girar del rover
         * @param cantidadGrados orientacion del rover
         */
        public movimiento (double cantidadGrados){
            direccion = orientacion - cantidadGrados*Math.PI/180;
            x = ubicacion.getX();
            y = ubicacion.getY();
            distanciaFaltante = 0;
            sentidoMasCorto = false;
            
        }
        /**
         * Metodo sobreescrito de Runnable.
         */
        @Override
        public void run(){
            rotacion();
            traslacion();
        }
        /**
         * Metodo void que permite rotar al rover cierta cantidad de grados.
         */
        public void rotacion(){
            
            double difAngular = direccion - orientacion;
            if (sentidoMasCorto){
                if (Math.abs(orientacion - direccion) <= Math.PI) anguloFaltante = difAngular;
                else anguloFaltante = difAngular - Math.signum(difAngular)*2*Math.PI;
            }else anguloFaltante = difAngular;
            
            while (Math.abs(anguloFaltante) > constantes.constantes.dTheta){
                
                orientacion += Math.signum(anguloFaltante)*constantes.constantes.dTheta;
                roverView.setRotate(orientacion*180/Math.PI);
                
                anguloFaltante -= Math.signum(anguloFaltante)*constantes.constantes.dTheta;
                try{
                    Thread.sleep(constantes.constantes.dt);
                } catch(InterruptedException e){
                    System.out.println("F en el sleep :(");
                }
            }
            orientacion = direccion;
            roverView.setRotate(orientacion*180/Math.PI);
            while (orientacion < -Math.PI/2) orientacion += 2*Math.PI;    
            while (orientacion >= 3*Math.PI/2) orientacion -= 2*Math.PI;  
            //Los whiles sirven para mantener orientacion dentro del intervalo [-pi/2, 3pi/2) 
        }
        /**
         * Metodo que permite trasladar al rover a cierta posicion.
         */
        public void traslacion(){
            double newX = ubicacion.getX() + constantes.constantes.dx*Math.cos(direccion);
            double newY = ubicacion.getY() + constantes.constantes.dx*Math.sin(direccion);
            
            while (distanciaFaltante > constantes.constantes.dx){
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
            ubicacion.setX(x);
            ubicacion.setY(y);
            roverView.setX(x);
            roverView.setY(y);
        }
    }
}