package csc413.tankgame.TankGame.entity;

import csc413.tankgame.TankGame.SoundEngine;
import csc413.tankgame.TankGame.level.Level;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {

    private int x, y;
    private boolean removed;
    private final Rectangle hitbox;

    protected BufferedImage image;
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

    /**
     * Has an entity been marked for removal?
     * @return removal status
     */
    public boolean isRemoved() {
        return this.removed;
    }

    /**
     * Marking an entity for removal.
     */
    public void setRemoved() {
        this.removed = true;
    }

    public Rectangle getHitbox() {
        return this.hitbox.getBounds();
    }

    public BufferedImage getImage() {
        return image;
    }

    /**
     * Checks if an Entity has collided with another Entity.
     * @param entity to check against
     * @return whether or not it collided
     */
    public boolean hasCollided(Entity entity) {
        // Make sure we're not comparing the same entity
        if(this != entity) {
            return this.hitbox.intersects(entity.getHitbox());
        }
        return false;
    }

    public void render(Graphics graphics) {
        if(this.image != null) {
            if(!this.isRemoved()) {
                Graphics2D g2 = (Graphics2D) graphics;
                g2.drawImage(image, x, y, null);
            }
        }
    }
}
