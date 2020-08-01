package main.java.edu.csc413.TankGame.graphics.ui;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class UIManager {

    private BufferedImage buffer;
    private List<UIPanel> panels = new ArrayList<>();

    public UIManager() {

    }

    public void addPanel(UIPanel uiPanel) {
        this.panels.add(uiPanel);
    }

    public void render(Graphics graphics) {
        for(UIPanel panel : panels) {
            panel.render(graphics);
        }
    }

}
