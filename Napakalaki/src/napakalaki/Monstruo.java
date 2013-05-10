package napakalaki;

public class Monstruo implements Carta {
    private String nombre;
    private int nivel;
    private MalRollo malRollo;
    private BuenRollo buenRollo;
    private int nivelContraSectarios; 

    public Monstruo(String nombre, int nivel, MalRollo malRollo, BuenRollo buenRollo) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.malRollo = malRollo;
        this.buenRollo = buenRollo;
    }
    
    @Override
    public String toString(){
        return nombre + ", nivel: " + nivel + malRollo.toString() + "\n" + buenRollo.toString();
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

    @Override
    public String getNombre() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getValorBasico() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getValorEspecial() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
