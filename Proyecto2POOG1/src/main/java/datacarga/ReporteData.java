/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datacarga;

import data.Crater;
import java.util.ArrayList;
import data.Reporte;
import static datacarga.CraterData.FILE_CRATERES;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author ai_to
 */
public class ReporteData {
    public static String FILE_REPORTES = DataHelper.ARCHIVOS+"/reportes.txt";

    
    public static ArrayList<Reporte> leerReportes() 
            throws IOException{
        try(BufferedReader br = new BufferedReader(
                                            new FileReader(FILE_REPORTES))){
            String line;
            ArrayList<Reporte> reportes = new ArrayList<>();
            while((line = br.readLine())!= null){
                String  dateStr = line.split("-")[0];
                DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                
                LocalDateTime date = LocalDateTime.parse(dateStr, format);
                String minerales = line.split("-")[1];
                ArrayList<String> mineralesT = new ArrayList<>();
                if(minerales.contains(",")){
                    String[] contenido = minerales.split(",");
                    for(String s : contenido){
                        mineralesT.add(s);
                    }
                }else{
                    mineralesT.add(minerales);
                }
                String nombre = line.split("-")[2];
                Reporte r = new Reporte(date, mineralesT, nombre);
                reportes.add(r);
            }
            return reportes;
        }  catch(FileNotFoundException ex){
            return new ArrayList<>();
        }
    }
    
    public static void escribirReporte(Reporte reporte) 
            throws IOException{
        try(BufferedWriter br = new BufferedWriter(
                            new FileWriter(FILE_REPORTES, true))){
            br.write(reporte.getFechaExploracion()+"-"+reporte.getMineralesStr()+"-"+reporte.getNombreCrater());
            br.newLine();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
}
