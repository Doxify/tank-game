package main.java.edu.csc413.TankGame;

import main.java.edu.csc413.TankGame.GameObject.Movable.Tank;
import main.java.edu.csc413.TankGame.GameObject.Stationary.Wall.Breakable;
import main.java.edu.csc413.TankGame.GameObject.Stationary.Wall.Unbreakable;
import main.java.edu.csc413.TankGame.GameObject.Stationary.Wall.Wall;
import main.java.edu.csc413.TankGame.Utilities.Resources;
import main.java.edu.csc413.TankGame.Utilities.TankControl;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

public class Game implements Runnable {

    private Thread gameThread;
    private boolean running;

    private Tank tank1;
    private Tank tank2;

    private Launcher launcher;
    private JPanel world;

    public static long tick;
    public static BufferedImage bulletImage = null;

    ArrayList<Wall> walls;

    public Game(Launcher launcher, JPanel world) {
        this.launcher = launcher;
        this.world = world;
        this.running = false;
        tick = 0;
    }

    public synchronized void start() {
        this.gameThread = new Thread(this, "TankGame");
        this.gameThread.start();
        this.running = true;
    }

    public synchronized void stop() {
        this.running = false;
        try {
            this.gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        this.resetGameState();
        try {
            while(running) {
                tick++;
                this.world.repaint();
                this.tank1.update();
                this.tank2.update();
                this.world.repaint();
                Thread.sleep(1000 / 144);
            }
        } catch (InterruptedException e) {
            this.running = false;
            e.printStackTrace();
        }
    }

    /**
     * Resets the game to its initial state.
     */
    public void resetGameState() {
        this.tick = 0;
        this.tank1.setX(300);
        this.tank1.setY(300);
        this.tank2.setX(500);
        this.tank2.setY(500);
    }

    public void initializeGame() {
        BufferedImage tank1Image = null;
        BufferedImage tank2Image = null;
        BufferedImage breakableWall = null;
        BufferedImage unbreakableWall = null;

        this.walls = new ArrayList<>();

        try {
//            tank1Image = read(Objects.requireNonNull(this.getClass().getClassLoader().getResource("tank1.gif")));
//            tank2Image = read(Objects.requireNonNull(this.getClass().getClassLoader().getResource("tank2.gif")));
//
//            // WALLS
//            // TODO: OOPIFY
//            unbreakableWall = read(Objects.requireNonNull(this.getClass().getClassLoader().getResource("wall1.gif")));
//            breakableWall = read(Objects.requireNonNull(this.getClass().getClassLoader().getResource("wall2.gif")));
//
//            bulletImage = read(Objects.requireNonNull(this.getClass().getClassLoader().getResource("rocket.gif")));


            InputStreamReader isr = new InputStreamReader(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream("maps/map1")));
            BufferedReader mapReader = new BufferedReader(isr);

            String row = mapReader.readLine();
            if(row == null) {
                throw new IOException("no data in map file");
            }
            String[] mapInfo = row.split("\t");
            int numCols = Integer.parseInt(mapInfo[0]);
            int numRows = Integer.parseInt(mapInfo[1]);

            for(int curRow = 0; curRow < numRows; curRow++) {
                row = mapReader.readLine();
                mapInfo = row.split("\t");
                for(int curCol = 0; curCol < numCols; curCol++) {
                    switch (mapInfo[curCol]) {
                        case "2": {
                            Breakable br = new Breakable(curCol * 30, curRow * 30, Resources.breakableWallImage);
                            this.walls.add(br);
                            break;
                        }
                        case "3":
                        case "9": {
                            // Unbreakable
                            Unbreakable ubr = new Unbreakable(curCol * 30, curRow * 30, Resources.unbreakableWallImage);
                            this.walls.add(ubr);
                            break;
                        }
                    }
                }
            }


        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        // Creating tank objects
        this.tank1 = new Tank(300, 300, 0, 0, 0, Resources.tank1Image);
        this.tank2 = new Tank(500, 500, 0, 0, 0, Resources.tank2Image);

        // Creating tank control objects
        TankControl tank1Control = new TankControl(tank1, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_RIGHT, KeyEvent.VK_LEFT, KeyEvent.VK_ENTER);
        TankControl tank2Control = new TankControl(tank2, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_SPACE);

        // Adding key listeners to JFrame
        this.launcher.getJFrame().addKeyListener(tank1Control);
        this.launcher.getJFrame().addKeyListener(tank2Control);
    }

    public Tank getTank(int tankNumber) {
        if(tankNumber == 1) {
            return tank1;
        }

        if(tankNumber == 2) {
            return tank2;
        }

        // Invalid tank number given.
        // TODO: look into handling this better.
        return null;
    }

    public ArrayList<Wall> getWalls() {
        return this.walls;
    }




}
