/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Luces;

/**
 *
 * @author USER
 */
public enum TipoLuz {
   APAGADAS("Apagadas"),
    BAJAS("Luces bajas"),
    ALTAS("Luces Altas");
    
private final String estado;

public String getEstado() {
        return estado;
    }

private TipoLuz(String Estado) {
        this.estado = Estado;
    }
   
}
