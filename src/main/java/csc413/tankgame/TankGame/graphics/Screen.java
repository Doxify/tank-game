package csc413.tankgame.TankGame.graphics;

import csc413.tankgame.TankGame.entity.boost.Boost;
import csc413.tankgame.TankGame.entity.movable.Tank;
import csc413.tankgame.TankGame.level.Level;
import csc413.tankgame.TankGame.util.Assets;
import csc413.tankgame.TankGame.util.GameConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Screen extends JPanel {

    private final Level level;
    private final BufferedImage game;
    private final BufferedImage ui;

    public Screen(Level level) {
        this.level = level;
        this.game = new BufferedImage(GameConstants.WORLD_WIDTH, GameConstants.WORLD_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        this.ui = new BufferedImage(GameConstants.GAME_WIDTH - GameConstants.SCREEN_WIDTH, GameConstants.GAME_HEIGHT, BufferedImage.TYPE_INT_ARGB);
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

    private void renderMiniMap(Graphics buffer) {
        Graphics2D g2 = (Graphics2D) buffer;
        BufferedImage miniMap = game.getSubimage(0, 0, GameConstants.WORLD_WIDTH, GameConstants.WORLD_WIDTH);
        g2.scale(0.12, 0.12);
        g2.drawImage(miniMap, 40, 40, null);
    }

    private BufferedImage generateTankHealthData() {
        BufferedImage image = new BufferedImage(GameConstants.GAME_WIDTH - GameConstants.SCREEN_WIDTH, 200, BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();
        int yOffset = 30; // Used to evenly space data from Tank1 and Tank2.

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, image.getWidth(), image.getHeight());

        g.setColor(Color.WHITE);
        g.setFont(new Font("Helvetica", Font.BOLD, 25));
        g.drawString("Tank Health", 5, 25);
        g.setFont(new Font("Helvetica", Font.PLAIN, 20));

        // Looping through tanks and rendering each one.
        for(Tank tank : level.getTanks()) {
            g.drawImage(tank.getImage(), 5, yOffset, null);

            // Health Points + Lives
            g.drawString("HP: " + tank.getHealth(),tank.getImage().getWidth() + 10, (yOffset - 3) + tank.getImage().getHeight() / 2 );
            g.drawString("Lives: ",tank.getImage().getWidth() + 105,(yOffset - 3) + tank.getImage().getHeight() / 2 );
            for(int i = 0; i < Tank.MAX_LIVES; i++) {
                if(tank.getLives() > i) {
                    g.setColor(Color.RED);
                } else {
                    g.setColor(Color.GRAY);
                }
                g.drawString("|",tank.getImage().getWidth() + 150 + (i * 10), (yOffset - 3) + tank.getImage().getHeight() / 2 );
            }

            // Healthbar
            g.setColor(Color.RED);
            g.fillRect(tank.getImage().getWidth() + 10, (yOffset + 3) + tank.getImage().getHeight() / 2, 9 * tank.getHealth(), 15);
            g.setColor(Color.WHITE);
            g.drawRect(tank.getImage().getWidth() + 10, (yOffset + 3) + tank.getImage().getHeight() / 2, 180, 15);

            // Increase the yOffset
            yOffset*=3;
        }

        return image;
    }


    private BufferedImage generateActiveBoosts() {
        BufferedImage image = new BufferedImage(GameConstants.GAME_WIDTH - GameConstants.SCREEN_WIDTH, 500, BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, image.getWidth(), image.getHeight());

        g.setColor(Color.WHITE);
        g.setFont(new Font("Helvetica", Font.BOLD, 25));
        g.drawString("Active Boosts", 5, 22);
        g.setFont(new Font("Helvetica", Font.PLAIN, 18));

        // Looping through tanks and rendering each one.
        int yOffset = 20; // Used to evenly space data from Tank1 and Tank2.
        int line = 1;
        for(Boost boost : level.getBoosts()) {
            if(boost.isActive()) {
                int tank = (boost.getTank() == level.getTank(0) ? 1 : 2);
                g.setColor(tank == 1 ? Color.RED : Color.BLUE);
                g.drawString("T" + tank + ": " + boost.toString(), 5, 30 + yOffset * line);
                line++;
            }
        }

        return image;
    }

    private void renderGameScreen(Graphics2D g2) {
        Graphics gameBuffer = game.createGraphics();
        gameBuffer.setColor(Color.BLACK);
        gameBuffer.fillRect(0, 0, GameConstants.WORLD_WIDTH, GameConstants.WORLD_HEIGHT);

        // Rendering Level
        gameBuffer.drawImage(Assets.worldImage, 0, 0, GameConstants.WORLD_WIDTH, GameConstants.WORLD_HEIGHT, null);
        level.render(gameBuffer);

        // Rendering Split Screen
        renderSplitScreen(g2);
    }

    private void renderUserInterface(Graphics2D g2) {
        Graphics uiBuffer = ui.createGraphics();
        uiBuffer.setColor(Color.BLACK);
        uiBuffer.fillRect(0, 0, GameConstants.GAME_WIDTH, GameConstants.GAME_HEIGHT);

        // Rendering User Interface
        renderMiniMap(uiBuffer);

        BufferedImage tankData = generateTankHealthData();
        BufferedImage boostData = generateActiveBoosts();

        g2.drawImage(ui, GameConstants.SCREEN_WIDTH + 10, 0, null);
        g2.drawImage(tankData, GameConstants.SCREEN_WIDTH + 10, 250, null);
        g2.drawImage(boostData, GameConstants.SCREEN_WIDTH + 10, 400, null);

    }


    @Override
    public void paintComponent(Graphics graphics) {
        Graphics2D g2 = (Graphics2D) graphics;
        renderGameScreen(g2);
        renderUserInterface(g2);
    }

}
