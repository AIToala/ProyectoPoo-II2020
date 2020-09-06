/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Clase Reporte que contiene informacion con referente al crater sensado.
 * @author ai_to
 */
public class Reporte /*implements Comparable<Reporte>*/{
    private LocalDateTime fechaExploracion;
    private List<String> minerales;
    private String nombreCrater;
    /**
     * Constructor de la clase reporte que recibe los siguientes parametros.
     * @param fechaExploracion fecha de exploracion del rover
     * @param minerales minerales que obtuvo del crater
     * @param nombreCrater nombre del crater
     */
    public Reporte(LocalDateTime fechaExploracion, List<String> minerales, String nombreCrater) {
        this.fechaExploracion = fechaExploracion;
        this.minerales = minerales;
        this.nombreCrater = nombreCrater;
    }
    /**
     * Metodo que retorna la fecha de exploracion registrada por el rover.
     * @return fecha de exploracion
     */
    public LocalDateTime getFechaExploracion() {
        return fechaExploracion;
    }    
    /**
     * Metodo que modifica la fecha de exploracion registrada por el rover
     * @param fechaExploracion fecha de exploracion
     */
    public void setFechaExploracion(LocalDateTime fechaExploracion) {
        this.fechaExploracion = fechaExploracion;
    }
    /**
     * Metodo que retorna la lista de minerales registrada por el rover.
     * @return lista de minerales
     */
    public List<String> getMinerales() {
        return minerales;
    }
    /**
     * Metodo que retorna un string que contiene la lista de minerales registrada por el rover.
     * @return string con todos los minerales.
     */
    public String getMineralesStr(){
        if(this.minerales.isEmpty()){return "";}
        String minerales = "";
        for(String s: this.minerales){
            minerales += s + ",";
        }
        return minerales.substring(0, minerales.lastIndexOf(","));
    }
    /**
     * Metodo que modifica la lista de minerales registrada por el rover.
     * @param minerales lista de minerales
     */
    public void setMinerales(List<String> minerales) {
        this.minerales = minerales;
    }
    /**
     * Metodo que retorna el nombre del crater registrado.
     * @return 
     */
    public String getNombreCrater() {
        return nombreCrater;
    }
    /**
     * Metodo que modifica el nombre del crater registrado.
     * @param nombreCrater 
     */
    public void setNombreCrater(String nombreCrater) {
        this.nombreCrater = nombreCrater;
    }
    
    /*public int compareTo(Reporte r){
        return this.getFechaExploracion().compareTo(r.getFechaExploracion());
    }
    
    public class NombreComparator implements Comparator<Reporte> {
 
        @Override
        public int compare(Reporte r1, Reporte r2) {
           return r1.getNombreCrater().compareTo(r2.getNombreCrater());
        }
 
    }*/

    @Override
    public String toString() {
        return "Reporte{" + "fechaExploracion=" + fechaExploracion + '}';
    }
}
