/*
 * Equipo: Antonio Álvarez Caballero y Adrián Ranea Robles
 */
package napakalaki;

import java.util.ArrayList;

public class Napakalaki {
    private Jugador jugadorActivo;
    private ArrayList<Jugador> Jugadores = new ArrayList();
    
    private Monstruo monstruoActivo;
    private ArrayList<Monstruo> descarteMonstruos = new ArrayList();
    private ArrayList<Monstruo> mazoMonstruos = new ArrayList();
    private ArrayList<Tesoro> descarteTesoros  = new ArrayList();
    private ArrayList<Tesoro> mazoTesoros = new ArrayList();
    
    // Establecemos la clase como singleton
    private static final Napakalaki instance = new Napakalaki();
    
    // Constructor privado
    private Napakalaki() { }

    public static Napakalaki getInstance() {
        return instance;
    }
    
    public void comenzarJuego(String[] nombreJugadores) {
        
    }
    
    private void inicializarJuego() {
        // Inicializamos las cartas de tesoro
        mazoTesoros.add (new Tesoro ("Sí mi amo!",TipoTesoro.CASCO ,4,7,0) );
        mazoTesoros.add (new Tesoro ("Botas de investigación ",TipoTesoro.CALZADO,3,4,600) );
        mazoTesoros.add (new Tesoro ("Capucha de Cthulhu ",TipoTesoro.CASCO,3,5,500) );
        mazoTesoros.add (new Tesoro ("A prueba de babas",TipoTesoro.ARMADURA,3,5,400) );
        mazoTesoros.add (new Tesoro ("Botas de lluvia áácida",TipoTesoro.DOSMANOS ,1,1,800) );
        mazoTesoros.add (new Tesoro ("Casco minero",TipoTesoro.CASCO,2,4,400) );
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
                
        // Inicializamos las cartas de monstruo
        ArrayList<TipoTesoro> tipoOcultosPerdidos = new ArrayList();
        ArrayList<TipoTesoro> tipoVisiblesPerdidos = new ArrayList();
    
        tipoOcultosPerdidos.clear();
        tipoVisiblesPerdidos.clear();
        tipoVisiblesPerdidos.add(TipoTesoro.ARMADURA);
        tipoOcultosPerdidos.add(TipoTesoro.ARMADURA);
        
        mazoMonstruos.add(new Monstruo(
                "3 Byakhees de bonanza",8,
                new MalRollo("Pierdes tu armadura visible y otra oculta.",
                    0,1,1,false,
                    tipoOcultosPerdidos,
                    tipoVisiblesPerdidos
                ),
                new BuenRollo(3,1) )
        );
        
        tipoOcultosPerdidos.clear();
        tipoVisiblesPerdidos.clear();
        tipoVisiblesPerdidos.add(TipoTesoro.CASCO);
        
        mazoMonstruos.add(new Monstruo(
                "Chibithulhu",7,
                new MalRollo("Embobados con el lindo primigenio te descartas de tu casco visible.",
                    0,0,1,false,
                    tipoOcultosPerdidos,
                    tipoVisiblesPerdidos
                ),
                new BuenRollo(1,1) )
        );
        
        tipoOcultosPerdidos.clear();
        tipoVisiblesPerdidos.clear();
        tipoVisiblesPerdidos.add(TipoTesoro.CASCO);
        
        mazoMonstruos.add(new Monstruo(
                "El sopor de Dunwich",2,
                new MalRollo("El primordial bostezo contagioso. Pierdes el calzado visible.",
                    0,0,1,false,
                    tipoOcultosPerdidos,
                    tipoVisiblesPerdidos
                ),
                new BuenRollo(1,1) )
        );
        
        tipoOcultosPerdidos.clear();
        tipoVisiblesPerdidos.clear();
        tipoVisiblesPerdidos.add(TipoTesoro.MANO);
        tipoOcultosPerdidos.add(TipoTesoro.MANO);
        
        mazoMonstruos.add(new Monstruo(
                "Ángeles de la noche ibicenca",14,
                new MalRollo("Te atrapan para llevarte de fiesta y te dejan caer en mitad del vuelo. Descarta 1 mano visible y 1 mano oculta.",
                    0,1,1,false,
                    tipoOcultosPerdidos,
                    tipoVisiblesPerdidos
                ),
                new BuenRollo(4,1) )
       );    
        
        tipoOcultosPerdidos.clear();
        tipoVisiblesPerdidos.clear();
        
        mazoMonstruos.add(new Monstruo(
            "El gorrón en el umbral",10, 
            new MalRollo("Pierdes todos tus tesoros visibles.",
                        0,0,6,false, 
                        tipoOcultosPerdidos,
                        tipoVisiblesPerdidos
                    ),
            new BuenRollo(3,1) ) 
        );
        
        tipoOcultosPerdidos.clear();
        tipoVisiblesPerdidos.clear();
        tipoVisiblesPerdidos.add(TipoTesoro.ARMADURA);
        
        mazoMonstruos.add(new Monstruo(
                "H.P. Munchcraft",6,
                new MalRollo("Pierdes la armadura visible.",
                    0,0,1,false,
                    tipoOcultosPerdidos,
                    tipoVisiblesPerdidos
                ),
                new BuenRollo(2,1) )
        );
        
        tipoOcultosPerdidos.clear();
        tipoVisiblesPerdidos.clear();
        tipoVisiblesPerdidos.add(TipoTesoro.ARMADURA);
        
        mazoMonstruos.add(new Monstruo(
                "Bichgooth",2,
                new MalRollo("Sientes bichos bajo la ropa. Descarta la armadura visible.",
                    0,0,1,false,
                    tipoOcultosPerdidos,
                    tipoVisiblesPerdidos
                ),
                new BuenRollo(1,1) )
        );
        
        tipoOcultosPerdidos.clear();
        tipoVisiblesPerdidos.clear();
        
        mazoMonstruos.add(new Monstruo(
                "El rey de rosa",13,
                new MalRollo("Pierdes 5 niveles y 3 tesoros visibles.",
                    5,0,3,false,
                    tipoOcultosPerdidos,
                    tipoVisiblesPerdidos
                ),
                new BuenRollo(4,2) )
        );
        
        tipoOcultosPerdidos.clear();
        tipoVisiblesPerdidos.clear();
        
        mazoMonstruos.add(new Monstruo(
                "La que redacta en las tinieblas",2,
                new MalRollo("Toses los pulmones y pierdes 2 niveles.",
                    2,0,0,false,
                    tipoOcultosPerdidos,
                    tipoVisiblesPerdidos
                ),
                new BuenRollo(1,1) )
        );
        
        tipoOcultosPerdidos.clear();
        tipoVisiblesPerdidos.clear();
        
        mazoMonstruos.add(new Monstruo(
                "Los hondos",8,
                new MalRollo("Estos monstruos resultan bastante superficiales y te aburren mortalmente. Estás muerto.",
                    0,0,0,true,
                    tipoOcultosPerdidos,
                    tipoVisiblesPerdidos
                ),
                new BuenRollo(2,1) )
        );
        
        tipoOcultosPerdidos.clear();
        tipoVisiblesPerdidos.clear();
        
        mazoMonstruos.add(new Monstruo(
                "Semillas Cthulhu",4,
                new MalRollo("Pierdes 2 niveles y 2 tesoros ocultos.",
                    2,2,0,false,
                    tipoOcultosPerdidos,
                    tipoVisiblesPerdidos
                ),
                new BuenRollo(2,1) )
        );
        
        tipoOcultosPerdidos.clear();
        tipoVisiblesPerdidos.clear();
        tipoVisiblesPerdidos.add(TipoTesoro.MANO);
        
        mazoMonstruos.add(new Monstruo(
                "Dameargo",1,
                new MalRollo("Te intentas escaquear. Pierdes una mano visible.",
                    0,0,1,false,
                    tipoOcultosPerdidos,
                    tipoVisiblesPerdidos
                ),
                new BuenRollo(2,1) )
        );
        
        tipoOcultosPerdidos.clear();
        tipoVisiblesPerdidos.clear();
        
        mazoMonstruos.add(new Monstruo(
                "Pollipólipo volante",3,
                new MalRollo("Da mucho asquito. Pierdes 3 niveles.",
                    3,0,0,false,
                    tipoOcultosPerdidos,
                    tipoVisiblesPerdidos
                ),
                new BuenRollo(1,1) )
        );
        
        tipoOcultosPerdidos.clear();
        tipoVisiblesPerdidos.clear();
        
        mazoMonstruos.add(new Monstruo(
                "Yskhtihyssg-Goth",12,
                new MalRollo("No le hace gracia que pronuncien mal su nombre. Estás muerto.",
                    0,0,0,true,
                    tipoOcultosPerdidos,
                    tipoVisiblesPerdidos
                ),
                new BuenRollo(3,1) )
        );
        
        tipoOcultosPerdidos.clear();
        tipoVisiblesPerdidos.clear();
        
        mazoMonstruos.add(new Monstruo(
                "Familia feliz",1,
                new MalRollo("La familia te atrapa. Estás muerto.",
                    0,0,0,true,
                    tipoOcultosPerdidos,
                    tipoVisiblesPerdidos
                ),
                new BuenRollo(4,1) )
        );
        
        tipoOcultosPerdidos.clear();
        tipoVisiblesPerdidos.clear();
        tipoVisiblesPerdidos.add(TipoTesoro.DOSMANOS);
        
        mazoMonstruos.add(new Monstruo(
                "Roboggoth",8,
                new MalRollo("La qiunta directiva primaria te obliga a perder 2 niveles y un tesoro 2 manos visibles.",
                    2,0,1,false,
                    tipoOcultosPerdidos,
                    tipoVisiblesPerdidos
                ),
                new BuenRollo(2,1) )
        );
        
        tipoOcultosPerdidos.clear();
        tipoVisiblesPerdidos.clear();
        tipoVisiblesPerdidos.add(TipoTesoro.CASCO);
        
        mazoMonstruos.add(new Monstruo(
                "El espía",5,
                new MalRollo("Te asusta en la noche. Pierdes un casco visible.",
                    0,0,1,false,
                    tipoOcultosPerdidos,
                    tipoVisiblesPerdidos
                ),
                new BuenRollo(1,1) )
        );
        
        tipoOcultosPerdidos.clear();
        tipoVisiblesPerdidos.clear();
        
        mazoMonstruos.add(new Monstruo(
                "El lenguas",20,
                new MalRollo("Menudo susto te llevas. Pierdes 2 niveles y 5 tesoros visibles.",
                    2,0,5,false,
                    tipoOcultosPerdidos,
                    tipoVisiblesPerdidos
                ),
                new BuenRollo(1,1) )
        );
        
        tipoOcultosPerdidos.clear();
        tipoVisiblesPerdidos.clear();
        tipoVisiblesPerdidos.add(TipoTesoro.MANO);
        tipoVisiblesPerdidos.add(TipoTesoro.DOSMANOS);
        
        mazoMonstruos.add(new Monstruo(
                "Bicéfalo",8,
                new MalRollo("Te faltan manos para tanta cabeza. Pierdes 3 niveles y tus tesoros visibles de las manos.",
                    3,0,6,false,
                    tipoOcultosPerdidos,
                    tipoVisiblesPerdidos
                ),
                new BuenRollo(1,1) )
        );
        
        tipoOcultosPerdidos.clear();
        tipoVisiblesPerdidos.clear();
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
