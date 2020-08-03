package csc413.tankgame.TankGame.menu;

import csc413.tankgame.TankGame.Launcher;
import csc413.tankgame.TankGame.graphics.Assets;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class End extends JPanel {


    private BufferedImage background;
    private JButton start;
    private JButton exit;
    private Launcher launcher;

    public End(Launcher launcher) {
        this.launcher = launcher;

        // Configuring JPanel
        this.setBackground(Color.BLACK);
        this.setLayout(null);

        // Loading Background
        this.background = Assets.titleImage;

        // Configuring Buttons
        configureButtons();
    }

    private void configureButtons() {
        // Start Button
        this.start = new JButton("Play Again");
        this.start.setFont(new Font("Courier New", Font.BOLD, 24));
        this.start.setBounds(150, 300, 150, 50);
        this.start.addActionListener((actionEvent -> {
            this.launcher.setPanel("game");
        }));

        // Exit Button
        this.exit = new JButton("Exit");
        this.exit.setSize(new Dimension(200, 100));
        this.exit.setFont(new Font("Courier New", Font.BOLD, 24));
        this.exit.setBounds(150, 400, 150, 50);
        this.exit.addActionListener((actionEvent -> {
            this.launcher.setPanel("exit");
        }));

        // Adding buttons to the Panel
        this.add(start);
        this.add(exit);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        Graphics2D g2 = (Graphics2D) graphics;
        g2.drawImage(this.background, 0, 0, null);
    }
}
