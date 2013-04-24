/* BUG 24-04-14 Adrián
 * ARREGLADO.
 *  -   siguienteMonstruo() funcionado 100%
 *  -   cumpleMalRollo parece que funciona -> funcionando 99% (no me ha dado errores)
 *              si el ArrayTesoros tiene mas de un elemento -> Arreglado!
 *  -   venta funciona 100%
 * BUGS:
 *  -   equipar NO FUNCIONA ----> Arreglar
 * PROBLEMA:
 *  -   el malRollo se puede hacer al final del turno y esta ajustado con las cartas que tiene...
 *      ¿que pasa si un jugador se equipar y/o vende cartas? -> ya nunca podría librarse del malRollo
 *          - solucion? -> limitar la venta de cartas y/o equipar a cuando no haya malRollo o no afecte al malRollo
 *          - piensalo ;)
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
            System.out.println("// ---TRAS VENTA--- //");
            System.out.println(juego.obtenerJugadorActivo().toString());
            System.out.println(""); 
            
            //final del turno: puedo pasar
            int fin = juego.siguienteTurno();
            while( fin != 0)
            {   
                if(fin > 0){
                    System.out.println("// ---EXCESO DE CARTAS--- //");
                    System.out.println(juego.obtenerJugadorActivo().toString());
                    System.out.println("Indices de las cartas ocultas a descartar (-1 abortar)");
                    ocuDes = lectorCartas.leeCartas(juego.obtenerJugadorActivo().obtenerTesorosOcultos());
                    
                    juego.descartarTesoros(new ArrayList(), ocuDes);
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