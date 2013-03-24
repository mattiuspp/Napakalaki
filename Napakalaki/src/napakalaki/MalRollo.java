package napakalaki;

import java.util.ArrayList;

/**
 * CLASE LISTA!
 * @author Antonio Álvarez y Adrián Ranea
 */
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
            ArrayList<TipoTesoro> tipoVisiblesPerdidos) {
        this.texto = texto;
        this.nivelesPerdidos = nivelesPerdidos;
        this.ocultosPerdidos = ocultosPerdidos;
        this.visiblesPerdidos = visiblesPerdidos;
        this.muerte = muerte;
        this.tipoOcultosPerdidos.addAll(tipoOcultosPerdidos);
        this.tipoVisiblesPerdidos.addAll(tipoVisiblesPerdidos);
    }
    
    public boolean muerte() {
        // queda por implementar
        return true;
    }
    
}
