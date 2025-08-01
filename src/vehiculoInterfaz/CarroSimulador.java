package vehiculoInterfaz;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Arc2D;
import java.awt.geom.Rectangle2D;
import java.util.Timer;
import java.util.TimerTask;

// Importar todas las clases necesarias
import Vehiculo.Vehiculo;
import Alarma.Seguridad;
import Cinturones.Asiento;
import Luces.TipoLuz;
import Radio.Modo;
import Climatizacion.velocidadVentilador;
import parabrisas.Velocidad;
import Puertas.EstadoPuerta;

public class CarroSimulador extends JFrame {
    private Vehiculo vehiculo;
    private Timer simulationTimer;
    
    // Colores del tema del carro más realistas
    private static final Color DASHBOARD_BLACK = new Color(12, 12, 12);
    private static final Color DASHBOARD_GRAY = new Color(35, 35, 35);
    private static final Color DASHBOARD_SILVER = new Color(85, 85, 85);
    private static final Color LED_GREEN = new Color(0, 255, 100);
    private static final Color LED_RED = new Color(255, 50, 50);
    private static final Color LED_BLUE = new Color(100, 200, 255);
    private static final Color LED_ORANGE = new Color(255, 140, 0);
    private static final Color LED_YELLOW = new Color(255, 255, 100);
    private static final Color BUTTON_GRAY = new Color(55, 55, 60);
    private static final Color ACCENT_BLUE = new Color(0, 120, 215);
    private static final Color LCD_GREEN = new Color(50, 255, 50);
    private static final Color WARNING_AMBER = new Color(255, 191, 0);
    
    // Componentes del tablero
    private VelocimetroPanel velocimetroPanel;
    private CombustiblePanel combustiblePanel;
    private JLabel rpmLabel;
    private JLabel gearLabel;
    private JLabel odometerLabel;
    private JLabel tripLabel;
    
    // Luces indicadoras
    private JLabel motorLED;
    private JLabel fuelLED;
    private JLabel beltLED;
    private JLabel doorLED;
    private JLabel alarmLED;
    
    // Controles principales
    private JButton ignitionBtn;
    private JSlider acceleratorPedal;
    private JSlider brakePedal;
    
    // Panel de luces del carro
    private JToggleButton headlightsBtn;
    private JToggleButton highBeamBtn;
    private JToggleButton leftTurnBtn;
    private JToggleButton rightTurnBtn;
    private JToggleButton hazardsBtn;
    
    // Panel del radio más realista
    private JToggleButton radioOnBtn;
    private JLabel radioDisplay;
    private JButton[] radioPresets;
    private JSlider volumeSlider;
    private JButton seekUpBtn;
    private JButton seekDownBtn;
    
    // Panel de climatización realista
    private JSlider tempSlider;
    private JToggleButton acBtn;
    private JToggleButton heatBtn;
    private JSlider fanSpeedSlider;
    private JToggleButton recirculateBtn;
    
    // Controles del vehículo
    private JButton[] doorBtns;
    private JCheckBox[] seatbeltChecks;
    private JComboBox<Seguridad> securityModeCombo;
    
    public CarroSimulador() {
        vehiculo = new Vehiculo();
        initRealisticUI();
        setupEventListeners();
        startSimulation();
        updateDisplay();
    }
    
    private void initRealisticUI() {
        setTitle("🚗 Simulador de Carro - Tablero Realista");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(DASHBOARD_BLACK);
        getContentPane().setBackground(DASHBOARD_BLACK);
        
        // Layout principal
        setLayout(new BorderLayout(10, 10));
        
        // Panel superior - Tablero principal
        JPanel dashboardPanel = createDashboardPanel();
        add(dashboardPanel, BorderLayout.NORTH);
        
        // Panel central - Controles principales
        JPanel centerPanel = createCenterControlPanel();
        add(centerPanel, BorderLayout.CENTER);
        
        // Panel inferior - Controles secundarios
        JPanel bottomPanel = createBottomControlPanel();
        add(bottomPanel, BorderLayout.SOUTH);
        
        // Configuración de ventana
        setSize(1200, 900);
        setLocationRelativeTo(null);
        setResizable(true);
        
        // Aplicar tema oscuro
        applyDarkTheme();
    }
    
    private JPanel createDashboardPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(DASHBOARD_BLACK);
        panel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(DASHBOARD_GRAY, 2),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        // Panel de instrumentos
        JPanel instrumentsPanel = new JPanel(new GridBagLayout());
        instrumentsPanel.setBackground(DASHBOARD_BLACK);
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Velocímetro
        velocimetroPanel = new VelocimetroPanel();
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        instrumentsPanel.add(velocimetroPanel, gbc);
        
        // Medidor de combustible
        combustiblePanel = new CombustiblePanel();
        gbc.gridx = 2; gbc.gridy = 0; gbc.gridwidth = 1;
        instrumentsPanel.add(combustiblePanel, gbc);
        
        // RPM y Gear display
        JPanel rpmGearPanel = createRPMGearPanel();
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 3;
        instrumentsPanel.add(rpmGearPanel, gbc);
        
        panel.add(instrumentsPanel, BorderLayout.CENTER);
        
        // Panel de LEDs indicadores
        JPanel ledPanel = createLEDPanel();
        panel.add(ledPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createDashboardPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(DASHBOARD_BLACK);
        
        // Borde con efecto de cuero acolchado
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createCompoundBorder(
                new LineBorder(DASHBOARD_SILVER, 1),
                BorderFactory.createRaisedBevelBorder()
            ),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        
        // Panel de instrumentos con disposición más realista
        JPanel instrumentsPanel = new JPanel(new GridBagLayout());
        instrumentsPanel.setBackground(DASHBOARD_BLACK);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 15, 10, 15);
        
