package main.java.edu.csc413.TankGame;

import main.java.edu.csc413.TankGame.Panels.End;
import main.java.edu.csc413.TankGame.Panels.Game;
import main.java.edu.csc413.TankGame.Panels.Start;
import main.java.edu.csc413.TankGame.Utilities.GameConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class Launcher {

    // Panels
    private JPanel mainPanel;
    private JPanel startPanel;
    private JPanel endPanel;

    // this is a runnable
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
        this.game = new Game(this);
        this.game.initializeGame();
        this.cardLayout = new CardLayout();

        // Configuring Panels
        this.jFrame.setResizable(false);
        this.mainPanel.setLayout(this.cardLayout);
        this.mainPanel.add(this.startPanel, "start");
        this.mainPanel.add(this.endPanel, "end");
        this.mainPanel.add(this.game, "game");
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
                break;
            }
            case "game": {
                this.jFrame.setSize(GameConstants.GAME_SCREEN_WIDTH, GameConstants.GAME_SCREEN_HEIGHT);
                (new Thread((Runnable) this.game)).start();
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
