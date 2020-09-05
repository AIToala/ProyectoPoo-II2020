/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import approver.App;
import data.Crater;
import java.util.ArrayList;
import java.util.Collections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * Clase vistaPlanificar, contiene la vista que obtiene la ruta optimizada pasado
 * como parametro los crateres a recorrer.
 * @author ai_to
 */
public class VistaPlanificar {
    private VBox root;
    TextArea crateresList;
    /**
     * Constructor de VistaPlanificar
     * inicializa la vista grafica de esta Escena.
     */
    public VistaPlanificar(){
        root = new VBox();
        root.setPadding(new Insets(20,20,20,20));
        root.setSpacing(10);
        
        Label nomCrater = new Label("Nombre de crateres");
        TextField ta = new TextField();
        ta.setMinWidth(root.getWidth()-13);
        HBox top = new HBox();
        top.getChildren().addAll(nomCrater, ta);
        top.setSpacing(20);
        
        VBox boxRuta = new VBox();
        crateresList = new TextArea("");
        crateresList.autosize();
        boxRuta.setAlignment(Pos.CENTER);
        root.getChildren().add(top);
        root.getChildren().add(boxRuta);
        boxRuta.getChildren().add(crateresList);
        // Evento sobre el TextArea que contiene los crateres a recorrer.
        ta.setOnKeyReleased(
            (key)->{
                //planificarRuta() --> que devuelve la lista de crateres en orden de visita.
                if(key.getCode()== KeyCode.ENTER && !ta.getText().equals("")){
                    crateresList.clear();
                    crateresList.setEditable(false);
                    String texto = ta.getText().toUpperCase();
                    if(texto.contains(",")){
                        String[] crateres = texto.split(",");
                        ArrayList<String> cratStrList = new ArrayList<>();
                        for(String crater : crateres){
                            crater = crater.toUpperCase();
                            cratStrList.add(crater);
                        }
                        planificarRuta(cratStrList);
                    
                    }else{
                        if(!App.crateres.isEmpty()){
                            for(Crater crat: App.crateres){
                                if(crat.getNombre().toUpperCase().equals(texto)){
                                    crateresList.setText("1. /t" + texto.toUpperCase());
                                }
                            }
                        }
                        crateresList.setText(ta.getText());
                    }
                    ta.clear();
                    
                    //usar comparable con distancias...
                    
                }
            }
        );
    }
    /**
     * Metodo que modifica la escena y genera la ruta optimizada con los crateres que se agregan.
     * @param cratList lista de crateres por recorrer
     */
    public void planificarRuta(ArrayList<String> cratList){
        ArrayList<Crater> destinosCrt = new ArrayList<>();
        if(!App.crateres.isEmpty()){
            if(!cratList.isEmpty()){
                for(String cratStr : cratList){
                    for(Crater crat : App.crateres){
                        if(cratStr.equals(crat.getNombre().toUpperCase())){
                            destinosCrt.add(crat);
                        }
                    }
                }
                if(!destinosCrt.isEmpty()){
                    //doble for para comparar entre todos en la lista.
                    Collections.sort(destinosCrt, (c1, c2)-> c1.compareTo(c2));
                    int i=1;
                    for(Crater crat : destinosCrt){
                        crateresList.setText(crateresList.getText()+"\n"+i+".- "+crat.getNombre());
                        i++;
                    }
                }
            }
        
        }   
    }
    /**
     * Metodo que retorna el nodo principal de esta escena.
     * @return Pane
     */
    public Pane getRoot(){
        return root;
    }

}
