package csc413.tankgame.TankGame;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.ArrayList;
import java.util.List;

public class SoundEngine implements Runnable {

    private Thread soundThread;
    private boolean running;

    public static AudioInputStream soundtrack;
    public static AudioInputStream bulletExplosion;
    public static List<AudioInputStream> queue = new ArrayList<>();

    public SoundEngine() {
        init();
    }

    private void init() {
        try {
            soundtrack = AudioSystem.getAudioInputStream(this.getClass().getClassLoader().getResourceAsStream("sounds/soundtrack.wav"));
            bulletExplosion = AudioSystem.getAudioInputStream(this.getClass().getClassLoader().getResourceAsStream("sounds/bulletExplosion.wav"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Starts the game thread.
     */
    public synchronized void start() {
        if(!running) {
            this.soundThread = new Thread(this, "TankGameSound");
            this.soundThread.start();
            this.running = true;
        }
    }

    public static void addToQueue(AudioInputStream audioInputStream) {
        queue.add(audioInputStream);
    }

    public static void playSound(AudioInputStream audioInputStream) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            clip.wait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Stops the game thead.
     */
    public synchronized void stop() {
        if(running) {
            this.running = false;
            try {
                this.soundThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void playSoundtrack() {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(soundtrack);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        playSoundtrack();
        while(running) {
            for (AudioInputStream audioInputStream : queue) {
                playSound(audioInputStream);
                queue.remove(audioInputStream);
            }
        }
    }

}
