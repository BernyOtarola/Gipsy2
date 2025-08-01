/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Alarma;

/**
 *
 * @author gipsy
 */
public enum Seguridad {
    NORMAL("Modo libre"),
    NOCHE("Modo nocturno"),
    VIAJE("MODO DE SEGURIDAD EN MOVIMIENTO");
    private final String Estado;

    private Seguridad(String Estado) {
        this.Estado = Estado;
    }

    public String getEstado() {
        return Estado;
    }
    
    
}
