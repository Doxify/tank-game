package main.java.edu.csc413.TankGame.GameObject.Movable;

import java.awt.image.BufferedImage;

public class Bullet extends Movable {

    public Bullet(int x, int y, int angle, BufferedImage image) {
        super(x, y, 0, 0, angle, image);

        // Setting the speed of bullets.
        setSpeed(7);
    }

    @Override
    public void update() {
        moveForward();
    };
}
