package napakalaki;

/**
 * CLASE LISTA!
 * @author Antonio Álvarez y Adrián Ranea
 */
class BuenRollo {
    private int gananciaTesoros;
    private int gananciaNiveles;

    public BuenRollo(int gananciaTesoros, int gananciaNiveles) {
        this.gananciaTesoros = gananciaTesoros;
        this.gananciaNiveles = gananciaNiveles;
    }
        
    public int obtenerGananciaTesoros() {
        return gananciaTesoros;
    }

    public int obtenerGananciaNiveles() {
        return gananciaNiveles;
    }

    public void setGananciaTesoros(int gananciaTesoros) {
        this.gananciaTesoros = gananciaTesoros;
    }

    public void setGananciaNiveles(int gananciaNiveles) {
        this.gananciaNiveles = gananciaNiveles;
    }
    
    
    
}
