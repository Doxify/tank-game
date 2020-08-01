package main.java.edu.csc413.TankGame.graphics.ui;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UIPanel extends UIComponent {

    private int width, height;
    private List<UIComponent> components = new ArrayList<>();

    public UIPanel(int x, int y, int width, int height) {
        super(x, y);
        this.width = width;
        this.height = height;
    }

    public void addComponenet(UIComponent component) {
        this.components.add(component);
    }

    public void render(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(x, y, width, height);

        for(UIComponent component : components) {
            component.render(graphics);
        }
    }



}
