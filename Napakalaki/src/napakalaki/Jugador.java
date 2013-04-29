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
        if (nivel < 1) nivel = 1;
    }
    
    public ResultadoCombate combatir(Monstruo monstruoEnJuego){
        ResultadoCombate resultado;
        // Agregado nivel de combate (si no no gana nadie nunca XD)
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
        
    
    
    // ----------------------- INCOMPLETO -------------
    
    // Ajusta el malRollo al jugador (le quitamos aquello no pueda descartar)
    public void incluirMalRollo(MalRollo malRollo){
        
        
        /*
        int ocuPerdidos = Math.min(tesorosOcultos.size(), malRollo.obtenerOcultosPerdidos());
        int visPerdidos = Math.min(tesorosVisibles.size(), malRollo.obtenerVisiblesPerdidos());
        
        ArrayList<TipoTesoro> tipoOcuJug = new ArrayList();
        ArrayList<TipoTesoro> tipoVisJug = new ArrayList();
        
        // Caso con un tipo perdido (oculto o visible)
        if (malRollo.obtenerTipoOcultosPerdidos().size() == 1 || malRollo.obtenerTipoVisiblesPerdidos().size() == 1)
        {
            
            
            // Comprobamos si ha perdido tipos ocultos
            if (!malRollo.obtenerTipoOcultosPerdidos().isEmpty())
            {
                // Si tiene el que corresponde con el mal rollo, lo agregamos
                for (Tesoro t: tesorosOcultos)
                    if (t.obtenerTipo() == malRollo.obtenerTipoOcultosPerdidos().get(0))
                        tipoOcuJug.add(t.obtenerTipo());
            }
            
            // Ídem para los visibles 
            if (!malRollo.obtenerTipoVisiblesPerdidos().isEmpty())
            {
                // Si tiene el que corresponde con el mal rollo, lo agregamos
                for (Tesoro t: tesorosVisibles)
                    if (t.obtenerTipo() == malRollo.obtenerTipoVisiblesPerdidos().get(0))
                    {
                        tipoVisJug.add(t.obtenerTipo());
                        break;
                    }
            }
        }
        
        // Caso de 2 tipos visibles perdidos (Bicéfalo)
        else if (malRollo.obtenerTipoVisiblesPerdidos().size() == 2)
        {
            // Comprobamos si tiene tesoro visible de una o dos manos
            
            for (Tesoro t: tesorosVisibles)
                if (t.obtenerTipo() == TipoTesoro.MANO || t.obtenerTipo() == TipoTesoro.DOSMANOS)
                    tipoVisJug.add(t.obtenerTipo());
            
        }
        
        malRolloPendiente = new MalRollo (malRollo.obtenerTexto(), malRollo.obtenerNivelesPerdidos(),
                                ocuPerdidos,visPerdidos,malRollo.muerte(),tipoOcuJug,tipoVisJug);
        
        */
        
        
        
        
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

    }
    
    
    // Actualiza el malRolloPendiente y devuelve su estado
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
    

    
