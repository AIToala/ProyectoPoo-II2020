/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import javafx.scene.effect.Light.Point;
import javafx.scene.image.ImageView;

/**
 *
 * @author ai_to
 */
public class Rover {
ImageView rover;
Point ubicacion;
double orientacion;
double roverAncho;
double roverAlto;

public boolean avanzar(double distancia){return true;}
public boolean girar(double grados){return true;}
public boolean dirigirse(double x, double y){return true;}
public String sensar(){return "Hola";}

}