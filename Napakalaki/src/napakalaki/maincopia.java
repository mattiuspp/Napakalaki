/*
 * Autores: Antonio Álvarez Caballero y Adrián Ranea Robles
 */

/*
 * Meter shuffle - HECHO!!!
 * Modificar interfaz (Mal Rollo) - obtenerTexto hecho, sólo falta crear la interfaz
 * Completar actualizarJugador
 * pagina 5 informacion vista
 * cambiar dado por getvista
 */

package napakalaki;

import java.util.ArrayList;
import java.util.Scanner;


public class maincopia {
    public static class LectorCartas{
        public Scanner sc = new Scanner(System.in);

        public ArrayList<Tesoro> leeCartas(ArrayList<Tesoro> listaTesoros){
            ArrayList<Tesoro> cartasLeidas = new ArrayList();
            int indiceCarta;

            indiceCarta = sc.nextInt();
            while(indiceCarta != -1){
                if(indiceCarta<listaTesoros.size())
                    cartasLeidas.add(listaTesoros.get(indiceCarta));
                else
                    System.out.println("» Indice invalido.");
                
                indiceCarta = sc.nextInt();
            }
            return cartasLeidas;
        }    
}
        
    public static void main(String args[]) {
	LectorCartas lectorCartas = new LectorCartas();
        Napakalaki juego = Napakalaki.getInstance();
        ArrayList<Tesoro> visDes;
        ArrayList<Tesoro> ocuDes;
        
        String jugadores[] = {"Antonio", "Adrián", "Eva"};
        juego.comenzarJuego(jugadores);
        
        while(true)
        {
            boolean cumplioMalRollo;
            
            // 1 - Combate
            System.out.println("\n// ------- COMBATE ------- //" + "\n" + 
                                juego.obtenerJugadorActivo().toString() + "\n" + 
                                juego.obtenerMonstruoActivo().toString());   
            
            ResultadoCombate resultado = juego.desarrollarCombate();
            
            System.out.println("\n» Resultado del combate: " + resultado);
            if (resultado == ResultadoCombate.VENCEYFIN){
                System.out.println("» FIN DEL JUEGO"); break;
            }
            
            
            // 2 - Resolver malrollo (1er intento) 
            System.out.println("\n// ------- CUMPLE MAL ROLLO(COMBATE) ------- //"
                                +  "\n" + juego.obtenerJugadorActivo().toString());
            System.out.println("Indices de las cartas ocultas a descartar (-1 aborta)");
            ocuDes =  lectorCartas.leeCartas(juego.obtenerJugadorActivo().obtenerTesorosOcultos());
            System.out.println("Indices de las cartas equipadas(visibles) a descartar (-1 aborta)");
            visDes = lectorCartas.leeCartas(juego.obtenerJugadorActivo().obtenerTesorosVisibles());

            cumplioMalRollo = juego.descartarTesoros(visDes, ocuDes);
            
            
            if(!cumplioMalRollo)
                System.out.println("\n» No puedes equipar ni vender si tienes un malrollo pendiente: "
                                    + "este turno perdiste la opcion ");    
            else
            {
                // 3 - Equipar
                System.out.println("\n// ------- EQUIPO ------- //" + "\n" +
                                   juego.obtenerJugadorActivo().toString());
                
                System.out.println("Indices de las cartas ocultas a equipar (-1 abortar)");
                ArrayList<Tesoro> cartasaEquipar = lectorCartas.leeCartas(juego.obtenerJugadorActivo().obtenerTesorosOcultos());
                
                juego.obtenerJugadorActivo().equiparTesoros(cartasaEquipar);

                
                // 4 - Comprar de niveles
                System.out.println("\n// ------- VENTA ------- //" + "\n" +
                                   juego.obtenerJugadorActivo().toString());
                
                System.out.println("Indices de las cartas ocultas a vender (-1 abortar)");
                ArrayList<Tesoro> cartasaVender = lectorCartas.leeCartas(juego.obtenerJugadorActivo().obtenerTesorosOcultos());
                System.out.println("Indices de las cartas equipadas(visibles) a vender (-1 abortar)");
                cartasaVender.addAll(lectorCartas.leeCartas(juego.obtenerJugadorActivo().obtenerTesorosVisibles()));
                
                juego.comprarNivelesJugador(cartasaVender);
                
                
                System.out.println("\n// ------- TRAS VENTA ------- //" + "\n" +
                                    juego.obtenerJugadorActivo().toString());
            }
                

            // 5 - Finalizacion del turno
            int fin = juego.siguienteTurno();
            while( fin != 0)
            {   
                if(fin > 0)
                {
                    System.out.println("\n// ------- EXCESO DE CARTAS ------- //" + "\n"
                                        + juego.obtenerJugadorActivo().toString());
                    
                    System.out.println("Indices de las cartas ocultas a descartar (-1 abortar)");
                    ocuDes = lectorCartas.leeCartas(juego.obtenerJugadorActivo().obtenerTesorosOcultos());
                    
                    juego.descartarTesoros(new ArrayList(), ocuDes); 
                } 
                
                else if(fin < 0)
                {
                    System.out.println("\n// ------- CUMPLE MALROLLO(FIN DEL TURNO) ------- //" + 
                                        "\n" + juego.obtenerJugadorActivo().toString());
                    
                    System.out.println("Indices de las cartas ocultas a descartar (-1 abortar)");
                    ocuDes = lectorCartas.leeCartas(juego.obtenerJugadorActivo().obtenerTesorosOcultos());
                    System.out.println("Indices de las cartas equipadas(visibles) a descartar (-1 abortar)");
                    visDes = lectorCartas.leeCartas(juego.obtenerJugadorActivo().obtenerTesorosVisibles());
                    
                    juego.descartarTesoros(visDes, ocuDes);
                }

                fin = juego.siguienteTurno();                
            }
            
            System.out.println("\n\n----------------------------------------------------------------\n\n");
        }//fin while
        
    }//fin maincopia
}
