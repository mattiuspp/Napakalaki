package napakalaki;

public class Monstruo {
    private String nombre;
    private int nivel;
    private MalRollo malRollo;
    private BuenRollo buenRollo;

    public Monstruo(String nombre, int nivel, MalRollo malRollo, BuenRollo buenRollo) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.malRollo = malRollo;
        this.buenRollo = buenRollo;
    }
    
    public String obtenerNombre() {
        return nombre;
    }
    
    public int obtenerNivel() {
        return nivel;
    }

    public BuenRollo cualEsTuBuenRollo() {
        // queda implementar
        return null;
    }
    
    public MalRollo cualEsTuMalRollo() {
        // queda implementar
        return null;
    }    
}
