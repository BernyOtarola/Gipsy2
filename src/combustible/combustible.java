/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package combustible;

/**
 *
 * @author gipsy
 */
public class combustible {
    private double Nivel;
    private final double capacidad;
    private final double consumoPorKm;
    private final double reservaLitros;
    private double ConsumoTotal;

    public double getNivel() {
        return Nivel;
    }

    public double getCapacidad() {
        return capacidad;
    }

    public double getConsumoTotal() {
        return ConsumoTotal;
    }
    

 
    public void consumirlitros(double Litros) {
        if (Litros <= 0 || Nivel ==0) return;
        double usados = Math.min(Litros, Nivel);
        Nivel -= usados;
        ConsumoTotal += usados;
    }
    public void consumirKm(double Km){
        if(Km <= 0) return;
        consumirlitros(Km * consumoPorKm);
    }
    public void reabastecer(double Litros) {
       if(Litros <=0)return;
       Nivel = Math.min(Nivel + Litros, capacidad);
       if(Nivel == capacidad) ConsumoTotal = 0.0;
        }
    
    
    
   
    public EstadoDelTanque getEstado() {
     double porcentaje = (capacidad == 0) ? 0.0 : (Nivel / capacidad)* 100.0;
     return EstadoDelTanque.desdeNivel(porcentaje);

}

    public combustible(double capacidad, double kmPorLitro, double reservaFrac) {
        this.capacidad     = Math.max(0, capacidad);
        this.consumoPorKm  = 1.0 / Math.max(0.0001, kmPorLitro);
        this.reservaLitros = this.capacidad * Math.max(0.0, Math.min(reservaFrac, 1.0));
        this.Nivel         = this.capacidad;
        this.ConsumoTotal  = 0.0;

   
    }
}
