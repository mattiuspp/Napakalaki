package napakalaki;

import java.util.ArrayList;
import java.util.Scanner;

public class main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

	
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
            int indice;
            ArrayList<Tesoro> visDes = new ArrayList();
            ArrayList<Tesoro> ocuDes = new ArrayList(); 
            
            System.out.println("Indices de cartas visibles a descartar, -1 para finalizar");
            indice = sc.nextInt();
            while(indice != -1){
                visDes.add(juego.obtenerJugadorActivo().obtenerTesorosOcultos().get(indice));
                indice = sc.nextInt();
            }
            
            System.out.println("Indices de cartas ocultas a descartar, -1 para finalizar");
            indice = sc.nextInt();
            while(indice != -1){
                visDes.add(juego.obtenerJugadorActivo().obtenerTesorosVisibles().get(indice));
                indice = sc.nextInt();
            }
            
            juego.descartarTesoros(visDes, ocuDes);
            // tengo mis dudas.... ¿hace falta eliminar los array?
            // ¿se hace un clear dentro?
            
            
            // tercero: equipar
            // problema: napakalaki no tiene un metodo para hacerlo desde fuera
            //           solucion: creamos metodo obtener jugador 
            //      devuelve un bool si pudiera equipar
            ArrayList<Tesoro> cartasaEquipar = new ArrayList();
            
            System.out.println("Indices de cartas visibles a equipar, -1 para finalizar");
            indice = sc.nextInt();
            while(indice != -1){
                cartasaEquipar.add(juego.obtenerJugadorActivo().obtenerTesorosOcultos().get(indice));
                indice = sc.nextInt();
            }
            juego.obtenerJugadorActivo().equiparTesoros(cartasaEquipar);
            
            // cuarto: comprar niveles
            //      devuelve un bool si pudiera comprar niveles
            ArrayList<Tesoro> cartasaVender = new ArrayList();
            
            // por ahora no damos la opcion de vender cartas equipadas (visibles)
            // ¿se puede?
            System.out.println("Indices de cartas ocultas a vender, -1 para finalizar");
            indice = sc.nextInt();
            while(indice != -1){
                cartasaVender.add(juego.obtenerJugadorActivo().obtenerTesorosOcultos().get(indice));
                indice = sc.nextInt();
            }
            juego.comprarNivelesJugador(cartasaVender);
            
            //final del turno: puedo pasar
            int fin = juego.siguienteTurno();
            while( fin != 0)
            {   
                if(fin > 0){
                    System.out.println("Indices de cartas visibles a descartar, -1 para finalizar");
                    indice = sc.nextInt();
                    while(indice != -1){
                        visDes.add(juego.obtenerJugadorActivo().obtenerTesorosOcultos().get(indice));
                        indice = sc.nextInt();
                    }

                    System.out.println("Indices de cartas ocultas a descartar, -1 para finalizar");
                    indice = sc.nextInt();
                    while(indice != -1){
                        visDes.add(juego.obtenerJugadorActivo().obtenerTesorosVisibles().get(indice));
                        indice = sc.nextInt();
                        
                    juego.descartarTesoros(visDes, ocuDes);
            }
            
            
                }
                else if(fin < 0){
                    System.out.println("Indices de cartas visibles a descartar, -1 para finalizar");
                    indice = sc.nextInt();
                    while(indice != -1){
                        visDes.add(juego.obtenerJugadorActivo().obtenerTesorosOcultos().get(indice));
                        indice = sc.nextInt();
                    }

                    System.out.println("Indices de cartas ocultas a descartar, -1 para finalizar");
                    indice = sc.nextInt();
                    while(indice != -1){
                        visDes.add(juego.obtenerJugadorActivo().obtenerTesorosVisibles().get(indice));
                        indice = sc.nextInt();
                    }

                    juego.descartarTesoros(visDes, ocuDes);
                }

                fin = juego.siguienteTurno();
            }
            
    }
}