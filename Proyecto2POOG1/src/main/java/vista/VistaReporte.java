/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import approver.App;
import data.Reporte;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Usuario
 */
public class VistaReporte extends Vista {

    private VBox root;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String mineral;
    private TextField tx1;
    private TextField tx2;
    private TextField tx3;
    private TableView<Reporte> tableReporte;

    public VistaReporte() {
        root = new VBox();
        seccionBusqueda();
        seccionTabla();
    }

    public void seccionBusqueda() {
        HBox hb1 = new HBox();
        Label l1 = new Label("Fecha Inicio");
        tx1 = new TextField();

        hb1.setSpacing(20);
        hb1.getChildren().addAll(l1, tx1);

        HBox hb2 = new HBox();
        Label l2 = new Label("Fecha Fin    ");
        tx2 = new TextField();

        hb2.setSpacing(20);
        hb2.getChildren().addAll(l2, tx2);

        HBox hb3 = new HBox();
        Label l3 = new Label("Mineral       ");
        tx3 = new TextField();

        hb3.setSpacing(20);
        hb3.getChildren().addAll(l3, tx3);

        root.setSpacing(10);
        root.setPadding(new Insets(50));
        root.getChildren().addAll(hb1, hb2, hb3);

        tx1.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                try {
                    filtrar();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });

        tx2.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                try {
                    filtrar();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });

        tx3.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                filtrar();
            }
        });
    }

    public void filtrar() {
        String str1 = String.valueOf(tx1.getText());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime fechaInicio = LocalDateTime.parse(str1, formatter);
        String str2 = String.valueOf(tx2.getText());
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime fechaFin = LocalDateTime.parse(str2, formatter);
        String mineral = String.valueOf(tx3.getText());
        
        ArrayList<Reporte> reportesNuevos = new ArrayList<>();
        for(Reporte rp: App.reportes){
            LocalDateTime f = rp.getFechaExploracion();
            if (f.compareTo(fechaInicio)>=0 && f.compareTo(fechaFin)<=0 && rp.getMinerales().contains(mineral)){
                reportesNuevos.add(rp);
            }
        }
        
        ObservableList<Reporte> reportesNuevosObs = FXCollections.observableArrayList(reportesNuevos);
        tableReporte.setItems(reportesNuevosObs);
    }

    public void seccionTabla() {

        if (!App.reportes.isEmpty()) {
            VBox v1 = new VBox();
            v1.setAlignment(Pos.CENTER);
            v1.setSpacing(15);
            tableReporte = new TableView<>();
            //filtrado
            ObservableList<Reporte> reportes = FXCollections.observableArrayList(App.reportes);
            tableReporte.setItems(reportes);
            tableReporte.setMaxSize(500, 600);
            tableReporte.setMinSize(250, 300);

            TableColumn<Reporte, LocalDateTime> colFecha = new TableColumn<>("Fecha de exploracion");
            colFecha.setCellValueFactory(new PropertyValueFactory<>("fechaExploracion"));
            colFecha.setMinWidth(tableReporte.getMaxWidth() / 4);

            TableColumn<Reporte, LocalDateTime> colMinerales = new TableColumn<>("Minerales");
            colMinerales.setCellValueFactory(new PropertyValueFactory<>("minerales"));
            colMinerales.setMinWidth(tableReporte.getMaxWidth() / 4);

            TableColumn<Reporte, LocalDateTime> colNomCrater = new TableColumn<>("Nombre del cràter");
            colNomCrater.setCellValueFactory(new PropertyValueFactory<>("nombreCrater"));
            colNomCrater.setMinWidth(tableReporte.getMaxWidth() / 4);

            tableReporte.getColumns().addAll(colFecha, colMinerales, colNomCrater);
            v1.getChildren().add(tableReporte);
            root.getChildren().add(v1);
        }
    }

    public Pane getRoot() {
        return root;
    }
}
