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
                if(indiceCarta < listaTesoros.size())
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
       
            // primero: combate
            ResultadoCombate resultado = juego.desarrollarCombate();
            if (resultado == ResultadoCombate.VENCEYFIN)
                System.out.println("GANO!"); // ¿abortamos directamente?
            
            // segundo: resolver malrollo del combate
            // imprimir malRolloPendiente del jugador (del monstruo)
            // dar la opcion al jugador para que se descarte cartas
            //      devuelve un bool si completa malRolo
            ArrayList<Tesoro> visDes = lectorCartas.leeCartas(juego.obtenerJugadorActivo().obtenerTesorosVisibles());
            ArrayList<Tesoro> ocuDes = lectorCartas.leeCartas(juego.obtenerJugadorActivo().obtenerTesorosOcultos());
       
            juego.descartarTesoros(visDes, ocuDes); // tengo mis dudas.... ¿hace falta eliminar los array?
            
            
            // tercero: equipar
            // problema: napakalaki no tiene un metodo para hacerlo desde fuera
            //           solucion: creamos metodo obtener jugador 
            //      devuelve un bool si pudiera equipar
            ArrayList<Tesoro> cartasaEquipar = lectorCartas.leeCartas(juego.obtenerJugadorActivo().obtenerTesorosOcultos());
            juego.obtenerJugadorActivo().equiparTesoros(cartasaEquipar);
            
            // cuarto: comprar niveles
            //      devuelve un bool si pudiera comprar niveles
            ArrayList<Tesoro> cartasaVender = lectorCartas.leeCartas(juego.obtenerJugadorActivo().obtenerTesorosVisibles());
            cartasaVender.addAll(lectorCartas.leeCartas(juego.obtenerJugadorActivo().obtenerTesorosOcultos()));
            juego.comprarNivelesJugador(cartasaVender);
            
            //final del turno: puedo pasar
            int fin = juego.siguienteTurno();
            while( fin != 0)
            {   
                if(fin > 0){
                    ArrayList<Tesoro> visDes1 = lectorCartas.leeCartas(juego.obtenerJugadorActivo().obtenerTesorosVisibles());
                    ArrayList<Tesoro> ocuDes1 = lectorCartas.leeCartas(juego.obtenerJugadorActivo().obtenerTesorosOcultos());

                    juego.descartarTesoros(visDes1, ocuDes1); // tengo mis dudas.... ¿hace falta eliminar los array?
                } 
                else if(fin < 0){
                    ArrayList<Tesoro> visDes2 = lectorCartas.leeCartas(juego.obtenerJugadorActivo().obtenerTesorosVisibles());
                    ArrayList<Tesoro> ocuDes2 = lectorCartas.leeCartas(juego.obtenerJugadorActivo().obtenerTesorosOcultos());

                    juego.descartarTesoros(visDes2, ocuDes2); // tengo mis dudas.... ¿hace falta eliminar los array?
                }

                fin = juego.siguienteTurno();
            }
    }//fin main
}//fin class