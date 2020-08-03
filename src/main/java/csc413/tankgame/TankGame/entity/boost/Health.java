package csc413.tankgame.TankGame.entity.boost;

import java.awt.image.BufferedImage;

public class Health extends Boost {

    /**
     * Health boost resets a Tanks health.
     */

    public Health(int x, int y, BufferedImage image) {
        super(x, y, image);
    }

    @Override
    public void applyBoost() {
        tank.resetHealth();
    }

    @Override
    public void removeBoost() {

    }
}
