package napakalaki;

/**
 * CLASE LISTA!
 * @author Antonio Álvarez y Adrián Ranea
 */
public class Tesoro {
    private String nombre;
    private TipoTesoro tipo;
    private int bonusMinimo;
    private int bonusMaximo;
    private int piezasOro;

    public Tesoro(String nombre, TipoTesoro tipo, int bonusMinimo, 
            int bonusMaximo, int piezasOro) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.bonusMinimo = bonusMinimo;
        this.bonusMaximo = bonusMaximo;
        this.piezasOro = piezasOro;
    }
    
    
}
