/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author ai_to
 */
public interface RoverComandos {
    public void avanzar(double distancia);
    public void girar(double grados);
    public void dirigirse(double x, double y);
    public void sensar();
    
}
