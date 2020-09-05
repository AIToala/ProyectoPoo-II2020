package vista;

import data.Rover;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author user
 */
public class VistaExplorar {
    private VBox root = new VBox();
    private ImageView mapView;
    private Rover robot;
    
    public VistaExplorar(){
        try(FileInputStream f = new FileInputStream(constantes.constantes.mapFileName)){
            mapView = new ImageView(new Image(f));
            mapView.setFitWidth(constantes.constantes.mapAncho);
            mapView.setFitHeight(constantes.constantes.mapAlto);
        }catch (IOException e){
            System.out.println("¡Algo salió mal al cargar el mapa!");
        }
        
        robot = new Rover();
        
        Pane espacioExplorar = new Pane(mapView, robot.roverView);
        espacioExplorar.setMaxSize(constantes.constantes.mapAncho, constantes.constantes.mapAlto);
        
        Label ingreseComandosLabel = new Label("Ingrese comandos:");
        TextField comando = new TextField();
        Label comandosListLabel = new Label("Comandos ingresados:");
        TextArea comandosList = new TextArea("");
        
        
        
        VBox comandosPanel = new VBox(ingreseComandosLabel, comando, comandosListLabel, comandosList);
        comandosPanel.setMaxWidth(200);
        
        
        HBox mapaComandos = new HBox(espacioExplorar, comandosPanel);
        mapaComandos.setMaxSize(1480, 600);
        
        root.getChildren().add(mapaComandos);
        
        //Movimiento del robot
        comando.setOnKeyReleased((key) -> {
            if(key.getCode()== KeyCode.ENTER){
                String texto = comando.getText();
                Boolean esComando = true;
                Boolean escapa = false;
                String separador ="\n";
                try{
                    if("sensar".equals(texto)) robot.sensar();
                    
                    else if("girar:".equals(texto.substring(0, 6))){
                        double g = Double.parseDouble(texto.substring(6));
                        robot.girar(g);
                        
                    }else if(texto.substring(0, 8).equals("avanzar:")){
                        double d = Double.parseDouble(texto.substring(8));
                        double x = robot.getX() + d*Math.cos(robot.getOrientacion());
                        double y = robot.getY() + d*Math.sin(robot.getOrientacion());
                        escapa = 0 > x || x > constantes.constantes.mapAncho || 0 > y || y > constantes.constantes.mapAlto;
                        if(!escapa) robot.avanzar(d);
                    }else if("dirigirse:".equals(texto.substring(0, 10))){
                        String coordinates = texto.substring(10);
                        double x = Double.parseDouble(coordinates.split(",")[0]);
                        double y = Double.parseDouble(coordinates.split(",")[1]);
                        escapa = 0 > x || x > constantes.constantes.mapAncho || 0 > y || y > constantes.constantes.mapAlto;
                        if(!escapa) robot.dirigirse(x, y);
                    }
                    else esComando = false;
                    
                    if (escapa) separador = " (abortado)\n";
                    
                    if (esComando){
                        comandosList.setText(texto + separador + comandosList.getText());
                    }else throw new IndexOutOfBoundsException();
                    
                    comando.clear();
                }catch(NumberFormatException|IndexOutOfBoundsException e){
                    if (e instanceof NumberFormatException){
                        comandosList.setText("¡Entrada no numérica!\n" + comandosList.getText());
                        comando.clear();
                    }
                    else {
                        comandosList.setText(texto + " (El comando no existe)\n" + comandosList.getText());
                        comando.clear();
                    }
                }
            }
        });
        
        Label craterInfo = new Label("Crater jaja");
        root.getChildren().add(craterInfo);
    }
    
    public VBox getRoot(){
        return root;
    }
}
