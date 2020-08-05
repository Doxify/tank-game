package csc413.tankgame.TankGame.entity.boost;

import java.awt.image.BufferedImage;

public class Health extends Boost {

    /**
     * Health boost resets a Tanks health.
     */

    public Health(int x, int y, int durationMilliseconds, BufferedImage image) {
        super(x, y, durationMilliseconds, image);
    }

    @Override
    public void applyBoost() {
        this.tank.resetHealth();
    }

    @Override
    public void removeBoost() {
        this.isActive = false;
    }

    @Override
    public String toString() {
        return "Full Health Regen";
    }
}
