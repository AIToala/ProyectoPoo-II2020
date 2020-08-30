/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.util.Random;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
/**
 *
 * @author ai_to
 */
public class VistaPrincipal {
    private BorderPane root;
    
    public VistaPrincipal(){
        root = new BorderPane();
        
        VBox boxBotones = new VBox();
        Button btnExplorar = new Button("Explorar");
        Button btnPlanificar = new Button("Planificar Rutas");
        Button btnReportes = new Button("Ver Reportes");
        Button btnSalir = new Button("Salir");
    
        boxBotones.getChildren().addAll(btnExplorar, btnPlanificar, btnReportes, btnSalir);
        boxBotones.setAlignment(Pos.CENTER);
        boxBotones.setSpacing(20);
        root.setCenter(boxBotones);
        
        btnExplorar.setOnAction(
            (event)->{
                /*
                Scene sceneExp = new Scene(new VistaExplorar());
                Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                appStage.setScene(sceneExp);
                appStage.toFront();
                appStage.show();
                */
            }
        );
    }
    public Pane getRoot(){
        return root;
    }

    
}
