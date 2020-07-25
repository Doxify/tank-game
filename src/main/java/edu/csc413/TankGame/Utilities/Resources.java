package main.java.edu.csc413.TankGame.Utilities;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Resources {

    public static BufferedImage titleImage;
    public static BufferedImage tank1Image;
    public static BufferedImage tank2Image;
    public static BufferedImage bulletImage;
    public static BufferedImage unbreakableWallImage;
    public static BufferedImage breakableWallImage;

    static {
        titleImage = loadBufferedImageFromResources("title.bmp");
        tank1Image = loadBufferedImageFromResources("tank1.gif");
        tank2Image = loadBufferedImageFromResources("tank2.gif");
        bulletImage = loadBufferedImageFromResources("rocket.gif");
        unbreakableWallImage = loadBufferedImageFromResources("wall1.gif");
        breakableWallImage = loadBufferedImageFromResources("wall2.gif");
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
        } catch(IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
