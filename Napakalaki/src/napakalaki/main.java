package napakalaki;

import java.util.ArrayList;
import java.util.Scanner;



public class main {
    public static class LectorCartas{
        public Scanner sc = new Scanner(System.in);

        public ArrayList<Tesoro> leeCartas(ArrayList<Tesoro> listaTesoros){
            ArrayList<Tesoro> cartasLeidas = new ArrayList();
            int indiceCarta;

            System.out.println("Indices de cartas a descartar, -1 para finalizar");
            indiceCarta = sc.nextInt();
            while(indiceCarta != -1){
                if(indiceCarta<listaTesoros.size())
                    cartasLeidas.add(listaTesoros.get(indiceCarta));
                indiceCarta = sc.nextInt();
            }

            return cartasLeidas;
        }    
}
        
    public static void main(String args[]) {
	LectorCartas lectorCartas = new LectorCartas();
        Napakalaki juego = Napakalaki.getInstance();
        
        String jugadores[] = {"Pepe","Juan","Eva"};
        juego.comenzarJuego(jugadores);
        
        /**************+ Ejemplo de un turno *****************/
        // esta todo tabulado porque se ejecuta en un bucle
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
            ArrayList<Tesoro> visDes = lectorCartas.leeCartas(juego.obtenerJugadorActivo().obtenerTesorosVisibles());
            ArrayList<Tesoro> ocuDes = lectorCartas.leeCartas(juego.obtenerJugadorActivo().obtenerTesorosOcultos());
            juego.descartarTesoros(visDes, ocuDes); // tengo mis dudas.... ¿hace falta eliminar los array?
            System.out.println("");
            
                      
            // tercero: equipar (devuelve bool)
            System.out.println("// ---EQUIPO--- //");
            System.out.println(juego.obtenerJugadorActivo().toString());
            ArrayList<Tesoro> cartasaEquipar = lectorCartas.leeCartas(juego.obtenerJugadorActivo().obtenerTesorosOcultos());
            juego.obtenerJugadorActivo().equiparTesoros(cartasaEquipar);
            System.out.println("");
            
            // cuarto: comprar niveles
            //      devuelve un bool si pudiera comprar niveles
            System.out.println("// ---VENTA--- //");
            System.out.println(juego.obtenerJugadorActivo().toString());
            ArrayList<Tesoro> cartasaVender = lectorCartas.leeCartas(juego.obtenerJugadorActivo().obtenerTesorosVisibles());
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
                    ArrayList<Tesoro> visDes1 = lectorCartas.leeCartas(juego.obtenerJugadorActivo().obtenerTesorosVisibles());
                    ArrayList<Tesoro> ocuDes1 = lectorCartas.leeCartas(juego.obtenerJugadorActivo().obtenerTesorosOcultos());

                    juego.descartarTesoros(visDes1, ocuDes1);
                    System.out.println("");
                } 
                else if(fin < 0){
                    System.out.println("// ---CUMPLE MAL ROLLO (EOT)--- //");
                    System.out.println(juego.obtenerJugadorActivo().toString());
                    ArrayList<Tesoro> visDes2 = lectorCartas.leeCartas(juego.obtenerJugadorActivo().obtenerTesorosVisibles());
                    ArrayList<Tesoro> ocuDes2 = lectorCartas.leeCartas(juego.obtenerJugadorActivo().obtenerTesorosOcultos());

                    juego.descartarTesoros(visDes2, ocuDes2); 
                    System.out.println("");
                }

                fin = juego.siguienteTurno();
                
            }
            System.out.println("--------------------------------------");
        }//fin while
    }//fin main
}//fin class