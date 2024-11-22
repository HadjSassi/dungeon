import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class DynamicSprite extends SolidSprite {
    private boolean isWalking;
    private double speed;
    private int spriteSheetNumberOfColumn;
    private double timeBetweenFrame;
    private Direction direction;
    private double health;
    private boolean isHero;

    public DynamicSprite(double x, double y, double width, double height, Image image) {
        super(x, y, width, height, image);
        this.isWalking = false;
        this.speed = 5;
        this.spriteSheetNumberOfColumn = 10;//number of sprite in the sprite sheet
        this.timeBetweenFrame = 100;
        this.direction = Direction.EAST;
        this.health = 100;
        this.isHero = false;
    }

    public DynamicSprite(double x, double y, double width, double height, Image image, double health) {
        super(x, y, width, height, image);
        this.isWalking = false;
        this.speed = 5;
        this.spriteSheetNumberOfColumn = 10;//number of sprite in the sprite sheet
        this.timeBetweenFrame = 100;
        this.direction = Direction.EAST;
        this.isHero = false;
        this.health = health;

    }

    public DynamicSprite(double x, double y, double width, double height, Image image, boolean isWalking, double speed, int spriteSheetNumberOfColumn, double timeBetweenFrame, Direction direction) {
        super(x, y, width, height, image);
        this.isWalking = isWalking;
        this.speed = speed;
        this.spriteSheetNumberOfColumn = spriteSheetNumberOfColumn;
        this.timeBetweenFrame = timeBetweenFrame;
        this.direction = direction;
        this.health = 100;
        this.isHero = false;
    }

    public DynamicSprite(double x, double y, double width, double height, Image image, boolean isWalking, double speed, int spriteSheetNumberOfColumn, double timeBetweenFrame, Direction direction, double health) {
        super(x, y, width, height, image);
        this.isWalking = isWalking;
        this.speed = speed;
        this.spriteSheetNumberOfColumn = spriteSheetNumberOfColumn;
        this.timeBetweenFrame = timeBetweenFrame;
        this.direction = direction;
        this.health = health;
        this.isHero = false;
    }

    public DynamicSprite(double x, double y, double width, double height, Image image, boolean isHero) {
        super(x, y, width, height, image);
        this.isWalking = false;
        this.speed = 5;
        this.spriteSheetNumberOfColumn = 10;//number of sprite in the sprite sheet
        this.timeBetweenFrame = 100;
        this.direction = Direction.EAST;
        this.health = 100;
        this.isHero = isHero;
    }

    public DynamicSprite(double x, double y, double width, double height, Image image, double health, boolean isHero) {
        super(x, y, width, height, image);
        this.isWalking = false;
        this.speed = 5;
        this.spriteSheetNumberOfColumn = 10;//number of sprite in the sprite sheet
        this.timeBetweenFrame = 100;
        this.direction = Direction.EAST;
        this.isHero = isHero;
        this.health = health;

    }

    public DynamicSprite(double x, double y, double width, double height, Image image, boolean isWalking, double speed, int spriteSheetNumberOfColumn, double timeBetweenFrame, Direction direction, boolean isHero) {
        super(x, y, width, height, image);
        this.isWalking = isWalking;
        this.speed = speed;
        this.spriteSheetNumberOfColumn = spriteSheetNumberOfColumn;
        this.timeBetweenFrame = timeBetweenFrame;
        this.direction = direction;
        this.health = 100;
        this.isHero = isHero;
    }

    public DynamicSprite(double x, double y, double width, double height, Image image, boolean isWalking, double speed, int spriteSheetNumberOfColumn, double timeBetweenFrame, Direction direction, double health, boolean isHero) {
        super(x, y, width, height, image);
        this.isWalking = isWalking;
        this.speed = speed;
        this.spriteSheetNumberOfColumn = spriteSheetNumberOfColumn;
        this.timeBetweenFrame = timeBetweenFrame;
        this.direction = direction;
        this.health = health;
        this.isHero = isHero;
    }

    @Override
    public void draw(Graphics graphics) {
        int barWidth = (int) width;
        int barHeight = 5;
        int healthBarX = (int) x;
        int healthBarY = (int) y - barHeight - 2;

        int healthBarWidth = (int) (barWidth * (health / 100.0));

        graphics.setColor(Color.GRAY);
        graphics.fillRect(healthBarX, healthBarY, barWidth, barHeight);

        graphics.setColor(Color.RED);
        graphics.fillRect(healthBarX, healthBarY, healthBarWidth, barHeight);

        int dynamicSpriteImage = 0;
        if (isWalking) {
            dynamicSpriteImage = (int) (System.currentTimeMillis() / timeBetweenFrame % spriteSheetNumberOfColumn);

        }
        graphics.drawImage(
                image, (int) x, (int) y, (int) (x + width), (int) (y + height),
                (int) (dynamicSpriteImage * this.width), (int) (direction.getFrameLineNumber() * height),
                (int) ((dynamicSpriteImage + 1) * this.width), (int) ((direction.getFrameLineNumber() + 1) * this.height), null
        );
    }

    public void moveIfPossible(ArrayList<Sprite> environment, ArrayList<DynamicSprite> dynamicSprites) {
        if (isMovingPossible(environment, dynamicSprites))
            move();
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    private boolean isMovingPossible(ArrayList<Sprite> environment, ArrayList<DynamicSprite> dynamicSprites) {
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

        for (DynamicSprite sprite : dynamicSprites) {
            if (isHero && !sprite.isHero &&sprite.intersect(anitcipatedMove)) {
                this.setHealth(this.health - Main.getLevelNumber());
                Main.setOldHeroHealth(this.health);
            }
        }

        for (Sprite sprite : environment) {
            if (isHero && sprite instanceof MalusSprite && ((MalusSprite) sprite).intersect(anitcipatedMove)) {
                this.setHealth(this.health - ((MalusSprite) sprite).getDamage());
                Main.setOldHeroHealth(this.health);
            }
            if (isHero && sprite instanceof BonusSprite && ((BonusSprite) sprite).intersect(anitcipatedMove)) {
                this.setHealth(this.health + ((BonusSprite) sprite).getBonusValue());
                Main.setOldHeroHealth(this.health);
                //todo if the bonus is limited then remove it from the sprite list and repaint.

            }
            if (sprite instanceof SolidSprite) {
                if (sprite != this) {
                    if (((SolidSprite) sprite).intersect(anitcipatedMove)) {
                        if (sprite instanceof LevelSprite) {
                            if (((LevelSprite) sprite).isFinalLevel()) {
                                Main.gagner();
                            } else {
                                Main.increaseLevel();
                                Main.loadNextLevel();
                            }
                        } else {
                            isWalking = false;
                            return false;
                        }

                    }
                }
            }
        }
        isWalking = true;
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

    public double getSpeed() {
        return speed;
    }

    public boolean isWalking() {
        return isWalking;
    }

    public double getHealth() {
        return health;
    }

    private void setHealth(double health) {
        if (health > 0 && health < 100)
            this.health = health;
    }

}

