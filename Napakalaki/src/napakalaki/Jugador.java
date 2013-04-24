package napakalaki;

import java.util.ArrayList;
import java.util.HashSet;

public class Jugador {
    private String nombre;
    private int nivel = 1;
    private static int NIVEL_MINIMO = 1;
    private static int NIVEL_MAXIMO = 10;
    private static int TESOROS_OCULTOS_MAXIMO = 4;
 
    private MalRollo malRolloPendiente;
    private ArrayList<Tesoro> tesorosOcultos = new ArrayList();
    private ArrayList<Tesoro> tesorosVisibles = new ArrayList();

    @Override
    public String toString() {
        String f = new String();
        f+=nombre + ", nivel: " + nivel;
        f+="\n\tCartas en mano(ocultas): ";
        for(Tesoro t:tesorosOcultos)
            f+=t.obtenerNombre() + " | ";
                f+="\n\tCartas equipadas(visibles): ";
        for(Tesoro t:tesorosVisibles)
            f+=t.obtenerNombre() + " | ";
        
        if (malRolloPendiente != null && !malRolloPendiente.esVacio())
            f+= "\nMal rollo pendiente: " + malRolloPendiente.toString();
        
        return f;
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
        if (nuevoNivel >= NIVEL_MINIMO && nuevoNivel <= NIVEL_MAXIMO)
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
    
    public boolean tienesTesoros(){
        return !(tesorosOcultos.isEmpty() && tesorosVisibles.isEmpty());
    }
    
    public boolean comprarNiveles(ArrayList<Tesoro> tesoros){
        boolean puedo;
        
        int niveles = calcularNiveles(tesoros);
        puedo = (niveles+nivel) < NIVEL_MAXIMO;
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
            
            if(nivel >= NIVEL_MAXIMO)
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
            if (t.obtenerTipo() == TipoTesoro.COLLAR)
                return true;
        return false;
    }
    
    public void aplicarBuenRollo(BuenRollo buenRollo){
        nivel += buenRollo.obtenerNivelesGanados();
    }
    
    public void muere(){
        nivel = 1;
    }
        
    public int puedoPasar(){
        if(malRolloPendiente != null && !malRolloPendiente.esVacio())
                return -1;
        else if (tesorosOcultos.size() > TESOROS_OCULTOS_MAXIMO)
            return tesorosOcultos.size() - TESOROS_OCULTOS_MAXIMO;
        else
            return 0;
    }
    
    public boolean descartarTesoros(ArrayList<Tesoro> tesorosVisDes, 
            ArrayList<Tesoro> tesorosOcuDes){
        boolean cumpleMR;
        cumpleMR = cumploMalRollo(tesorosVisibles, tesorosOcultos);
        
        tesorosVisDes.clear();
        tesorosOcuDes.clear();
        
        return cumpleMR;
    }
        
    
    
    // ----------------------- INCOMPLETO -------------
    
    // Ajusta el malRollo al jugador (le quitamos aquello no pueda descartar)
    public void incluirMalRollo(MalRollo malRollo){
        int numVis, numOcu;
        ArrayList<TipoTesoro> tipoOcuJug = new ArrayList();
        ArrayList<TipoTesoro> tipoVisJug = new ArrayList();
        
        if (malRollo.obtenerVisiblesPerdidos() > tesorosVisibles.size())
            numVis = tesorosVisibles.size();
        else
            numVis = malRollo.obtenerVisiblesPerdidos();
        
        if (malRollo.obtenerOcultosPerdidos() > tesorosOcultos.size())
            numOcu = tesorosOcultos.size();
        else
            numOcu = malRollo.obtenerOcultosPerdidos();
        
        
        
        // Montamos un array de cada uno de los tipos disponibles
        
        for (Tesoro t: tesorosOcultos)
            tipoOcuJug.add(t.obtenerTipo());
        for (Tesoro t: tesorosVisibles)
            tipoVisJug.add(t.obtenerTipo());
        
        // Intersecamos esos arrays con los del malRollo
        
        ArrayList<TipoTesoro> tipoOcuMalRollo = new ArrayList(malRollo.obtenerTipoOcultosPerdidos());
        ArrayList<TipoTesoro> tipoVisMalRollo = new ArrayList(malRollo.obtenerTipoVisiblesPerdidos());
        
        ArrayList<TipoTesoro> tipoOcu = new ArrayList();
        ArrayList<TipoTesoro> tipoVis = new ArrayList();
        
        for (TipoTesoro t: tipoOcuJug)
            if (tipoOcuMalRollo.contains(t))
                tipoOcu.add(t);
                
        for (TipoTesoro t: tipoVisJug)
            if (tipoVisMalRollo.contains(t))
                tipoVis.add(t);
        
        // Quito los repetidos usando un Hashset
        
        HashSet aux = new HashSet();
        aux.addAll(tipoOcu);
        tipoOcu.clear();
        tipoOcu.addAll(aux);
        
        aux.clear();
        aux.addAll(tipoVis);
        tipoVis.clear();
        tipoVis.addAll(aux);
        aux.clear();
        
        
        // Montamos un malRolloPendiente
        
        malRolloPendiente = new MalRollo(malRollo.obtenerTexto(),malRollo.obtenerNivelesPerdidos(),
                numOcu,numVis,malRollo.muerte(),tipoOcu,tipoVis);
        
        // MalRolloPendiente se queda con lo que SE PUEDE QUITAR EL JUGADOR, NO CON LO QUE SE QUEDA PENDIENTE

        
        
    }
    
    private boolean cumploMalRollo(ArrayList<Tesoro> tesVisibles, 
            ArrayList<Tesoro> tesOcultos){
        
        int visiblesRestantes;
        for(int i=0; i<tesVisibles.size(); i++){
            tesorosVisibles.remove(tesVisibles.get(i));
            
            visiblesRestantes = malRolloPendiente.obtenerVisiblesPerdidos();
            if(visiblesRestantes>0)
                malRolloPendiente.modificarVisiblesPerdidos(visiblesRestantes-1);
            
            if(malRolloPendiente.obtenerTipoVisiblesPerdidos().size()>0)
                malRolloPendiente.obtenerTipoVisiblesPerdidos().remove(tesVisibles.get(i).obtenerTipo());           
        }
        
        int ocultosRestantes;
        for(int i=0; i<tesOcultos.size(); i++){
            tesorosOcultos.remove(tesOcultos.get(i));
            
            ocultosRestantes = malRolloPendiente.obtenerOcultosPerdidos();
            if(ocultosRestantes>0)
                malRolloPendiente.modificarOcultosPerdidos(ocultosRestantes-1);
            
            if(malRolloPendiente.obtenerTipoOcultosPerdidos().size()>0)
                malRolloPendiente.obtenerTipoOcultosPerdidos().remove(tesOcultos.get(i).obtenerTipo());
        }        
        
        //tesOcultos.clear(); lo elimina descartarTesoros()
        //tesVisibles.clear(); lo elimina descartarTesoros()
        
        return malRolloPendiente.esVacio();
    }
    
    // Ejecuta el descarte. Comprueba si se actualiza malRolloPendiente
    // ERROR: no actualiza malRollopendiente!!
    /*
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
     */
    
    // Metodo innecesario
   /*public ArrayList<Tesoro>  descartaTesorosRandom(int numTesoros)
        {
        ArrayList<Tesoro> descartados = new ArrayList();
        for (int i=0; i<numTesoros; i++){
        int indice_tesoro = (int) Math.random()*tesorosOcultos.size();
        descartados.add(tesorosOcultos.get(indice_tesoro));
        tesorosOcultos.remove(indice_tesoro);
        }
        return descartados;
    }*/
    
    
    // Testin!!
    public ArrayList<Tesoro> obtenerTesorosOcultos() {
        return tesorosOcultos;
    }
    public ArrayList<Tesoro> obtenerTesorosVisibles() {
        return tesorosVisibles;
    }
    
}
    

    
