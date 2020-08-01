package main.java.edu.csc413.TankGame.entity.movable;

import java.awt.image.BufferedImage;

public class Bullet extends Movable {

    // The tank that fired the bullet.
    private final Tank owner;

    public Bullet(int x, int y, float angle, Tank owner, int speed, BufferedImage image) {
        super(x, y, 0, 0, angle, image);
        // Setting the speed of bullets.
        this.setSpeed(speed);
        this.owner = owner;
    }

    /**
     * Returns the Tank that fired this bullet.
     * @return owner
     */
    public Tank getOwner() {
        return this.owner;
    }

    @Override
    public void handleCollision() {
        // Once a bullet collides with a wall, remove it.
        if(level.entityCollidedWithWall(this)) {
            this.setRemoved();
        } else if(level.entityCollidedWithTank(this)) {
            this.setRemoved();
            System.out.println("Bullet collided with tank!");
        }
    }

    /**
     * Moves the bullet forward along it's current trajectory.
     */
    @Override
    public void update() {
        moveForward();
        handleCollision();
    };
}
