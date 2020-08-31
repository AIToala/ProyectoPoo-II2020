/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import approver.App;
import java.util.Random;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.event.EventType;
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
import javafx.stage.WindowEvent;
/**
 *
 * @author ai_to
 */
public class VistaPrincipal {
    private BorderPane root;
    private boolean salida = false;
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
                App.stageP.hide();
                Scene scene = new Scene(new VistaExplorar().getRoot());
                Stage appStage = new Stage();
                appStage.setScene(scene);
                appStage.toFront();
                appStage.show();
                appStage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, 
                    (e)->{
                        App.stageP.show();
                    }
                );
            }
        );
        btnPlanificar.setOnAction(
            (event)->{
                App.stageP.hide();
                Scene scene = new Scene(new VistaPlanificar().getRoot(), 1200, 600);
                Stage appStage = new Stage();
                appStage.setScene(scene);
                appStage.toFront();
                appStage.show();
                appStage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, 
                    (e)->{
                        App.stageP.show();
                    }
                );
            }
        );
        btnReportes.setOnAction(
            (event)->{
                App.stageP.hide();
                Scene scene = new Scene(new VistaReporte().getRoot(), 1200, 600);
                Stage appStage = new Stage();
                appStage.setScene(scene);
                appStage.toFront();
                appStage.show();
                appStage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, 
                    (e)->{
                        App.stageP.show();
                    }
                );
            }
        );
        btnSalir.setOnAction(
            (event)->{
                App.stageP.close();
            }
        );
    }
    public Pane getRoot(){
        return root;
    }
}
