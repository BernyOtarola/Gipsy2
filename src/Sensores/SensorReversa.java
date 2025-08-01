/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sensores;

/**
 *
 * @author USER
 */
public class SensorReversa {

 private boolean frenoDeManoActivado;
    private boolean enReversa;
    private boolean obstaculoDetectado;
    private boolean freno; 
    private boolean alarmaActiva;
    private String mensajeAlarma;
    private String ultimoMensaje;

    public SensorReversa() {
        this.frenoDeManoActivado = false;
        this.enReversa = false;
        this.obstaculoDetectado = false;
        this.freno = false;
        this.alarmaActiva = false;
        this.mensajeAlarma = "Condiciones normales.";
        this.ultimoMensaje = "";
    }

    public void activarFrenoDeMano() {
        frenoDeManoActivado = true;
        ultimoMensaje = "Freno de mano activado.";
        verificarAlarma();
    }

    public void liberarFrenoDeMano() {
        frenoDeManoActivado = false;
        ultimoMensaje = "Freno de mano liberado.";
        verificarAlarma();
    }

    public void iniciarReversa() {
        enReversa = true;
        ultimoMensaje = "Vehículo en reversa.";
        verificarAlarma();
    }

    public void detenerReversa() {
        enReversa = false;
        ultimoMensaje = "Vehículo detenido.";
        verificarAlarma();
    }

    public void detectarObstaculo(boolean estado) {
        obstaculoDetectado = estado;
        ultimoMensaje = estado ? "Obstáculo detectado detrás del vehículo." : "Sin obstáculos en reversa.";
        verificarAlarma();
    }

    public void aplicarFreno() {
        freno = true;
        ultimoMensaje = "Freno aplicado.";
        verificarAlarma();
    }

    public void liberarFreno() {
        freno = false;
        ultimoMensaje = "Freno liberado.";
        verificarAlarma();
    }

    private void verificarAlarma() {
        if (enReversa && frenoDeManoActivado) {
            emitirAlarma("¡Alerta! Reversa activada con el freno de mano puesto.");
        } else if (enReversa && obstaculoDetectado) {
            emitirAlarma("¡Alerta! Obstáculo detectado al retroceder.");
        } else {
            apagarAlarma();
        }
    }

    private void emitirAlarma(String mensaje) {
        alarmaActiva = true;
        mensajeAlarma = mensaje;
    }

    private void apagarAlarma() {
        alarmaActiva = false;
        mensajeAlarma = "Condiciones normales.";
    }

    public boolean isAlarmaActiva() {
        return alarmaActiva;
    }

    public String getMensajeAlarma() {
        return mensajeAlarma;
    }

    public boolean isFrenoDeManoActivado() {
        return frenoDeManoActivado;
    }

    public boolean isEnReversa() {
        return enReversa;
    }

    public boolean isFreno() {
        return freno;
    }

    public void setFreno(boolean freno) {
        this.freno = freno;
        ultimoMensaje = freno ? "Freno aplicado (setFreno)." : "Freno liberado (setFreno).";
        verificarAlarma();
    }

    public String getUltimoMensaje() {
        return ultimoMensaje;
    }
}
