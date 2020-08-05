package csc413.tankgame.TankGame;

import csc413.tankgame.TankGame.level.Level;
import csc413.tankgame.TankGame.util.Assets;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

public class SoundEngine implements Runnable {

    private Thread soundThread;
    private Clip clip;
    private Level level;
    private boolean running;

    public SoundEngine(Level level) throws LineUnavailableException {
        this.clip = AudioSystem.getClip();
        this.level = level;
        this.running = false;
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
        clip.stop();
    }

    public void playSoundtrack() {
        try {
            if(!this.clip.isRunning()) {
                this.clip.open(Assets.soundTrack);
                this.clip.start();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        while(running) {
            playSoundtrack();
        }
        stop();
    }

}
