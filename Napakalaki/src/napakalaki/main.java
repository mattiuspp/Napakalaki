package napakalaki;

public class main {
    public static void main(String args[]) {
	
        Napakalaki juego = Napakalaki.getInstance();
        
        String jugadores[] = {"Pepe","Juan","Eva"};
        juego.comenzarJuego(jugadores);
        
        /**************+ Ejemplo de un turno *****************/
        int ronda = 0;
        while(ronda<10){
            System.out.println("¿Quiere vender objetos? S/N");
                     juego.comprarNivelesJugador(null);

            //final del turno se descarta
            descartasTesoros();

            jugador.comprarNiveles; // comprar niveles

            jugador.Equipar() //intentamos equipar

            int fin = juego.siguienteTurno();
            while( fin != 0){   
                if(fin > 0){
                    // el jugador elige que cartas descartas
                    juego.descartarTesoros(null,null);
                }
                else if(fin < 0){
                    // print infoMalRollo
                    juego.descartarTesoros(null, null);
                }

                fin = juego.siguienteTurno();
            }
            
            ronda++;
        }
        
    }
}