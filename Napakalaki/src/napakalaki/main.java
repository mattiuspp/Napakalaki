package napakalaki;

public class main {
    public static void main(String args[]) {
	
        Napakalaki juego = Napakalaki.getInstance();
        
        String jugadores[] = {"Pepe","Juan","Eva"};
        juego.comenzarJuego(jugadores);
        
        /**************+ Ejemplo de un turno *****************/
        int ronda = 0;
        while(ronda<10)
        {
            // primero: combate
            ResultadoCombate resultado = juego.desarrollarCombate();
            if (resultado == ResultadoCombate.VENCEYFIN)
                break;
            
            // segundo: resolver malrollo del combate
            // imprimir malRolloPendiente del jugador (del monstruo)
            // dar la opcion al jugador para que se descarte cartas
            juego.descartarTesoros(null, null);
            
            // tercero: equipar
            // jugador.equiparTesoros(null);
            
            // cuarto: comprar niveles
            juego.comprarNivelesJugador(null);
            
            //final del turno: puedo pasar
            int fin = juego.siguienteTurno();
            while( fin != 0)
            {   
                if(fin > 0){
                    // exceso de cartas: elegir para descartar
                    juego.descartarTesoros(null,null);
                }
                else if(fin < 0){
                    // malrollo pendiente: elegir para descartar
                    juego.descartarTesoros(null, null);
                }

                fin = juego.siguienteTurno();
            }
            
            ronda++;
        }
        
    }
}