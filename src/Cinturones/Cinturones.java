/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Cinturones;
import Cinturones.Asiento;
/**
 *
 * @author USER
 */


public class Cinturones {
  private final boolean[] Abrochados;

    public Cinturones() {
        this.Abrochados = new boolean[Asiento.values().length];
        
    }
    public void Abrochar(Asiento asiento) {
        Abrochados[asiento.ordinal()] = true;
    }
    public void Desabrochar(Asiento asiento) {
        Abrochados[asiento.ordinal()] = false;
        
    }
    public boolean Colocado(Asiento asiento) {
        return Abrochados[asiento.ordinal()];
    }
    public boolean todosAbrochados() {
        for (boolean A : Abrochados){
            if (!A) return false;
        }
        return true;
    }
    public String[] ObtenerEstado(){
        Asiento[] asiento = Asiento.values();
        String[] Estado = new String[asiento.length];
          for (int i = 0; i < asiento.length; i++) {
          Estado[i] = asiento[i].getEstado() + ": " + (Abrochados[i] ? "Colocado" : "No colocado");
          
}
    return Estado;

}
}

  