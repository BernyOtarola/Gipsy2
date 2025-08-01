/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Climatizacion;

/**
 *
 * @author AsusVivobook
 */
public enum velocidadVentilador {
    APAGADO("Apagado"),
    BAJO("Bajo"),
    MEDIO("Medio"),
    ALTO("Alto");   
    private final String Estado;

    private velocidadVentilador(String Estado) {
        this.Estado = Estado;
    }

    public String getEstado() {
        return Estado;
    }
    
}
