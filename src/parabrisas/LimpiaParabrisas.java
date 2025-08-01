/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parabrisas;

/**
 *
 * @author USER
 */
public class LimpiaParabrisas {
  private Velocidad velocidadActual;

public LimpiaParabrisas() {
        this.velocidadActual = Velocidad.APAGADO;
    }

    public void cambiarVelocidad(Velocidad nuevaVelocidad) {
        this.velocidadActual = nuevaVelocidad;
    }

    public Velocidad getVelocidadActual() {
        return velocidadActual;
    }

    public boolean estaActivo() {
        return velocidadActual != Velocidad.APAGADO;
    }
     public String Estado() {
        return "Velocidad: " + velocidadActual.name() +
               " (" + velocidadActual.getEstado() + ")" +
               " | Estado: " + (estaActivo() ? "Activado" : "Apagado");
    }
}

