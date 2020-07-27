package main.java.edu.csc413.TankGame.entity.movable;

import java.awt.image.BufferedImage;

public class Bullet extends Movable {

    public Bullet(int x, int y, float angle, BufferedImage image) {
        super(x, y, 0, 0, angle, image);
        // Setting the speed of bullets.
        this.setSpeed(7);
    }

    /**
     * Moves the bullet forward along it's current trajectory.
     */
    @Override
    public void update() {
        moveForward();
    };
}
