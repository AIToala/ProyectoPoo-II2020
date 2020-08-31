/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datacarga;

import java.util.ArrayList;
import data.Reporte;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author ai_to
 */
public class ReporteData {
    public static String FILE_REPORTES = DataHelper.ARCHIVOS+"/reportes.dat";

    
    public static ArrayList<Reporte> leerReportes() 
            throws IOException, ClassNotFoundException{
        try(ObjectInputStream input = new  ObjectInputStream(
                                            new FileInputStream(FILE_REPORTES))){
            return (ArrayList<Reporte>)input.readObject();
        }  catch(FileNotFoundException ex){
            escribirReportes(new ArrayList<>());
            return new ArrayList<>();
        }
    }
    
    public static void escribirReportes(ArrayList<Reporte> reportes) 
            throws IOException{
        Path p = Paths.get(FILE_REPORTES);
        try(ObjectOutputStream out = new ObjectOutputStream(
                                            new FileOutputStream(FILE_REPORTES))){
            out.writeObject(reportes);
        }  
    }
}
