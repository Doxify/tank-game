package csc413.tankgame.TankGame.util;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound implements Runnable {

    private boolean running;
    private Thread thread;
    private boolean play;
    private AudioInputStream inputStream;
    private Clip clip;

    public Sound(AudioInputStream inputStream) {
        this.running = false;
        this.inputStream = inputStream;
        this.start();
    }

    public void start() {
        if(!this.running) {
            this.thread = new Thread(this);
            this.running = true;
            this.thread.start();
        }
    }

    @Override
    public void run() {
        while(this.running) {
            if (this.clip != null) {
                if(this.play && this.inputStream != null){
                    this.play = false;
                    this.play();
                }
            } else {
                this.getAudioClip();
            }
        }
    }

    public void play() {
        if(this.clip != null) {
            this.clip.start();
            this.clip.stop();
        } else {
            this.getAudioClip();
        }

        this.play = true;
        this.inputStream = null;
    }

    public void stop() {
        if(this.clip != null) {
            this.clip.stop();
            this.clip.close();
        }
        this.clip = null;
        this.play = false;
        this.inputStream = null;
    }


    private void getAudioClip() {
        try {
            this.clip = AudioSystem.getClip();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
