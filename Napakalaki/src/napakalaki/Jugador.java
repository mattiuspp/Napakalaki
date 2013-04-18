package napakalaki;

import java.util.ArrayList;

public class Jugador {
    private String nombre;
    private int nivel = 1;
    private static int NIVEL_MINIMO = 1;
    private static int NIVEL_MAXIMO = 10;
    private static int TESOROS_OCULTOS_MAXIMO = 4;
 
    private MalRollo malRolloPendiente;
    private ArrayList<Tesoro> tesorosOcultos = new ArrayList();
    private ArrayList<Tesoro> tesorosVisibles = new ArrayList();

    public void infoJugador() {
        System.out.println("Jugador: " + nombre);
        System.out.println(" > Cartas en mano(ocultas):");
        for(Tesoro t:tesorosOcultos)
            System.out.println("" + t.obtenerNombre());
                System.out.println(" > Cartas equipadas(visibles):");
        for(Tesoro t:tesorosVisibles)
            System.out.println("" + t.obtenerNombre());
        
    }
    public Jugador(String nombre) {
        this.nombre = nombre;
    }
    
    public int obtenerNivel(){
        return nivel;
    }
    
    public int calcularNiveles(ArrayList<Tesoro> tesoros){
        int piezasOro = 0;
        
        for(Tesoro t: tesoros)
            piezasOro += t.obtenerPiezasOro();
                           
        return piezasOro / 1000;
    }
    
    public void modificarNivel(int nuevoNivel){   
        nivel = nuevoNivel;
    }
    
    public int obtenerNivelCombate(){
        int bonus=0;
        
        if(tienesCollar())
            for(Tesoro t: tesorosVisibles)
                bonus += t.obtenerBonusMaximo();
        else
            for(Tesoro t: tesorosVisibles)
                bonus += t.obtenerBonusMinimo();
       
        return bonus + nivel;
    }
    
    public void robarTesoro(Tesoro unTesoro){
        tesorosOcultos.add(unTesoro);
    }
    
    public boolean descartarTesoros(ArrayList<Tesoro> tesorosVisDes, 
            ArrayList<Tesoro> tesorosOcuDes)
    {
        boolean cumpleMR;
        cumpleMR = cumploMalRollo(tesorosVisibles, tesorosOcultos);
        
        tesorosVisDes.clear();
        tesorosOcuDes.clear();
        
        return cumpleMR;
    }
    
    /**************************** INCOMPLETO - Intentado jajaja ****************************************************/
    // intenta cumplir el malrollo
    // si lo cumple entero, devuelve true
    private boolean cumploMalRollo(ArrayList<Tesoro> tesVisibles, 
            ArrayList<Tesoro> tesOcultos){
        
        boolean cumplo = true;
        int numVis = tesVisibles.size();
        int numOcu = tesOcultos.size();
        
        int topeVis, topeOcu;
        if (numVis > tesorosVisibles.size())
        {
            cumplo = false;
            topeVis = tesorosVisibles.size();
        }
        else
            topeVis = numVis;
        
        if (numOcu > tesorosOcultos.size())
        {
            cumplo = false;
            topeOcu = tesorosOcultos.size();
        }
        else
            topeOcu = numOcu;
        
        for (int i = 0; i < topeVis; i++)
        {
            TipoTesoro tipoBorrado = tesVisibles.get(i).obtenerTipo();
            boolean existe = false;
            for (int j = 0; j < tesorosVisibles.size() && !existe; j++)
            {
                if (tipoBorrado == tesorosVisibles.get(j).obtenerTipo())
                {
                    existe = true;
                    tesorosVisibles.remove(j);
                }
            }
            if (!existe) cumplo = false;
        }
        
        for (int i = 0; i < topeOcu; i++)
        {
            TipoTesoro tipoBorrado = tesOcultos.get(i).obtenerTipo();
            boolean existe = false;
            for (int j = 0; j < tesorosOcultos.size() && !existe; j++)
            {
                if (tipoBorrado == tesorosOcultos.get(j).obtenerTipo())
                {
                    existe = true;
                    tesorosOcultos.remove(j);
                }
            }
            if (!existe) cumplo = false;
        }
        return cumplo;
    }
    
    public void equiparTesoros(ArrayList<Tesoro> listaTesoro){
        for (Tesoro tesoro: listaTesoro){
            if (puedoEquipar(tesoro)){
                tesorosVisibles.add(tesoro);
                tesorosOcultos.remove(tesoro);
            }     
        }
    }
    
