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

    private Jugador jugadorActivo;
    private ArrayList<Jugador> Jugadores = new ArrayList();
    
    private ArrayList<Monstruo> descarteMonstruos = new ArrayList();
    private ArrayList<Monstruo> mazoMonstruos = new ArrayList();
    private Monstruo monstruoActivo;
    
    private ArrayList<Tesoro> descarteTesoros  = new ArrayList();
    private ArrayList<Tesoro> mazoTesoros = new ArrayList();
    
    
    private static final Napakalaki instance = new Napakalaki();
    
    // Meter referencia "discontinua" a ResultadoCombate
    
    // Constructor privado
    private Napakalaki() { }

    public static Napakalaki getInstance() {
        return instance;
    }
    
    public void comenzarJuego(String[] nombreJugadores) {
        
    }
    
    private void inicializarJuego() {
        //mazoTesoro
        mazoTesoros.add (new Tesoro("Sí mi amo!",TipoTesoro.CASCO ,4,7,0) );
        mazoTesoros.add (new Tesoro("Botas de investigación ",TipoTesoro.CALZADO,3,4,600) );
        mazoTesoros.add (new Tesoro("Capucha de Cthulhu ",TipoTesoro.CASCO,3,5,500) );
        mazoTesoros.add (new Tesoro("A prueba de babas",TipoTesoro.ARMADURA,3,5,400) );
        mazoTesoros.add (new Tesoro("Botas de lluvia áácida",TipoTesoro.DOSMANOS ,1,1,800) );
        mazoTesoros.add (new Tesoro("Casco minero",TipoTesoro.CASCO,2,4,400) );
        mazoTesoros.add (new Tesoro ("Zapato deja-amigos",TipoTesoro.CALZADO,0,1,500));
        mazoTesoros.add (new Tesoro ("Fez alópodo" ,TipoTesoro.CASCO,3,5,700));
        mazoTesoros.add (new Tesoro ("Necrotelecom" ,TipoTesoro.CASCO,2,3,300));
        mazoTesoros.add (new Tesoro ("Porra preternatural" ,TipoTesoro.MANO,2,3,200));
        mazoTesoros.add (new Tesoro ("Cuchillo de sushi arcano" ,TipoTesoro.MANO,2,3,300));
        mazoTesoros.add (new Tesoro ("La rebeca metálica" ,TipoTesoro.ARMADURA,2,3,400));
        mazoTesoros.add (new Tesoro ("Linterna a 2 manos" ,TipoTesoro.DOSMANOS,3,6,400));
        mazoTesoros.add (new Tesoro ("Clavo de rail ferroviario" ,TipoTesoro.MANO,3,6,400));
        mazoTesoros.add (new Tesoro ("Shogulador" ,TipoTesoro.DOSMANOS,1,1,600));
        mazoTesoros.add (new Tesoro ("Gaita" ,TipoTesoro.DOSMANOS,4,5,500));
        mazoTesoros.add (new Tesoro ("Necronomicón" ,TipoTesoro.DOSMANOS,5,7,800));
        mazoTesoros.add (new Tesoro ("Camiseta de la UGR" ,TipoTesoro.ARMADURA,1,7,100));
        mazoTesoros.add (new Tesoro ("Necrognomicón",TipoTesoro.MANO,2,4,200));
        mazoTesoros.add (new Tesoro ("Tentáculo de pega",TipoTesoro.CASCO,0,1,200));
        mazoTesoros.add (new Tesoro ("Lanzallamas",TipoTesoro.DOSMANOS,4,8,800));
        mazoTesoros.add (new Tesoro ("Mazo de los antiguos",TipoTesoro.MANO,3,4,200));
        mazoTesoros.add (new Tesoro ("Insecticida",TipoTesoro.MANO,2,3,300));
        mazoTesoros.add (new Tesoro ("Hacha prehistórica",TipoTesoro.MANO,2,5,500));
        mazoTesoros.add (new Tesoro ("El aparato de Pr.Tesla",TipoTesoro.ARMADURA,4,8,900));
        mazoTesoros.add (new Tesoro ("Varita de atizamiento",TipoTesoro.MANO,3,4,400));
        mazoTesoros.add (new Tesoro ("Escopeta de tres cañones",TipoTesoro.DOSMANOS,4,6,700));
        mazoTesoros.add (new Tesoro ("Necrocomicón",TipoTesoro.MANO,1,1,100));
        mazoTesoros.add (new Tesoro ("La fuerza de Mr.T",TipoTesoro.COLLAR,0,0,1000));
        mazoTesoros.add (new Tesoro ("Ametralladora Thomson",TipoTesoro.DOSMANOS,4,8,600));
        mazoTesoros.add (new Tesoro ("Necroplayboycón",TipoTesoro.MANO,3,5,300));
        mazoTesoros.add (new Tesoro ("Grabato mísitico",TipoTesoro.MANO,2,2,300));
        
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
