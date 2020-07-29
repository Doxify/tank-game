package main.java.edu.csc413.TankGame.level;

import main.java.edu.csc413.TankGame.entity.Entity;
import main.java.edu.csc413.TankGame.entity.movable.Bullet;
import main.java.edu.csc413.TankGame.entity.movable.Movable;
import main.java.edu.csc413.TankGame.entity.movable.Tank;
import main.java.edu.csc413.TankGame.entity.stationary.Stationary;
import main.java.edu.csc413.TankGame.entity.stationary.wall.Breakable;
import main.java.edu.csc413.TankGame.entity.stationary.wall.Unbreakable;
import main.java.edu.csc413.TankGame.entity.stationary.wall.Wall;
import main.java.edu.csc413.TankGame.graphics.Assets;
import main.java.edu.csc413.TankGame.util.GameConstants;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Level {

    private int width = GameConstants.WORLD_WIDTH;
    private int height = GameConstants.WORLD_HEIGHT;

//    private final List<Movable> movables = new ArrayList<>();
    private final List<Bullet> bullets = new ArrayList<>();
    private final List<Stationary> stationaries = new ArrayList<>();
    private final List<Tank> tanks = new ArrayList<>();

    public Level(String levelName) {
        loadLevel(levelName);
    }

    private void loadLevel(String levelName) {
        try {
            InputStreamReader isr = new InputStreamReader(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream("maps/" + levelName)));
            BufferedReader mapReader = new BufferedReader(isr);

            String row = mapReader.readLine();
            if(row == null) throw new IOException("no data in map file.");

            String[] mapInfo = row.split("\t");
            int numCols = Integer.parseInt(mapInfo[0]);
            int numRows = Integer.parseInt(mapInfo[1]);

            for(int curRow = 0; curRow < numRows; curRow++) {
                row = mapReader.readLine();
                mapInfo = row.split("\t");
                for(int curCol = 0; curCol < numCols; curCol++) {
                    int x = curCol * 30;
                    int y = curRow * 30;

                    switch(mapInfo[curCol]) {
                        // Walls
                        case "2": {
                            addEntity(new Breakable(x, y, Assets.breakableWallImage));
                            break;
                        }
                        case "3": case "9": {
                            addEntity(new Unbreakable(x, y, Assets.unbreakableWallImage));
                            break;
                        }
                        // Boosts
                        case "4": {

                        }
                        case "5": {

                        }
                        case "6": {

                        }
                        case "7": {

                        }
                        case "8": {

                        }
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    /**
     * Handles the clean up of entities from the Level.
     */
    private void remove() {
        bullets.removeIf(Entity::isRemoved);
        stationaries.removeIf(Entity::isRemoved);
    }

    /**
     * Calls on entities to update their state.
     */
    public void update() {
        bullets.forEach(Movable::update);
        tanks.forEach(Tank::update);
        remove();
    }

    /**
     * Calls on entities to render themselves to the buffer.
     * @param buffer to render to
     */
    public void render(Graphics buffer) {
        bullets.forEach(movable -> movable.render(buffer));
        stationaries.forEach(stationary -> stationary.render(buffer));
        tanks.forEach(tank -> tank.render(buffer));
    }

    /**
     * Adds a new GameObject to the Level.
     * @param entity to add
     */
    public void addEntity(Entity entity) {
        entity.init(this);
        if (entity instanceof Tank) {
            tanks.add((Tank) entity);
//        } else if(entity instanceof Movable) {
//            movables.add((Movable) entity);
        } else if(entity instanceof Bullet) {
            bullets.add((Bullet) entity);
        } else if(entity instanceof Stationary) {
            stationaries.add((Stationary) entity);
        }
    }

    /**
     * Removes an entity from the level
     * @param entity to remove
     */
    public void removeEntity(Entity entity) {
        if (entity instanceof Tank) {
            tanks.remove(entity);
        } else if(entity instanceof Bullet) {
            bullets.remove(entity);
        } else if(entity instanceof Stationary) {
            stationaries.remove(entity);
        }
    }

    /**
     * Determines if an entity has collided with a wall.
     * @param entity that must be checked for collision
     * @return whether or not it has collided with a wall
     */
    public boolean entityCollidedWithWall(Entity entity) {
        for(Stationary stationary : stationaries) {
            if(stationary instanceof Wall) {
                if(stationary.hasCollided(entity)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Determines if an entity has collided with a tank.
     * @param entity that must be checked for collision
     * @return whether or not an entity has collided with
     *         entity.
     */
    public boolean entityCollidedWithTank(Entity entity) {
        for(Tank tank : tanks) {
            if(tank.hasCollided(entity)) {
                // If the entity that called the function is a Bullet,
                // check to make sure that the bullet was not fired
                // by the tank it hit.
                if(entity instanceof Bullet) {

                    if(((Bullet) entity).getOwner() != tank) {
                        tank.decreaseHealth(2);
                        System.out.println("Tank1: " + tank.getHealth() + "/" + tank.getMAX_HEALTH());
                        return true;
                    }
                    return false;
                }

                // Otherwise return true since a collision has
                // been detected.
                return true;
            }
        }
        return false;
    }


    public List<Bullet> getBullets() {
        return bullets;
    }

    public List<Stationary> getStationaries() {
        return stationaries;
    }

    public List<Tank> getTanks() {
        return tanks;
    }

    public Tank getTank(int index) {
        return tanks.get(index);
    }



}
