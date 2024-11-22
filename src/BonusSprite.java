import java.awt.*;

public class BonusSprite extends SolidSprite{

    private boolean limited ;
    private double bonusValue;

    public BonusSprite(double x, double y, double width, double height, Image image) {
        super(x, y, width, height, image);
        limited = false;
        bonusValue = 10;
    }
    public BonusSprite(double x, double y, double width, double height, Image image, boolean limited, double bonusValue) {
        super(x, y, width, height, image);
        this.limited = limited;
        this.bonusValue = bonusValue;
    }

    public double getBonusValue() {
        return bonusValue;
    }

    public boolean isLimited() {
        return limited;
    }
}
