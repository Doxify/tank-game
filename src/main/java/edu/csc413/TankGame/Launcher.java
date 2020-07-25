package main.java.edu.csc413.TankGame;

import main.java.edu.csc413.TankGame.Panels.End;
import main.java.edu.csc413.TankGame.Panels.Start;
import main.java.edu.csc413.TankGame.Panels.World;
import main.java.edu.csc413.TankGame.Utilities.GameConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class Launcher {

    // Panels
    private JPanel mainPanel;
    private JPanel startPanel;
    private JPanel endPanel;
    private JPanel worldPanel;

    // Game Class
    private Game game;

    // Frame and Card Layout
    private JFrame jFrame;
    private CardLayout cardLayout;

    public Launcher() {
        this.jFrame = new JFrame();
        this.jFrame.setTitle("Tank Wars | Andrei Georgescu");
        this.jFrame.setLocationRelativeTo(null);
        this.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initUserInterface() {
        // Instantiating Panels
        this.mainPanel = new JPanel();
        this.startPanel = new Start(this);
        this.endPanel = new End(this);
        this.worldPanel = new World(this);
        this.cardLayout = new CardLayout();

        // Configuring Panels
        this.jFrame.setResizable(false);
        this.mainPanel.setLayout(this.cardLayout);
        this.mainPanel.add(this.startPanel, "start");
        this.mainPanel.add(this.endPanel, "end");
        this.mainPanel.add(this.worldPanel, "game");
        this.jFrame.add(mainPanel);

        // Setting the current panel to start panel.
        this.setPanel("start");
    }

    /**
     * Changes the current panel.
     * Possible types are "start", "end", and "game".
     */
    public void setPanel(String type) {
        // Hiding the jFrame while changing it.
        this.jFrame.setVisible(false);

        // Setting the current panel.
        switch (type.toLowerCase()) {
            case "start": {
                this.jFrame.setSize(GameConstants.START_MENU_SCREEN_WIDTH, GameConstants.START_MENU_SCREEN_HEIGHT);
                break;
            }
            case "end": {
                this.jFrame.setSize(GameConstants.END_MENU_SCREEN_WIDTH, GameConstants.END_MENU_SCREEN_HEIGHT);
                this.game.stop();
                break;
            }
            case "game": {
                this.jFrame.setSize(GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT + 30);
                this.game = new Game(this, worldPanel);
                this.game.initializeGame();
                this.game.start();
                break;
            }
        }

        // Changing CardLayout and showing the jFrame again.
        this.cardLayout.show(mainPanel, type);
        this.jFrame.setVisible(true);
    }

    /**
     * Returns the jFrame
     */
    public JFrame getJFrame() {
        return this.jFrame;
    }

    public Game getGame() {
        return this.game;
    }

    /**
     * Closes the game
     */
    public void closeGame() {
        this.jFrame.dispatchEvent(new WindowEvent(this.jFrame, WindowEvent.WINDOW_CLOSING));
    }

    /**
     * Main method
     * @param args
     */
    public static void main(String[] args) {
        Launcher launcher = new Launcher();
        launcher.initUserInterface();

    }

}
