package main.java.edu.csc413.TankGame.Panels;

import main.java.edu.csc413.TankGame.Launcher;
import main.java.edu.csc413.TankGame.Utilities.GameConstants;
import main.java.edu.csc413.TankGame.Utilities.Resources;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

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
//        loadBackgroundImage();
        this.background = Resources.titleImage;

        // Configuring Buttons
        configureButtons();
    }

    private void loadBackgroundImage() {
        try {
            this.background = ImageIO.read(this.getClass().getClassLoader().getResource("title.bmp"));
        } catch(IOException e) {
            System.out.println("Error while trying to load StartPanel background.");
            System.out.println(e.getMessage());
            System.exit(-3);
        }
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
