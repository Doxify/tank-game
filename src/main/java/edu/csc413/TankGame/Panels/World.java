package main.java.edu.csc413.TankGame.Panels;

import main.java.edu.csc413.TankGame.GameObject.Movable.Tank;
import main.java.edu.csc413.TankGame.Launcher;
import main.java.edu.csc413.TankGame.Utilities.GameConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class World extends JPanel {

    private BufferedImage world;
    private final Launcher launcher;

    public World(Launcher launcher) {
        this.launcher = launcher;

        // JPanel settings
        this.setBackground(Color.BLACK);
        this.setLayout(null);

        // Creating the world buffer
        this.world = new BufferedImage(GameConstants.WORLD_WIDTH, GameConstants.WORLD_HEIGHT, BufferedImage.TYPE_INT_ARGB);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;
        Graphics2D buffer = this.world.createGraphics();
        buffer.setColor(Color.BLACK);
        buffer.fillRect(0, 0, GameConstants.WORLD_WIDTH, GameConstants.WORLD_HEIGHT);

        Tank tank1 = this.launcher.getGame().getTank(1);
        Tank tank2 = this.launcher.getGame().getTank(2);

        tank1.drawImage(buffer);
        tank2.drawImage(buffer);
        this.launcher.getGame().getWalls().forEach(wall -> { wall.drawImage(buffer);});

        BufferedImage left = this.world.getSubimage(tank1.getCameraX(), tank1.getCameraY(), GameConstants.SCREEN_WIDTH / 2, GameConstants.SCREEN_HEIGHT);
        BufferedImage right = this.world.getSubimage(tank2.getCameraX(), tank2.getCameraY(), GameConstants.SCREEN_WIDTH / 2, GameConstants.SCREEN_HEIGHT);
//        BufferedImage minimap = this.world.getSubimage(0, 0, GameConstants.WORLD_WIDTH, GameConstants.WORLD_WIDTH);

        g.drawImage(left,0,0,null);
        g.drawImage(right,4 + GameConstants.SCREEN_WIDTH / 2,0,null);

        String tank1Coords = tank1.toString();
        String tank2Coords = tank2.toString();

        g.setColor(Color.WHITE);
        g.drawString(tank1Coords, 0, 10);
        g.drawString(tank2Coords, 0, 25);

//        g.scale(0.10, 0.10);
//        g.drawImage(minimap, 0, 0, null);
    }

}
