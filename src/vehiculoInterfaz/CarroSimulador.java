package vehiculoInterfaz;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.plaf.basic.BasicSliderUI;

// Importar todas las clases necesarias
import Vehiculo.Vehiculo;
import Alarma.Seguridad;
import Cinturones.Asiento;
import Luces.TipoLuz;
import Radio.Modo;
import Climatizacion.velocidadVentilador;
import parabrisas.Velocidad;

/**
 * CarroSimulador Moderno - Versión minimalista y elegante Diseño inspirado en
 * interfaces de vehículos Tesla y BMW
 */
public class CarroSimulador extends JFrame {

    // === COLORES MODERNOS ===
    private static final Color DARK_BG = new Color(18, 18, 18);
    private static final Color CARD_BG = new Color(28, 28, 30);
    private static final Color ACCENT_BLUE = new Color(0, 122, 255);
    private static final Color ACCENT_GREEN = new Color(52, 199, 89);
    private static final Color ACCENT_ORANGE = new Color(255, 149, 0);
    private static final Color ACCENT_RED = new Color(255, 59, 48);
    private static final Color TEXT_PRIMARY = new Color(255, 255, 255);
    private static final Color TEXT_SECONDARY = new Color(152, 152, 157);
    private static final Color BUTTON_ACTIVE = new Color(0, 122, 255, 30);

    // === COMPONENTES PRINCIPALES ===
    private Vehiculo vehiculo;
    private Timer updateTimer;

    // Paneles principales
    private JPanel mainDashboard;
    private JPanel controlPanel;
    private JPanel statusPanel;

    // Componentes del dashboard
    private ModernGaugePanel speedGauge;
    private ModernGaugePanel fuelGauge;
    private JLabel digitalDisplay;
    private Map<String, ModernToggleButton> controlButtons;
    private Map<String, StatusIndicator> statusLights;

    // Controles de conducción
    private ModernSlider acceleratorSlider;
    private ModernSlider brakeSlider;
    private ModernButton ignitionButton;

    public CarroSimulador() {
        vehiculo = new Vehiculo();
        controlButtons = new HashMap<>();
        statusLights = new HashMap<>();

        initModernUI();
        setupControlLogic();
        startUpdateCycle();

        setVisible(true);
    }

