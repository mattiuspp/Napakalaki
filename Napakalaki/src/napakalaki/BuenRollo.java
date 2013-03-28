package napakalaki;

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
}
