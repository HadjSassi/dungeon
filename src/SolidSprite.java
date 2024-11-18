import java.awt.*;
import java.awt.geom.Rectangle2D;

public class SolidSprite extends Sprite {

    public SolidSprite(double x, double y, double width, double height, Image image) {
        super(x, y, width, height, image);
    }

    public Rectangle2D getHitBox(){
        return new Rectangle2D.Double(x,y,width-5,height-20);
    }

    public boolean intersect(Rectangle2D.Double rect) {
        return this.getHitBox().intersects(rect);
    }
}

//this.class is finished
