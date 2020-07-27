package main.java.edu.csc413.TankGame.entity;

import main.java.edu.csc413.TankGame.level.Level;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {

    private int x, y;
    private BufferedImage image;
    private Rectangle hitbox;
    private boolean removed;

    protected Level level;

    public Entity(int x, int y, BufferedImage image) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.removed = false;
        this.hitbox = new Rectangle(x, y, image.getWidth(), image.getHeight());
    }

    public void init(Level level) {
        this.level = level;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
        this.hitbox.setLocation(x, y);
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
        this.hitbox.setLocation(x, y);
    }

    public BufferedImage getImage() {
        return this.image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public boolean isRemoved() {
        return this.removed;
    }

    public void setRemoved() {
        this.removed = true;
    }

    public void remove() {
        this.removed = true;
        this.level.removeEntity(this);
    }

    public Rectangle getHitbox() {
        return this.hitbox.getBounds();
    }

    public boolean hasCollided(Rectangle hitbox) {
        if(this.hitbox != hitbox) {
            return this.hitbox.intersects(hitbox);
        }
        return false;
    }

    public void render(Graphics graphics) {
        if(this.image != null) {
            Graphics2D g2 = (Graphics2D) graphics;
            g2.drawImage(image, x, y, null);

            // Visualizing hitboxes.
            g2.setColor(Color.BLUE);
            g2.drawRect(getX(), getY(), getImage().getWidth(), getImage().getHeight());
        }
    }
}
