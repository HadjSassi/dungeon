import java.awt.*;

public class BonusSprite extends SolidSprite {

    private boolean limited;
    private double bonusValue;
    private boolean collected;

    public BonusSprite(double x, double y, double width, double height, Image image, String name) {
        super(x, y, width, height, image, name);
        this.limited = false;
        this.bonusValue = 10;
        this.collected = false;
    }

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
        if (!collected)
            return bonusValue;
        else
            return 0;
    }

    public boolean isLimited() {
        return limited;
    }

    public boolean isCollected() {
        return collected;
    }

    public void collect() {
        collected = true; // Marque la pièce comme collectée
    }

}
