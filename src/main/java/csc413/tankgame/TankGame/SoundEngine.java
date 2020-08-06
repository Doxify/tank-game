package csc413.tankgame.TankGame;

import csc413.tankgame.TankGame.util.Assets;
import csc413.tankgame.TankGame.util.Sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.BufferedInputStream;
import java.util.Stack;

public class SoundEngine implements Runnable {

    private Thread soundThread;
    private boolean running = false;
    private boolean musicEnabled = true;
    public static final Stack<Sound> stack = new Stack<>();


    /**
     * Adds a Sound object to the stack for processing.
     * @param sound object
     */
    public static void addToQueue(final Sound sound) {
        stack.add(sound);
    }

    /**
     * Starts the sound thread.
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

    /**
     * Waits for sounds to be added to the stack
     * and processes them accordingly.
     */
    @Override
    public void run() {
        if(musicEnabled) {
            playSoundtrack();
            while (running) {
                if(!stack.isEmpty()) {
                    Sound sound = stack.pop();
                    sound.play(sound.loadInputStream());
                }
            }
        }
    }

    /**
     * Toggles the sound engine on and off.
     */
    public void toggleMusic() {
        this.musicEnabled = !this.musicEnabled;
    }

    public boolean getMusicEnabled() {
        return this.musicEnabled;
    }


    /**
     * Plays the game soundtrack.
     */
    public void playSoundtrack() {
        if(this.musicEnabled) {
            try {
                Clip soundtrackClip = AudioSystem.getClip();
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(Assets.soundTrack));
                soundtrackClip.open(inputStream);
                soundtrackClip.start();
                soundtrackClip.loop(Clip.LOOP_CONTINUOUSLY);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println(e.getMessage());
            }
        }
    }

}
