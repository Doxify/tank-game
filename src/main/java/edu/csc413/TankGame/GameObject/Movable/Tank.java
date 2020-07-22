package main.java.edu.csc413.TankGame.GameObject.Movable;

import main.java.edu.csc413.TankGame.Utilities.GameConstants;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Tank extends _Movable {

    private int x;
    private int y;
    private int velocityX;
    private int velocityY;
    private float angle;

    private final int R = 2;
    private final float ROTATIONSPEED = 3.0f;

    private BufferedImage image;
    private boolean upPressed;
    private boolean downPressed;
    private boolean rightPressed;
    private boolean leftPressed;

    public Tank(int x, int y, int velocityX, int velocityY, float angle, BufferedImage image) {
        this.x = x;
        this.y = y;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.angle = angle;
        this.image = image;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void toggleUpPress() {
        this.upPressed = true;
    }

    public void toggleDownPress() {
        this.downPressed = true;
    }

    public void toggleRightPress() {
        this.rightPressed = true;
    }

    public void toggleLeftPress() {
        this.leftPressed = true;
    }

    public void unToggleUpPress() {
        this.upPressed = false;
    }

    public void unToggleDownPress() {
        this.downPressed = false;
    }

    public void unToggleRightPress() {
        this.rightPressed = false;
    }

    public void unToggleLeftPress() {
        this.leftPressed = false;
    }

    public void update() {
        if (this.upPressed) {
            this.moveForward();
        }

        if(this.downPressed) {
            this.moveBackward();
        }

        if(this.leftPressed) {
            this.rotateLeft();
        }

        if(this.rightPressed) {
            this.rotateRight();
        }
    }

    private void rotateLeft() {
        this.angle -= this.ROTATIONSPEED;
    }

    private void rotateRight() {
        this.angle += this.ROTATIONSPEED;
    }

    private void moveBackward() {
        velocityX = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        velocityY = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x -= velocityX;
        y -= velocityY;
        checkBorder();
    }

    private void moveForward() {
        velocityX = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        velocityY = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x += velocityX;
        y += velocityY;
        checkBorder();
    }

    private void checkBorder() {
        if (x < 30) {
            x = 30;
        }
        if (x >= GameConstants.GAME_SCREEN_WIDTH - 88) {
            x = GameConstants.GAME_SCREEN_WIDTH - 88;
        }
        if (y < 40) {
            y = 40;
        }
        if (y >= GameConstants.GAME_SCREEN_HEIGHT - 80) {
            y = GameConstants.GAME_SCREEN_HEIGHT - 80;
        }
    }

    public void drawImage(Graphics g) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), this.image.getWidth() / 2.0, this.image.getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.image, rotation, null);
    }

    @Override
    public String toString() {
        return "x=" + x + ", y=" + y + ", angle=" + angle;
    }

}
