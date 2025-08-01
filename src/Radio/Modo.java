/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Radio;

/**
 *
 * @author gipsy
 */
public enum Modo {
  AM(530, "Modo AM"),
  FM(88,  "Modo FM"),
  BLUETOOTH(-1, "Bluetooth conectado");
    private final int Frecuencia;
    private final String Estado;

    private Modo(int Frecuencia, String Estado) {
        this.Frecuencia = Frecuencia;
        this.Estado = Estado;
    }

    public int getFrecuencia() {
        return Frecuencia;
    }

    public String getEstado() {
        return Estado;
    }

    
    
}
