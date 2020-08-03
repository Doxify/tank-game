package csc413.tankgame.TankGame.menu;

import csc413.tankgame.TankGame.Launcher;
import csc413.tankgame.TankGame.graphics.Assets;
import csc413.tankgame.TankGame.util.GameConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Start extends JPanel {

    private BufferedImage background;
    private JButton start;
    private JButton exit;
    private Launcher launcher;

    public Start(Launcher launcher) {
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
        this.start = new JButton("Start");
        this.start.setFont(new Font("Helvetica", Font.BOLD, 24));
        this.start.setBounds(200, GameConstants.START_MENU_SCREEN_HEIGHT / 2, 150, 50);
        this.start.addActionListener((actionEvent -> {
            this.launcher.setPanel("game");
        }));

        // Exit Button
        this.exit = new JButton("Exit");
        this.exit.setFont(new Font("Courier New", Font.BOLD, 24));
        this.exit.setBounds(GameConstants.START_MENU_SCREEN_WIDTH / 2, GameConstants.START_MENU_SCREEN_HEIGHT / 2, 150, 50);
        this.exit.addActionListener((actionEvent -> {
            this.launcher.setPanel("exit");
        }));

        // Adding buttons to the Panel
        this.add(start);
//        this.add(exit);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        Graphics2D g2 = (Graphics2D) graphics;
        g2.drawImage(this.background, 0, 0, null);
    }

}