    private void initModernUI() {
        setTitle("🚙 Tesla-Style Vehicle Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1400, 900);
        setLocationRelativeTo(null);
        setBackground(DARK_BG);

        // Layout principal con márgenes modernos
        setLayout(new BorderLayout(20, 20));
        ((JComponent) getContentPane()).setBorder(new EmptyBorder(20, 20, 20, 20));
        getContentPane().setBackground(DARK_BG);

        // Crear paneles principales
        createDashboardSection();
        createControlSection();
        createStatusSection();

        // Aplicar estilo moderno
        applyModernStyling();
    }

    private void createDashboardSection() {
        mainDashboard = new ModernPanel();
        mainDashboard.setLayout(new GridBagLayout());
        mainDashboard.setPreferredSize(new Dimension(1400, 300));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);

        // Velocímetro moderno
        speedGauge = new ModernGaugePanel("SPEED", "km/h", 0, 200, ACCENT_BLUE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainDashboard.add(speedGauge, gbc);

        // Display digital central
        digitalDisplay = createDigitalDisplay();
        gbc.gridx = 1;
        gbc.gridy = 0;
        mainDashboard.add(digitalDisplay, gbc);

        // Medidor de combustible
        fuelGauge = new ModernGaugePanel("FUEL", "%", 0, 100, ACCENT_GREEN);
        gbc.gridx = 2;
        gbc.gridy = 0;
        mainDashboard.add(fuelGauge, gbc);

        add(mainDashboard, BorderLayout.NORTH);
    }

    private void createControlSection() {
        controlPanel = new ModernPanel();
        controlPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Panel de conducción
        JPanel drivingPanel = createDrivingControls();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        controlPanel.add(drivingPanel, gbc);

        // Panel de luces
        JPanel lightsPanel = createLightsPanel();
        gbc.gridx = 1;
        gbc.gridy = 0;
        controlPanel.add(lightsPanel, gbc);

        // Panel de climatización
        JPanel climatePanel = createClimatePanel();
        gbc.gridx = 2;
        gbc.gridy = 0;
        controlPanel.add(climatePanel, gbc);

        // Panel de entretenimiento
        JPanel mediaPanel = createMediaPanel();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        controlPanel.add(mediaPanel, gbc);

        add(controlPanel, BorderLayout.CENTER);
    }

    private void createStatusSection() {
        statusPanel = new ModernPanel();
        statusPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
        statusPanel.setPreferredSize(new Dimension(1400, 80));

        // Crear indicadores de estado
        createStatusIndicators();

        add(statusPanel, BorderLayout.SOUTH);
    }

    private JLabel createDigitalDisplay() {
        JLabel display = new JLabel();
        display.setPreferredSize(new Dimension(300, 200));
        display.setBackground(CARD_BG);
        display.setOpaque(true);
        display.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT_BLUE, 1),
                new EmptyBorder(20, 20, 20, 20)
        ));
        display.setHorizontalAlignment(SwingConstants.CENTER);
        display.setVerticalAlignment(SwingConstants.CENTER);
        display.setForeground(TEXT_PRIMARY);
        display.setFont(new Font("SF Pro Display", Font.PLAIN, 14));

        updateDigitalDisplay(display);
        return display;
    }

    private JPanel createDrivingControls() {
        JPanel panel = new ModernPanel("🚗 DRIVING CONTROLS");
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Botón de ignición moderno
        ignitionButton = new ModernButton("ENGINE START", ACCENT_RED);
        ignitionButton.setPreferredSize(new Dimension(200, 50));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(ignitionButton, gbc);

        // Controles de pedales
        JLabel accelLabel = new JLabel("ACCELERATOR");
        accelLabel.setForeground(TEXT_SECONDARY);
        accelLabel.setFont(new Font("SF Pro Display", Font.PLAIN, 12));

        acceleratorSlider = new ModernSlider(0, 100, 0, ACCENT_GREEN);
        acceleratorSlider.setOrientation(JSlider.VERTICAL);
        acceleratorSlider.setPreferredSize(new Dimension(60, 120));

        JLabel brakeLabel = new JLabel("BRAKE");
        brakeLabel.setForeground(TEXT_SECONDARY);
        brakeLabel.setFont(new Font("SF Pro Display", Font.PLAIN, 12));

        brakeSlider = new ModernSlider(0, 100, 0, ACCENT_RED);
        brakeSlider.setOrientation(JSlider.VERTICAL);
        brakeSlider.setPreferredSize(new Dimension(60, 120));

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(accelLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(brakeLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(acceleratorSlider, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(brakeSlider, gbc);

        return panel;
    }

    private JPanel createLightsPanel() {
        JPanel panel = new ModernPanel("💡 LIGHTING");
        panel.setLayout(new GridLayout(3, 2, 10, 10));

        // Crear botones de luces modernos
        ModernToggleButton headlights = new ModernToggleButton("Headlights", "💡");
        ModernToggleButton highBeam = new ModernToggleButton("High Beam", "🔆");
        ModernToggleButton leftTurn = new ModernToggleButton("Left Turn", "⬅️");
        ModernToggleButton rightTurn = new ModernToggleButton("Right Turn", "➡️");
        ModernToggleButton hazards = new ModernToggleButton("Hazards", "⚠️");
        ModernToggleButton fog = new ModernToggleButton("Fog Lights", "🌫️");

        controlButtons.put("headlights", headlights);
        controlButtons.put("highbeam", highBeam);
        controlButtons.put("leftturn", leftTurn);
        controlButtons.put("rightturn", rightTurn);
        controlButtons.put("hazards", hazards);
        controlButtons.put("fog", fog);

        panel.add(headlights);
        panel.add(highBeam);
        panel.add(leftTurn);
        panel.add(rightTurn);
        panel.add(hazards);
        panel.add(fog);

        return panel;
    }

    private JPanel createClimatePanel() {
        JPanel panel = new ModernPanel("🌡️ CLIMATE");
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Control de temperatura
        JLabel tempLabel = new JLabel("Temperature");
        tempLabel.setForeground(TEXT_SECONDARY);
        tempLabel.setFont(new Font("SF Pro Display", Font.PLAIN, 12));

        ModernSlider tempSlider = new ModernSlider(16, 30, 22, ACCENT_ORANGE);
        tempSlider.setPreferredSize(new Dimension(200, 40));

        // Botones de clima
        ModernToggleButton acButton = new ModernToggleButton("A/C", "❄️");
        ModernToggleButton heatButton = new ModernToggleButton("Heat", "🔥");

        controlButtons.put("ac", acButton);
        controlButtons.put("heat", heatButton);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(tempLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(tempSlider, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panel.add(acButton, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(heatButton, gbc);

        return panel;
    }

    private JPanel createMediaPanel() {
        JPanel panel = new ModernPanel("🎵 MEDIA & CONNECTIVITY");
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));

        // Controles de radio modernos
        ModernToggleButton radioOnOff = new ModernToggleButton("Radio", "📻");
        ModernButton seekDown = new ModernButton("⏮️", ACCENT_BLUE);
        ModernButton seekUp = new ModernButton("⏭️", ACCENT_BLUE);
        ModernToggleButton bluetooth = new ModernToggleButton("Bluetooth", "📱");

        controlButtons.put("radio", radioOnOff);
        controlButtons.put("bluetooth", bluetooth);

        // Presets modernos
        JPanel presetsPanel = new JPanel(new GridLayout(1, 6, 5, 5));
        presetsPanel.setOpaque(false);
        for (int i = 1; i <= 6; i++) {
            ModernButton preset = new ModernButton(String.valueOf(i), ACCENT_BLUE);
            preset.setPreferredSize(new Dimension(40, 40));
            presetsPanel.add(preset);
        }

        panel.add(radioOnOff);
        panel.add(seekDown);
        panel.add(seekUp);
        panel.add(bluetooth);
        panel.add(Box.createHorizontalStrut(20));
        panel.add(presetsPanel);

        return panel;
    }

    private void createStatusIndicators() {
        String[] indicators = {
            "ENGINE", "FUEL", "BATTERY", "TEMPERATURE",
            "SEATBELT", "DOORS", "ALARM", "SERVICE"
        };

        String[] icons = {
            "🔥", "⛽", "🔋", "🌡️",
            "🔒", "🚪", "🛡️", "🔧"
        };

        for (int i = 0; i < indicators.length; i++) {
            StatusIndicator indicator = new StatusIndicator(indicators[i], icons[i]);
            statusLights.put(indicators[i].toLowerCase(), indicator);
            statusPanel.add(indicator);
        }
    }

    private void setupControlLogic() {
        // Lógica del botón de ignición
        ignitionButton.addActionListener(e -> {
            if (vehiculo.estaEncendido()) {
                vehiculo.apagar();
                ignitionButton.setText("ENGINE START");
                ignitionButton.setBackground(ACCENT_RED);
                showNotification("Engine stopped", "Vehicle turned off successfully.");
            } else {
                String result = vehiculo.encender();
                if (result.contains("vacío")) {
                    showNotification("Cannot start", "Fuel tank is empty!");
                } else {
                    ignitionButton.setText("ENGINE STOP");
                    ignitionButton.setBackground(ACCENT_GREEN);
                    showNotification("Engine started", "Vehicle is now running.");
                }
            }
            updateAllDisplays();
        });

        // Lógica del acelerador
        acceleratorSlider.addChangeListener(e -> {
            if (vehiculo.estaEncendido() && !acceleratorSlider.getValueIsAdjusting()) {
                int speed = (int) (acceleratorSlider.getValue() * 1.8); // Max 180 km/h
                vehiculo.getKilometraje().actualizarVelocidad(speed);
                updateAllDisplays();
            }
        });

        // Lógica del freno
        brakeSlider.addChangeListener(e -> {
            if (brakeSlider.getValue() > 30) {
                // Reducir velocidad gradualmente
                int currentAccel = acceleratorSlider.getValue();
                int reduction = brakeSlider.getValue() / 10;
                acceleratorSlider.setValue(Math.max(0, currentAccel - reduction));
            }
        });

        // Lógica de las luces
        controlButtons.get("headlights").addActionListener(e -> {
            ModernToggleButton btn = controlButtons.get("headlights");
            if (btn.isSelected()) {
                vehiculo.getLuces().LucesDelanteras(TipoLuz.BAJAS);
            } else {
                vehiculo.getLuces().LucesDelanteras(TipoLuz.APAGADAS);
            }
        });

        controlButtons.get("highbeam").addActionListener(e -> {
            ModernToggleButton btn = controlButtons.get("highbeam");
            if (btn.isSelected()) {
                vehiculo.getLuces().LucesDelanteras(TipoLuz.ALTAS);
                controlButtons.get("headlights").setSelected(false);
            } else {
                vehiculo.getLuces().LucesDelanteras(TipoLuz.APAGADAS);
            }
        });

        // Direccionales
        controlButtons.get("leftturn").addActionListener(e -> {
            ModernToggleButton btn = controlButtons.get("leftturn");
            vehiculo.getLuces().activarDireccionalIzquierda(btn.isSelected());
            if (btn.isSelected()) {
                controlButtons.get("rightturn").setSelected(false);
            }
        });

        controlButtons.get("rightturn").addActionListener(e -> {
            ModernToggleButton btn = controlButtons.get("rightturn");
            vehiculo.getLuces().activarDireccionalDerecha(btn.isSelected());
            if (btn.isSelected()) {
                controlButtons.get("leftturn").setSelected(false);
            }
        });

        // Luces de emergencia
        controlButtons.get("hazards").addActionListener(e -> {
            ModernToggleButton btn = controlButtons.get("hazards");
            vehiculo.getLuces().lucesEmergencia(btn.isSelected());
        });

        // Control de climatización
        controlButtons.get("ac").addActionListener(e -> {
            ModernToggleButton btn = controlButtons.get("ac");
            if (btn.isSelected()) {
                vehiculo.getClimatizacion().encenderAireAcondicinado();
                controlButtons.get("heat").setSelected(false);
            } else {
                vehiculo.getClimatizacion().apagarClimatizacion();
            }
        });

        controlButtons.get("heat").addActionListener(e -> {
            ModernToggleButton btn = controlButtons.get("heat");
            if (btn.isSelected()) {
                vehiculo.getClimatizacion().encenderCalefaccion();
                controlButtons.get("ac").setSelected(false);
            } else {
                vehiculo.getClimatizacion().apagarClimatizacion();
            }
        });

        // Radio
        controlButtons.get("radio").addActionListener(e -> {
            ModernToggleButton btn = controlButtons.get("radio");
            if (btn.isSelected()) {
                vehiculo.getRadio().Encendida();
                vehiculo.getRadio().cambiarModo(Modo.FM);
            } else {
                vehiculo.getRadio().apagar();
            }
            updateAllDisplays();
        });
    }

    private void startUpdateCycle() {
        updateTimer = new Timer();
        updateTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    if (vehiculo.estaEncendido() && vehiculo.getKilometraje().getVelocidad() > 0) {
                        vehiculo.moverVehiculo(1);
                    }
                    updateAllDisplays();
                });
            }
        }, 500, 500); // Actualizar cada 500ms para fluidez
    }

    private void updateAllDisplays() {
        // Actualizar medidores
        speedGauge.setValue(vehiculo.getKilometraje().getVelocidad());

        double fuelLevel = vehiculo.getCombustible().getNivel();
        double capacity = vehiculo.getCombustible().getCapacidad();
        int fuelPercent = (int) ((fuelLevel / capacity) * 100);
        fuelGauge.setValue(fuelPercent);

        // Actualizar display digital
        updateDigitalDisplay(digitalDisplay);

        // Actualizar indicadores de estado
        updateStatusIndicators();
    }

    private void updateDigitalDisplay(JLabel display) {
        StringBuilder html = new StringBuilder("<html><div style='text-align: center;'>");

        // Estado del motor
        html.append("<div style='font-size: 16px; color: ")
                .append(vehiculo.estaEncendido() ? "#34C759" : "#FF3B30")
                .append(";'>")
                .append(vehiculo.estaEncendido() ? "🟢 ENGINE ON" : "🔴 ENGINE OFF")
                .append("</div><br>");

        // RPM
        html.append("<div style='font-size: 14px; color: #FFFFFF;'>")
                .append("RPM: ").append(vehiculo.getKilometraje().getRpm())
                .append("</div>");

        // Odómetro
        html.append("<div style='font-size: 12px; color: #98989D;'>")
                .append("ODO: ").append(String.format("%.1f km", vehiculo.getKilometraje().getTotalKm()))
                .append("</div>");

        // Combustible
        double fuelLevel = vehiculo.getCombustible().getNivel();
        html.append("<div style='font-size: 12px; color: #98989D;'>")
                .append("FUEL: ").append(String.format("%.1f L", fuelLevel))
                .append("</div>");

        // Estado de la radio
        if (vehiculo.getRadio().isEncendida()) {
            html.append("<div style='font-size: 12px; color: #007AFF;'>")
                    .append("📻 ").append(vehiculo.getRadio().getModoActual().name())
                    .append(" ").append(vehiculo.getRadio().getFrecuencia())
                    .append("</div>");
        }

        html.append("</div></html>");
        display.setText(html.toString());
    }

    private void updateStatusIndicators() {
        statusLights.get("engine").setState(vehiculo.estaEncendido());

        double fuelPercent = (vehiculo.getCombustible().getNivel() / vehiculo.getCombustible().getCapacidad()) * 100;
        statusLights.get("fuel").setState(fuelPercent < 20);

        statusLights.get("seatbelt").setState(!vehiculo.getCinturones().todosAbrochados());
        statusLights.get("alarm").setState(vehiculo.getAlarma().isAlarmaActiva());
    }

    private void showNotification(String title, String message) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    private void applyModernStyling() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Could not set system look and feel: " + e.getMessage());
        }
    }

    // === COMPONENTES PERSONALIZADOS MODERNOS ===
    private class ModernPanel extends JPanel {

        public ModernPanel() {
            this("");
        }

        public ModernPanel(String title) {
            setBackground(CARD_BG);
            setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(48, 48, 48), 1),
                    new EmptyBorder(15, 15, 15, 15)
            ));

            if (!title.isEmpty()) {
                setBorder(BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(new Color(48, 48, 48), 1),
                        title,
                        javax.swing.border.TitledBorder.CENTER,
                        javax.swing.border.TitledBorder.TOP,
                        new Font("SF Pro Display", Font.PLAIN, 14),
                        TEXT_SECONDARY
                ));
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Fondo con esquinas redondeadas
            RoundRectangle2D roundRect = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 12, 12);
            g2.setColor(getBackground());
            g2.fill(roundRect);

            g2.dispose();
        }
    }

    private class ModernButton extends JButton {

        private Color accentColor;

        public ModernButton(String text, Color accentColor) {
            super(text);
            this.accentColor = accentColor;
            setupModernButton();
        }

        private void setupModernButton() {
            setFont(new Font("SF Pro Display", Font.PLAIN, 12));
            setForeground(TEXT_PRIMARY);
            setBackground(accentColor);
            setBorder(new EmptyBorder(8, 16, 8, 16));
            setFocusPainted(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            Color bgColor = getModel().isPressed() ? accentColor.darker()
                    : getModel().isRollover() ? accentColor.brighter() : accentColor;

            RoundRectangle2D roundRect = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 8, 8);
            g2.setColor(bgColor);
            g2.fill(roundRect);

            g2.dispose();
            super.paintComponent(g);
        }
    }

    private class ModernToggleButton extends JToggleButton {

        private String icon;

        public ModernToggleButton(String text, String icon) {
            super(text);
            this.icon = icon;
            setupModernToggle();
        }

        private void setupModernToggle() {
            setFont(new Font("SF Pro Display", Font.PLAIN, 11));
            setForeground(TEXT_PRIMARY);
            setBackground(CARD_BG);
            setBorder(new EmptyBorder(8, 12, 8, 12));
            setFocusPainted(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            setText(icon + " " + getText());
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            Color bgColor = isSelected() ? BUTTON_ACTIVE : CARD_BG;
            Color borderColor = isSelected() ? ACCENT_BLUE : new Color(48, 48, 48);

            RoundRectangle2D roundRect = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 8, 8);
            g2.setColor(bgColor);
            g2.fill(roundRect);

            g2.setColor(borderColor);
            g2.setStroke(new BasicStroke(1));
            g2.draw(roundRect);

            g2.dispose();
            super.paintComponent(g);
        }
    }

    private class ModernSlider extends JSlider {

        private Color accentColor;

        public ModernSlider(int min, int max, int value, Color accentColor) {
            super(min, max, value);
            this.accentColor = accentColor;
            setupModernSlider();
        }

        private void setupModernSlider() {
            setBackground(CARD_BG);
            setForeground(accentColor);
            setUI(new ModernSliderUI());
        }

        private class ModernSliderUI extends BasicSliderUI {

            @Override
            public void paintTrack(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                Rectangle trackBounds = trackRect;

                // Dibujar el fondo del track
                g2.setColor(new Color(48, 48, 48));
                g2.fillRoundRect(trackBounds.x, trackBounds.y + trackBounds.height / 2 - 2,
                        trackBounds.width, 4, 4, 4);

                // Calcular el ancho del progreso
                int progressWidth = 0;
                if (slider.getOrientation() == JSlider.HORIZONTAL) {
                    double percent = (double) (slider.getValue() - slider.getMinimum())
                            / (slider.getMaximum() - slider.getMinimum());
                    progressWidth = (int) (trackBounds.width * percent);
                } else {
                    double percent = (double) (slider.getValue() - slider.getMinimum())
                            / (slider.getMaximum() - slider.getMinimum());
                    progressWidth = (int) (trackBounds.height * percent);
                }

                // Dibujar el progreso
                g2.setColor(accentColor);
                if (slider.getOrientation() == JSlider.HORIZONTAL) {
                    g2.fillRoundRect(trackBounds.x, trackBounds.y + trackBounds.height / 2 - 2,
                            progressWidth, 4, 4, 4);
                } else {
                    // Para sliders verticales
                    int yPos = trackBounds.y + trackBounds.height - progressWidth;
                    g2.fillRoundRect(trackBounds.x + trackBounds.width / 2 - 2, yPos,
                            4, progressWidth, 4, 4);
                }

                g2.dispose();
            }

            @Override
            public void paintThumb(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                Rectangle thumbBounds = thumbRect;
                g2.setColor(accentColor);
                g2.fillOval(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height);

                g2.setColor(TEXT_PRIMARY);
                g2.setStroke(new BasicStroke(2));
                g2.drawOval(thumbBounds.x + 1, thumbBounds.y + 1,
                        thumbBounds.width - 2, thumbBounds.height - 2);

                g2.dispose();
            }
        }
    }

    private class ModernGaugePanel extends JPanel {

        private String title;
        private String unit;
        private int minValue;
        private int maxValue;
        private int currentValue;
        private Color accentColor;

        public ModernGaugePanel(String title, String unit, int min, int max, Color accentColor) {
            this.title = title;
            this.unit = unit;
            this.minValue = min;
            this.maxValue = max;
            this.currentValue = min;
            this.accentColor = accentColor;

            setPreferredSize(new Dimension(220, 200));
            setBackground(CARD_BG);
            setBorder(new EmptyBorder(10, 10, 10, 10));
        }

        public void setValue(int value) {
            this.currentValue = Math.max(minValue, Math.min(maxValue, value));
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int centerX = getWidth() / 2;
            int centerY = getHeight() / 2 + 20;
            int radius = Math.min(getWidth(), getHeight()) / 2 - 40;

            // Fondo del gauge
            g2.setColor(new Color(38, 38, 38));
            g2.setStroke(new BasicStroke(8));
            g2.drawArc(centerX - radius, centerY - radius, radius * 2, radius * 2, 180, 180);

            // Progreso del gauge
            double progress = (double) (currentValue - minValue) / (maxValue - minValue);
            int arcAngle = (int) (180 * progress);

            g2.setColor(accentColor);
            g2.setStroke(new BasicStroke(8));
            g2.drawArc(centerX - radius, centerY - radius, radius * 2, radius * 2, 180, arcAngle);

            // Marcas del gauge
            g2.setColor(TEXT_SECONDARY);
            g2.setStroke(new BasicStroke(2));
            for (int i = 0; i <= 10; i++) {
                double angle = Math.toRadians(180 - i * 18);
                int x1 = (int) (centerX + (radius - 15) * Math.cos(angle));
                int y1 = (int) (centerY + (radius - 15) * Math.sin(angle));
                int x2 = (int) (centerX + (radius - 5) * Math.cos(angle));
                int y2 = (int) (centerY + (radius - 5) * Math.sin(angle));
                g2.drawLine(x1, y1, x2, y2);
            }

            // Valor digital en el centro
            g2.setColor(TEXT_PRIMARY);
            g2.setFont(new Font("SF Pro Display", Font.BOLD, 24));
            String valueText = String.valueOf(currentValue);
            FontMetrics fm = g2.getFontMetrics();
            int textX = centerX - fm.stringWidth(valueText) / 2;
            int textY = centerY - 10;
            g2.drawString(valueText, textX, textY);

            // Unidad
            g2.setColor(TEXT_SECONDARY);
            g2.setFont(new Font("SF Pro Display", Font.PLAIN, 12));
            fm = g2.getFontMetrics();
            textX = centerX - fm.stringWidth(unit) / 2;
            textY = centerY + 10;
            g2.drawString(unit, textX, textY);

            // Título
            g2.setColor(TEXT_SECONDARY);
            g2.setFont(new Font("SF Pro Display", Font.PLAIN, 14));
            fm = g2.getFontMetrics();
            textX = centerX - fm.stringWidth(title) / 2;
            textY = 25;
            g2.drawString(title, textX, textY);

            g2.dispose();
        }
    }

    private class StatusIndicator extends JPanel {

        private String label;
        private String icon;
        private boolean isActive;

        public StatusIndicator(String label, String icon) {
            this.label = label;
            this.icon = icon;
            this.isActive = false;

            setPreferredSize(new Dimension(80, 60));
            setBackground(CARD_BG);
            setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(48, 48, 48), 1),
                    new EmptyBorder(5, 5, 5, 5)
            ));
        }

        public void setState(boolean active) {
            this.isActive = active;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Fondo con esquinas redondeadas
            RoundRectangle2D roundRect = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 8, 8);
            Color bgColor = isActive ? new Color(255, 59, 48, 30) : CARD_BG;
            g2.setColor(bgColor);
            g2.fill(roundRect);

            // Borde
            Color borderColor = isActive ? ACCENT_RED : new Color(48, 48, 48);
            g2.setColor(borderColor);
            g2.setStroke(new BasicStroke(1));
            g2.draw(roundRect);

            // Icono
            g2.setColor(isActive ? ACCENT_RED : TEXT_SECONDARY);
            g2.setFont(new Font("Apple Color Emoji", Font.PLAIN, 16));
            FontMetrics fm = g2.getFontMetrics();
            int iconX = getWidth() / 2 - fm.stringWidth(icon) / 2;
            int iconY = getHeight() / 2 - 5;
            g2.drawString(icon, iconX, iconY);

            // Etiqueta
            g2.setColor(isActive ? TEXT_PRIMARY : TEXT_SECONDARY);
            g2.setFont(new Font("SF Pro Display", Font.PLAIN, 8));
            fm = g2.getFontMetrics();
            int labelX = getWidth() / 2 - fm.stringWidth(label) / 2;
            int labelY = getHeight() - 8;
            g2.drawString(label, labelX, labelY);

            g2.dispose();
        }
    }

    // === MÉTODOS ADICIONALES ===
    private void createAdvancedFeatures() {
        // Panel de configuración avanzada
        JPanel advancedPanel = new ModernPanel("⚙️ ADVANCED SETTINGS");
        advancedPanel.setLayout(new GridLayout(2, 3, 10, 10));

        // Modo de conducción
        JComboBox<String> drivingMode = new JComboBox<>(new String[]{
            "🏎️ Sport", "🌿 Eco", "❄️ Snow", "🏔️ Off-Road"
        });
        drivingMode.setBackground(CARD_BG);
        drivingMode.setForeground(TEXT_PRIMARY);

        // Control de cinturones
        JPanel seatbeltPanel = new JPanel(new GridLayout(2, 3, 5, 5));
        seatbeltPanel.setBackground(CARD_BG);
        seatbeltPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(48, 48, 48), 1),
                "🔒 SEATBELTS",
                javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.TOP,
                new Font("SF Pro Display", Font.PLAIN, 12),
                TEXT_SECONDARY
        ));

        Asiento[] asientos = Asiento.values();
        JCheckBox[] seatbeltChecks = new JCheckBox[asientos.length];

        for (int i = 0; i < asientos.length; i++) {
            seatbeltChecks[i] = new JCheckBox(asientos[i].getEstado());
            seatbeltChecks[i].setBackground(CARD_BG);
            seatbeltChecks[i].setForeground(TEXT_SECONDARY);
            seatbeltChecks[i].setFont(new Font("SF Pro Display", Font.PLAIN, 10));

            final int seatIndex = i;
            seatbeltChecks[i].addActionListener(e -> {
                Asiento asiento = Asiento.values()[seatIndex];
                if (seatbeltChecks[seatIndex].isSelected()) {
                    vehiculo.getCinturones().Abrochar(asiento);
                } else {
                    vehiculo.getCinturones().Desabrochar(asiento);
                }
                updateAllDisplays();
            });

            seatbeltPanel.add(seatbeltChecks[i]);
        }

        // Panel de puertas
        JPanel doorsPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        doorsPanel.setBackground(CARD_BG);
        doorsPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(48, 48, 48), 1),
                "🚪 DOORS",
                javax.swing.border.TitledBorder.CENTER,
                javax.swing.border.TitledBorder.TOP,
                new Font("SF Pro Display", Font.PLAIN, 12),
                TEXT_SECONDARY
        ));

        String[] doorNames = {"Driver", "Passenger", "Rear L", "Rear R"};
        for (int i = 0; i < 4; i++) {
            ModernToggleButton doorBtn = new ModernToggleButton(doorNames[i], "🚪");
            doorsPanel.add(doorBtn);
        }

        // Seguridad
        JComboBox<Seguridad> securityCombo = new JComboBox<>(Seguridad.values());
        securityCombo.setBackground(CARD_BG);
        securityCombo.setForeground(TEXT_PRIMARY);
        securityCombo.addActionListener(e -> {
            Seguridad modo = (Seguridad) securityCombo.getSelectedItem();
            vehiculo.getAlarma().setSeguridad(modo);
            updateAllDisplays();
        });

        advancedPanel.add(drivingMode);
        advancedPanel.add(seatbeltPanel);
        advancedPanel.add(doorsPanel);
        advancedPanel.add(securityCombo);

        // Agregar al panel de control
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(10, 10, 10, 10);
        controlPanel.add(advancedPanel, gbc);
    }

    private void addKeyboardShortcuts() {
        // Atajos de teclado para control rápido
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getRootPane().getActionMap();

        // Space para ignición
        inputMap.put(KeyStroke.getKeyStroke("SPACE"), "toggle_ignition");
        actionMap.put("toggle_ignition", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ignitionButton.doClick();
            }
        });

        // Flechas para acelerar/frenar
        inputMap.put(KeyStroke.getKeyStroke("UP"), "accelerate");
        actionMap.put("accelerate", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int current = acceleratorSlider.getValue();
                acceleratorSlider.setValue(Math.min(100, current + 5));
            }
        });

        inputMap.put(KeyStroke.getKeyStroke("DOWN"), "brake");
        actionMap.put("brake", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int current = acceleratorSlider.getValue();
                acceleratorSlider.setValue(Math.max(0, current - 10));
            }
        });

        // L para luces
        inputMap.put(KeyStroke.getKeyStroke("L"), "toggle_lights");
        actionMap.put("toggle_lights", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlButtons.get("headlights").doClick();
            }
        });
    }

    private void setupAnimations() {
        // Efectos de animación para indicadores
        Timer animationTimer = new Timer();
        animationTimer.scheduleAtFixedRate(new TimerTask() {
            private boolean blink = false;

            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    blink = !blink;

                    // Parpadeo de direccionales
                    if (vehiculo.getLuces().isDireccionalIzquierda()
                            || vehiculo.getLuces().isDireccionalDerecha()
                            || vehiculo.getLuces().isEmergencia()) {

                        ModernToggleButton leftBtn = controlButtons.get("leftturn");
                        ModernToggleButton rightBtn = controlButtons.get("rightturn");
                        ModernToggleButton hazardBtn = controlButtons.get("hazards");

                        if (vehiculo.getLuces().isEmergencia()) {
                            leftBtn.setVisible(blink);
                            rightBtn.setVisible(blink);
                            hazardBtn.setBackground(blink ? ACCENT_RED : CARD_BG);
                        } else if (vehiculo.getLuces().isDireccionalIzquierda()) {
                            leftBtn.setBackground(blink ? ACCENT_GREEN : CARD_BG);
                        } else if (vehiculo.getLuces().isDireccionalDerecha()) {
                            rightBtn.setBackground(blink ? ACCENT_GREEN : CARD_BG);
                        }
                    }

                    // Parpadeo de indicadores críticos
                    double fuelPercent = (vehiculo.getCombustible().getNivel()
                            / vehiculo.getCombustible().getCapacidad()) * 100;
                    if (fuelPercent < 10) {
                        statusLights.get("fuel").setBackground(blink ? ACCENT_RED : CARD_BG);
                    }
                });
            }
        }, 500, 500);
    }

    @Override
    public void dispose() {
        if (updateTimer != null) {
            updateTimer.cancel();
        }
        super.dispose();
    }

    // === MÉTODO MAIN ===
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Configurar propiedades del sistema para mejor apariencia
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            System.setProperty("apple.awt.application.name", "Car Simulator");

            try {
                // Intentar usar el Look and Feel nativo del sistema
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception e) {
                System.out.println("Could not set system look and feel: " + e.getMessage());
            }

            // Crear y mostrar el simulador
            CarroSimulador simulador = new CarroSimulador();

            // Mensaje de bienvenida
            JOptionPane.showMessageDialog(simulador,
                    "🚗 Bienvenido al Simulador de Carro Moderno\n"
                    + "Usa ESPACIO para encender/apagar\n"
                    + "Flechas ↑↓ para acelerar/frenar\n"
                    + "L para luces\n\n"
                    + "¡Disfruta la experiencia!",
                    "Car Simulator",
                    JOptionPane.INFORMATION_MESSAGE);
        });
    }
}
