package csc413.tankgame.TankGame;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundEngine implements Runnable {

    private Thread soundThread;
    private boolean running;

    private AudioInputStream soundtrack;

    public SoundEngine() {
        init();
    }

    private void init() {
        try {
            this.soundtrack = AudioSystem.getAudioInputStream(this.getClass().getClassLoader().getResourceAsStream("sounds/soundtrack.wav"));
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
            clip.open(this.soundtrack);
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
    }

}
