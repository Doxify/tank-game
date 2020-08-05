package csc413.tankgame.TankGame;

import csc413.tankgame.TankGame.util.Sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.Stack;

public class SoundEngine implements Runnable {

    private Thread soundThread;
    private boolean running;

    public static AudioInputStream soundtrack;
    public static AudioInputStream bulletExplosion;
    public static final Stack<Sound> stack = new Stack<>();

    // Live Sounds
    private boolean musicEnabled;
    public static Clip soundtrackClip;

    public SoundEngine() {
        init();
    }

    private void init() {
        try {
            soundtrack = AudioSystem.getAudioInputStream(this.getClass().getClassLoader().getResourceAsStream("sounds/soundtrack.wav"));
            bulletExplosion = AudioSystem.getAudioInputStream(this.getClass().getClassLoader().getResourceAsStream("sounds/bulletExplosion.wav"));
            soundtrackClip = AudioSystem.getClip();
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

    public static void addToQueue(final Sound sound) {
        stack.add(sound);
    }

//    public static void playSound(final AudioInputStream audioInputStream) {
//        try {
//            Clip clip = AudioSystem.getClip();
//            clip.open(audioInputStream);
//            clip.start();
//            clip.wait();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

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

    @Override
    public void run() {
        while(running) {
            if (musicEnabled) {
                if(!stack.isEmpty()) {
                    Sound sound = stack.pop();
                    sound.start();
                    sound.play();
                }
            }
        }
        stop();
//        if(musicEnabled) playSoundtrack();
//        while(running) {
//            if (!stack.isEmpty()) {
//                AudioInputStream stream = stack.pop();
//                playSound(stream);
//            }
//        }
    }


    public void toggleMusic() {
        this.musicEnabled = !this.musicEnabled;
        System.out.println("music is now " + this.musicEnabled);
    }


//    public void playSoundtrack() {
//        if(soundtrackClip != null && !soundtrackClip.isRunning() && this.musicEnabled) {
//            try {
//                soundtrackClip.open(soundtrack);
//                soundtrackClip.start();
//                soundtrackClip.loop(Clip.LOOP_CONTINUOUSLY);
//            } catch (Exception e) {
//                e.printStackTrace();
//                System.err.println(e.getMessage());
//            }
//        }
//    }

//    public void stopSoundtrack() {
//        if(soundtrackClip != null && soundtrackClip.isRunning()){
//            try {
//                soundtrackClip.stop();
//                soundtrackClip.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//                System.err.println(e.getMessage());
//            }
//        }
//    }

}
