/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import approver.App;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

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
        TextArea crateresList = new TextArea("");

        boxRuta.setAlignment(Pos.BASELINE_CENTER);
        root.getChildren().add(top);
        root.getChildren().add(boxRuta);
                    
        ta.setOnKeyReleased(
            (key)->{
                //planificarRuta() --> que devuelve la lista de crateres en orden de visita.
                if(key.getCode()== KeyCode.ENTER && !ta.getText().equals("")){
                    String texto = ta.getText();
                    if(texto.contains(",")){
                        String[] crateres = texto.split(",");
                        double distancePiv = Double.MAX_VALUE;
                        for(String crater: crateres){
                            System.out.println("");
                        }
                    
                    }else{
                        crateresList.setText(ta.getText());
                    }
                    ta.clear();
                    
                    //usar comparable con distancias...
                    
                }
            }
        );
    }
    public Pane getRoot(){
        return root;
    }

}
