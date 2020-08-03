package csc413.tankgame.TankGame.graphics;

import csc413.tankgame.TankGame.entity.movable.Tank;
import csc413.tankgame.TankGame.level.Level;
import csc413.tankgame.TankGame.util.GameConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Screen extends JPanel {

    private final Level level;
    private final BufferedImage game;
//    private final BufferedImage ui;

    // User Interface Variables
    private final int xUiOffset = Math.round(GameConstants.SCREEN_WIDTH * 8.4f);
    private final int yUiOffset = 10;


    public Screen(Level level) {
        this.level = level;
        this.game = new BufferedImage(GameConstants.WORLD_WIDTH, GameConstants.WORLD_HEIGHT, BufferedImage.TYPE_INT_ARGB);
    }

    /**
     * Creates a split screen of the current Level buffer.
     * @return buffer
     */
    private void renderSplitScreen(Graphics2D g) {
        Tank tank1 = level.getTank(0);
        Tank tank2 = level.getTank(1);
        BufferedImage left = game.getSubimage(tank1.getCameraX(), tank1.getCameraY(), GameConstants.SCREEN_WIDTH / 2, GameConstants.SCREEN_HEIGHT);
        BufferedImage right = game.getSubimage(tank2.getCameraX(), tank2.getCameraY(), GameConstants.SCREEN_WIDTH / 2, GameConstants.SCREEN_HEIGHT);

        g.drawImage(left, 0, 0, null);
        g.drawImage(right, (GameConstants.SCREEN_WIDTH / 2) + 4, 0, null);
    }

    private void renderMiniMap(Graphics2D g) {
        BufferedImage miniMap = game.getSubimage(0, 0, GameConstants.WORLD_WIDTH, GameConstants.WORLD_WIDTH);
        g.scale(0.12, 0.12);
        g.drawImage(miniMap, xUiOffset, 0, null);
    }

//    private void renderTankData(Graphics2D g) {
//        BufferedImage tank1 = new BufferedImage()
//    }

    @Override
    public void paintComponent(Graphics graphics) {
        Graphics2D g2 = (Graphics2D) graphics;
        Graphics gameBuffer = game.createGraphics();
//        Graphics uiBuffer = ui.createGraphics();

        gameBuffer.setColor(Color.BLACK);
        gameBuffer.fillRect(0, 0, GameConstants.WORLD_WIDTH, GameConstants.WORLD_HEIGHT);

//        uiBuffer.setColor(Color.BLACK);
//        uiBuffer.fillRect(0, 0, GameConstants.GAME_WIDTH, GameConstants.GAME_HEIGHT);

        // Rendering Level
        level.render(gameBuffer);

        // Rendering User Interface
        renderSplitScreen(g2);
        renderMiniMap(g2);

    }

}