    private boolean puedoEquipar(Tesoro unTesoro){
        boolean puedo;
        ArrayList<TipoTesoro> tipos = new ArrayList();
        for(Tesoro t: tesorosVisibles)
            tipos.add(t.obtenerTipo());
        
        if(unTesoro.obtenerTipo() != TipoTesoro.MANO && unTesoro.obtenerTipo() != TipoTesoro.DOSMANOS ){
            if(tipos.contains(unTesoro.obtenerTipo())){
                puedo = true;
                tesorosVisibles.add(unTesoro);
                tesorosOcultos.remove((unTesoro));
            }
            else
                puedo = false;
        }
        else if(unTesoro.obtenerTipo() == TipoTesoro.DOSMANOS){
            if(!tipos.contains(TipoTesoro.DOSMANOS) && !tipos.contains(TipoTesoro.MANO)){
                puedo = true;
                tesorosVisibles.add(unTesoro);
                tesorosOcultos.remove((unTesoro));
            }
            else
                puedo = false;
            
        }
        else{
            if( !tipos.contains(TipoTesoro.DOSMANOS) && 
                    (tipos.indexOf(TipoTesoro.MANO) == tipos.lastIndexOf(TipoTesoro.MANO)   ) ){
                puedo = true;
                tesorosVisibles.add(unTesoro);
                tesorosOcultos.remove((unTesoro));
            }
            else
                puedo = false;
        }
           
        return puedo;
    }
    
    /**************************** INCOMPLETO ****************************************************/
    public int puedoPasar(){
        //falta por implementar
        return 0;
    }
    
    public boolean tienesTesoros(){
        return tesorosOcultos.isEmpty() && tesorosVisibles.isEmpty();
    }
    
    public boolean comprarNiveles(ArrayList<Tesoro> tesoros){
        boolean puedo;
        
        int niveles = calcularNiveles(tesoros);
        puedo = (niveles+nivel)<10;
        if(puedo){
            incDecNivel(niveles);
            tesorosOcultos.removeAll(tesoros);
            tesorosVisibles.removeAll(tesoros);
        }
        
        return puedo;
    }
    
    public void incDecNivel(int incDec){
        nivel += incDec;
        if (nivel < 1) nivel = 1;
    }
    
    public ResultadoCombate combatir(Monstruo monstruoEnJuego){
        ResultadoCombate resultado;
        int nivelM = monstruoEnJuego.obtenerNivel();
                
        if (nivel > nivelM)
        {
            aplicarBuenRollo(monstruoEnJuego.cualEsTuBuenRollo());
            
            if(nivel >= 10)
                resultado = ResultadoCombate.VENCEYFIN;
            else
                resultado = ResultadoCombate.VENCE;
        }
        else{
            int dado = (int) Math.random()*6+1;
            
            if (dado <5)
            {
                MalRollo malRollo = monstruoEnJuego.cualEsTuMalRollo();
                boolean muerte = malRollo.muerte();
                
                if(muerte)
                {
                    muere();
                    resultado = ResultadoCombate.PIERDEYMUERE;                    
                }
                else
                {
                    incluirMalRollo(malRollo);
                    resultado = ResultadoCombate.PIERDE;
                }
                    
            }
            else
                resultado = ResultadoCombate.PIERDEYESCAPA;
        }
        
        return resultado;
    }
    
    public ArrayList<Tesoro> dameTodosTusTesoros(){
        ArrayList<Tesoro> tesoros = new ArrayList(tesorosOcultos);
        tesoros.addAll(tesorosVisibles);
        
        tesorosOcultos.clear();
        tesorosVisibles.clear();
        
        return tesoros;
    }
    
    /**************************** INCOMPLETO ****************************************************/
    public void incluirMalRollo(MalRollo malRollo){
        malRolloPendiente = malRollo;
    }
    
      public Tesoro devuelveElCollar(){
        for(Tesoro t: tesorosVisibles)
            if (t.obtenerTipo()==TipoTesoro.COLLAR){
                tesorosVisibles.remove(t);
                return t;
            }
        return null;
    }
    
    public boolean tienesCollar(){
        for(Tesoro t: tesorosVisibles)
            return t.obtenerTipo() == TipoTesoro.COLLAR;
        return false;
    }
    
    public void aplicarBuenRollo(BuenRollo buenRollo){
        // suponemos que es subir niveles
        // los tesoros los roba la clase napakalaki
        nivel += buenRollo.obtenerNivelesGanados();
    }
    
    /**/
    public void muere(){
        nivel = 1;
        // habria que descartarlos ----> ¿como los llevo al mazo?
        tesorosOcultos.clear();;
        tesorosVisibles.clear();
    }
    
    /**************************** INCOMPLETO ****************************************************/
    public void descartaTesorosInteractivo(int numTesoros){
        // modificar con el modo texto
        // habria que descartarlos ----> ¿como los llevo al mazo?
        for (int i=0; i<numTesoros; i++){
            tesorosOcultos.remove((int) Math.random()*tesorosOcultos.size());
        }
    }
    
    /**************************** INCOMPLETO ****************************************************/
    public void cumpleMalRolloInteractivo(){
        // implementar cumpleMalRollo y incluirMalRollo
        
    }
    
    
}
