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
        
    }
    
    public int obtenerNivelCombate(){
        // falta por implementar
        return 0;
    }
    
    public void robarTesoro(Tesoro unTesoro){
        
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
    
    private boolean puedoEquipar(Tesoro unTesoro){
        return true;
    }
    
    public int puedoPasar(){
        //falta por implementar
        return 0;
    }
    
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
        //falta por implementar
        return null;
    }
    
    public void incluirMalRollo(MalRollo malRollo){
        
        
    }
    
    public Tesoro devuelveElCollar(){
        //falta por implementar
        return null;
    }
    
    public boolean tienesCollar(){
        //falta por implementar
        return true;
    }
    
    public void aplicarBuenRollo(BuenRollo buenRollo){
        // suponemos que es subir niveles
        nivel += buenRollo.obtenerNivelesGanados();
    }
    
    public void muere(){
        
    }
    
    public void descartaTesorosInteractivo(int numTesoros){
        // modificar con el modo texto
        for (int i=0; i<numTesoros; i++){
            tesorosOcultos.remove((int) Math.random()*tesorosOcultos.size());
        }
    }
    
    public void cumpleMalRolloInteractivo(){
        // implementar cumpleMalRollo y incluirMalRollo
        
    }
    
    
}
