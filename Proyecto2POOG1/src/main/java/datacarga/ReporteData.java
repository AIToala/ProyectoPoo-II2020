/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datacarga;

import java.util.ArrayList;
import data.Reporte;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;

/**
 * Clase que maneja archivos con informacion importantes para la aplicacion
 * @author ai_to
 */
public class ReporteData {
    public static String FILE_REPORTES = DataHelper.ARCHIVOS+"/reportes.txt";

    /**
     * Metodo estatico que obtiene reportes dentro de un archivo y retorna lista de reportes.
     * @return lista de reportes
     * @throws IOException 
     */
    public static ArrayList<Reporte> leerReportes() 
            throws IOException{
        try(BufferedReader br = new BufferedReader(
                                            new FileReader(FILE_REPORTES))){
            String line;
            ArrayList<Reporte> reportes = new ArrayList<>();
            while((line = br.readLine())!= null){
                if(line.equals("")){continue;}
                String[]  dateStr = line.split(";")[0].split(" ");
                String[] date = dateStr[0].split("-");
                String[] time = dateStr[1].split(":");
                //5-9-2020 18:15
                LocalDateTime d = LocalDateTime.of(Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0]),
                                                        Integer.parseInt(time[0]), Integer.parseInt(time[1]));
                String minerales = line.split(";")[1];
                ArrayList<String> mineralesT = new ArrayList<>();
                if(minerales.contains(",")){
                    String[] contenido = minerales.split(",");
                    for(String s : contenido){
                        mineralesT.add(s);
                    }
                }else{
                    mineralesT.add(minerales);
                }
                String nombre = line.split(";")[2];
                Reporte r = new Reporte(d, mineralesT, nombre);
                reportes.add(r);
            }
            return reportes;
        }  catch(FileNotFoundException ex){
            return new ArrayList<>();
        }
    }
    /**
     * Metodo que escribe reportes a un archivo, como parametro necesita de un reporte.
     * @param reporte Objeto tipo Reporte
     * @throws IOException 
     */
    public static void escribirReporte(Reporte reporte) 
            throws IOException{
        try(BufferedWriter br = new BufferedWriter(
                            new FileWriter(FILE_REPORTES, true))){
            String dia = Integer.toString(reporte.getFechaExploracion().getDayOfMonth());
            String mes = Integer.toString(reporte.getFechaExploracion().getMonthValue());
            String year = Integer.toString(reporte.getFechaExploracion().getYear());
            String hour = Integer.toString(reporte.getFechaExploracion().getHour());
            String minute = Integer.toString(reporte.getFechaExploracion().getMinute());
            
            String fecha = dia+"-"+mes+"-"+year+" "+hour+":"+minute;
            br.write(fecha+";"+reporte.getMineralesStr()+";"+reporte.getNombreCrater());
            br.newLine();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
}
