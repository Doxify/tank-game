package main.java.edu.csc413.TankGame.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Assets {

    public final static BufferedImage titleImage;
    public final static BufferedImage tank1Image;
    public final static BufferedImage tank2Image;
    public final static BufferedImage bulletImage;
    public final static BufferedImage unbreakableWallImage;
    public final static BufferedImage breakableWallImage;

    static {
        titleImage = loadBufferedImageFromResources("title.bmp");
        tank1Image = loadBufferedImageFromResources("tank1.png");
        tank2Image = loadBufferedImageFromResources("tank2.png");
        bulletImage = loadBufferedImageFromResources("shell.png");
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
            System.out.println("Error occurred while loading " + resourceName + " in Resources.");
            ex.printStackTrace();
            return null;
        }
    }

}
