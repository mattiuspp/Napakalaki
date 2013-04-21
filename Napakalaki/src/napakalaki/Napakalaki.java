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
        inicializarJuego();
        
        if(nombreJugadores.length < 2 || nombreJugadores.length > 5)
            throw new Error("Numero de jugadores incorrecto");
        
        inicializarJugadores(nombreJugadores); 
        repartirCartas();
        siguienteTurno();        
    }
    
    // Hay que cambiar los niveles del buen rollo (soy capullo y puse 0) <- Soy Adrián
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
        tipoVisiblesPerdidos.add(TipoTesoro.CALZADO);
        
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
        for (String jugador: nombreJugadores){
            Jugadores.add(new Jugador(jugador));
        }
    }
    
    private void repartirCartas() {
        for (Jugador jugador: Jugadores){
            int dado = (int) Math.random()*6+1;
            int numTesoros;
            switch (dado){
                    case 1:{
                        numTesoros = 1;
                        break;
                    }
                    case 6:{
                        numTesoros = 6;
                        break;
                    }
                    default:{
                        numTesoros = 2;
                        break;
                    }   
            }
            for (int i = 1; i <= numTesoros; i++){
                jugador.robarTesoro(siguienteTesoro());
            }  
        }
    }
    
    private Jugador primerJugador() {
        return Jugadores.get( (int)Math.random() * Jugadores.size() );
    }
    
    private Monstruo siguienteMonstruo() {
        if(monstruoActivo != null){
            mazoMonstruos.add(monstruoActivo);
            mazoMonstruos.remove(monstruoActivo);
        }
        monstruoActivo = mazoMonstruos.get((int)Math.random()*mazoMonstruos.size());
        return monstruoActivo;
        
    }
    
    private Tesoro siguienteTesoro() {
        Tesoro tesoro = mazoTesoros.get(mazoTesoros.size()-1);
        mazoTesoros.remove(mazoTesoros.size()-1);
        descarteTesoros.add(tesoro);
        return tesoro;
    }
    
    private Jugador siguienteJugador() {
        return Jugadores.get((Jugadores.indexOf(jugadorActivo) + 1) % Jugadores.size());     
    }
    
    public ResultadoCombate desarrollarCombate() {
        ResultadoCombate resultado = jugadorActivo.combatir(monstruoActivo);
        
        if (resultado == ResultadoCombate.VENCE)
        {
            BuenRollo buenRollo = monstruoActivo.cualEsTuBuenRollo();
            int tesorosGanados = buenRollo.obtenerTesorosGanados();
            
            for (int i = 1; i <= tesorosGanados; i++)
            {
                jugadorActivo.robarTesoro(siguienteTesoro());
            }
            
            if (jugadorActivo.tienesCollar())
            {
                descarteTesoros.add(jugadorActivo.devuelveElCollar());
            }
        }
        
        if (resultado == ResultadoCombate.PIERDEYMUERE)
        {
            descarteTesoros.addAll(jugadorActivo.dameTodosTusTesoros());
        }
        
        return resultado;
        
    }
    
    public boolean comprarNivelesJugador(ArrayList<Tesoro> listaTesoros) {
        boolean puedo; 
        puedo = jugadorActivo.comprarNiveles(listaTesoros);
        if (puedo)
            descarteTesoros.addAll(listaTesoros);
        
        return puedo;
    }
    
    public int siguienteTurno() {
        if(jugadorActivo == null){
            jugadorActivo = primerJugador();
            monstruoActivo = siguienteMonstruo();
        }
                
        int fin = jugadorActivo.puedoPasar();
        
        if (fin == 0){
            jugadorActivo = siguienteJugador();
            
            boolean tieneTesoros = jugadorActivo.tienesTesoros();
            
            if(!tieneTesoros){
                int dado = (int) Math.random()*6+1;
                int numTesoros;
                switch (dado){
                        case 1:{
                            numTesoros = 1;
                            break;
                        }
                        case 6:{
                            numTesoros = 6;
                            break;
                        }
                        default:{
                            numTesoros = 2;
                            break;
                        }   
                }
                for (int i = 1; i <= numTesoros; i++){
                    jugadorActivo.robarTesoro(siguienteTesoro());
                }
                
                monstruoActivo = siguienteMonstruo();
            } 
            
        }
        
        return fin;        
    }
    
    public boolean descartarTesoros(ArrayList<Tesoro> tesorosVisibles, 
            ArrayList<Tesoro> tesorosOcultos) 
    {
        boolean cumpleMR;
        
        if(tesorosOcultos.isEmpty() && tesorosVisibles.isEmpty()){
            return true;
        }
        
        cumpleMR = jugadorActivo.descartarTesoros(tesorosVisibles, tesorosOcultos);
        
        descarteTesoros.addAll(tesorosVisibles);
        descarteTesoros.addAll(tesorosOcultos);
        
        return cumpleMR;
    }
    
    //Testin!
    public Jugador obtenerJugadorActivo() {
        return jugadorActivo;
    }
    public Monstruo obtenerMonstruoActivo() {
        return monstruoActivo;
    }
    
    
}
