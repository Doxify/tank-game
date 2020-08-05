package csc413.tankgame.TankGame.util;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.Objects;

public class Assets {

    public final static InputStream map1;
    public final static AudioInputStream soundTrack;

    public final static BufferedImage worldImage;
    public final static BufferedImage menuImage;

    public final static BufferedImage tank1Image;
    public final static BufferedImage tank2Image;

    public final static BufferedImage bulletImage;

    public final static BufferedImage unbreakableWallImage;
    public final static BufferedImage breakableWallImage;
    public final static BufferedImage breakableWallImage2;

    public final static BufferedImage speedBoostImage;
    public final static BufferedImage healthBoostImage;
    public final static BufferedImage ammoBoostImage;
    public final static BufferedImage shieldBoostImage;

    static {
        worldImage = loadBufferedImageFromResources("world.png");
        menuImage = loadBufferedImageFromResources("menu.png");
        map1 = loadStreamFromResources("maps/map1");
        soundTrack = loadAudioStreamFromResource("sounds/soundtrack.mp3");
        tank1Image = loadBufferedImageFromResources("tank1.png");
        tank2Image = loadBufferedImageFromResources("tank2.png");
        bulletImage = loadBufferedImageFromResources("shell.png");
        unbreakableWallImage = loadBufferedImageFromResources("walls/unbreakable_wall.png");
        breakableWallImage = loadBufferedImageFromResources("walls/breakable_wall.png");
        breakableWallImage2 = loadBufferedImageFromResources("walls/breakable_wall2.png");
        speedBoostImage = loadBufferedImageFromResources("boosts/speed_boost.png");
        healthBoostImage = loadBufferedImageFromResources("boosts/health_boost.png");
        ammoBoostImage = loadBufferedImageFromResources("boosts/ammo_boost.png");
        shieldBoostImage = loadBufferedImageFromResources("boosts/shield_boost.png");
    }

    /**
     * Loads a image resource from resource folder.
     * @param resourceName the name of the resource.
     * @return BufferedImage representing resource.
     */
    private static BufferedImage loadBufferedImageFromResources(String resourceName) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try {
            return ImageIO.read(Objects.requireNonNull(loader.getResource(resourceName)));
        } catch(Exception ex) {
            System.out.println("Error occurred while loading " + resourceName + " in Resources.");
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Loads an input stream from resource folder
     * @param resourceName tha name of the resource.
     * @return InputStream representing resource
     */
    private static InputStream loadStreamFromResources(String resourceName) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try {
            return Objects.requireNonNull(loader.getResourceAsStream(resourceName));
        } catch(Exception ex) {
            System.out.println("Error occurred while loading " + resourceName + " in Resources.");
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Loads an input stream from resource folder
     * @param resourceName tha name of the resource.
     * @return InputStream representing resource
     */
    private static AudioInputStream loadAudioStreamFromResource(String resourceName) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try {
            return AudioSystem.getAudioInputStream(Objects.requireNonNull(loader.getResourceAsStream(resourceName)));
        } catch(Exception ex) {
            System.out.println("Error occurred while loading " + resourceName + " in Resources.");
            ex.printStackTrace();
            return null;
        }
    }

}
