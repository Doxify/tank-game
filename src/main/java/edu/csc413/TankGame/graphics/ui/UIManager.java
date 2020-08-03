package main.java.edu.csc413.TankGame.graphics.ui;

import main.java.edu.csc413.TankGame.util.GameConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class UIManager {

    private final int width = GameConstants.GAME_WIDTH;
    private final int height = GameConstants.GAME_HEIGHT;

    private static final JFrame frame = null;
    private static final JPanel game = null;
    private static final JPanel ui = null;

    private final BufferedImage image;
    private final List<UIPanel> panels = new ArrayList<>();

    public UIManager() {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    }

    public void addPanel(UIPanel uiPanel) {
        this.panels.add(uiPanel);
    }

    private void update() {
        for(UIPanel uiPanel : panels) {
            uiPanel.update();
        }
    }

    private void render(Graphics graphics) {
        for(UIPanel uiPanel : panels) {
            uiPanel.render(graphics);
        }
    }

//    @Override
//    public void paintComponent(Graphics graphics) {
//        Graphics2D g2 = (Graphics2D) graphics;
//        Graphics buffer = image.createGraphics();
//
//        buffer.setColor(Color.BLACK);
//        buffer.fillRect(0, 0, width, height);
//
//        update();
//        render(buffer);
//
//        // Drawing to the screen
//        g2.drawImage(image, 0, 0, width, height, null);
//
//    }

    public static void main(String[] args) {
        UIManager uiManager = new UIManager();
        frame.setResizable(false);
        frame.setTitle("TITLE");
        frame.add(game);
        frame.add(ui);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
