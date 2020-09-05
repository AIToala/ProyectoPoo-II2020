/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ai_to
 */
public class Reporte {
    private LocalDateTime fechaExploracion;
    private List<String> minerales;
    private String nombreCrater;

    public Reporte(LocalDateTime fechaExploracion, List<String> minerales, String nombreCrater) {
        this.fechaExploracion = fechaExploracion;
        this.minerales = minerales;
        this.nombreCrater = nombreCrater;
    }

    public LocalDateTime getFechaExploracion() {
        return fechaExploracion;
    }

    public void setFechaExploracion(LocalDateTime fechaExploracion) {
        this.fechaExploracion = fechaExploracion;
    }

    public List<String> getMinerales() {
        return minerales;
    }
    public String getMineralesStr(){
        if(this.minerales.isEmpty()){return "";}
        String minerales = "";
        for(String s: this.minerales){
            minerales += s + ",";
        }
        return minerales.substring(0, minerales.lastIndexOf(","));
    }

    public void setMinerales(List<String> minerales) {
        this.minerales = minerales;
    }

    public String getNombreCrater() {
        return nombreCrater;
    }

    public void setNombreCrater(String nombreCrater) {
        this.nombreCrater = nombreCrater;
    }
    
    
}
