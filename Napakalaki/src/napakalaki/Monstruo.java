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
    
    public Monstruo(String nombre, int nivel, MalRollo malRollo, BuenRollo buenRollo, int nivelContraSectarios) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.malRollo = malRollo;
        this.buenRollo = buenRollo;
        this.nivelContraSectarios = nivelContraSectarios;
    }
    
    @Override
    public String toString(){
        return nombre + ", nivel: " + nivel + malRollo.toString() + "\n" + buenRollo.toString();
    }
    
    @Override
    public String getNombre() {
        return nombre;
    }
    
    @Override
    public int getValorBasico() {
        return nivel;
    }

    public BuenRollo cualEsTuBuenRollo() {
        return buenRollo;
    }
    
    public MalRollo cualEsTuMalRollo() {
        return malRollo;
    }    

//    @Override
//    public String getNombre() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

//    @Override
//    public int getValorBasico() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    public int getValorEspecial() {
        return getValorBasico() + nivelContraSectarios;
    }
}
