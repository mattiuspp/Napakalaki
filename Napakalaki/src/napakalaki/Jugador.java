package napakalaki;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
    private String nombre;
    private int nivel = 1;
    private static int NIVEL_MINIMO = 1;
    private static int NIVEL_MAXIMO = 10;
    private static int TESOROS_OCULTOS_MAXIMO = 4;
 
    private MalRollo malRolloPendiente;
    private ArrayList<Tesoro> tesorosOcultos = new ArrayList();
    private ArrayList<Tesoro> tesorosVisibles = new ArrayList();

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
    
    /**************************** INCOMPLETO ****************************************************/
    // intenta cumplir el malrollo
    // si lo cumple entero, devuelve true
    private boolean cumploMalRollo(ArrayList<Tesoro> tesVisibles, 
            ArrayList<Tesoro> tesOcultos){
        return true;
    }
    
    public void equiparTesoros(ArrayList<Tesoro> listaTesoro){
        for (Tesoro tesoro: listaTesoro){
            if (puedoEquipar(tesoro)){
                tesorosVisibles.add(tesoro);
                tesorosOcultos.remove(tesoro);
            }     
        }
    }
    
    // ¿Relamente funciona?
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
    
    /**************************** INCOMPLETO ****************************************************/
    public boolean tienesTesoros(){
        //falta por implementar
        return true;
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
    
    /**************************** INCOMPLETO ****************************************************/
    // y esto donde se llama?
    public ArrayList<Tesoro> dameTodosTusTesoros(){
        //falta por implementar
        return null;
    }
    
    /**************************** INCOMPLETO ****************************************************/
    public void incluirMalRollo(MalRollo malRollo){
        // adaptamos al jugador
        
        
    }
    
    /**************************** INCOMPLETO ****************************************************/
    public Tesoro devuelveElCollar(){
        //falta por implementar
        return null;
    }
    
    /**************************** INCOMPLETO ****************************************************/
    public boolean tienesCollar(){
        //falta por implementar
        return true;
    }
    
    public void aplicarBuenRollo(BuenRollo buenRollo){
        // suponemos que es subir niveles
        // los tesoros los roba la clase napakalaki
        nivel += buenRollo.obtenerNivelesGanados();
    }
    
    /**************************** INCOMPLETO ****************************************************/
    public void muere(){
        
    }
    
    /**************************** INCOMPLETO ****************************************************/
    public void descartaTesorosInteractivo(int numTesoros){
        // modificar con el modo texto
        for (int i=0; i<numTesoros; i++){
            tesorosOcultos.remove((int) Math.random()*tesorosOcultos.size());
        }
    }
    
    /**************************** INCOMPLETO ****************************************************/
    public void cumpleMalRolloInteractivo(){
        // implementar cumpleMalRollo y incluirMalRollo
        
    }
    
    
}
