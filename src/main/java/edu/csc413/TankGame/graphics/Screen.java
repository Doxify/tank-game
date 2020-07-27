package main.java.edu.csc413.TankGame.graphics;

import main.java.edu.csc413.TankGame.entity.movable.Tank;
import main.java.edu.csc413.TankGame.level.Level;
import main.java.edu.csc413.TankGame.util.GameConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Screen extends JPanel {

    private final int width;
    private final int height;
    private final Level level;
    private final BufferedImage image;

    public Screen(Level level) {
        this.width = GameConstants.SCREEN_WIDTH;
        this.height = GameConstants.SCREEN_HEIGHT;
        this.level = level;
        this.image = new BufferedImage(level.getWidth(), level.getHeight(), BufferedImage.TYPE_INT_ARGB);
    }

    public BufferedImage getImage() {
        return this.image;
    }

    /**
     * Renders all entities to the world buffer.
     * @param buffer to render to
     */
    private void renderEntities(Graphics2D buffer) {
        this.level.render(buffer);
    }

    /**
     * Creates a split screen on the world buffer.
     * @param buffer
     */
    private void renderSplitScreen(Graphics2D buffer) {
        Tank tank1 = this.level.getTank(0);
        Tank tank2 = this.level.getTank(1);

        BufferedImage left = this.image.getSubimage(tank1.getCameraX(), tank1.getCameraY(), width / 2, height);
        BufferedImage right = this.image.getSubimage(tank2.getCameraX(), tank2.getCameraY(), width / 2, height);

        buffer.drawImage(left, 0, 0, null);
        buffer.drawImage(right, (width / 2) + 4, 0, null);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;
        Graphics2D buffer = this.image.createGraphics();

        buffer.setColor(Color.BLACK);
        buffer.fillRect(0, 0, level.getWidth(), level.getHeight());

        renderEntities(buffer);
//        renderSplitScreen(buffer);

        Tank tank1 = this.level.getTank(0);
        Tank tank2 = this.level.getTank(1);

        BufferedImage left = this.image.getSubimage(tank1.getCameraX(), tank1.getCameraY(), width / 2, height);
        BufferedImage right = this.image.getSubimage(tank2.getCameraX(), tank2.getCameraY(), width / 2, height);

        g.drawImage(left, 0, 0, null);
        g.drawImage(right, (width / 2) + 4, 0, null);

//        g.drawImage(this.image, 0, 0, null);

    }

}
