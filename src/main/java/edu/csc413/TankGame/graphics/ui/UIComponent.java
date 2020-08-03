package main.java.edu.csc413.TankGame.graphics.ui;

import java.awt.*;

public abstract class UIComponent  {

    private int x, y;
    private int xOffset, yOffset;
    private UIPanel uiPanel;

    public UIComponent(int x, int y, int xOffset, int yOffset) {
        this.x = x;
        this.y = y;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public void init(UIPanel uiPanel) {
        this.uiPanel = uiPanel;
    }

    public abstract void update();

    public abstract void render(Graphics graphics);


}
