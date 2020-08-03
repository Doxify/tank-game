package csc413.tankgame.TankGame.entity.boost;

import csc413.tankgame.TankGame.entity.Entity;
import csc413.tankgame.TankGame.entity.movable.Tank;

import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Boost extends Entity {

    protected Tank tank;
    private int durationMilliseconds;
    private final boolean containsDuration;

    public Boost(int x, int y, int durationMilliseconds, BufferedImage image) {
        super(x, y, image);
        this.durationMilliseconds = durationMilliseconds;
        this.containsDuration = true;
    }

    public Boost(int x, int y, BufferedImage image) {
        super(x, y, image);
        this.containsDuration = false;
    }

    public void enableBoost(Tank tank) {
        if(!this.isRemoved()) {
            this.tank = tank;
            applyBoost();

            // If the boost contains a duration then we
            // should schedule removing the boost.
            if(containsDuration) {
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if(Boost.this.tank != null && !Boost.this.tank.isRemoved()) {
                            removeBoost();
                            System.out.print("Removing Boost!");
                        }
                    }
                }, durationMilliseconds);
            }
            System.out.println("Enabled boost" + (containsDuration ? " for " + durationMilliseconds + " MS" : "!"));
        }
    }

    public abstract void applyBoost();

    public abstract void removeBoost();



}
