package csc413.tankgame.TankGame.graphics.menu;

import csc413.tankgame.TankGame.Launcher;
import csc413.tankgame.TankGame.util.Assets;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class End extends JPanel {


    private static final BufferedImage background = Assets.endImage;
    private final Launcher launcher;
    private JButton exit;

    public End(Launcher launcher) {
        this.launcher = launcher;

        // Configuring JPanel
        this.setBackground(Color.BLACK);
        this.setLayout(null);

        // Configuring Buttons
        configureButtons();
    }

    private void configureButtons() {
        // Exit Button
        this.exit = new JButton("Exit");
        this.exit.setSize(new Dimension(200, 100));
        this.exit.setFont(new Font("Courier New", Font.BOLD, 24));
        this.exit.setBounds(150, 400, 150, 50);
        this.exit.addActionListener((actionEvent -> {
            this.launcher.close();
        }));

        // Adding buttons to the Panel
        this.add(exit);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        Graphics2D g2 = (Graphics2D) graphics;
        g2.drawImage(background, 0, 0, null);
    }
}
