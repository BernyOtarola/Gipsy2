/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package parabrisas;

/**
 *
 * @author USER
 */
public enum Velocidad {
APAGADO(0,"Apagado"),
    LENTO(1,"Lento"),
    MEDIO(2,"Medio"),
    RAPIDO(3,"Rápido");
    private final int Nivel;
    private final String Estado;

        private Velocidad(int Nivel, String Estado) {
            this.Nivel = Nivel;
            this.Estado = Estado;
        }

    public int getNivel() {
        return Nivel;
    }

    public String getEstado() {
        return Estado;
    }

}