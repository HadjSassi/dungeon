import java.awt.*;

import static enumerations.NumericalConstants.INITIAL_VALUE;

public class BonusSprite extends SolidSprite {

    private boolean limited;
    private double bonusValue;
    private boolean collected;

    public BonusSprite(double x, double y, double width, double height, Image image, String name, boolean limited, double bonusValue) {
        super(x, y, width, height, image, name);
        this.limited = limited;
        this.bonusValue = bonusValue;
        this.collected = false;
    }

    @Override
    public void draw(Graphics graphics) {
        if (!collected)
            graphics.drawImage(image, (int) x, (int) y, null);
    }

    public double getBonusValue() {
        return (!collected) ? bonusValue : INITIAL_VALUE.getNumericalValue();
    }

    public boolean isLimited() {
        return limited;
    }

    public void collect() {
        collected = true;
    }

}
