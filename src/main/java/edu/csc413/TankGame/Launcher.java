package main.java.edu.csc413.TankGame;

import main.java.edu.csc413.TankGame.menu.End;
import main.java.edu.csc413.TankGame.menu.Start;
import main.java.edu.csc413.TankGame.util.GameConstants;

import javax.swing.*;
import java.awt.*;

public class Launcher {

    // Frame and Card Layout
    private static JFrame jFrame;
    private CardLayout cardLayout;

    // Panels
    private JPanel mainPanel;
    private JPanel startPanel;
    private JPanel endPanel;

    private Game game;

    public Launcher() {
        jFrame = new JFrame();
        jFrame.setResizable(false);
        jFrame.setTitle("Tank Wars | Andrei Georgescu");
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initUserInterface() {
        // Instantiating panels
        this.mainPanel = new JPanel();
        this.startPanel = new Start(this);
        this.endPanel = new End(this);
        this.cardLayout = new CardLayout();

        // Instantiating game
        this.game = new Game(this);

        // Adding panels into Frame
        this.mainPanel.setLayout(this.cardLayout);
        this.mainPanel.add(this.startPanel, "start");
        this.mainPanel.add(this.endPanel, "end");
        this.mainPanel.add(this.game, "game");
        jFrame.add(mainPanel);

        // Setting the current panel to start panel.
        this.setPanel("start");
    }

    /**
     * Changes the current panel.
     * Possible types are "start", "end", and "game".
     */
    public void setPanel(String type) {
        // Hiding the jFrame while making changes.
        jFrame.setVisible(false);

        // Setting the current panel.
        switch (type.toLowerCase()) {
            case "start": {
                jFrame.setSize(GameConstants.START_MENU_SCREEN_WIDTH, GameConstants.START_MENU_SCREEN_HEIGHT);
                break;
            }
            case "end": {
                jFrame.setSize(GameConstants.END_MENU_SCREEN_WIDTH, GameConstants.END_MENU_SCREEN_HEIGHT);
//                this.game.stop();
                break;
            }
            case "game": {
                jFrame.setSize(GameConstants.GAME_WIDTH, GameConstants.GAME_HEIGHT);
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
    public static JFrame getJFrame() {
        return jFrame;
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
