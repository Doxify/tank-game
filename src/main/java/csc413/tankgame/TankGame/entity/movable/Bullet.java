package csc413.tankgame.TankGame.entity.movable;

import csc413.tankgame.TankGame.SoundEngine;
import csc413.tankgame.TankGame.util.Assets;
import csc413.tankgame.TankGame.util.Sound;

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
        boolean playSound = false;
        // Once a bullet collides with a wall, remove it.
        if(level.entityCollidedWithWall(this)) {
            this.setRemoved();
            playSound = true;
        } else if(level.entityCollidedWithTank(this)) {
            this.setRemoved();
            playSound = true;
        }

        if(playSound) {
            this.playSound();
        }
    }

    /**
     * Moves the bullet forward along it's current trajectory.
     */
    @Override
    public void update() {
        moveForward();
        handleCollision();
    }

    @Override
    public void playSound() {
        SoundEngine.addToQueue(new Sound(Assets.bulletExplosion));
    }
}
