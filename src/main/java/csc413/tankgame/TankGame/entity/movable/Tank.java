package csc413.tankgame.TankGame.entity.movable;

import csc413.tankgame.TankGame.Game;
import csc413.tankgame.TankGame.util.Assets;
import csc413.tankgame.TankGame.util.GameConstants;

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

    // Health Variables
    public final static int MAX_HEALTH = 20;
    public final static int MAX_LIVES = 3;
    private int HEALTH_DECREASE_MODIFIER = 2;
    private int lives;
    private int health;

    public Tank(int x, int y, int vX, int vY, float angle, BufferedImage image) {
        super(x, y, vX, vY, angle, image);
        this.setSpeed(2);
        this.setRotationSpeed(2);
        this.health = MAX_HEALTH;
        this.lives = MAX_LIVES;
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
        return MAX_HEALTH;
    }

    public int getHealth() {
        return this.health;
    }

    public void resetHealth() {
        this.health = MAX_HEALTH;
    }

    public int getLives() {
        return this.lives;
    }

    public int getHealthDecreaseModifier() {
        return this.HEALTH_DECREASE_MODIFIER;
    }

    public void setHealthDecreaseModifier(int HEALTH_DECREASE_MODIFIER) {
        this.HEALTH_DECREASE_MODIFIER = HEALTH_DECREASE_MODIFIER;
    }

    public void decreaseHealth() {
        int health = this.health - HEALTH_DECREASE_MODIFIER;
        if (health <= 0) {
            this.lives = this.lives - 1;
            this.level.respawnTank(this);
        } else {
            this.health = health;
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
        if(this.level.entityCollidedWithWall(this) || this.level.entityCollidedWithTank(this)) {
            this.setX(this.getPrevX());
            this.setY(this.getPrevY());
        }
        this.level.handleTankCollidedWithBoost(this);
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

    @Override
    public void playSound() {

    }
}
