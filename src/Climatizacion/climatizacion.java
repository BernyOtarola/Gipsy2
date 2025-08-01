/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Climatizacion;

import java.util.Locale;

/**
 *
 * @author AsusVivobook
 */
public class climatizacion {
    private boolean aireAcondicionado;
    private boolean calefaccion;
    private int temperatura;
    private velocidadVentilador velocidadVentilador;

    public climatizacion() {
        this.aireAcondicionado = false;
        this.calefaccion = false;
        this.temperatura = 20;
        this.velocidadVentilador = velocidadVentilador.APAGADO;
    }
    
    public void encenderAireAcondicinado(){
            aireAcondicionado = true;
            calefaccion = false;
    }
    
    public void encenderCalefaccion(){
            calefaccion = true;
            aireAcondicionado = false;
    }
    
    public void apagarClimatizacion() {
         aireAcondicionado = false;
         calefaccion = false;
         velocidadVentilador = velocidadVentilador.APAGADO;
    }
    
    public void subirTemperatura() {
        if (temperatura < 30){
            temperatura++;
        }
    }
    
    public void bajarTemperatura(){
        if (temperatura > 16){
            temperatura--;
        }       
    }

    public void setVelocidadVentilador(velocidadVentilador velocidadVentilador) {
        this.velocidadVentilador = velocidadVentilador;
    }

    public boolean isAireAcondicionado() {
        return aireAcondicionado;
    }

    public boolean isCalefaccion() {
        return calefaccion;
    }

    public int getTemperatura() {
        return temperatura;
    }

    public velocidadVentilador getVelocidadVentilador() {
        return velocidadVentilador;
    }
}
