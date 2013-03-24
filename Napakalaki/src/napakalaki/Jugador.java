/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package napakalaki;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author antonio
 */
public class Jugador {
    private String nombre;
    private int nivel = 1;
    private static int NIVEL_MINIMO = 1;
    private static int NIVEL_MAXIMO = 10;
    private static int TESOROS_OCULTOS_MAXIMO = 4;
    
    // Referencias a otras clases
    private MalRollo malRolloPendiente;
    private ArrayList<Tesoro> tesorosOcultos = new ArrayList();
    private ArrayList<Tesoro> tesorosVisibles = new ArrayList();
    
    // Meter la referencia "discontinua" a ResultadoCombate
    
    // Todavia no hay que implementar el constructor de jugador!
    // OK!
    
    public int obtenerNivel(){
        return nivel;
    }
    
    public void modificarNivel(int nuevoNivel){
        
    }
    
    public int obtenerNivelCombate(){
        // falta por implementar
        return 0;
    }
    
    public void robarTesoro(Tesoro unTesoro){
        
    }
    
    public boolean descartarTesoros(ArrayList<Tesoro> tesorosVisDes, 
            ArrayList<Tesoro> tesorosOcuDes){
        return true;
    }
    
    private boolean cumploMalRollo(ArrayList<Tesoro> tesVisibles, 
            ArrayList<Tesoro> tesOcultos){
        return true;
    }
    
    public void equiparTesoros(ArrayList<Tesoro> listaTesoro){
        
    }
    
    private boolean puedoEquipar(Tesoro unTesoro){
        return true;
    }
    
    public int puedoPasar(){
        //falta por implementar
        return 0;
    }
    
    public boolean tienesTesoros(){
        //falta por implementar
        return true;
    }
    
    public boolean comprarNiveles(ArrayList<Tesoro> tesoros){
        //falta por implementar
        return true;
    }
    
    public void incDecNivel(int incDec){
        
    }
    
    public ResultadoCombate combatir(Monstruo monstruoEnJuego){
        //falta por implementar
        return null;
    }
    
    public ArrayList<Tesoro> dameTodosTusTesoros(){
        //falta por implementar
        return null;
    }
    
    public void incluirMalRollo(List malRollo){
        
    }
    
    public Tesoro devuelveElCollar(){
        //falta por implementar
        return null;
    }
    
    public boolean tienesCollar(){
        //falta por implementar
        return true;
    }
    
    public void aplicarBuenRollo(BuenRollo buenRollo){
        
    }
    
    public void muere(){
        
    }
    
    
}
