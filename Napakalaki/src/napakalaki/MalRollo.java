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
    
    public boolean muerte() {
        // queda por implementar
        return true;
    }
    
}
