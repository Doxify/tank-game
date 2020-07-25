package main.java.edu.csc413.TankGame.GameObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class GameObject {

    private int x;
    private int y;

    private final BufferedImage image;
    private final Rectangle hitbox;

    // Constructor
    public GameObject(int x, int y, BufferedImage image) {
        this.x = x;
        this.y = y;

        this.image = image;
        this.hitbox = new Rectangle(x, y, image.getWidth(), image.getHeight());
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Rectangle getHitbox() {
        return this.hitbox;
    }

    public BufferedImage getImage() {
        return this.image;
    }

//    public void drawImage(Graphics graphics) {
//        Graphics2D g2 = (Graphics2D) graphics;
//        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
//
//        rotation.rotate(Math.toRadians(angle), image.getWidth() / 2.0, image.getHeight() / 2.0);
//        g2.drawImage(image, rotation, null);
//
//        // Debug stuff
//        g2.setColor(Color.BLUE);
//        g2.drawRect(x, y, image.getWidth(), image.getHeight());
//    };

    public abstract void drawImage(Graphics graphics);
}
