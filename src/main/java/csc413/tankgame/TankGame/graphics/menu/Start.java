package csc413.tankgame.TankGame.graphics.menu;

import csc413.tankgame.TankGame.Launcher;
import csc413.tankgame.TankGame.util.Assets;
import csc413.tankgame.TankGame.util.GameConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Start extends JPanel {

    private static final BufferedImage background = Assets.menuImage;
    private final Launcher launcher;
    private JButton start;
    private JButton exit;
    private JButton music;

    public Start(Launcher launcher) {
        this.launcher = launcher;

        // Configuring JPanel
        this.setBackground(Color.BLACK);
        this.setLayout(null);

        // Configuring Buttons
        configureButtons();
    }

    private void configureButtons() {
        // Start Button
        this.start = new JButton("Start");
        this.start.setFont(new Font("Helvetica", Font.BOLD, 24));
        this.start.setBounds(175, GameConstants.START_MENU_SCREEN_HEIGHT / 2, 150, 50);
        this.start.addActionListener((actionEvent -> {
            this.launcher.setPanel("game");
        }));
        // Soundtrack Button
        this.music = new JButton("Music " + (this.launcher.getSoundEngine().getMusicEnabled() ? "Off" : "On"));
        this.music.setFont(new Font("Arial", Font.BOLD, 24));
        this.music.setBounds(175, (GameConstants.START_MENU_SCREEN_HEIGHT / 2) + 75, 150, 50);
        this.music.addActionListener((actionEvent -> {
            this.launcher.getSoundEngine().toggleMusic();
            this.music.setText("Music " + (this.launcher.getSoundEngine().getMusicEnabled() ? "Off" : "On"));
        }));
        // Exit Button
        this.exit = new JButton("Exit");
        this.exit.setFont(new Font("Helvetica", Font.BOLD, 24));
        this.exit.setBounds(175, (GameConstants.START_MENU_SCREEN_HEIGHT / 2) + 150, 150, 50);
        this.exit.addActionListener((actionEvent -> {
            this.launcher.close();
        }));


        // Adding buttons to the Panel
        this.add(exit);
        this.add(start);
        this.add(music);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        Graphics2D g2 = (Graphics2D) graphics;
        g2.drawImage(background, 0, 0, null);
    }

}
