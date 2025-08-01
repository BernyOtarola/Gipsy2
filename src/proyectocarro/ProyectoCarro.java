package proyectocarro;

import vehiculoInterfaz.CarroSimulador;
import javax.swing.SwingUtilities;

/**
 * Clase principal del proyecto Simulador de Carro
 * @author USER
 */
public class ProyectoCarro {

    /**
     * Método principal que inicia la aplicación
     * @param args argumentos de línea de comandos
     */
    public static void main(String[] args) {
        System.out.println("=== SIMULADOR DE CARRO ===");
        System.out.println("Iniciando interfaz gráfica...");
        
        // Ejecutar la interfaz gráfica en el Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            try {
                // Configurar Look and Feel
                for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("Usando Look and Feel por defecto: " + e.getMessage());
            }
            
            // Crear y mostrar la ventana principal
            CarroSimulador simulador = new CarroSimulador();
            simulador.setVisible(true);
            
            System.out.println("Simulador iniciado correctamente.");
        });
    }
}