import interfaces.Displayable;

import java.awt.*;

public class Sprite implements Displayable {

    protected double height;
    protected Image image;
    protected double width;
    protected double x;
    protected double y;
    protected String name ;

    public Sprite(double x, double y, double width, double height, Image image, String name) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = image;
        this.name = name;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }


    @Override
    public void draw(Graphics graphics) {
        graphics.drawImage(image, (int) x, (int) y, (int) width, (int) height, null);
    }

    @Override
    public String toString() {
        return name;
    }
}
