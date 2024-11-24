import java.awt.*;

public class LevelSprite extends SolidSprite{

    private boolean finalLevel ;

    public LevelSprite(double x, double y, double width, double height, Image image,String name) {
        super(x, y, width, height, image, name);
        finalLevel = false;
    }
    public LevelSprite(double x, double y, double width, double height, Image image,String name, boolean finalLevel) {
        super(x, y, width, height, image, name);
        this.finalLevel = finalLevel;
    }


    public boolean isFinalLevel() {
        return finalLevel;
    }

    public void setFinalLevel(boolean finalLevel) {
        this.finalLevel = finalLevel;
    }
}
