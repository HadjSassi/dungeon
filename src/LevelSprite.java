import java.awt.*;

public class LevelSprite extends SolidSprite{

    private boolean finalLevel ;

    public LevelSprite(double x, double y, double width, double height, Image image) {
        super(x, y, width, height, image);
        finalLevel = false;
    }
    public LevelSprite(double x, double y, double width, double height, Image image, boolean finalLevel) {
        super(x, y, width, height, image);
        this.finalLevel = finalLevel;
    }


    public boolean isFinalLevel() {
        return finalLevel;
    }

    public void setFinalLevel(boolean finalLevel) {
        this.finalLevel = finalLevel;
    }
}
