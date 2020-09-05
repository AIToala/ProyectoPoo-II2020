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
/**
 * Clase Crater, datos que el programa analizara. 
 * Implementa interfaz Comparable.
 * @author ai_to
 */
public class Crater implements Comparable<Crater>{
    private String id;
    private String nombre;
    private double radio;
    private double latitud;
    private double longitud;
    private boolean sensado;
    private List<String> minerales;
    private LocalDateTime fechaExploracion;
    
    /**
     * Constructor Crater con los 5 atributos obtenidos de fuentes externas.
     * @param id
     * @param nombre
     * @param latitud
     * @param longitud
     * @param radio 
     */
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
    /**
     * Metodo generarMinerales, genera minerales de forma aleatoria 
     * listos para ser agregados a un Crater.
     * @return Lista de Strings con minerales generados aleatoriamente. 
     */
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
    /**
     * Metodo que retorna el id del Crater
     * @return id del Crater
     */
    public String getId() {
        return id;
    }
    /**
     * Metodo que modifica el id del crater
     * @param id id del crater
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * Metodo que retorna el nombre del crater
     * @return nombre del crater
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Metodo que modifica el nombre del crater
     * @param nombre nombre del crater
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * Metodo que retorna el radio del crater.
     * @return radio del crater
     */
    public double getRadio() {
        return radio;
    }
    /**
     * Metodo que modifica el radio del crater
     * @param radio radio del crater
     */
    public void setRadio(double radio) {
        this.radio = radio;
    }
    /**
     * Metodo que retorna la latitud del crater
     * @return latitud del crater
     */
    public double getLatitud() {
        return latitud;
    }

    /**
     * Metodo que modifica la latitud del crater
     * @param latitud latitud del crater
     */
    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    /**
     * Metodo que retorna la longitud del crater
     * @return longitud del crater
     */
    public double getLongitud() {
        return longitud;
    }

    /**
     * Metodo que modifica la longitud del crater
     * @param longitud longitud del crater
     */
    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    /**
     * Metodo que retorna si el crater fue sensado.
     * @return true si fue sensado, false si no fue sensado.
     */
    public boolean isSensado() {
        return sensado;
    }

    /**
     * Metodo que modifica si el crater fue sensado.
     * @param sensado true o false si el sensado se realizo con exito.
     */
    public void setSensado(boolean sensado) {
        this.sensado = sensado;
    }

    /**
     * Metodo que retorna los minerales del crater
     * @return lista con los minerales del crater
     */
    public List<String> getMinerales() {
        return minerales;
    }
    /**
     * Metodo que retorna los minerales del crater
     * @return string con los minerales del crater
     */
    public String getMineralesStr(){
        if(this.minerales.isEmpty()){return "";}
        String mineralesT = "";
        for(String s: this.minerales){
            mineralesT += s + ",";
        }
        return mineralesT.substring(0, mineralesT.lastIndexOf(","));
    }
    /**
     * Metodo que modifica los minerales del crater
     * @param minerales lista de minerales
     */
    public void setMinerales(List<String> minerales) {
        this.minerales = minerales;
    }

    /**
     * Metodo que retorna la fecha que fue explorada el crater
     * @return fecha de exploracion
     */
    public LocalDateTime getFechaExploracion() {
        return fechaExploracion;
    }

    /**
     * Metodo que modifica la fecha que fue explorada el crater
     * @param fechaExploracion fecha de exploracion
     */
    public void setFechaExploracion(LocalDateTime fechaExploracion) {
        this.fechaExploracion = fechaExploracion;
    }
    /**
     * Metodo que retorna la distancia mas corta desde el origen al crater
     * @return distancia mas corta
     */
    public double getDistanciaCorta(){
        return Math.abs(Math.sqrt(Math.pow(latitud, 2)+Math.pow(longitud, 2))-radio);
    }
    /**
     * Metodo compareTo sobreescrito de interfaz Comparable
     * @return 
     */
    @Override
    public int compareTo(Crater c){
        if(this.getDistanciaCorta() < c.getDistanciaCorta()){
            return -1;
        }
        if(this.getDistanciaCorta() > c.getDistanciaCorta()){
            return 1;
        }
        return 0;
    }
    
}
