package main.java.edu.csc413.TankGame.graphics.ui;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UIPanel extends UIComponent {

    private int width, height;
    private int x, y;

    private List<UIComponent> componenets = new ArrayList<>();

    public UIPanel(int x, int y, int xOffset, int yOffset, int width, int height) {
        super(x, y, xOffset, yOffset);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void addComponent(UIComponent uiComponent) {
        uiComponent.init(this);
        componenets.add(uiComponent);
    }

    public void update() {
        for(UIComponent uiComponent : componenets) {
            uiComponent.update();
        }
    }

    public void render(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, width, height);
        for(UIComponent uiComponent : componenets) {
            uiComponent.render(graphics);
        }
    }



}
