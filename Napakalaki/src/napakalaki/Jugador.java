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
    
    public Jugador(String nombre){
        this.nombre = nombre;
    }
    
    public int obtenerNivel(){
        return nivel;
    }
    
    public void modificarNivel(int nuevoNivel){
        
    }
    
    public int obtenerNivelCombate(){
        
    }
    
    public void robarTesoro(Tesoro unTesoro){
        
    }
    
    public boolean descartarTesoros(ArrayList<Tesoro> tesorosVisDes, ArrayList<Tesoro> tesorosOcuDes){
        return true;
    }
    
    private boolean cumploMalRollo(ArrayList<Tesoro> tesVisibles, ArrayList<Tesoro> tesOcultos){
        return true;
    }
    
    public void equiparTesoros(ArrayList<Tesoro> listaTesoro){
        
    }
    
    private boolean puedoEquipar(Tesoro unTesoro){
        return true;
    }
    
    public int puedoPasar(){
        
    }
    
    public boolean tienesTesoros(){
        
    }
    
    public boolean comprarNiveles(ArrayList<Tesoro> tesoros){
        
    }
    
    public void incDecNivel(int incDec){
        
    }
    
    public ResultadoCombate combatir(Monstruo monstruoEnJuego){
        
    }
    
    public ArrayList<Tesoro> dameTodosTusTesoros(){
        
    }
    
    public void incluirMalRollo(List malRollo){
        
    }
    
    public Tesoro devuelveElCollar(){
        
    }
    
    public boolean tienesCollar(){
        
    }
    
    public void aplicarBuenRollo(BuenRollo buenRollo){
        
    }
    
    public void muere(){
        
    }
    
    
}
