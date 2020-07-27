package main.java.edu.csc413.TankGame.entity.movable;

import main.java.edu.csc413.TankGame.graphics.Assets;
import main.java.edu.csc413.TankGame.util.GameConstants;

import java.awt.image.BufferedImage;

public class Tank extends Movable {

    // Tank action variables
    private boolean upPressed;
    private boolean downPressed;
    private boolean rightPressed;
    private boolean leftPressed;
    private boolean shootPressed;

    // Camera variables
    private int cameraX;
    private int cameraY;

    public Tank(int x, int y, int vX, int vY, float angle, BufferedImage image) {
        super(x, y, vX, vY, angle, image);
        // Setting the speed and rotation speed.
        this.setSpeed(3);
        this.setRotationSpeed(3);
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

    public void toggleShootPress() {
        this.shootPressed = true;
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

    public void unToggleShootPress() {
        this.shootPressed = false;
    }

    /**
     * Checks if the Tank is within the world's border and
     * adjusts it's camera coordinates accordingly.
     */
    void checkCameraBorder() {
        int xLowerBound = GameConstants.SCREEN_WIDTH / 4;
        int xUpperBound = GameConstants.WORLD_WIDTH - xLowerBound;
        int yLowerBound = GameConstants.SCREEN_HEIGHT / 2;
        int yUpperBound = GameConstants.WORLD_HEIGHT - yLowerBound;

        if(getX() > xLowerBound && getX() < xUpperBound) {
            this.cameraX = Math.abs(getX() - (GameConstants.SCREEN_WIDTH / 4));
        }

        if(getY() > yLowerBound && getY() < yUpperBound) {
            this.cameraY = Math.abs(getY() - (GameConstants.SCREEN_HEIGHT / 2));
        }
    }

    /**
     * Moves the Tank according to which buttons are pressed.
     */
    @Override
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

        if(this.shootPressed) {
            this.level.addEntity(new Bullet(getX(), getY(), getAngle(), Assets.bulletImage));
        }

        checkCameraBorder();

//        if(this.shootPressed && (Game.tick % 25 == 0)) {
//            Bullet bullet = new Bullet(x, y, angle, Game.bulletImage);
//            this.ammo.add(bullet);
//        }
//
//        this.ammo.forEach(bullet -> bullet.update());
    }

//    @Override
//    public void drawImage(Graphics graphics) {
//        Graphics2D g2 = (Graphics2D) graphics;
//        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
//
//        rotation.rotate(Math.toRadians(angle), this.image.getWidth() / 2.0, this.image.getHeight() / 2.0);
//        g2.drawImage(this.image, rotation, null);
//
//        this.ammo.forEach(bullet -> bullet.drawImage(graphics));
//
//        g2.setColor(Color.CYAN);
//        g2.drawRect(x, y, this.image.getWidth(), this.image.getHeight());
//    }

    /**
     * Returns the X coordinate of the camera with the Tank at the center.
     * @return x coordinate of the camera.
     */
    public int getCameraX() {
        return this.cameraX;
    }

    /**
     * Returns the Y coordinate of the camera with the Tank at the center.
     * @return Y coordinate of the camera.
     */
    public int getCameraY() {
        return this.cameraY;
    }

    /**
     * String representation of the Tank, used for debug purposes.
     * @return x,y coordinates as well as x,y camera coordinates.
     */
    @Override
    public String toString() {
        String tank = "Movable<Tank>: [x;y;angle]: " + getX() + ";" + getY() + ";" + getAngle();
        String camera = "[cX;cY]: " + getCameraX() + ";" + getCameraY();
        return tank + " " + camera;
    }
}
