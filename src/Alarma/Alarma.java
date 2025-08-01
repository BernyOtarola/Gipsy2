/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Alarma;

/**
 *
 * @author USER
 */
public class Alarma {

    private boolean Alarma;
    private boolean bloqueoPuertas;
    private Seguridad ModoActual;

    public Alarma() {
        setSeguridad(Seguridad.NORMAL);
    }

    public void activar() {
        System.out.println("Alarma activada.");
    }

    public void desactivar() {
        System.out.println("Alarma desactivada.");
    }

    public void activarAlarma() {
        Alarma = true;
    }

    public void desactivarAlarma() {
        Alarma = false;
    }

    public void bloquearPuertas() {
        bloqueoPuertas = true;
    }

    public void desbloquearPuertas() {
        bloqueoPuertas = false;
    }

    public boolean isAlarmaActiva() {
        return Alarma;
    }

    public boolean isPuertasBloqueadas() {
        return bloqueoPuertas;
    }

    public void setSeguridad(Seguridad ModoActual) {
        this.ModoActual = ModoActual;

        switch (ModoActual) {
            case NORMAL:
                desactivarAlarma();
                desbloquearPuertas();
                break;
            case NOCHE:
            case VIAJE:
                activarAlarma();
                bloquearPuertas();
                break;
        }
    }

    public String Estado() {
        return "Modo: " + ModoActual.name()
                + " | " + ModoActual.getEstado()
                + " | Alarma: " + (Alarma ? "Activada" : "Desactivada")
                + " | Puertas: " + (bloqueoPuertas ? "Bloqueadas" : "Desbloqueadas");
    }
}
