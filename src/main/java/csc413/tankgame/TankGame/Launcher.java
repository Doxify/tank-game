package csc413.tankgame.TankGame;

import csc413.tankgame.TankGame.menu.End;
import csc413.tankgame.TankGame.menu.Start;
import csc413.tankgame.TankGame.util.GameConstants;

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
        mainPanel = new JPanel();
        startPanel = new Start(this);
        endPanel = new End(this);
        cardLayout = new CardLayout();

        // Instantiating game
        game = new Game(this);

        // Adding panels into Frame
        mainPanel.setLayout(cardLayout);
        mainPanel.add(startPanel, "start");
        mainPanel.add(endPanel, "end");
        mainPanel.add(game.getScreen(), "game");
        jFrame.add(mainPanel);

        // Setting the current panel to start panel.
        setPanel("start");
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
//                game.stop();
                break;
            }
            case "game": {
                jFrame.setSize(GameConstants.GAME_WIDTH, GameConstants.GAME_HEIGHT);
                game.start();
                break;
            }
        }

        // Changing CardLayout and showing the jFrame again.
        cardLayout.show(mainPanel, type);
        jFrame.setVisible(true);
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