        // Velocímetro (lado izquierdo como en carros reales)
        velocimetroPanel = new VelocimetroPanel();
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 1;
        instrumentsPanel.add(velocimetroPanel, gbc);
        
        // Panel central con información digital
        JPanel centralInfoPanel = createCentralInfoPanel();
        gbc.gridx = 1; gbc.gridy = 0; gbc.gridwidth = 1;
        instrumentsPanel.add(centralInfoPanel, gbc);
        
        // Medidor de combustible (lado derecho)
        combustiblePanel = new CombustiblePanel();
        gbc.gridx = 2; gbc.gridy = 0; gbc.gridwidth = 1;
        instrumentsPanel.add(combustiblePanel, gbc);
        
        panel.add(instrumentsPanel, BorderLayout.CENTER);
        
        // Panel de LEDs indicadores mejorado
        JPanel ledPanel = createAdvancedLEDPanel();
        panel.add(ledPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createCentralInfoPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(DASHBOARD_BLACK);
        panel.setPreferredSize(new Dimension(250, 200));
        
        // Borde con efecto de pantalla LCD
        panel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(LCD_GREEN, 1),
            BorderFactory.createLoweredBevelBorder()
        ));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        
        // Display principal más sofisticado
        JLabel mainDisplay = new JLabel("VEHICLE STATUS");
        mainDisplay.setFont(new Font("Consolas", Font.BOLD, 16));
        mainDisplay.setForeground(LCD_GREEN);
        mainDisplay.setHorizontalAlignment(SwingConstants.CENTER);
        
        // RPM con diseño mejorado
        JPanel rpmPanel = new JPanel(new BorderLayout());
        rpmPanel.setBackground(Color.BLACK);
        rpmPanel.setBorder(new LineBorder(LCD_GREEN, 1));
        JLabel rpmTitle = new JLabel("RPM", SwingConstants.CENTER);
        rpmTitle.setFont(new Font("Arial", Font.BOLD, 10));
        rpmTitle.setForeground(WARNING_AMBER);
        rpmLabel = new JLabel("0", SwingConstants.CENTER);
        rpmLabel.setFont(new Font("Digital-7", Font.BOLD, 20));
        rpmLabel.setForeground(LCD_GREEN);
        rpmPanel.add(rpmTitle, BorderLayout.NORTH);
        rpmPanel.add(rpmLabel, BorderLayout.CENTER);
        
        // Gear con diseño premium
        JPanel gearPanel = new JPanel(new BorderLayout());
        gearPanel.setBackground(Color.BLACK);
        gearPanel.setBorder(new LineBorder(ACCENT_BLUE, 1));
        JLabel gearTitle = new JLabel("GEAR", SwingConstants.CENTER);
        gearTitle.setFont(new Font("Arial", Font.BOLD, 10));
        gearTitle.setForeground(ACCENT_BLUE);
        gearLabel = new JLabel("P", SwingConstants.CENTER);
        gearLabel.setFont(new Font("Arial", Font.BOLD, 32));
        gearLabel.setForeground(Color.WHITE);
        gearPanel.add(gearTitle, BorderLayout.NORTH);
        gearPanel.add(gearLabel, BorderLayout.CENTER);
        
        // Odometer con más detalles
        JPanel odoPanel = new JPanel(new GridLayout(3, 1, 2, 2));
        odoPanel.setBackground(Color.BLACK);
        odoPanel.setBorder(new LineBorder(Color.WHITE, 1));
        
        JLabel odoTitle = new JLabel("ODOMETER", SwingConstants.CENTER);
        odoTitle.setFont(new Font("Arial", Font.BOLD, 8));
        odoTitle.setForeground(Color.GRAY);
        
        odometerLabel = new JLabel("000000.0 km", SwingConstants.CENTER);
        odometerLabel.setFont(new Font("Consolas", Font.BOLD, 12));
        odometerLabel.setForeground(Color.WHITE);
        
        tripLabel = new JLabel("Trip A: 0.0 km", SwingConstants.CENTER);
        tripLabel.setFont(new Font("Consolas", Font.PLAIN, 10));
        tripLabel.setForeground(LED_BLUE);
        
        odoPanel.add(odoTitle);
        odoPanel.add(odometerLabel);
        odoPanel.add(tripLabel);
        
        // Layout del panel central
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(mainDisplay, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1;
        panel.add(rpmPanel, gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        panel.add(gearPanel, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        panel.add(odoPanel, gbc);
        
        return panel;
    }
    
    private JPanel createAdvancedLEDPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 8));
        panel.setBackground(DASHBOARD_BLACK);
        
        // LEDs indicadores con diseño premium
        motorLED = createPremiumLED("ENGINE", "🔥", LED_RED);
        fuelLED = createPremiumLED("FUEL", "⛽", WARNING_AMBER);
        beltLED = createPremiumLED("SEATBELT", "🔒", LED_RED);
        doorLED = createPremiumLED("DOOR", "🚪", WARNING_AMBER);
        alarmLED = createPremiumLED("SECURITY", "🛡️", ACCENT_BLUE);
        
        // LEDs adicionales para más realismo
        JLabel oilLED = createPremiumLED("OIL", "🛢️", LED_RED);
        JLabel tempLED = createPremiumLED("TEMP", "🌡️", LED_RED);
        JLabel batteryLED = createPremiumLED("BATT", "🔋", WARNING_AMBER);
        
        panel.add(motorLED);
        panel.add(fuelLED);
        panel.add(beltLED);
        panel.add(doorLED);
        panel.add(alarmLED);
        panel.add(oilLED);
        panel.add(tempLED);
        panel.add(batteryLED);
        
        return panel;
    }
    
    private JLabel createPremiumLED(String text, String icon, Color color) {
        JLabel led = new JLabel("<html><center>" + icon + "<br><small>" + text + "</small></center></html>");
        led.setFont(new Font("Arial", Font.BOLD, 9));
        led.setForeground(DASHBOARD_GRAY);
        led.setOpaque(true);
        led.setBackground(Color.BLACK);
        led.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(color, 1),
            BorderFactory.createEmptyBorder(4, 6, 4, 6)
        ));
        led.setPreferredSize(new Dimension(50, 35));
        led.setHorizontalAlignment(SwingConstants.CENTER);
        return led;
    }
    
    private JPanel createCenterControlPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(DASHBOARD_BLACK);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;
        
        // Panel de ignición y pedales
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(createIgnitionPanel(), gbc);
        
        // Panel de luces
        gbc.gridx = 1; gbc.gridy = 0;
        panel.add(createLightsPanel(), gbc);
        
        // Panel de radio
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(createRadioPanel(), gbc);
        
        // Panel de climatización
        gbc.gridx = 1; gbc.gridy = 1;
        panel.add(createClimatePanel(), gbc);
        
        return panel;
    }
    
    private JPanel createIgnitionPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(DASHBOARD_GRAY);
        panel.setBorder(new TitledBorder(null, "🔑 CONTROL DEL MOTOR", TitledBorder.CENTER, 
            TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 12), Color.WHITE));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Botón de ignición más realista con efecto push-button
        ignitionBtn = new JButton("🔑 ENGINE START");
        ignitionBtn.setFont(new Font("Arial", Font.BOLD, 12));
        ignitionBtn.setBackground(new Color(139, 0, 0)); // Rojo oscuro más realista
        ignitionBtn.setForeground(Color.WHITE);
        ignitionBtn.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(DASHBOARD_SILVER, 1),
            BorderFactory.createRaisedBevelBorder()
        ));
        ignitionBtn.setPreferredSize(new Dimension(140, 45));
        ignitionBtn.setFocusPainted(false);
        
        // Agregar efecto de presión al botón
        ignitionBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ignitionBtn.setBorder(BorderFactory.createLoweredBevelBorder());
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ignitionBtn.setBorder(BorderFactory.createRaisedBevelBorder());
            }
        });
        
        // Pedal del acelerador
        JLabel accelLabel = new JLabel("🦶 ACELERADOR");
        accelLabel.setForeground(Color.WHITE);
        acceleratorPedal = new JSlider(0, 100, 0);
        acceleratorPedal.setOrientation(JSlider.VERTICAL);
        acceleratorPedal.setBackground(DASHBOARD_GRAY);
        acceleratorPedal.setForeground(LED_GREEN);
        acceleratorPedal.setPaintTicks(true);
        acceleratorPedal.setMajorTickSpacing(25);
        
        // Pedal del freno
        JLabel brakeLabel = new JLabel("🛑 FRENO");
        brakeLabel.setForeground(Color.WHITE);
        brakePedal = new JSlider(0, 100, 0);
        brakePedal.setOrientation(JSlider.VERTICAL);
        brakePedal.setBackground(DASHBOARD_GRAY);
        brakePedal.setForeground(LED_RED);
        brakePedal.setPaintTicks(true);
        brakePedal.setMajorTickSpacing(25);
        
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(ignitionBtn, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1;
        panel.add(accelLabel, gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        panel.add(brakeLabel, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(acceleratorPedal, gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        panel.add(brakePedal, gbc);
        
        return panel;
    }
    
    private JPanel createLightsPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(DASHBOARD_GRAY);
        panel.setBorder(new TitledBorder(null, "💡 SISTEMA DE LUCES", TitledBorder.CENTER, 
            TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 12), Color.WHITE));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Botones de luces con iconos
        headlightsBtn = createLightButton("🔦 LUCES", LED_BLUE);
        highBeamBtn = createLightButton("💡 ALTAS", LED_YELLOW);
        leftTurnBtn = createLightButton("⬅️ IZQ", LED_GREEN);
        rightTurnBtn = createLightButton("➡️ DER", LED_GREEN);
        hazardsBtn = createLightButton("⚠️ EMERGENCIA", LED_RED);
        
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(headlightsBtn, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2;
        panel.add(highBeamBtn, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 1;
        panel.add(leftTurnBtn, gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        panel.add(rightTurnBtn, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        panel.add(hazardsBtn, gbc);
        
        return panel;
    }
    
    private JToggleButton createLightButton(String text, Color color) {
        JToggleButton btn = new JToggleButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 11));
        btn.setBackground(BUTTON_GRAY);
        btn.setForeground(Color.WHITE);
        btn.setBorder(BorderFactory.createRaisedBevelBorder());
        btn.setFocusPainted(false);
        return btn;
    }
    
    private JPanel createRadioPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(DASHBOARD_GRAY);
        panel.setBorder(new TitledBorder(null, "📻 SISTEMA DE AUDIO", TitledBorder.CENTER, 
            TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 12), Color.WHITE));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(3, 3, 3, 3);
        
        // Display del radio
        radioDisplay = new JLabel("📻 RADIO OFF");
        radioDisplay.setFont(new Font("Consolas", Font.BOLD, 14));
        radioDisplay.setForeground(LED_GREEN);
        radioDisplay.setOpaque(true);
        radioDisplay.setBackground(Color.BLACK);
        radioDisplay.setBorder(BorderFactory.createLoweredBevelBorder());
        radioDisplay.setHorizontalAlignment(SwingConstants.CENTER);
        radioDisplay.setPreferredSize(new Dimension(200, 30));
        
        // Botón ON/OFF
        radioOnBtn = new JToggleButton("⚡ ON/OFF");
        radioOnBtn.setBackground(LED_RED);
        radioOnBtn.setForeground(Color.WHITE);
        radioOnBtn.setFont(new Font("Arial", Font.BOLD, 10));
        
        // Botones de búsqueda
        seekDownBtn = new JButton("⏪");
        seekUpBtn = new JButton("⏩");
        seekDownBtn.setBackground(BUTTON_GRAY);
        seekUpBtn.setBackground(BUTTON_GRAY);
        seekDownBtn.setForeground(Color.WHITE);
        seekUpBtn.setForeground(Color.WHITE);
        
        // Presets
        radioPresets = new JButton[6];
        JPanel presetsPanel = new JPanel(new GridLayout(2, 3, 2, 2));
        presetsPanel.setBackground(DASHBOARD_GRAY);
        for (int i = 0; i < 6; i++) {
            radioPresets[i] = new JButton(String.valueOf(i + 1));
            radioPresets[i].setFont(new Font("Arial", Font.BOLD, 10));
            radioPresets[i].setBackground(BUTTON_GRAY);
            radioPresets[i].setForeground(Color.WHITE);
            radioPresets[i].setPreferredSize(new Dimension(30, 25));
            presetsPanel.add(radioPresets[i]);
        }
        
        // Volume slider
        volumeSlider = new JSlider(0, 100, 50);
        volumeSlider.setBackground(DASHBOARD_GRAY);
        volumeSlider.setForeground(LED_BLUE);
        
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 3;
        panel.add(radioDisplay, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1;
        panel.add(radioOnBtn, gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        panel.add(seekDownBtn, gbc);
        gbc.gridx = 2; gbc.gridy = 1;
        panel.add(seekUpBtn, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 3;
        panel.add(presetsPanel, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 3;
        panel.add(new JLabel("🔊 VOLUMEN", SwingConstants.CENTER) {{
            setForeground(Color.WHITE);
        }}, gbc);
        
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 3;
        panel.add(volumeSlider, gbc);
        
        return panel;
    }
    
    private JPanel createClimatePanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(DASHBOARD_GRAY);
        panel.setBorder(new TitledBorder(null, "🌡️ CLIMATIZACIÓN", TitledBorder.CENTER, 
            TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 12), Color.WHITE));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Control de temperatura
        tempSlider = new JSlider(16, 30, 20);
        tempSlider.setBackground(DASHBOARD_GRAY);
        tempSlider.setForeground(LED_BLUE);
        tempSlider.setPaintTicks(true);
        tempSlider.setPaintLabels(true);
        tempSlider.setMajorTickSpacing(2);
        
        // Botones de A/C y calefacción
        acBtn = new JToggleButton("❄️ A/C");
        acBtn.setBackground(LED_BLUE);
        acBtn.setForeground(Color.WHITE);
        acBtn.setFont(new Font("Arial", Font.BOLD, 12));
        
        heatBtn = new JToggleButton("🔥 HEAT");
        heatBtn.setBackground(LED_ORANGE);
        heatBtn.setForeground(Color.WHITE);
        heatBtn.setFont(new Font("Arial", Font.BOLD, 12));
        
        // Velocidad del ventilador
        fanSpeedSlider = new JSlider(0, 4, 0);
        fanSpeedSlider.setBackground(DASHBOARD_GRAY);
        fanSpeedSlider.setForeground(LED_GREEN);
        fanSpeedSlider.setPaintTicks(true);
        fanSpeedSlider.setMajorTickSpacing(1);
        
        // Botón de recirculación
        recirculateBtn = new JToggleButton("🔄 RECIRC");
        recirculateBtn.setBackground(BUTTON_GRAY);
        recirculateBtn.setForeground(Color.WHITE);
        
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(new JLabel("🌡️ TEMPERATURA", SwingConstants.CENTER) {{
            setForeground(Color.WHITE);
        }}, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2;
        panel.add(tempSlider, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 1;
        panel.add(acBtn, gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        panel.add(heatBtn, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        panel.add(new JLabel("💨 VENTILADOR", SwingConstants.CENTER) {{
            setForeground(Color.WHITE);
        }}, gbc);
        
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        panel.add(fanSpeedSlider, gbc);
        
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        panel.add(recirculateBtn, gbc);
        
        return panel;
    }
    
    private JPanel createBottomControlPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 3, 10, 10));
        panel.setBackground(DASHBOARD_BLACK);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Panel de puertas
        panel.add(createDoorsPanel());
        
        // Panel de cinturones
        panel.add(createSeatbeltsPanel());
        
        // Panel de seguridad
        panel.add(createSecurityPanel());
        
        return panel;
    }
    
    private JPanel createDoorsPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        panel.setBackground(DASHBOARD_GRAY);
        panel.setBorder(new TitledBorder(null, "🚪 PUERTAS", TitledBorder.CENTER, 
            TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 12), Color.WHITE));
        
        doorBtns = new JButton[4];
        String[] doorNames = {"🚗 CONDUCTOR", "👤 COPILOTO", "👥 TRAS. IZQ", "👥 TRAS. DER"};
        
        for (int i = 0; i < 4; i++) {
            doorBtns[i] = new JButton(doorNames[i]);
            doorBtns[i].setFont(new Font("Arial", Font.BOLD, 10));
            doorBtns[i].setBackground(LED_GREEN);
            doorBtns[i].setForeground(Color.WHITE);
            doorBtns[i].setBorder(BorderFactory.createRaisedBevelBorder());
            panel.add(doorBtns[i]);
        }
        
        return panel;
    }
    
    private JPanel createSeatbeltsPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        panel.setBackground(DASHBOARD_GRAY);
        panel.setBorder(new TitledBorder(null, "🔒 CINTURONES", TitledBorder.CENTER, 
            TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 12), Color.WHITE));
        
        Asiento[] asientos = Asiento.values();
        seatbeltChecks = new JCheckBox[asientos.length];
        
        for (int i = 0; i < asientos.length; i++) {
            seatbeltChecks[i] = new JCheckBox(asientos[i].getEstado());
            seatbeltChecks[i].setFont(new Font("Arial", Font.BOLD, 10));
            seatbeltChecks[i].setBackground(DASHBOARD_GRAY);
            seatbeltChecks[i].setForeground(Color.WHITE);
            panel.add(seatbeltChecks[i]);
        }
        
        return panel;
    }
    
    private JPanel createSecurityPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(DASHBOARD_GRAY);
        panel.setBorder(new TitledBorder(null, "🛡️ SEGURIDAD", TitledBorder.CENTER, 
            TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 12), Color.WHITE));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        securityModeCombo = new JComboBox<>(Seguridad.values());
        securityModeCombo.setBackground(BUTTON_GRAY);
        securityModeCombo.setForeground(Color.WHITE);
        
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Modo:") {{
            setForeground(Color.WHITE);
        }}, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(securityModeCombo, gbc);
        
        return panel;
    }
    
    // Clase para el velocímetro personalizado
    private class VelocimetroPanel extends JPanel {
        private int speed = 0;
        private final int MAX_SPEED = 180;
        
        public VelocimetroPanel() {
            setPreferredSize(new Dimension(200, 200));
            setBackground(DASHBOARD_BLACK);
        }
        
        public void setSpeed(int speed) {
            this.speed = Math.min(speed, MAX_SPEED);
            repaint();
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            int centerX = getWidth() / 2;
            int centerY = getHeight() / 2;
            int radius = Math.min(centerX, centerY) - 20;
            
            // Dibujar el fondo del velocímetro con efecto metálico
            GradientPaint gradient = new GradientPaint(
                centerX - radius, centerY - radius, new Color(60, 60, 60),
                centerX + radius, centerY + radius, new Color(30, 30, 30)
            );
            g2d.setPaint(gradient);
            g2d.fillOval(centerX - radius, centerY - radius, radius * 2, radius * 2);
            
            // Borde exterior cromado
            g2d.setColor(DASHBOARD_SILVER);
            g2d.setStroke(new BasicStroke(3));
            g2d.drawOval(centerX - radius, centerY - radius, radius * 2, radius * 2);
            
            // Zona roja de peligro (160-180 km/h)
            g2d.setColor(new Color(255, 0, 0, 100));
            double redZoneStart = Math.toRadians(225 - (160 * 270.0 / 180));
            double redZoneEnd = Math.toRadians(225 - (180 * 270.0 / 180));
            Arc2D redZone = new Arc2D.Double(centerX - radius + 5, centerY - radius + 5, 
                (radius - 5) * 2, (radius - 5) * 2, 
                Math.toDegrees(redZoneStart), Math.toDegrees(redZoneEnd - redZoneStart), Arc2D.PIE);
            g2d.fill(redZone);
            
            // Dibujar las marcas
            g2d.setColor(Color.WHITE);
            for (int i = 0; i <= 180; i += 20) {
                double angle = Math.toRadians(225 - (i * 270.0 / 180));
                int x1 = (int) (centerX + (radius - 15) * Math.cos(angle));
                int y1 = (int) (centerY + (radius - 15) * Math.sin(angle));
                int x2 = (int) (centerX + radius * Math.cos(angle));
                int y2 = (int) (centerY + radius * Math.sin(angle));
                g2d.drawLine(x1, y1, x2, y2);
                
                // Números
                if (i % 40 == 0) {
                    g2d.setFont(new Font("Arial", Font.BOLD, 12));
                    String speedText = String.valueOf(i);
                    FontMetrics fm = g2d.getFontMetrics();
                    int textX = (int) (centerX + (radius - 30) * Math.cos(angle) - fm.stringWidth(speedText) / 2);
                    int textY = (int) (centerY + (radius - 30) * Math.sin(angle) + fm.getHeight() / 2);
                    g2d.drawString(speedText, textX, textY);
                }
            }
            
            // Dibujar la aguja
            double speedAngle = Math.toRadians(225 - (speed * 270.0 / MAX_SPEED));
            int needleX = (int) (centerX + (radius - 40) * Math.cos(speedAngle));
            int needleY = (int) (centerY + (radius - 40) * Math.sin(speedAngle));
            
            g2d.setColor(LED_RED);
            g2d.setStroke(new BasicStroke(3));
            g2d.drawLine(centerX, centerY, needleX, needleY);
            
            // Centro del velocímetro
            g2d.setColor(Color.WHITE);
            g2d.fillOval(centerX - 8, centerY - 8, 16, 16);
            
            // Display digital de velocidad
            g2d.setColor(LED_GREEN);
            g2d.setFont(new Font("Digital-7", Font.BOLD, 16));
            String speedText = speed + " km/h";
            FontMetrics fm = g2d.getFontMetrics();
            g2d.drawString(speedText, centerX - fm.stringWidth(speedText) / 2, centerY + 40);
        }
    }
    
    // Clase para el medidor de combustible
    private class CombustiblePanel extends JPanel {
        private int fuelLevel = 100;
        
        public CombustiblePanel() {
            setPreferredSize(new Dimension(150, 200));
            setBackground(DASHBOARD_BLACK);
        }
        
        public void setFuelLevel(int level) {
            this.fuelLevel = Math.max(0, Math.min(100, level));
            repaint();
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            int width = getWidth() - 20;
            int height = getHeight() - 40;
            int x = 10;
            int y = 20;
            
            // Marco del tanque
            g2d.setColor(DASHBOARD_GRAY);
            g2d.fillRect(x, y, width, height);
            g2d.setColor(Color.WHITE);
            g2d.drawRect(x, y, width, height);
            
            // Nivel de combustible
            int fuelHeight = (int) (height * fuelLevel / 100.0);
            Color fuelColor;
            if (fuelLevel > 50) fuelColor = LED_GREEN;
            else if (fuelLevel > 20) fuelColor = LED_ORANGE;
            else fuelColor = LED_RED;
            
            g2d.setColor(fuelColor);
            g2d.fillRect(x + 2, y + height - fuelHeight, width - 4, fuelHeight);
            
            // Marcas de nivel
            g2d.setColor(Color.WHITE);
            for (int i = 0; i <= 4; i++) {
                int markY = y + (height * i / 4);
                g2d.drawLine(x, markY, x + 10, markY);
                g2d.drawLine(x + width - 10, markY, x + width, markY);
            }
            
            // Etiquetas
            g2d.setFont(new Font("Arial", Font.BOLD, 10));
            g2d.drawString("F", x - 5, y + 15);
            g2d.drawString("E", x - 5, y + height + 5);
            g2d.drawString("⛽", x + width/2 - 5, y + height + 15);
            
            // Porcentaje digital
            g2d.setColor(LED_GREEN);
            g2d.setFont(new Font("Arial", Font.BOLD, 12));
            String percent = fuelLevel + "%";
            FontMetrics fm = g2d.getFontMetrics();
            g2d.drawString(percent, x + width/2 - fm.stringWidth(percent)/2, y - 5);
        }
    }
    
    private void applyPremiumDarkTheme() {
        // Aplicar tema oscuro premium con más detalles
        UIManager.put("Panel.background", DASHBOARD_BLACK);
        UIManager.put("Label.foreground", Color.WHITE);
        UIManager.put("CheckBox.background", DASHBOARD_GRAY);
        UIManager.put("CheckBox.foreground", Color.WHITE);
        UIManager.put("ComboBox.background", BUTTON_GRAY);
        UIManager.put("ComboBox.foreground", Color.WHITE);
        UIManager.put("ComboBox.selectionBackground", ACCENT_BLUE);
        UIManager.put("Button.background", BUTTON_GRAY);
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Button.select", ACCENT_BLUE);
        UIManager.put("Slider.background", DASHBOARD_GRAY);
        UIManager.put("Slider.foreground", ACCENT_BLUE);
        UIManager.put("TitledBorder.titleColor", Color.WHITE);
        UIManager.put("ProgressBar.background", DASHBOARD_GRAY);
        UIManager.put("ProgressBar.foreground", ACCENT_BLUE);
        UIManager.put("ProgressBar.selectionBackground", Color.WHITE);
        UIManager.put("ProgressBar.selectionForeground", Color.BLACK);
    }
    
    private void setupEventListeners() {
        // Control de ignición
        ignitionBtn.addActionListener(e -> {
            if (vehiculo.estaEncendido()) {
                String resultado = vehiculo.apagar();
                ignitionBtn.setBackground(LED_RED);
                ignitionBtn.setText("🔑 START");
                JOptionPane.showMessageDialog(this, resultado, "Motor", JOptionPane.INFORMATION_MESSAGE);
            } else {
                String resultado = vehiculo.encender();
                if (resultado.contains("vacío")) {
                    JOptionPane.showMessageDialog(this, resultado, "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    ignitionBtn.setBackground(LED_GREEN);
                    ignitionBtn.setText("🔑 STOP");
                    JOptionPane.showMessageDialog(this, resultado, "Motor", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            updateDisplay();
        });
        
        // Control del acelerador
        acceleratorPedal.addChangeListener(e -> {
            if (vehiculo.estaEncendido() && !acceleratorPedal.getValueIsAdjusting()) {
                int velocidad = (int) (acceleratorPedal.getValue() * 1.2); // Max 120 km/h
                vehiculo.getKilometraje().actualizarVelocidad(velocidad);
                
                // Cambio de marcha automático
                String gear = "P";
                if (velocidad > 0) {
                    if (velocidad < 20) gear = "1";
                    else if (velocidad < 40) gear = "2";
                    else if (velocidad < 60) gear = "3";
                    else if (velocidad < 80) gear = "4";
                    else gear = "5";
                }
                gearLabel.setText(gear);
                
                updateDisplay();
            }
        });
        
        // Control del freno
        brakePedal.addChangeListener(e -> {
            if (brakePedal.getValue() > 50) {
                // Aplicar freno fuerte - reducir velocidad
                acceleratorPedal.setValue(Math.max(0, acceleratorPedal.getValue() - 10));
                vehiculo.getSensores().aplicarFreno();
            } else {
                vehiculo.getSensores().liberarFreno();
            }
            updateDisplay();
        });
        
        // Luces
        headlightsBtn.addActionListener(e -> {
            if (headlightsBtn.isSelected()) {
                vehiculo.getLuces().LucesDelanteras(TipoLuz.BAJAS);
                headlightsBtn.setBackground(LED_YELLOW);
            } else {
                vehiculo.getLuces().LucesDelanteras(TipoLuz.APAGADAS);
                headlightsBtn.setBackground(BUTTON_GRAY);
            }
        });
        
        highBeamBtn.addActionListener(e -> {
            if (highBeamBtn.isSelected()) {
                vehiculo.getLuces().LucesDelanteras(TipoLuz.ALTAS);
                highBeamBtn.setBackground(LED_BLUE);
                headlightsBtn.setSelected(false);
                headlightsBtn.setBackground(BUTTON_GRAY);
            } else {
                vehiculo.getLuces().LucesDelanteras(TipoLuz.APAGADAS);
                highBeamBtn.setBackground(BUTTON_GRAY);
            }
        });
        
        leftTurnBtn.addActionListener(e -> {
            vehiculo.getLuces().activarDireccionalIzquierda(leftTurnBtn.isSelected());
            leftTurnBtn.setBackground(leftTurnBtn.isSelected() ? LED_GREEN : BUTTON_GRAY);
            if (leftTurnBtn.isSelected()) {
                rightTurnBtn.setSelected(false);
                rightTurnBtn.setBackground(BUTTON_GRAY);
            }
        });
        
        rightTurnBtn.addActionListener(e -> {
            vehiculo.getLuces().activarDireccionalDerecha(rightTurnBtn.isSelected());
            rightTurnBtn.setBackground(rightTurnBtn.isSelected() ? LED_GREEN : BUTTON_GRAY);
            if (rightTurnBtn.isSelected()) {
                leftTurnBtn.setSelected(false);
                leftTurnBtn.setBackground(BUTTON_GRAY);
            }
        });
        
        hazardsBtn.addActionListener(e -> {
            vehiculo.getLuces().lucesEmergencia(hazardsBtn.isSelected());
            hazardsBtn.setBackground(hazardsBtn.isSelected() ? LED_RED : BUTTON_GRAY);
        });
        
        // Radio
        radioOnBtn.addActionListener(e -> {
            if (radioOnBtn.isSelected()) {
                vehiculo.getRadio().Encendida();
                vehiculo.getRadio().cambiarModo(Modo.FM);
                radioOnBtn.setBackground(LED_GREEN);
                radioDisplay.setText("📻 FM 88.0");
            } else {
                vehiculo.getRadio().apagar();
                radioOnBtn.setBackground(LED_RED);
                radioDisplay.setText("📻 RADIO OFF");
            }
            updateDisplay();
        });
        
        seekUpBtn.addActionListener(e -> {
            if (vehiculo.getRadio().isEncendida()) {
                vehiculo.getRadio().subirEstacion();
                updateDisplay();
            }
        });
        
        seekDownBtn.addActionListener(e -> {
            if (vehiculo.getRadio().isEncendida()) {
                vehiculo.getRadio().bajarEstacion();
                updateDisplay();
            }
        });
        
        // Presets del radio
        for (int i = 0; i < radioPresets.length; i++) {
            final int preset = i;
            radioPresets[i].addActionListener(e -> {
                if (vehiculo.getRadio().isEncendida()) {
                    // Simular preset guardado
                    double[] presetFreqs = {88.5, 95.3, 101.7, 106.1, 89.9, 104.5};
                    vehiculo.getRadio().cambiarModo(Modo.FM);
                    // Aquí podrías implementar la lógica de presets en tu clase Radio
                    radioDisplay.setText("📻 FM " + presetFreqs[preset]);
                }
            });
        }
        
        // Climatización
        acBtn.addActionListener(e -> {
            if (acBtn.isSelected()) {
                vehiculo.getClimatizacion().encenderAireAcondicinado();
                acBtn.setBackground(LED_BLUE);
                heatBtn.setSelected(false);
                heatBtn.setBackground(BUTTON_GRAY);
            } else {
                vehiculo.getClimatizacion().apagarClimatizacion();
                acBtn.setBackground(BUTTON_GRAY);
            }
        });
        
        heatBtn.addActionListener(e -> {
            if (heatBtn.isSelected()) {
                vehiculo.getClimatizacion().encenderCalefaccion();
                heatBtn.setBackground(LED_ORANGE);
                acBtn.setSelected(false);
                acBtn.setBackground(BUTTON_GRAY);
            } else {
                vehiculo.getClimatizacion().apagarClimatizacion();
                heatBtn.setBackground(BUTTON_GRAY);
            }
        });
        
        tempSlider.addChangeListener(e -> {
            if (!tempSlider.getValueIsAdjusting()) {
                int targetTemp = tempSlider.getValue();
                int currentTemp = vehiculo.getClimatizacion().getTemperatura();
                
                while (currentTemp != targetTemp) {
                    if (currentTemp < targetTemp) {
                        vehiculo.getClimatizacion().subirTemperatura();
                        currentTemp = vehiculo.getClimatizacion().getTemperatura();
                    } else {
                        vehiculo.getClimatizacion().bajarTemperatura();
                        currentTemp = vehiculo.getClimatizacion().getTemperatura();
                    }
                }
                updateDisplay();
            }
        });
        
        fanSpeedSlider.addChangeListener(e -> {
            if (!fanSpeedSlider.getValueIsAdjusting()) {
                velocidadVentilador[] speeds = velocidadVentilador.values();
                int speedIndex = fanSpeedSlider.getValue();
                if (speedIndex < speeds.length) {
                    vehiculo.getClimatizacion().setVelocidadVentilador(speeds[speedIndex]);
                }
            }
        });
        
        // Puertas
        for (int i = 0; i < doorBtns.length; i++) {
            final int doorIndex = i;
            doorBtns[i].addActionListener(e -> {
                // Aquí necesitarías implementar la lógica de abrir/cerrar puertas individuales
                // Por ahora simulamos el cambio de color
                if (doorBtns[doorIndex].getBackground().equals(LED_GREEN)) {
                    doorBtns[doorIndex].setBackground(LED_RED);
                    doorBtns[doorIndex].setText(doorBtns[doorIndex].getText().replace("CERRADA", "ABIERTA"));
                } else {
                    doorBtns[doorIndex].setBackground(LED_GREEN);
                    doorBtns[doorIndex].setText(doorBtns[doorIndex].getText().replace("ABIERTA", "CERRADA"));
                }
                updateDisplay();
            });
        }
        
        // Cinturones
        for (int i = 0; i < seatbeltChecks.length; i++) {
            final int seatIndex = i;
            seatbeltChecks[i].addActionListener(e -> {
                Asiento asiento = Asiento.values()[seatIndex];
                if (seatbeltChecks[seatIndex].isSelected()) {
                    vehiculo.getCinturones().Abrochar(asiento);
                } else {
                    vehiculo.getCinturones().Desabrochar(asiento);
                }
                updateDisplay();
            });
        }
        
        // Seguridad
        securityModeCombo.addActionListener(e -> {
            Seguridad modo = (Seguridad) securityModeCombo.getSelectedItem();
            vehiculo.getAlarma().setSeguridad(modo);
            updateDisplay();
        });
    }
    
    private void startSimulation() {
        simulationTimer = new Timer();
        simulationTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    if (vehiculo.estaEncendido() && vehiculo.getKilometraje().getVelocidad() > 0) {
                        String resultado = vehiculo.moverVehiculo(1);
                        
                        // Parpadeo de LEDs de alerta
                        if (resultado.contains("puertas") || resultado.contains("cinturones")) {
                            doorLED.setForeground(doorLED.getForeground().equals(LED_RED) ? DASHBOARD_GRAY : LED_RED);
                            beltLED.setForeground(beltLED.getForeground().equals(LED_RED) ? DASHBOARD_GRAY : LED_RED);
                        }
                        
                        if (resultado.contains("Combustible agotado")) {
                            fuelLED.setForeground(fuelLED.getForeground().equals(LED_RED) ? DASHBOARD_GRAY : LED_RED);
                        }
                        
                        updateDisplay();
                    }
                });
            }
        }, 1000, 1000);
    }
    
    private void updateDisplay() {
        // Actualizar velocímetro
        velocimetroPanel.setSpeed(vehiculo.getKilometraje().getVelocidad());
        
        // Actualizar RPM
        rpmLabel.setText(String.valueOf(vehiculo.getKilometraje().getRpm()));
        
        // Actualizar combustible
        double nivelCombustible = vehiculo.getCombustible().getNivel();
        double capacidad = vehiculo.getCombustible().getCapacidad();
        int porcentaje = (int) ((nivelCombustible / capacidad) * 100);
        combustiblePanel.setFuelLevel(porcentaje);
        
        // Actualizar odómetro
        odometerLabel.setText(String.format("%07.1f km", vehiculo.getKilometraje().getTotalKm()));
        tripLabel.setText(String.format("Trip: %.1f km", vehiculo.getKilometraje().getTotalKm()));
        
        // LEDs de estado
        motorLED.setForeground(vehiculo.estaEncendido() ? LED_GREEN : DASHBOARD_GRAY);
        
        if (porcentaje < 20) {
            fuelLED.setForeground(LED_RED);
        } else if (porcentaje < 50) {
            fuelLED.setForeground(LED_ORANGE);
        } else {
            fuelLED.setForeground(DASHBOARD_GRAY);
        }
        
        beltLED.setForeground(vehiculo.getCinturones().todosAbrochados() ? DASHBOARD_GRAY : LED_RED);
        
        // Actualizar display del radio
        if (vehiculo.getRadio().isEncendida()) {
            Modo modo = vehiculo.getRadio().getModoActual();
            if (modo == Modo.BLUETOOTH) {
                radioDisplay.setText("📱 BLUETOOTH");
            } else {
                radioDisplay.setText(String.format("📻 %s %.1f", 
                    modo.name(), vehiculo.getRadio().getFrecuencia()));
            }
        }
        
        // Estado de alarma
        alarmLED.setForeground(vehiculo.getAlarma().isAlarmaActiva() ? LED_BLUE : DASHBOARD_GRAY);
        
        repaint();
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("No se pudo cargar Nimbus Look and Feel: " + e.getMessage());
            }
            
            new CarroSimulador().setVisible(true);
        });
    }
}