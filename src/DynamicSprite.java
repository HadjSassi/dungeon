import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class DynamicSprite extends SolidSprite {
    private boolean isWalking;
    private double speed;
    private int spriteSheetNumberOfColumn;
    private double timeBetweenFrame;
    private Direction direction;

    public DynamicSprite(double x, double y, double width, double height, Image image) {
        super(x, y, width, height, image);
        this.isWalking = false;
        this.speed = 5;
        this.spriteSheetNumberOfColumn = 10;//number of sprite in the sprite sheet
        this.timeBetweenFrame = 100;
        this.direction = Direction.EAST;
    }

    public DynamicSprite(double x, double y, double width, double height, Image image, boolean isWalking, double speed, int spriteSheetNumberOfColumn, double timeBetweenFrame, Direction direction) {
        super(x, y, width, height, image);
        this.isWalking = isWalking;
        this.speed = speed;
        this.spriteSheetNumberOfColumn = spriteSheetNumberOfColumn;
        this.timeBetweenFrame = timeBetweenFrame;
        this.direction = direction;
    }

    @Override
    public void draw(Graphics graphics) {

        int dynamicSpriteImage = (int) (System.currentTimeMillis() / timeBetweenFrame % spriteSheetNumberOfColumn);

        graphics.drawImage(
                image, (int) x, (int) y, (int) (x + width), (int) (y + height),
                (int) (dynamicSpriteImage * this.width), (int) (direction.getFrameLineNumber() * height),
                (int) ((dynamicSpriteImage + 1) * this.width), (int) ((direction.getFrameLineNumber() + 1) * this.height), null
        );
    }

    public void moveIfPossible(ArrayList<Sprite> environment) {
        if (isMovingPossible(environment))
            move();
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    private boolean isMovingPossible(ArrayList<Sprite> environment) {
        Rectangle2D.Double anitcipatedMove = new Rectangle2D.Double();
        switch (direction) {
            case NORTH:
                anitcipatedMove.setRect(super.getHitBox().getX(), super.getHitBox().getY() - speed, super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
            case SOUTH:
                anitcipatedMove.setRect(super.getHitBox().getX(), super.getHitBox().getY() + speed, super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
            case EAST:
                anitcipatedMove.setRect(super.getHitBox().getX() + speed, super.getHitBox().getY(), super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
            case WEST:
                anitcipatedMove.setRect(super.getHitBox().getX() - speed, super.getHitBox().getY(), super.getHitBox().getWidth(), super.getHitBox().getHeight());
                break;
        }

        for (Sprite sprite : environment) {
            if (sprite instanceof SolidSprite) {
                if (sprite != this) {
                    if (((SolidSprite) sprite).intersect(anitcipatedMove))
                        return false;
                }
            }
        }

        return true;
    }

    private void move() {
        switch (direction) {
            case NORTH:
                this.y -= speed;
                break;
            case SOUTH:
                this.y += speed;
                break;
            case EAST:
                this.x += speed;
                break;
            case WEST:
                this.x -= speed;
                break;
        }
    }
}

