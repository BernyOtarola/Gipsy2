/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Puertas;

/**
 *
 * @author USER
 */

public class Puertas {
  
public  EstadoPuerta puerta1 = EstadoPuerta.ABIERTA;
public  EstadoPuerta puerta2 = EstadoPuerta.CERRADA;
public  EstadoPuerta puerta3 = EstadoPuerta.CERRADA;
public  EstadoPuerta puerta4 = EstadoPuerta.CERRADA;


    public boolean abrirPuerta() {
        return puerta1 == EstadoPuerta.ABIERTA||
        puerta2 == EstadoPuerta.ABIERTA || 
        puerta3 == EstadoPuerta.ABIERTA || 
        puerta4 == EstadoPuerta.ABIERTA;
        }
    

    public void cerrarPuerta() {
       puerta1 = EstadoPuerta.CERRADA;
       puerta2 = EstadoPuerta.CERRADA;
       puerta3 = EstadoPuerta.CERRADA;
       puerta4 = EstadoPuerta.CERRADA;
}

    public String  mostrarPuertasAbiertas() {
        String Estado = "Puertas abiertas: ";
        boolean Abiertas = false;
    
        if(puerta1 == EstadoPuerta.ABIERTA){
            Estado += "Puerta1";
            Abiertas = true;
        }
        if (puerta2 == EstadoPuerta.ABIERTA){
            Estado += "Puerta2";
            Abiertas = true;
        }
        if (puerta3 == EstadoPuerta.ABIERTA){
            Estado += "Puerta3";
            Abiertas = true;
    }
        if (puerta4 == EstadoPuerta.ABIERTA){
            Estado += "Puerta1";
            Abiertas = true;
    }
        if (!Abiertas){
            Estado = "Todas las puertas estan cerradas";
        }
        return Estado;
    }
}