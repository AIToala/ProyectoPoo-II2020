package vista;

import approver.App;
import data.Crater;
import data.Reporte;
import data.Rover;
import datacarga.ReporteData;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Clase VistaExplorar nos genera la vista grafica del menu Explorar de la aplicacion.
 * @author Grupo2POO
 */
public class VistaExplorar extends Vista{
    private VBox root = new VBox();
    private ImageView mapView;
    private Rover robot;
    private Pane espacioExplorar;
    private Label craterInfo;
    /**
     * Constructor para generar la vista grafica del menu Explorar.
     */
    public VistaExplorar(){
        try(FileInputStream f = new FileInputStream(constantes.constantes.mapFileName)){
            mapView = new ImageView(new Image(f));
            mapView.setFitWidth(constantes.constantes.mapAncho);
            mapView.setFitHeight(constantes.constantes.mapAlto);
        }catch (IOException e){
            System.out.println("¡Algo salió mal al cargar el mapa!");
            App.stageP.show();
        }
        
        robot = new Rover();
        espacioExplorar = new Pane(mapView, robot.roverView);
        espacioExplorar.setMaxSize(constantes.constantes.mapAncho, constantes.constantes.mapAlto);
        ubicarCrateres();
        robot.roverView.toFront();
        Label ingreseComandosLabel = new Label("Ingrese comandos:");
        TextField comando = new TextField();
        Label comandosListLabel = new Label("Comandos ingresados:");
        TextArea comandosList = new TextArea("");
        comandosList.setEditable(false);
        
        
        VBox comandosPanel = new VBox(ingreseComandosLabel, comando, comandosListLabel, comandosList);
        comandosPanel.setMaxWidth(200);
        
        
        HBox mapaComandos = new HBox(espacioExplorar, comandosPanel);
        mapaComandos.setMaxSize(1480, 600);
        
        root.getChildren().add(mapaComandos);
        
        //Acciones del robot mediante comandos
        comando.setOnKeyReleased((key) -> {
            if(key.getCode()== KeyCode.ENTER){
                String texto = comando.getText();
                Boolean esComando = true;
                Boolean escapa = false;
                String separador ="\n";
                try{
                    if("sensar".equals(texto)){
                        boolean sensado = false;
                        for(int i=2; i<espacioExplorar.getChildren().size(); i++){
                            if(espacioExplorar.getChildren().get(i) instanceof Circle){
                                Circle c = (Circle) espacioExplorar.getChildren().get(i);
                                if(robot.roverView.intersects(c.getBoundsInParent())){
                                    for(Crater crat : App.crateres){
                                        if(c.getLayoutX()==crat.getLatitud() && 
                                                c.getLayoutY()==crat.getLongitud() &&
                                                    c.getRadius()==crat.getRadio()){
                                            try{
                                                Reporte r = new Reporte(LocalDateTime.now(), crat.getMinerales(), crat.getNombre());
                                                ReporteData.escribirReporte(r);
                                                sensado = true;
                                                crat.setSensado(true);
                                                crat.setFechaExploracion(LocalDateTime.now());
                                                App.reportes.add(r);
                                                c.setFill(Color.GREENYELLOW);
                                                c.setOpacity(50);
                                            }catch(IOException ex){
                                                System.out.println("ERROR EN ESCRITURA REPORTE...");
                                            }
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                        if(!sensado){comandosList.setText(texto+"-No hay elemento para sensar.\n"+comandosList.getText());}
                        
                    }else if("girar:".equals(texto.substring(0, 6))){
                        double g = Double.parseDouble(texto.substring(6));
                        robot.girar(g);
                        
                    }else if(texto.substring(0, 8).equals("avanzar:")){
                        double d = Double.parseDouble(texto.substring(8));
                        double x = robot.getX() + d*Math.cos(robot.getOrientacion());
                        double y = robot.getY() + d*Math.sin(robot.getOrientacion());
                        escapa = 0 > x || x > constantes.constantes.mapAncho || 0 > y || y > constantes.constantes.mapAlto;
                        if(!escapa) robot.avanzar(d);
                    }else if("dirigirse:".equals(texto.substring(0, 10))){
                        String coordinates = texto.substring(10);
                        double x = Double.parseDouble(coordinates.split(",")[0]);
                        double y = Double.parseDouble(coordinates.split(",")[1]);
                        escapa = 0 > x || x > constantes.constantes.mapAncho || 0 > y || y > constantes.constantes.mapAlto;
                        if(!escapa) robot.dirigirse(x, y);
                    }else esComando = false;
                    
                    if (escapa) separador = " (abortado)\n";
                    
                    if (esComando){
                        comandosList.setText(texto + separador + comandosList.getText());
                    }else throw new IndexOutOfBoundsException();
                    
                    comando.clear();
                }catch(NumberFormatException|IndexOutOfBoundsException e){
                    e.printStackTrace();
                    if (e instanceof NumberFormatException){
                        comandosList.setText("¡Entrada no numérica!\n" + comandosList.getText());
                        comando.clear();
                    }
                    else {
                        comandosList.setText(texto + " (El comando no existe)\n" + comandosList.getText());
                        comando.clear();
                    }
                }
            }
        });
        craterInfo = new Label("");
        craterInfo.setMaxSize(constantes.constantes.mapAncho, 20);
        craterInfo.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        root.getChildren().add(craterInfo);
    }
    /**
     * Metodo void que crea los crateres y les da funcionalidad.
     */
    public void ubicarCrateres(){
        for(Crater c : App.crateres){
            Circle circulito = new Circle();
            circulito.setLayoutX(c.getLatitud());
            circulito.setLayoutY(c.getLongitud());
            circulito.setRadius(c.getRadio());
            circulito.setFill(Color.TRANSPARENT);
            circulito.setStroke(Color.RED);
            circulito.setOpacity(50);
            circulito.toFront();
            if(c.isSensado()){
                circulito.setFill(Color.CYAN);
            }
            espacioExplorar.getChildren().add(circulito);
            
            circulito.setOnMouseClicked(
                (event)->{
                    craterInfo.setText(c.getNombre() + "-" + c.getMineralesStr());
                }
            );
        }
    }
    /**
     * Metodo que retorna el nodo raiz de la escena VistaExplorar.
     * @return 
     */
    public VBox getRoot(){
        return root;
    }
}
