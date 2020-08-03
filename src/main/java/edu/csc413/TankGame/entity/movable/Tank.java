package main.java.edu.csc413.TankGame.entity.movable;

import main.java.edu.csc413.TankGame.Game;
import main.java.edu.csc413.TankGame.graphics.Assets;
import main.java.edu.csc413.TankGame.graphics.ui.UIManager;
import main.java.edu.csc413.TankGame.util.GameConstants;

import java.awt.image.BufferedImage;

public class Tank extends Movable {

    // Tank action variables
    private boolean upPressed;
    private boolean downPressed;
    private boolean rightPressed;
    private boolean leftPressed;
    private boolean shootPressed;
    private int firingSpeed;

    // Camera variables
    private int cameraX;
    private int cameraY;

    private UIManager uiManager;

    // Health Variables
    private int MAX_HEALTH = 20;
    private int HEALTH_DECREASE_MODIFIER = 2;
    private int lives = 3;
    private int health;

    public Tank(int x, int y, int vX, int vY, float angle, BufferedImage image) {
        super(x, y, vX, vY, angle, image);
        // Setting the speed and rotation speed.
        this.setSpeed(3);
        this.setRotationSpeed(2);
        this.uiManager = Game.getUiManager();
        this.health = MAX_HEALTH;
        this.firingSpeed = 7;
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

    public int getFiringSpeed() {
        return this.firingSpeed;
    }

    public void setFiringSpeed(int firingSpeed) {
        this.firingSpeed = firingSpeed;
    }

    // Health Functions
    public int getMaxHealth() {
        return this.MAX_HEALTH;
    }

    public int getHealth() {
        return this.health;
    }

    public void resetHealth() {
        this.health = this.MAX_HEALTH;
    }

    public int getHealthDecreaseModifier() {
        return this.HEALTH_DECREASE_MODIFIER;
    }

    public void setHealthDecreaseModifier(int HEALTH_DECREASE_MODIFIER) {
        this.HEALTH_DECREASE_MODIFIER = HEALTH_DECREASE_MODIFIER;
    }

    public void decreaseHealth() {
        this.health -= HEALTH_DECREASE_MODIFIER;
        if(this.health <= 0) {
            lives--;
            if(lives <= 0) {
                this.lives = 0;
                this.health = 0;
                this.setRemoved();
            }
        }
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

        if(this.shootPressed && Game.getTick() % 25 == 0) {
            this.level.addEntity(new Bullet(getX(), getY(), getAngle(), this, firingSpeed, Assets.bulletImage));
        }

        checkCameraBorder();
        handleCollision();
    }

    @Override
    public void handleCollision() {
        // Checking if TANK collided with wall or another tank.
        if(level.entityCollidedWithWall(this) || level.entityCollidedWithTank(this)) {
            this.setX(this.getPrevX());
            this.setY(this.getPrevY());
        }

        // Checking if TANK collided with boost.
        level.entityCollidedWithBoost(this);
    }

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
