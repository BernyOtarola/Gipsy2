/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Motor;

/**
 *
 * @author USER
 */

public class Motor {
    private boolean motor;

    public void  sistemaEncendido() {
        this.motor = false;
    }

    public void encender() {
        motor = true;
    }

    public void apagar() {
        motor = false;
    }

    public boolean motorEncendido() {
        return motor;
    }
}
