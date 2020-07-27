package main.java.edu.csc413.TankGame.util;

import main.java.edu.csc413.TankGame.entity.movable.Tank;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TankControl implements KeyListener {

    private Tank tank;
    private final int up;
    private final int down;
    private final int right;
    private final int left;
    private final int shoot;

    public TankControl(Tank tank, int up, int down, int right, int left, int shoot) {
        this.tank = tank;
        this.up = up;
        this.down = down;
        this.right = right;
        this.left = left;
        this.shoot = shoot;
    }

    /**
     * DEBUG USE ONLY
     * @param event the event for the pressed key
     */
    @Override
    public void keyTyped(KeyEvent event) {
//        System.out.println(this.tank + " KEY: " + event.getKeyCode());
    }

    @Override
    public void keyPressed(KeyEvent event) {
        int keyPressed = event.getKeyCode();
        if(keyPressed == up) {
            this.tank.toggleUpPress();
        }

        if(keyPressed == down) {
            this.tank.toggleDownPress();
        }

        if(keyPressed == right) {
            this.tank.toggleRightPress();
        }

        if(keyPressed == left) {
            this.tank.toggleLeftPress();
        }

        if(keyPressed == shoot) {
            this.tank.toggleShootPress();
        }
    }

    @Override
    public void keyReleased(KeyEvent event) {
        int keyPressed = event.getKeyCode();
        if(keyPressed == up) {
            this.tank.unToggleUpPress();
        }

        if(keyPressed == down) {
            this.tank.unToggleDownPress();
        }

        if(keyPressed == right) {
            this.tank.unToggleRightPress();
        }

        if(keyPressed == left) {
            this.tank.unToggleLeftPress();
        }

        if(keyPressed == shoot) {
            this.tank.unToggleShootPress();
        }
    }

}
