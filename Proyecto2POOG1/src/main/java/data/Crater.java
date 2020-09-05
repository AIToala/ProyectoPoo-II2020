/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import javafx.geometry.Point2D;

/**
 *
 * @author ai_to
 */
public class Crater {
    private String id;
    private String nombre;
    private double radio;
    private double latitud;
    private double longitud;
    private boolean sensado;
    private List<String> minerales;
    private LocalDateTime fechaExploracion;

    public Crater(String id, String nombre, double latitud, double longitud, double radio) {
        this.id = id;
        this.nombre = nombre;
        this.radio = radio;
        this.latitud = latitud;
        this.longitud = longitud;
        this.sensado = false;
        this.minerales = generarMinerales();
        this.fechaExploracion = LocalDateTime.MIN; 
    }
    
    public List<String> generarMinerales(){
        List<String> generado = new ArrayList<>();
        List<String> minerales = Arrays.asList("Adirondack", "Barnacle Bill", "Bathurst Inlet", "Big Joe", "Block Island",
                                "M Bounce", "Coronation", "El Capitan", "Esperance", "Goulburn", "Heat Shield");
        int cantMinerales = (int)(Math.random()*minerales.size()) + 1;
        for (int i = 0; i < cantMinerales; i++) {
            int index = (int)(Math.random()*minerales.size());
            generado.add(minerales.get(index));
        }
        return generado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getRadio() {
        return radio;
    }

    public void setRadio(double radio) {
        this.radio = radio;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public boolean isSensado() {
        return sensado;
    }

    public void setSensado(boolean sensado) {
        this.sensado = sensado;
    }

    public List<String> getMinerales() {
        return minerales;
    }
    public String getMineralesStr(){
        if(this.minerales.isEmpty()){return "";}
        String mineralesT = "";
        for(String s: this.minerales){
            mineralesT += s + ",";
        }
        return mineralesT.substring(0, mineralesT.lastIndexOf(","));
    }
    public void setMinerales(List<String> minerales) {
        this.minerales = minerales;
    }

    public LocalDateTime getFechaExploracion() {
        return fechaExploracion;
    }

    public void setFechaExploracion(LocalDateTime fechaExploracion) {
        this.fechaExploracion = fechaExploracion;
    }
    public double getDistanciaCorta(Point2D origen){
        return Math.abs(Math.sqrt(Math.pow(latitud - origen.getX(), 2)+Math.pow(longitud - origen.getY(), 2))-radio);
    }
    
}
