/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package napakalaki;

import java.util.ArrayList;

/**
 *
 * @author hhrr
 */
class MalRollo {
    private String texto;
    private int nivelesPerdidos;
    private int ocultosPerdidos;
    private int visiblesPerdidos;
    private boolean muerte;
    
    // Referencias a otras clases
    private ArrayList<TipoTesoro> tipoOcultosPerdidos;
    private ArrayList<TipoTesoro> tipoVisiblesPerdidos;
    
    public boolean muerte() {
        // queda por implementar
        return true;
    }
    
}
