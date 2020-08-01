package main.java.edu.csc413.TankGame.entity.boost;

import java.awt.image.BufferedImage;

public class Shield extends Boost {

    /**
     * Shield boost makes a tank take less damage.
     * When the boost is enabled, the damage a tank takes
     * when hit with a bullet is decreased by 50%.
     */

    private int previousHealthDecreaseModifier;

    public Shield(int x, int y, int durationMilliseconds, BufferedImage image) {
        super(x, y, durationMilliseconds, image);
    }

    @Override
    public void applyBoost() {
        if(tank != null) {
            if(!tank.isRemoved()) {
                previousHealthDecreaseModifier = tank.getHealthDecreaseModifier();
                tank.setHealthDecreaseModifier(previousHealthDecreaseModifier / 2);
            }
        }
    }

    @Override
    public void removeBoost() {
        if(tank != null) {
            if(!tank.isRemoved()) {
                tank.setHealthDecreaseModifier(previousHealthDecreaseModifier);
            }
        }
    }
}
