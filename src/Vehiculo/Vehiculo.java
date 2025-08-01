/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vehiculo;

import Motor.Motor;
import Luces.Luces;
import Puertas.Puertas;
import Cinturones.Cinturones;
import Alarma.Alarma;
import parabrisas.LimpiaParabrisas;
import Radio.Radio;
import Climatizacion.climatizacion;
import combustible.combustible;
import Sensores.SensorReversa;
import Kilometraje.kilometraje;
import combustible.EstadoDelTanque;
import combustible.combustible;

/**
 *
 * @author Student
 */
public class Vehiculo {
    private final Motor motor;
    private final Luces luces;
    private final Puertas puertas;
    private final Cinturones cinturones;
    private final Alarma alarma;
    private final LimpiaParabrisas parabrisas;
    private final Radio radio;
    private final climatizacion climatizacion;
    private final SensorReversa sensores;
    private final kilometraje kilometraje;
    private final combustible combustible;
    private double nivelCombustible;

    public Vehiculo() {
        this.motor = new Motor();
        this.luces = new Luces();
        this.puertas = new Puertas();
        this.cinturones = new Cinturones();
        this.alarma = new Alarma();
        this.parabrisas = new LimpiaParabrisas();
        this.radio = new Radio();
        this.climatizacion = new climatizacion();
        this.sensores = new SensorReversa();
        this.kilometraje = new kilometraje();
        this.combustible = new combustible(50, 15, 0.1);
    }

    public String encender() {
        if (combustible.getEstado() == EstadoDelTanque.VACIO) {
            return "El tanque está vacío, no se puede encender el motor";
        }

        motor.encender();
        alarma.desactivarAlarma();

        return "Vehículo encendido.";
    }

    public String apagar() {
        motor.apagar();
        alarma.activarAlarma();
        return "Vehículo apagado.";
    }

    public boolean estaEncendido() {
        return motor.motorEncendido();
    }
    
    public String moverVehiculo(int segundos) {
        StringBuilder resultado = new StringBuilder();

        if (!motor.motorEncendido()) {
            return "El motor se encuentra apagado.";
        }

        if (combustible.getNivel() <= 0) {
            motor.apagar();
            return "Combustible agotado, el motor se apagará.";
        }
        
        String puertasAbiertas = puertas.mostrarPuertasAbiertas();
            if (!puertasAbiertas.contains("cerradas")){
            resultado.append(puertasAbiertas).append("Debe cerrar las puertas");
       }
        
            if (!cinturones.todosAbrochados()){
            resultado.append("Los cinturones no han sido abrochados.");
       }    
                
                
        kilometraje.recorer(segundos);
        double kmRecorridos = kilometraje.getTotalKm();
        combustible.consumirKm(kmRecorridos);

        if (combustible.getNivel() <= 0) {
            motor.apagar();
            resultado.append("Combustible agotado durante el recorrido,El motor se apagó.");
        }

        resultado.append("Vehículo en movimiento ")
        .append("(Velocidad: ").append(kilometraje.getVelocidad()).append(" km/h, ")
        .append("Recorrido: ").append(String.format("%.2f", kilometraje.getTotalKm())).append(" km, ")
        .append("Combustible: ").append(String.format("%.2f", combustible.getNivel())).append(" L)");

        return resultado.toString();
    }

    public String cargarCombustible(double litros) {
        combustible.reabastecer(litros);
        return "Combustible repostado, cantidad actual de combustible: " + combustible.getNivel() + " L";
    }

    public String getEstadoCombustible() {
        return combustible.getEstado().getDescripcionTanque();
    }

    public Luces getLuces() {
        return luces; 
    }
    
    public Puertas getPuertas() { 
        return puertas; 
    }
    
    public Cinturones getCinturones() { 
        return cinturones; 
    }
    
    public Alarma getAlarma() { 
        return alarma; 
    }
    
    public LimpiaParabrisas getParabrisas() { 
        return parabrisas; 
    }
    
    public Radio getRadio() { 
        return radio; 
    }
    
    public climatizacion getClimatizacion() {
        return climatizacion; 
    }
    
    public SensorReversa getSensores() { 
        return sensores; 
    }
    
    public kilometraje getKilometraje() { 
        return kilometraje; 
    }
    
    public combustible getCombustible() { 
        return combustible; 
    }
    
}