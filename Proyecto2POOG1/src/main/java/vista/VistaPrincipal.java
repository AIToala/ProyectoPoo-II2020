/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import approver.App;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
/**
 * Clase de Vista Principal, vista del menu principal de la aplicacion
 * @author ai_to
 */
public class VistaPrincipal {
    private BorderPane root;
    private boolean salida = false;
    /**
     * Constructor de Vista Principal, inicializa informacion y graficos del 
     * escenario.
     */
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
        //Evento de Boton Explorar
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
        //Evento del boton Planificar Ruta
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
        //Evento del boton Reportes
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
        //Evento del boton salir
        btnSalir.setOnAction(
            (event)->{
                App.stageP.close();
            }
        );
    }
    /**
     * Metodo que retorna el nodo raiz de la vista principal
     * @return pane
     */
    public Pane getRoot(){
        return root;
    }
}
