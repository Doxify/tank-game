package main.java.edu.csc413.TankGame.graphics;

import main.java.edu.csc413.TankGame.entity.movable.Tank;
import main.java.edu.csc413.TankGame.level.Level;
import main.java.edu.csc413.TankGame.util.GameConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Screen extends JPanel {

    private final int width = GameConstants.SCREEN_WIDTH;
    private final int height = GameConstants.SCREEN_HEIGHT;
    private final Level level;
    private final BufferedImage image;

    public Screen(Level level) {
        this.level = level;
        this.image = new BufferedImage(level.getWidth(), level.getHeight(), BufferedImage.TYPE_INT_ARGB);
    }


    public void render(Graphics graphics) {
        this.level.render(graphics);
    }

    /**
     * Creates a split screen of the current Level buffer.
     * @return buffer
     */
    private Graphics2D getSplitScreen() {
        Graphics2D buffer = this.image.createGraphics();
        Tank tank1 = this.level.getTank(0);
        Tank tank2 = this.level.getTank(1);
        BufferedImage left = this.image.getSubimage(tank1.getCameraX(), tank1.getCameraY(), width / 2, height);
        BufferedImage right = this.image.getSubimage(tank2.getCameraX(), tank2.getCameraY(), width / 2, height);

        buffer.drawImage(left, 0, 0, null);
        buffer.drawImage(right, (width / 2) + 4, 0, null);

        return buffer;
    }

//    @Override
//    public void paintComponent(Graphics graphics) {
//        Graphics2D g = (Graphics2D) graphics;
//        Graphics2D buffer = this.image.createGraphics();
//
//        buffer.setColor(Color.BLACK);
//        buffer.fillRect(0, 0, level.getWidth(), level.getHeight());
//
//        renderEntities(buffer);
////        renderSplitScreen(buffer);
//
//        Tank tank1 = this.level.getTank(0);
//        Tank tank2 = this.level.getTank(1);
//
//        BufferedImage left = this.image.getSubimage(tank1.getCameraX(), tank1.getCameraY(), width / 2, height);
//        BufferedImage right = this.image.getSubimage(tank2.getCameraX(), tank2.getCameraY(), width / 2, height);
////        BufferedImage minimap = this.image.getSubimage(0, 0, GameConstants.WORLD_WIDTH, GameConstants.WORLD_WIDTH);
//
//        g.drawImage(left, 0, 0, null);
//        g.drawImage(right, (width / 2) + 4, 0, null);
//
////        g.scale(0.10, 0.10);
////        g.drawImage(minimap, GameConstants.SCREEN_WIDTH / 2 * 10, 0, null);
//
//    }

}
