import java.awt.*;
import java.awt.geom.Rectangle2D;

public class MalusSprite extends SolidSprite {

    private boolean killing;
    private int damage;

    public MalusSprite(double x, double y, double width, double height, Image image, String name) {
        super(x, y, width, height, image, name);
        this.killing = false;
        this.damage = 1;
    }

    public MalusSprite(double x, double y, double width, double height, Image image, String name, boolean killing, int damage) {
        super(x, y, width, height, image, name);
        this.damage = damage;
        this.killing = killing;
    }


    public int getDamage() {
        return damage;
    }
}
