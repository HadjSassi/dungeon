import java.awt.*;

import static enumerations.NumericalConstants.DAMAGE;

public class MalusSprite extends SolidSprite {

    private int damage;

    public MalusSprite(double x, double y, double width, double height, Image image, String name) {
        super(x, y, width, height, image, name);
        this.damage = (int) DAMAGE.getNumericalValue();
    }

    public int getDamage() {
        return damage;
    }
}
