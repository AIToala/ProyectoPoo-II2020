/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import approver.App;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Usuario
 */
public class VistaReporte {

    private VBox root;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String mineral;

    public VistaReporte() {
        root = new VBox();
        seccionBusqueda();
    }

    public void seccionBusqueda() {
        HBox hb1 = new HBox();
        Label l1 = new Label("Fecha Inicio");
        TextField tx1 = new TextField();
        
        hb1.setSpacing(20);
        hb1.getChildren().addAll(l1, tx1);
        
        HBox hb2 = new HBox();
        Label l2 = new Label("Fecha Fin    ");
        TextField tx2 = new TextField();
        
        hb2.setSpacing(20);
        hb2.getChildren().addAll(l2, tx2);

        HBox hb3 = new HBox();
        Label l3 = new Label("Mineral       ");
        TextField tx3 = new TextField();
        
        hb3.setSpacing(20);
        hb3.getChildren().addAll(l3, tx3);

        root.setSpacing(10);
        root.setPadding(new Insets(50));
        root.getChildren().addAll(hb1, hb2, hb3);

        tx1.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                try {
                    String str = String.valueOf(tx1.getText());
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    fechaInicio = LocalDate.parse(str, formatter);
                    System.out.println("MMMMHOLA");
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });

        tx2.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                try {
                    String str = String.valueOf(tx2.getText());
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    fechaFin = LocalDate.parse(str, formatter);
                    System.out.println("MMMMHOLA2");
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
        
        tx3.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                mineral = String.valueOf(tx3.getText());
            }
        });
    }
    
    
    
    
    
    
    
    

    public void seccionTabla() {
        VBox v1 = new VBox();
        v1.setAlignment(Pos.CENTER);
        v1.setSpacing(15);
    }

    public Pane getRoot() {
        return root;
    }
}
