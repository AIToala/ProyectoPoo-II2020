/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datacarga;

import data.Crater;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import javafx.scene.control.Alert;
/**
 *
 * @author ai_to
 */
public class CraterData {
    public static String FILE_CRATERES = DataHelper.ARCHIVOS+"/crateres.txt";
    
    public static ArrayList<Crater> leerCrateresData() 
            throws IOException, ClassNotFoundException{
        try(ObjectInputStream input = new ObjectInputStream(
                                            new FileInputStream(FILE_CRATERES))){
            return (ArrayList<Crater>)input.readObject();
        }  catch(FileNotFoundException ex){
            return new ArrayList<>();
        }
    }
}
