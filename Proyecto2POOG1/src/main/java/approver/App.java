/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package approver;

import data.Crater;
import data.Reporte;
import data.Rover;
import java.util.ArrayList;
import vista.VistaPrincipal;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 *
 * @author ai_to
 */
public class App extends Application{
    VistaPrincipal vp;
    Rover roverPrincipal;
    ArrayList<Crater> crateres;
    ArrayList<Reporte> reportes;
    
    
    
    @Override
    public void start(Stage primaryStage) {
        //creamos un objeto de tipo VistaPrincipal
        vp = new VistaPrincipal();
        //creamos la escena y a la escena le agregamos el contenedor raiz
        Scene theScene = new Scene(vp.getRoot(), 600, 600);
        //fijamos la escena al stage
        primaryStage.setScene(theScene);
        primaryStage.setTitle("Exploracion Rover");
        primaryStage.show();
    }
    
    public static void main(String[] args){
        launch(args);
    }
    
    /**
     * Este metodo se llama cuando se manda a cerrar la aplicacion
     */
    public void stop(){
    }
    
}
