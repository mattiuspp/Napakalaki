package napakalaki;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Jugador {
    private String nombre;
    private int nivel = 1;
    private static int NIVEL_MINIMO = 1;
    private static int NIVEL_MAXIMO = 10;
    private static int TESOROS_OCULTOS_MAXIMO = 4;
 
    private MalRollo malRolloPendiente = new MalRollo();
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
        
        if (!malRolloPendiente.esVacio())
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
        
        if(unTesoro.obtenerTipo() != TipoTesoro.MANO && unTesoro.obtenerTipo() != TipoTesoro.DOSMANOS )
            puedo = !tipos.contains(unTesoro.obtenerTipo());
        else if(unTesoro.obtenerTipo() == TipoTesoro.DOSMANOS)
            puedo = !tipos.contains(TipoTesoro.DOSMANOS) && !tipos.contains(TipoTesoro.MANO);
        else
            puedo = !tipos.contains(TipoTesoro.DOSMANOS) &&
                    (tipos.indexOf(TipoTesoro.MANO) == tipos.lastIndexOf(TipoTesoro.MANO));
           
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
        if (nivel < NIVEL_MINIMO) 
            nivel =  NIVEL_MINIMO;
    }
    
    public ResultadoCombate combatir(Monstruo monstruoEnJuego){
        ResultadoCombate resultado;
        int nivelC = obtenerNivelCombate();
        int nivelM = monstruoEnJuego.obtenerNivel();
                
        if (nivelC > nivelM)
        {
            aplicarBuenRollo(monstruoEnJuego.cualEsTuBuenRollo());
            
            if(nivel >= NIVEL_MAXIMO)
                resultado = ResultadoCombate.VENCEYFIN;
            else
                resultado = ResultadoCombate.VENCE;
        }
        else{
            Random dado = new Random();
            if ((dado.nextInt(6)+1) < 5)
            {
                MalRollo malRollo = monstruoEnJuego.cualEsTuMalRollo();
                boolean muerte = malRollo.muerte();
                
                if(muerte){
                    muere();
                    resultado = ResultadoCombate.PIERDEYMUERE;                    
                }else{
                    incDecNivel(-malRollo.obtenerNivelesPerdidos());
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
            if (t.obtenerTipo()==TipoTesoro.COLLAR)
            {
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
        nivel = NIVEL_MINIMO;
    }
        
    public int puedoPasar(){
        if(!malRolloPendiente.esVacio())
            return -1;
        else if (tesorosOcultos.size() > TESOROS_OCULTOS_MAXIMO)
            return tesorosOcultos.size() - TESOROS_OCULTOS_MAXIMO;
        else
            return 0;
    }
    
    public boolean descartarTesoros(ArrayList<Tesoro> tesorosVisDes, 
            ArrayList<Tesoro> tesorosOcuDes){
        
        boolean cumpleMR;
        
        if(tesorosVisDes.isEmpty() && tesorosOcuDes.isEmpty())
            return malRolloPendiente.esVacio();
        
        cumpleMR = cumploMalRollo(tesorosVisDes, tesorosOcuDes);
        
        tesorosVisDes.clear();
        tesorosOcuDes.clear();
        
        return cumpleMR;
    }
        
    public void incluirMalRollo(MalRollo malRollo){
        //Datos que se incluiran en el malRollo del jugador
        int ocuPerdidos = 0;
        int visPerdidos = 0;
        ArrayList<TipoTesoro> tipoOcuPerdidos = new ArrayList();
        ArrayList<TipoTesoro> tipoVisPerdidos = new ArrayList();
        
        //Datos de los que parte el jugador;
        ArrayList<TipoTesoro> tipoOcuJug = new ArrayList();
        ArrayList<TipoTesoro> tipoVisJug = new ArrayList();
        for (Tesoro t: tesorosOcultos)
            tipoOcuJug.add(t.obtenerTipo());
        for (Tesoro t: tesorosVisibles)
            tipoVisJug.add(t.obtenerTipo());
        
        // CARTAS OCULTAS
        //Caso 1: descartamos cartas cualesquiera
        if(malRollo.obtenerTipoOcultosPerdidos().isEmpty()){ 
            ocuPerdidos = Math.min(tesorosOcultos.size(),malRollo.obtenerOcultosPerdidos());
        }
        else{
            //Caso 2: descartamos uno por cada tipo
            if(malRollo.obtenerTipoOcultosPerdidos().size()==malRollo.obtenerOcultosPerdidos()){
                for (TipoTesoro tipo:malRollo.obtenerTipoOcultosPerdidos())
                    if(tipoOcuJug.contains(tipo)){
                        tipoOcuPerdidos.add(tipo);
                        ocuPerdidos++;
                    }
            }
            //Caso 3: descartamos todos los posibles por cada tipo
            else{    
                for (TipoTesoro tipo:malRollo.obtenerTipoOcultosPerdidos()){                    
                    for(TipoTesoro tipoJugador:tipoOcuJug)
                        if(tipo == tipoJugador && ocuPerdidos<malRollo.obtenerOcultosPerdidos()){
                            if(!tipoOcuPerdidos.contains(tipo)) //lo añadimos solo una vez
                                tipoOcuPerdidos.add(tipo);
                            
                            ocuPerdidos++;
                        }
                }
            }
        }
        
        // CARTAS VISIBLES - Analogo
        if(malRollo.obtenerTipoVisiblesPerdidos().isEmpty()){ 
            visPerdidos = Math.min(tesorosVisibles.size(),malRollo.obtenerVisiblesPerdidos());
        }
        else{
            if(malRollo.obtenerTipoVisiblesPerdidos().size()==malRollo.obtenerVisiblesPerdidos()){
                for (TipoTesoro tipo:malRollo.obtenerTipoVisiblesPerdidos())
                    if(tipoVisJug.contains(tipo)){
                        tipoVisPerdidos.add(tipo);
                        visPerdidos++;
                    }
            }
            else{    
                for (TipoTesoro tipo:malRollo.obtenerTipoVisiblesPerdidos()){                    
                    for(TipoTesoro tipoJugador:tipoVisPerdidos)
                        if(tipo == tipoJugador && visPerdidos<malRollo.obtenerVisiblesPerdidos()){
                            if(!tipoVisPerdidos.contains(tipo)) 
                                tipoVisPerdidos.add(tipo);
                            
                            visPerdidos++;
                        }
                }
            }
        }
        
        malRolloPendiente = new MalRollo("MalRollo pendiente",malRollo.obtenerNivelesPerdidos(),
                ocuPerdidos,visPerdidos,malRollo.muerte(),tipoOcuPerdidos,tipoVisPerdidos);
        
        
        /*
        // * ESTA MANERA ME SIGUE PARECIENDO BUENA, NO SÉ POR QUÉ FALLA, Y ME PARECE MÁS GENERAL *
        
        int numVis = Math.min(malRollo.obtenerVisiblesPerdidos(),tesorosVisibles.size());
        int numOcu = Math.min(malRollo.obtenerOcultosPerdidos(),tesorosOcultos.size());
        
        ArrayList<TipoTesoro> tipoOcuJug = new ArrayList();
        ArrayList<TipoTesoro> tipoVisJug = new ArrayList();
  
        // Montamos un array de cada uno de los tipos disponibles
        
        for (Tesoro t: tesorosOcultos)
            tipoOcuJug.add(t.obtenerTipo());
        for (Tesoro t: tesorosVisibles)
            tipoVisJug.add(t.obtenerTipo());
        
        // Quito los repetidos usando un Hashset
        
        HashSet aux = new HashSet();
        aux.addAll(tipoOcuJug);
        tipoOcuJug.clear();
        tipoOcuJug.addAll(aux);
        
        aux.clear();
        aux.addAll(tipoVisJug);
        tipoVisJug.clear();
        tipoVisJug.addAll(aux);
        aux.clear();
        
        // Intersecamos esos arrays con los del malRollo
        
        ArrayList<TipoTesoro> tipoOcuMalRollo = new ArrayList(malRollo.obtenerTipoOcultosPerdidos());
        ArrayList<TipoTesoro> tipoVisMalRollo = new ArrayList(malRollo.obtenerTipoVisiblesPerdidos());
        
        ArrayList<TipoTesoro> tipoOcu = new ArrayList();
        ArrayList<TipoTesoro> tipoVis = new ArrayList();
        
        // Sólo intentamos intersecar si no es vacío
        if (!tipoOcuMalRollo.isEmpty())
        {
            for (TipoTesoro t: tipoOcuJug)
                if (tipoOcuMalRollo.contains(t))
                    tipoOcu.add(t);
            
            if (tipoOcu.size() < tipoOcuMalRollo.size() && // El jugador sólo pierde de los que puede
                    tipoOcuMalRollo.size() != TESOROS_OCULTOS_MAXIMO) numOcu = tipoOcu.size();
        }
          
        if (!tipoVisMalRollo.isEmpty())
        {
            for (TipoTesoro t: tipoVisJug)
                if (tipoVisMalRollo.contains(t))
                    tipoVis.add(t);
            
            if (tipoVis.size() < tipoVisMalRollo.size() && 
                    tipoVisMalRollo.size() != 6) numVis = tipoVis.size(); // El jugador sólo pierde de los que puede
        }

        // Montamos un malRolloPendiente
        
        malRolloPendiente = new MalRollo(malRollo.obtenerTexto(),malRollo.obtenerNivelesPerdidos(),
                numOcu,numVis,malRollo.muerte(),tipoOcu,tipoVis);
        
        // MalRolloPendiente se queda con lo que SE PUEDE QUITAR EL JUGADOR, NO CON LO QUE SE QUEDA PENDIENTE
        */

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
        
        return malRolloPendiente.esVacio();
    }
    
    // Metodos auxiliares para la prueba del main
    public ArrayList<Tesoro> obtenerTesorosOcultos() {
        return tesorosOcultos;
    }
    public ArrayList<Tesoro> obtenerTesorosVisibles() {
        return tesorosVisibles;
    }
    
}
    

    
