/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package napakalaki;

import java.util.ArrayList;

/**
 *
 * @author hhrr
 */
public class Napakalaki {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
    private static final Napakalaki instance = new Napakalaki();
    
    // Constructor privado
    private Napakalaki() { }

    public static Napakalaki getInstance() {
        return instance;
    }
    
    private void crear() {
        
    }
    
    public void comenzarJuego(String[] nombreJugadores) {
        
    }
    
    private void inicializarJuego() {
        
    }
    
    private void inicializarJugadores(String[] nombreJugadores){
        
    }
    
    private void repartirCartas() {
        
    }
    
    public int siguienteTurno() {
        // queda implementar
        return 0;
    }
    
    private Jugador primerJugador() {
        return null;
    }
    
    private Monstruo siguienteMonstruo() {
        return null;        
    }
    
    private Tesoro siguienteTesoro() {
        return null;        
    }
    
    private Jugador siguienteJugador() {
        return null;     
    }
    
    public ResultadoCombate desarrollarCombate() {
        return null;
        
    }
    
    public boolean comprarNivelesJugador(ArrayList<Tesoro> listaTesoros) {
        return false;
    }
    
    public boolean descartarTesoros(ArrayList<Tesoro> tesorosVisibles, 
            ArrayList<Tesoro> tesorosOcultos) {
        return false;       
    }

    
    
    
    
    
    

}
