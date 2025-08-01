/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Radio;

/**
 *
 * @author gipsy
 */
public class Radio {
    private boolean Encendida;
    private Modo modoActual;
    private double Frecuencia;
    
    public void Encendida () {
    Encendida = true;
   }
    public void apagar () {
        Encendida = false;
    }

    public boolean isEncendida() {
        return Encendida;
    }

    public Modo getModoActual() {
        return modoActual;
    }

    public double getFrecuencia() {
        return Frecuencia;
    }
    public void cambiarModo(Modo nuevoModo) {
        if(Encendida && nuevoModo != modoActual) {
            modoActual = nuevoModo;
            Frecuencia =0;
        }
    }
    public void subirEstacion() {
        if(!Encendida || modoActual == Modo.BLUETOOTH) return;
        
        if(modoActual == Modo.AM) {
            Frecuencia += 10;
        } else if (modoActual == Modo.FM) {
            Frecuencia += 0.2;
        }
    }
    public void bajarEstacion() {
        if(!Encendida || modoActual == Modo.BLUETOOTH) return;
        
        if(modoActual == Modo.AM) {
            Frecuencia -= 10;
        } else if (modoActual == Modo.FM) {
            Frecuencia -= 0.2;
        }
    }
    public String Estado() {
    if (!Encendida) {
        return "Radio apagada";
    }
    return "Modo: " + modoActual.name() +
           " | Frecuencia: " + (modoActual == Modo.BLUETOOTH ? "E/A" : Frecuencia) +
           " | Estado: Encendida";
}
    }