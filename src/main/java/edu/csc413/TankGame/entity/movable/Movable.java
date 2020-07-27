package main.java.edu.csc413.TankGame.entity.movable;

import main.java.edu.csc413.TankGame.entity.Entity;
import main.java.edu.csc413.TankGame.util.GameConstants;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public abstract class Movable extends Entity {

    private int ROTATION_SPEED;
    private int SPEED;
    private int vX;
    private int vY;
    private float angle;

    public Movable(int x, int y, int vX, int vY, float angle, BufferedImage image) {
        super(x, y, image);
        this.vX = vX;
        this.vY = vY;
        this.angle = angle;
    }

    public int getVelocityX() {
        return this.vX;
    }

    public void setVelocityX(int vX) {
        this.vX = vX;
    }

    public int getVelocityY() {
        return this.vY;
    }

    public void setVelocityY(int vY) {
        this.vY = vY;
    }

    public float getAngle() {
        return this.angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    void setSpeed(int SPEED) {
        this.SPEED = SPEED;
    }

    void setRotationSpeed(int ROTATION_SPEED) {
        this.ROTATION_SPEED = ROTATION_SPEED;
    }

    /**
     * Moves the Movable object backward at the rate of SPEED and
     * at the angle of ANGLE.
     */
    void moveBackward() {
        this.setVelocityX((int) Math.round(this.SPEED * Math.cos(Math.toRadians(this.getAngle()))));
        this.setVelocityY((int) Math.round(this.SPEED * Math.sin(Math.toRadians(this.getAngle()))));
        this.setX(this.getX() - this.getVelocityX());
        this.setY(this.getY() - this.getVelocityY());
        this.checkBorder();
    }

    /**
     * Moves the Movable object forward at the rate of SPEED and
     * at the angle of ANGLE.
     */
    void moveForward() {
        this.setVelocityX((int) Math.round(this.SPEED * Math.cos(Math.toRadians(this.getAngle()))));
        this.setVelocityY((int) Math.round(this.SPEED * Math.sin(Math.toRadians(this.getAngle()))));
        this.setX(this.getX() + this.getVelocityX());
        this.setY(this.getY() + this.getVelocityY());
        this.checkBorder();
    }

    /**
     * Rotates the Movable to the left at a rate of ROTATION_SPEED.
     */
    void rotateLeft() {
        this.angle -= ROTATION_SPEED;
    }

    /**
     * Rotates the Movable to the right at a rate of ROTATION_SPEED.
     */
    void rotateRight() {
        this.angle += ROTATION_SPEED;
    }

    /**
     * Checks if the GameObject is within the world's border and
     * adjusts it's coordinates accordingly.
     */
    void checkBorder() {
        if (this.getX() < 30) {
            this.setX(30);
        }

        if (this.getX() >= GameConstants.WORLD_HEIGHT - 88) {
            this.setX(GameConstants.WORLD_WIDTH - 88);
        }

        if (this.getY() < 30) {
            this.setY(30);
        }

        if (this.getY() >= GameConstants.WORLD_HEIGHT - 80) {
            this.setY(GameConstants.WORLD_HEIGHT - 80);
        }
    }

    /**
     * Called every time a game objects state should be updated.
     */
    public abstract void update();

    /**
     * Draws a Movable to the buffer.
     * @param graphics the buffer
     */
    @Override
    public void render(Graphics graphics) {
        Graphics2D g2 = (Graphics2D) graphics;
        AffineTransform rotation = AffineTransform.getTranslateInstance(getX(), getY());

        rotation.rotate(Math.toRadians(angle), getImage().getWidth() / 2.0, getImage().getHeight() / 2.0);
        g2.drawImage(getImage(), rotation, null);
    };

}
