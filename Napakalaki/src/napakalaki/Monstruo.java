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
    
    @Override
    public String toString(){
        return nombre + "\nNivel " + nivel + "\nMal rollo: "+ malRollo.toString() + "\n" + buenRollo.toString();
    }
    
    public String obtenerNombre() {
        return nombre;
    }
    
    public int obtenerNivel() {
        return nivel;
    }

    public BuenRollo cualEsTuBuenRollo() {
        return buenRollo;
    }
    
    public MalRollo cualEsTuMalRollo() {
        return malRollo;
    }    
}
