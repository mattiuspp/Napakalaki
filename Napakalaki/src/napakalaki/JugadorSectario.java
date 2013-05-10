/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package napakalaki;

import java.util.ArrayList;

/**
 *
 * @author antonio
 */
public class JugadorSectario extends Jugador{
    private static int numeroSectarios = 0; //como se el valor que tengo
    private Sectario miCartaSectario;

    public JugadorSectario(Jugador jugador, Sectario carta) {
        super(jugador);
        miCartaSectario = new Sectario(carta.getNombre(), carta.getValorBasico());
        incrementarSectarios();
    }
    
    @Override
    public String toString() {
        return super.toString() + "\nJugador sectario: " 
                + "\n\tBonus basico: " + miCartaSectario.getValorBasico() 
                + ", bonus especial: " + miCartaSectario.getValorEspecial();
    }

    public static int getNumeroSectarios() {
        return numeroSectarios;
    }
    
    private static void incrementarSectarios() {
        numeroSectarios++;
    }
    
    @Override
    public JugadorSectario convertirme(Sectario cartaSectario){
        return this;
    }
    
    @Override
    public int obtenerNivelCombate() {
        return super.obtenerNivelCombate() + miCartaSectario.getValorEspecial();
    }
    
    @Override
    protected int obtenerNivelContrincante(Monstruo monstruo) {
        return monstruo.getValorEspecial();
    }
    
    public int calcularNivelesAComprar(ArrayList<Tesoro> tesoros){
        int piezasOro = 0;
                
        for(Tesoro t: tesoros)
            piezasOro += 2*t.obtenerPiezasOro();
                           
        return piezasOro / 1000;
    }
    
    @Override
    public boolean puedoConvertirme(){
        return false;
    }
    
    
    
    
    
    
}
