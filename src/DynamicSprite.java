//todo finish the DynamicSprite class' methods

import java.awt.*;

public class DynamicSprite extends SolidSprite{
    private boolean isWalking;
    private double speed;
    private int spriteSheetNumberOfColumn;
    private double timeBetweenFrame;
    private Direction direction;

    public DynamicSprite(double x, double y, double width, double height, Image image){
        super(x,y,width,height,image);
    }

    @Override
    public void draw(Graphics graphics) {
    }

    public void moveIfPossible(){

    }

    public void setDirection(Direction direction){
        this.direction = direction;
    }

    private boolean isMovingPossible(){
        return false;
    }

    private void move(){

    }
}
