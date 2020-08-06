package csc413.tankgame.TankGame.util;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Objects;

public class Sound {

    private final String name;

    public Sound(String name) {
        this.name = name;
    }

    /**
     * Returns an AudioInputStream based on the given
     * name value.
     */
    public AudioInputStream loadInputStream() {
        try {
            InputStream is = Objects.requireNonNull(Sound.class.getClassLoader().getResourceAsStream(this.name));
            return AudioSystem.getAudioInputStream(new BufferedInputStream(is));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Play an AudioInputStream on a separate thread.
     * @param inputStream to play
     */
    public void play(AudioInputStream inputStream) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    clip.open(inputStream);
                    clip.start();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }
}
