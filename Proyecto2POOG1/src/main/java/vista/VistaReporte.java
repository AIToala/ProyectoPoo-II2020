/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Usuario
 */
public class VistaReporte {
    private GridPane gp;
    
    public VistaReporte(){
        gp = new GridPane();
        seccionBusqueda();
    }
    
    public void seccionBusqueda(){
        VBox vb = new VBox();
        Label l1 = new Label("Fecha Inicio");
        Label l2 = new Label("Fecha Fin");
        Label l3 = new Label("Mineral");
        vb.getChildren().addAll(l1, l2, l3);
        
        VBox vb2 = new VBox();
        TextField tx1 = new TextField();
        TextField tx2 = new TextField();
        TextField tx3 = new TextField();
        vb2.getChildren().addAll(tx1, tx2, tx3);
        
        GridPane.setConstraints(vb, 0, 0);
        GridPane.setConstraints(vb2, 1, 0);
        gp.getChildren().addAll(vb, vb2);
    }
    
    
}
