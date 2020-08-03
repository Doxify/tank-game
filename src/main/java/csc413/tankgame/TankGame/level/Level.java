package csc413.tankgame.TankGame.level;

import csc413.tankgame.TankGame.Launcher;
import csc413.tankgame.TankGame.entity.Entity;
import csc413.tankgame.TankGame.entity.boost.*;
import csc413.tankgame.TankGame.entity.movable.Bullet;
import csc413.tankgame.TankGame.entity.movable.Movable;
import csc413.tankgame.TankGame.entity.movable.Tank;
import csc413.tankgame.TankGame.entity.wall.Breakable;
import csc413.tankgame.TankGame.entity.wall.Unbreakable;
import csc413.tankgame.TankGame.entity.wall.Wall;
import csc413.tankgame.TankGame.graphics.Assets;
import csc413.tankgame.TankGame.util.TankControl;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Level {

    private final List<Bullet> bullets = new ArrayList<>();
    private final List<Wall> walls = new ArrayList<>();
    private final List<Boost> boosts = new ArrayList<>();
    private final List<Tank> tanks = new ArrayList<>();

    public Level(String levelName) {
        loadLevel(levelName);
        initializeTanks();
    }

    /**
     * Initializes Tanks and adds them to the level.
     */
    private void initializeTanks() {
        Tank tank1 = new Tank(400, 400, 0, 0, 0, Assets.tank1Image);
        Tank tank2 = new Tank(400, 475, 0, 0, 0, Assets.tank2Image);
        TankControl tank1Control = new TankControl(tank1, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_RIGHT, KeyEvent.VK_LEFT, KeyEvent.VK_ENTER);
        TankControl tank2Control = new TankControl(tank2, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_D, KeyEvent.VK_A, KeyEvent.VK_SPACE);

        // Registering control listeners
        Launcher.getJFrame().addKeyListener(tank1Control);
        Launcher.getJFrame().addKeyListener(tank2Control);

        // Adding tanks into Level
        addEntity(tank1);
        addEntity(tank2);
    }

    /**
     * Loads a map from file into memory.
     * Responsible for loading walls and boosts.
     * @param levelName to load from /resources/maps/
     */
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
                            addEntity(new Health(x, y, Assets.healthBoostImage));
                            break;
                        }
                        case "5": {
                            addEntity(new BulletSpeed(x, y, 5000, Assets.ammoBoostImage));
                            break;
                        }
                        case "6": {
                            addEntity(new Speed(x, y, 5000, Assets.speedBoostImage));
                            break;
                        }
                        case "7": {
                            addEntity(new Shield(x, y, 5000, Assets.shieldBoostImage));
                            break;
                        }
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Handles the clean up of entities from the Level.
     */
    private void remove() {
        bullets.removeIf(Entity::isRemoved);
        walls.removeIf(Entity::isRemoved);
        boosts.removeIf(Entity::isRemoved);
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
     * Renders all entities to the graphics buffer.
     * @param buffer to render to
     */
    public void render(Graphics buffer) {
        bullets.forEach(movable -> movable.render(buffer));
        tanks.forEach(tank -> tank.render(buffer));
        walls.forEach(stationary -> stationary.render(buffer));
        boosts.forEach(boost -> boost.render(buffer));
    }

    /**
     * Adds a new Entity to the Level.
     * @param entity to add
     */
    public void addEntity(Entity entity) {
        entity.init(this);
        if (entity instanceof Tank) {
            tanks.add((Tank) entity);
        } else if(entity instanceof Bullet) {
            bullets.add((Bullet) entity);
        } else if(entity instanceof Wall) {
            walls.add((Wall) entity);
        } else if(entity instanceof Boost) {
            boosts.add((Boost) entity);
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
        } else if(entity instanceof Wall) {
            walls.remove(entity);
        } else if(entity instanceof Boost) {
            boosts.remove(entity);
        }
    }

    /**
     * Determines if an entity has collided with a wall.
     * @param entity that must be checked for collision
     * @return whether or not it has collided with a wall
     */
    public boolean entityCollidedWithWall(Entity entity) {
        for(Wall wall : walls) {
            if(wall.hasCollided(entity)) {
                handleEntityWallCollision(entity, wall);
                return true;
            }
        }
        return false;
    }

    public boolean entityCollidedWithBoost(Entity entity) {
        for(Boost boost : boosts) {
            if(boost.hasCollided(entity)) {
                if(entity instanceof Tank) {
                    boost.enableBoost((Tank) entity);
                    boost.setRemoved();
                }
                return true;
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
                        tank.decreaseHealth();
                        System.out.println("Tank1: " + tank.getHealth() + "/" + tank.getMaxHealth());
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

    private void handleEntityWallCollision(Entity entity, Wall wall) {
        // If a bullet collides with a wall and the wall is breakable
        // we should decrease the state of the wall.
        if(entity instanceof Bullet && wall.isBreakable()) {
            Breakable breakable = (Breakable) wall;
            breakable.decreaseState();
        }
    }

    public Tank getTank(int index) {
        return tanks.get(index);
    }



}
