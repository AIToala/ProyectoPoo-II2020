/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package approver;

import data.Crater;
import data.Reporte;
import data.Rover;
import datacarga.CraterData;
import datacarga.ReporteData;
import java.io.IOException;
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
    public static ArrayList<Crater> crateres;
    public static ArrayList<Reporte> reportes;
    public static Stage stageP;
    
    @Override
    public void start(Stage primaryStage) {
        try{
            crateres = CraterData.leerCrateresData();
            reportes = ReporteData.leerReportes();
            System.out.println(crateres.size());
        }catch(IOException ex){
            crateres = new ArrayList<>();
            reportes = new ArrayList<>();
        }
        //creamos un objeto de tipo VistaPrincipal
        vp = new VistaPrincipal();
        //creamos la escena y a la escena le agregamos el contenedor raiz
        //fijamos la escena al stage
        stageP = primaryStage;
        Scene sceneP = new Scene(vp.getRoot(), 600, 600);
        stageP.setScene(sceneP);
        stageP.setTitle("Exploracion Rover");
        stageP.show();
    }
    
    public static void main(String[] args){
        launch(args);
    }

    
}
