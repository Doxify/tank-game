package main.java.edu.csc413.TankGame.graphics;

import main.java.edu.csc413.TankGame.entity.movable.Tank;
import main.java.edu.csc413.TankGame.level.Level;
import main.java.edu.csc413.TankGame.util.GameConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Screen extends JPanel {

    private final Level level;
    private final BufferedImage image;

    public Screen(Level level) {
        this.level = level;
        this.image = new BufferedImage(GameConstants.WORLD_WIDTH, GameConstants.WORLD_HEIGHT, BufferedImage.TYPE_INT_ARGB);
    }

    /**
     * Creates a split screen of the current Level buffer.
     * @return buffer
     */
    private void generateSplitScreen(Graphics2D g) {
        Tank tank1 = level.getTank(0);
        Tank tank2 = level.getTank(1);
        BufferedImage left = image.getSubimage(tank1.getCameraX(), tank1.getCameraY(), GameConstants.SCREEN_WIDTH / 2, GameConstants.SCREEN_HEIGHT);
        BufferedImage right = image.getSubimage(tank2.getCameraX(), tank2.getCameraY(), GameConstants.SCREEN_WIDTH / 2, GameConstants.SCREEN_HEIGHT);

        g.drawImage(left, 0, 0, null);
        g.drawImage(right, (GameConstants.SCREEN_WIDTH / 2) + 4, 0, null);
    }

    private BufferedImage generateMiniMap() {
        return image.getSubimage(0, 0, GameConstants.WORLD_WIDTH, GameConstants.WORLD_WIDTH);
        // g.scale(0.10, 0.10);
        // g.drawImage(minimap, GameConstants.SCREEN_WIDTH / 2 * 10, 0, null);
    }

    public void render(Graphics graphics) {
        Graphics2D g2 = (Graphics2D) graphics;
        Graphics buffer = image.createGraphics();

        buffer.setColor(Color.BLACK);
        buffer.fillRect(0, 0, GameConstants.WORLD_WIDTH, GameConstants.WORLD_HEIGHT);

        level.update();
        level.render(buffer);

        generateSplitScreen(g2);

        BufferedImage miniMap = generateMiniMap();
        g2.scale(0.10, 0.10);
        g2.drawImage(miniMap, Math.round(GameConstants.SCREEN_WIDTH * 10f + 100), 0, null);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        render(graphics);
    }

}
