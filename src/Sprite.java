import java.awt.*;

public class Sprite implements Displayable{

    protected double height;
    protected Image image;
    protected double width;
    protected double x;
    protected double y;

    public Sprite(double x, double y, double width, double height, Image image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = image;
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.drawImage(image, (int) x, (int) y, (int) width, (int) height, null);
    }
}
