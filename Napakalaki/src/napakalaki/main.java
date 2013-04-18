package napakalaki;

public class main {
    public static void main(String args[]) {
	
        Napakalaki juego = Napakalaki.getInstance();
        
        String jugadores[] = {"Pepe","Juan"};
        juego.comenzarJuego(jugadores);
        
        
        //Ejemplo de turno
        juego.desarrollarCombate();
        
        //si quiere comprar
        System.out.println("¿Quiere vender objetos? S/N");
        //captar caracter S/N
        String letra = "A";
        if (letra == "S")
            juego.comprarNivelesJugador(null);
        
        //final del turno se descarta
        
        //Se empieza un nuevo turno
        juego.siguienteTurno();     
        
    }
}