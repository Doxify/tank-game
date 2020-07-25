package main.java.edu.csc413.TankGame.GameObject.Movable;

import main.java.edu.csc413.TankGame.Utilities.GameConstants;

import java.awt.image.BufferedImage;

public class Tank extends Movable {

    private boolean upPressed;
    private boolean downPressed;
    private boolean rightPressed;
    private boolean leftPressed;
    private boolean shootPressed;

//    private ArrayList<Bullet> ammo;

    public Tank(int x, int y, int vX, int vY, int angle, BufferedImage image) {
        super(x, y, vX, vY, angle, image);

        // Setting the speed and rotation speed.
        setSpeed(3);
        setRotationSpeed(3);
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

    @Override
    public void update() {
        if (this.upPressed) {
            moveForward();
        }

        if(this.downPressed) {
            moveBackward();
        }

        if(this.leftPressed) {
            rotateLeft();
        }

        if(this.rightPressed) {
            rotateRight();
        }

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

    @Override
    public String toString() {
        return "x=" + getX() + ", y=" + getY() + ", angle=" + getAngle() + ", cX: " + getCameraX() + ", cY: " + getCameraY();
    }

    public int getCameraX() {
        return Math.abs(getX() - (GameConstants.SCREEN_WIDTH / 4));
    }

    public int getCameraY() {
        return Math.abs(getY() - (GameConstants.SCREEN_HEIGHT / 2));
    }
}
