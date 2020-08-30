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
import javafx.scene.control.TextArea;
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
public class VistaPlanificar {
    private VBox root;
    
    public VistaPlanificar(){
        root = new VBox();
        Label nomCrater = new Label("Nombre de crateres");
        TextArea ta = new TextArea();
        HBox top = new HBox();
        top.getChildren().addAll(nomCrater, ta);
        
        VBox boxRuta = new VBox();
        boxRuta.setAlignment(Pos.BASELINE_CENTER);
        root.getChildren().add(top);
        root.getChildren().add(boxRuta);
        
        ta.setOnKeyPressed(
            (event)->{
                //planificarRuta() --> que devuelve la lista de crateres en orden de visita.
                int listaRuta = 10;
                for (int i = 0; i < listaRuta; i++) {
                    boxRuta.getChildren().add(new Label(i + ". " + "Crater"));
                }
                
            }
        );
    }
    public Pane getRoot(){
        return root;
    }

}
