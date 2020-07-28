package main.java.edu.csc413.TankGame.entity.movable;

import main.java.edu.csc413.TankGame.Game;

import java.awt.image.BufferedImage;

public class Bullet extends Movable {

    // The tick that the bullet was created on.
    private long firedTick;

    public Bullet(int x, int y, float angle, BufferedImage image) {
        super(x, y, 0, 0, angle, image);
        // Setting the speed of bullets.
        this.setSpeed(7);
        this.firedTick = Game.tick;
    }

    @Override
    public void handleCollision() {
        // Once a bullet collides with a wall, remove it.
        if(level.collidedWithWall(this)) {
            this.setRemoved();
        } else if(Game.tick >= firedTick + 20 && level.collidedWithTank(this)) {
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
