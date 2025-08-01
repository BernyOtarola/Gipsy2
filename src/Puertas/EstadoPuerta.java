/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Puertas;

/**
 *
 * @author gipsy
 */
public enum EstadoPuerta {
    ABIERTA("Abierta"),
    CERRADA("Cerrada");
    
    private final String Estado;

    private EstadoPuerta(String Estado) {
        this.Estado = Estado;
    }

    public String getEstado() {
        return Estado;
    }
    
}
