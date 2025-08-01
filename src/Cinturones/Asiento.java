/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Cinturones;

/**
 *
 * @author gipsy
 */
public enum Asiento {
    CONDUCTOR("Conductor"),
    COPILOTO("Copiloto"),
    TRASEROIZQUIERDDO("Trasero Izquierdo"),
    TRASEROCENTRAL("Trasero Central"),
    TRASERODERECHO("Trasero Derecho");
    
    private final String Estado;

    private Asiento(String Estado) {
        this.Estado = Estado;
    }

    public String getEstado() {
        return Estado;
    }
    
    
    
    
}
