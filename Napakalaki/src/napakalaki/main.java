/* BUG 22-04-14 Adrián
 *  -   Aleatorio Horrible(mirar metodos siguienteMonstruo()
 *  -   CumpleMalRollo de jugador no cambia malRolloPendiente
 *  Antonio
 *  -   Si no se quitan todos los objetos da IndexOutOfBoundsException
 *  -   A todos les asigna el mismo monstruo cada turno... Aleatorio funciona bien pero parece no cambiar hasta que no cambia el turno
 */

package napakalaki;

import java.util.ArrayList;
import java.util.Scanner;



public class main {
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
                    System.out.println("Indice invalido!");
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
        
        String jugadores[] = {"Juan","Eva","Pepe"};
        juego.comenzarJuego(jugadores);
        
        /**************+ Ejemplo de un turno *****************/
        while(true){
            
            // primero: combate
            System.out.println("// ---COMBATE--- //");
            System.out.println(juego.obtenerJugadorActivo().toString());
            System.out.println(juego.obtenerMonstruoActivo().toString());
            ResultadoCombate resultado = juego.desarrollarCombate();
            if (resultado == ResultadoCombate.VENCEYFIN){
                System.out.println("GANO!"); // ¿abortamos directamente?
                break;
            }
            System.out.println("");
            
            // segundo: resolver malrollo del combate (devuelve bool)    
            System.out.println("// ---CUMPLE MAL ROLLO(COMBATE)--- //");
            System.out.println(juego.obtenerJugadorActivo().toString());
            System.out.println("Indices de las cartas visibles a descartar (-1 abortar)");
            visDes = lectorCartas.leeCartas(juego.obtenerJugadorActivo().obtenerTesorosVisibles());
            System.out.println("Indices de las cartas ocultas a descartar (-1 abortar)");
            ocuDes =  lectorCartas.leeCartas(juego.obtenerJugadorActivo().obtenerTesorosOcultos());
            juego.descartarTesoros(visDes, ocuDes); // tengo mis dudas.... ¿hace falta eliminar los array?
            System.out.println("");
            
                      
            // tercero: equipar (devuelve bool)
            System.out.println("// ---EQUIPO--- //");
            System.out.println(juego.obtenerJugadorActivo().toString());
            System.out.println("Indices de las cartas ocultas a equipar (-1 abortar)");
            ArrayList<Tesoro> cartasaEquipar = lectorCartas.leeCartas(juego.obtenerJugadorActivo().obtenerTesorosOcultos());
            juego.obtenerJugadorActivo().equiparTesoros(cartasaEquipar);
            System.out.println("");
            
            // cuarto: comprar niveles
            //      devuelve un bool si pudiera comprar niveles
            System.out.println("// ---VENTA--- //");
            System.out.println(juego.obtenerJugadorActivo().toString());
            System.out.println("Indices de las cartas visibles a vender (-1 abortar)");
            ArrayList<Tesoro> cartasaVender = lectorCartas.leeCartas(juego.obtenerJugadorActivo().obtenerTesorosVisibles());
            System.out.println("Indices de las cartas ocultas a vender (-1 abortar)");
            cartasaVender.addAll(lectorCartas.leeCartas(juego.obtenerJugadorActivo().obtenerTesorosOcultos()));
            juego.comprarNivelesJugador(cartasaVender);
            System.out.println("");
            
            //final del turno: puedo pasar
            int fin = juego.siguienteTurno();
            while( fin != 0)
            {   
                if(fin > 0){
                    System.out.println("// ---EXCESO DE CARTAS--- //");
                    System.out.println(juego.obtenerJugadorActivo().toString());
                    System.out.println("Indices de las cartas visibles a descartar (-1 abortar)");
                    visDes = lectorCartas.leeCartas(juego.obtenerJugadorActivo().obtenerTesorosVisibles());
                    System.out.println("Indices de las cartas ocultas a descartar (-1 abortar)");
                    ocuDes = lectorCartas.leeCartas(juego.obtenerJugadorActivo().obtenerTesorosOcultos());
                    
                    juego.descartarTesoros(visDes, ocuDes);
                    System.out.println("");
                } 
                else if(fin < 0){
                    System.out.println("// ---CUMPLE MAL ROLLO (EOT)--- //");
                    System.out.println(juego.obtenerJugadorActivo().toString());
                    System.out.println("Indices de las cartas visibles a descartar (-1 abortar)");
                    visDes = lectorCartas.leeCartas(juego.obtenerJugadorActivo().obtenerTesorosVisibles());
                    System.out.println("Indices de las cartas ocultas a descartar (-1 abortar)");
                    ocuDes = lectorCartas.leeCartas(juego.obtenerJugadorActivo().obtenerTesorosOcultos());

                    juego.descartarTesoros(visDes, ocuDes); 
                    System.out.println("");
                }

                fin = juego.siguienteTurno();                
            }
            System.out.println("\n\n-----------------------------------------------------\n\n");
        }//fin while
    }//fin main
}//fin class