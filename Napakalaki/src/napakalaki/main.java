package napakalaki;

public class main {
    public static void main(String args[]) {
	
        Napakalaki juego = Napakalaki.getInstance();
        
        String jugadores[] = {"Pepe","Juan","Eva"};
        juego.comenzarJuego(jugadores);
        
        /* Ejemplo de un turno */
           
        //si quiere comprar
        System.out.println("¿Quiere vender objetos? S/N");
        //captar caracter S/N
        String letra = "A";
        if (letra == "S")
            juego.comprarNivelesJugador(null);
        
        //final del turno se descarta
        
        
        //quiere equipar?
        jugador.Equipar()
        //Se empieza un nuevo turno
        juego.siguienteTurno();     
        
    }
}