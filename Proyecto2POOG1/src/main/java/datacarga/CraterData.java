/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datacarga;

import data.Crater;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
/**
 *
 * @author ai_to
 */
public class CraterData {
    public static String FILE_CRATERES = DataHelper.ARCHIVOS+"/crateres_info.txt";
    
    public static ArrayList<Crater> leerCrateresData() 
            throws IOException{
        try(BufferedReader br = new BufferedReader(
                                            new FileReader(FILE_CRATERES))){
            String line;
            ArrayList<Crater> crateres = new ArrayList<>();
            while((line = br.readLine())!= null){
                String  id = line.split(",")[0];
                String nombre = line.split(",")[1];
                double latitud = Double.parseDouble(line.split(",")[2]);
                double longitud = Double.parseDouble(line.split(",")[3]);
                double radio = Double.parseDouble(line.split(",")[4]);
                Crater c = new Crater(id, nombre, latitud, longitud, radio);
                crateres.add(c);
            }
            return crateres;
        }  catch(FileNotFoundException ex){
            System.out.println("h");
            return new ArrayList<>();
        }
    }
}
