package constantes;

/**
 * Clase que contiene las Constantes del programa.
 * @author ai_to
 */
public class constantes {
    
    public static String RESOURCE_FOLDER = "src/main/resources/imagenes";
    static public String mapFileName = RESOURCE_FOLDER + "/map.jpg";
    static public String robotFileName = RESOURCE_FOLDER + "/robot.png";
    static public int FPS = 30;                               //frames/seg
    static public double velocidadTraslacional = 100;             //pixeles/seg
    static public double velocidadRotacional = 0.7;             //rad/seg
    static public double mapAncho = 600*1440/810;
    static public double mapAlto = 600;
    
    static public int dt = 1000/FPS;     //milisegundos
    static public double dx = velocidadTraslacional/FPS;          //pixeles
    static public double dTheta = velocidadRotacional/FPS;         //rad
}