package csc413.tankgame.TankGame.entity.boost;

import csc413.tankgame.TankGame.entity.Entity;
import csc413.tankgame.TankGame.entity.movable.Tank;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Boost extends Entity {

    protected Tank tank = null;
    private final int durationMilliseconds;
    protected boolean isActive;

    public Boost(int x, int y, int durationMilliseconds, BufferedImage image) {
        super(x, y, image);
        this.durationMilliseconds = durationMilliseconds;
        this.isActive = false;
    }

    public Tank getTank() {
        return tank;
    }

    public void enableBoost(Tank tank) {
        if(!this.isRemoved() && !tank.isRemoved()) {
            this.tank = tank;
            this.isActive = true;
            applyBoost();

            // If the boost contains a duration then we
            // should schedule removing the boost.
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    if(Boost.this.tank != null && !Boost.this.tank.isRemoved()) {
                        isActive = false;
                        removeBoost();
                    }
                }
            }, durationMilliseconds);
        }
    }

    public boolean isActive() {
        return this.isActive;
    }

    public abstract void applyBoost();

    public abstract void removeBoost();

    @Override
    public void render(Graphics g) {
        if(!this.isRemoved()) {
            if(this.image != null) {
                Graphics2D g2 = (Graphics2D) g;
                g2.drawImage(image, getX(), getY(), null);
            }
        }
    }



}
