/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package napakalaki;

/**
 *
 * @author hhrr
 */
public class Monstruo {
    private String nombre;
    private int nivel;
    
    // Referencias a otras clases
    private MalRollo malRollo;
    private BuenRollo buenRollo;

    public Monstruo(String nombre, int nivel, MalRollo malRollo, BuenRollo buenRollo) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.malRollo = malRollo;
        this.buenRollo = buenRollo;
    }

    public String obtenerNombre() {
        return nombre;
    }

    public int obtenerNivel() {
        return nivel;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    
    public BuenRollo cualEsTuBuenRollo() {
        // queda implementar
        return null;
    }
    
    public MalRollo cualEsTuMalRollo() {
        // queda implementar
        return null;
    }
    
    
    
}
