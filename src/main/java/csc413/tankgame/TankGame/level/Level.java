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
import csc413.tankgame.TankGame.util.Assets;
import csc413.tankgame.TankGame.util.GameConstants;
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

    private Tank winner;
    private List<Bullet> bullets;
    private List<Wall> walls;
    private List<Boost> boosts;
    private List<Tank> tanks;

    public Level() {
        resetLevel();
    }

    public void resetLevel() {
        this.winner = null;
        loadLevel();
        initializeTanks();
    }

    /**
     * Initializes Tanks and adds them to the level.
     */
    private void initializeTanks() {
        Tank tank1 = new Tank(330, GameConstants.WORLD_HEIGHT / 2, 0, 0, 0, Assets.tank1Image);
        Tank tank2 = new Tank(GameConstants.WORLD_WIDTH - 330, GameConstants.WORLD_HEIGHT / 2, 0, 0, 180, Assets.tank2Image);

//        Tank tank1 = new Tank(330, GameConstants.WORLD_HEIGHT / 2, 0, 0, 0, Assets.tank1Image);
//        Tank tank2 = new Tank(400, GameConstants.WORLD_HEIGHT / 2, 0, 0, 180, Assets.tank2Image);

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
     * Respawns a tank after it loses a life.
     */
    public void respawnTank(Tank tank) {
        if(tank.getLives() > 0) {
            tank.setX(tank.getXSpawn());
            tank.setY(tank.getYSpawn());
            tank.setAngle(tank.getAngleSpawn());
            tank.resetHealth();
        } else {
            // If the tank has no more lives, mark it for removal
            tank.setRemoved();

            // Getting the winner
            for(Tank t : tanks) {
                if(t != tank) {
                    this.winner = t;
                }
            }
        }
    }

    /**
     * Loads a map from file into memory.
     * Responsible for loading walls and boosts.
     */
    private void loadLevel() {
        this.bullets = new ArrayList<>();
        this.walls = new ArrayList<>();
        this.tanks = new ArrayList<>();
        this.boosts = new ArrayList<>();

        try {
            InputStreamReader isr = new InputStreamReader(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream("maps/map1")));
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
                            Breakable br = new Breakable(x, y, Assets.breakableWallImage);
                            addEntity(br);
                            break;
                        }
                        case "3": case "9": {
                            Unbreakable ubr = new Unbreakable(x, y, Assets.unbreakableWallImage);
                            addEntity(ubr);
                            break;
                        }
                        // Boosts
                        case "4": {
                            Boost health = new Health(x, y, 10000, Assets.healthBoostImage);
                            addEntity(health);
                            break;
                        }
                        case "5": {
                            Boost bulletSpeed = new BulletSpeed(x, y, 10000, Assets.ammoBoostImage);
                            addEntity(bulletSpeed);
                            break;
                        }
                        case "6": {
                            Boost speed = new Speed(x, y, 10000, Assets.speedBoostImage);
                            addEntity(speed);
                            break;
                        }
                        case "7": {
                            Boost shield = new Shield(x, y, 10000, Assets.shieldBoostImage);
                            addEntity(shield);
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
        boosts.removeIf(boost -> boost.isRemoved() && !boost.isActive());
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
        }
        if(entity instanceof Bullet) {
            bullets.add((Bullet) entity);
        }
        if(entity instanceof Wall) {
            walls.add((Wall) entity);
        }
        if(entity instanceof Boost) {
            boosts.add((Boost) entity);
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
                // If a bullet collides with a wall and the wall is breakable
                // we should decrease the state of the wall.
                if(entity instanceof Bullet && wall.isBreakable()) {
                    Breakable breakable = (Breakable) wall;
                    breakable.decreaseState();
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Handles what happens when a Tank collides with a boost
     * @param tank to check for
     */
    public void handleTankCollidedWithBoost(Tank tank) {
        for(Boost boost : boosts) {
            if(boost.hasCollided(tank)) {
                boost.enableBoost(tank);
                boost.setRemoved();
            }
        }
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
                    ((Bullet) entity).playSound();
                    if(((Bullet) entity).getOwner() != tank) {
                        tank.decreaseHealth();
                        return true;
                    }
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    public List<Tank> getTanks() {
        return this.tanks;
    }

    public List<Boost> getBoosts() {
        return this.boosts;
    }

    /**
     * Returns a tank from the list.
     * @param index of tank in list
     * @return tanks[index]
     */
    public Tank getTank(int index) {
        return this.tanks.get(index);
    }




}
