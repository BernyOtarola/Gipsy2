/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Kilometraje;

/**
 *
 * @author AsusVivobook
 */
public class kilometraje {
    private double totalKm;
    private int velocidad;
    private int rpm;
    private int metros;
  
    public kilometraje(){
    totalKm = 0.0;
    velocidad = 0;
    rpm = 0;
    metros = 0;
    }
  
    public void actualizarVelocidad(int nuevaVelocidad){
    velocidad = nuevaVelocidad;
    rpm = calcularRPM(velocidad);
    }

    public void recorer(int segundos){
        if (segundos<=0 || velocidad<=0) return;
        double horas = segundos/3600.0;
        double kmRecorridos = velocidad*horas;
        double metrosRecorridos = kmRecorridos*1000;
        
        metros+=(int) metrosRecorridos;
        totalKm+= metrosRecorridos/1000.0;
    }

    private int calcularRPM(int velocidad){
    if (velocidad == 0) return 0;
    int marcha = (velocidad / 20) + 1;
     return (velocidad * 90) + (marcha * 120);
    }

    public double getTotalKm() {
        return totalKm;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public int getRpm() {
        return rpm;
    }
    
    public int getMetros() {
        return metros;
    }

}
