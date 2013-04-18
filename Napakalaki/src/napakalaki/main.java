package napakalaki;

public class main {
    public static void main(String args[]) {
	
        Napakalaki juego = Napakalaki.getInstance();
        
        String jugadores[] = {"Pepe","Juan"};
        juego.comenzarJuego(jugadores);
        
        
        //Ejemplo de turno
        juego.desarrollarCombate();
        
        //si quiere comprar
        juego.comprarNivelesJugador(null);
        
        //final del turno se descarta
        juego.descartarTesoros(null, null);
        
        //Se empieza un nuevo turno
        juego.siguienteTurno();
        
        
        
        
        
    }
}