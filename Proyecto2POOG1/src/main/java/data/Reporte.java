/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author ai_to
 */
public class Reporte {
    LocalDateTime fechaInicio;
    LocalDateTime fechaFin;
    ArrayList<LocalDateTime> fechaExploracion;
    ArrayList<String> minerales;
    ArrayList<String> nombreCrater;
}
