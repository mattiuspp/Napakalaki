package napakalaki;

import java.util.ArrayList;

class MalRollo {
    private String texto;
    private int nivelesPerdidos;
    private int ocultosPerdidos;
    private int visiblesPerdidos;
    private boolean muerte;
    
    private ArrayList<TipoTesoro> tipoOcultosPerdidos = new ArrayList();
    private ArrayList<TipoTesoro> tipoVisiblesPerdidos = new ArrayList();

    public MalRollo(String texto, int nivelesPerdidos, int ocultosPerdidos, 
            int visiblesPerdidos, boolean muerte,
            ArrayList<TipoTesoro> tipoOcultosPerdidos,
            ArrayList<TipoTesoro> tipoVisiblesPerdidos)
    {
        this.texto = texto;
        this.nivelesPerdidos = nivelesPerdidos;
        this.ocultosPerdidos = ocultosPerdidos;
        this.visiblesPerdidos = visiblesPerdidos;
        this.muerte = muerte;
        this.tipoOcultosPerdidos.addAll(tipoOcultosPerdidos);
        this.tipoVisiblesPerdidos.addAll(tipoVisiblesPerdidos);
    }
    
    public MalRollo (MalRollo malRollo)
    {
        this.texto = malRollo.texto;
        this.nivelesPerdidos = malRollo.nivelesPerdidos;
        this.ocultosPerdidos = malRollo.ocultosPerdidos;
        this.visiblesPerdidos = malRollo.visiblesPerdidos;
        this.muerte = malRollo.muerte;
        this.tipoOcultosPerdidos = new ArrayList(malRollo.tipoOcultosPerdidos);
        this.tipoVisiblesPerdidos = new ArrayList(malRollo.tipoVisiblesPerdidos);
    }
    
    @Override
    public String toString() {
        String f = new String();
        f+= texto + "\nNiveles perdidos = " + nivelesPerdidos + "\nTesoros ocultos perdidos = " + ocultosPerdidos +
                "\nTipo de tesoros ocultos perdidos = ";
        for (TipoTesoro t: tipoOcultosPerdidos)
            f+= t.toString() + " ";
        f+= "\nTesoros visibles perdidos = " + visiblesPerdidos + "\nTipo de tesoros visibles perdidos = ";
        for (TipoTesoro t: tipoVisiblesPerdidos)
            f+= t.toString() + " ";
        f+= muerte ? "\nMueres" : "\nNo mueres";
        
        return f;
    }
    
    public String obtenerTexto() {
        return texto;
    }
    
    public boolean muerte() {
        return muerte;
    }
    
    public int obtenerNivelesPerdidos() {
        return nivelesPerdidos;
    }

    public ArrayList<TipoTesoro> obtenerTipoOcultosPerdidos() {
        return tipoOcultosPerdidos;
    }

    public ArrayList<TipoTesoro> obtenerTipoVisiblesPerdidos() {
        return tipoVisiblesPerdidos;
    }
    
    public int obtenerVisiblesPerdidos(){
        return visiblesPerdidos;
    }
    
    public int obtenerOcultosPerdidos(){
        return ocultosPerdidos;
    }
    
    public boolean esVacio(){
        return ocultosPerdidos==0 && visiblesPerdidos==0;
    }
}
