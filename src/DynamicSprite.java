import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class DynamicSprite extends SolidSprite {
    private boolean isWalking;
    private double speed;
    private double oldSpeed;
    private int spriteSheetNumberOfColumn;
    private double timeBetweenFrame;
    private Direction direction;
    private double health;
    private boolean isHero;
    private boolean isAttacking;
    private float attackOpacity;
    private double attackRadius;
    private double attackValue;
    private boolean isAlive;

/*
    // Constructors !
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
*/

    public DynamicSprite(double x, double y, double width, double height, Image image, String name, double health, boolean isHero) {
        super(x, y, width, height, image, name);
        this.isWalking = false;
        this.speed = 5;
        this.oldSpeed = 5;
        this.spriteSheetNumberOfColumn = 10;
        this.timeBetweenFrame = 100;
        this.direction = Direction.EAST;
        this.isHero = isHero;
        this.health = health;
        this.isAttacking = false;
        this.attackOpacity = 1.0f;
        this.attackValue = 30;
        this.isAlive = true;
    }

    public DynamicSprite(double x, double y, double width, double height, Image image, String name, boolean isWalking, double speed, int spriteSheetNumberOfColumn, double timeBetweenFrame, Direction direction, boolean isHero, double attackValue) {
        super(x, y, width, height, image, name);
        this.isWalking = isWalking;
        this.oldSpeed = speed;
        this.speed = speed;
        this.spriteSheetNumberOfColumn = spriteSheetNumberOfColumn;
        this.timeBetweenFrame = timeBetweenFrame;
        this.direction = direction;
        this.health = 100;
        this.isHero = isHero;
        this.isAttacking = false;
        this.attackOpacity = 1.0f;
        this.attackValue = attackValue;
        this.isAlive = true;
    }

    @Override
    public void draw(Graphics graphics) {
        if (health > 0) {
            Graphics2D g2d = (Graphics2D) graphics;

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
            if (isAttacking) {
                g2d.setColor(new Color(255, 0, 0, (int) (attackOpacity * 255)));
                Ellipse2D.Double attackCircle = new Ellipse2D.Double(
                        x + width / 2 - attackRadius / 2,
                        y + height / 2 - attackRadius / 2,
                        attackRadius,
                        attackRadius
                );
                g2d.setStroke(new BasicStroke(2));
                g2d.draw(attackCircle);
            }
        }
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
            if (isHero && !sprite.isHero && sprite.isAlive && sprite.intersect(anitcipatedMove)) {
                this.setHealth(this.health - Main.getLevelNumber());
                Main.setOldHeroHealth(this.health);
            }
        }

        for (Sprite sprite : environment) {
            if (isHero && sprite instanceof MalusSprite && ((MalusSprite) sprite).intersect(anitcipatedMove)) {
                this.setHealth(this.health - ((MalusSprite) sprite).getDamage());
                Main.setOldHeroHealth(this.health);
                return true;
            }
            if (isHero && sprite instanceof BonusSprite && ((BonusSprite) sprite).intersect(anitcipatedMove)) {
                this.setHealth(this.health + ((BonusSprite) sprite).getBonusValue());
                Main.setOldHeroHealth(this.health);
                if (((BonusSprite) sprite).isLimited()) {
                    ((BonusSprite) sprite).collect();
                    return true;
                }

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

    public boolean isAlive(){
        return isAlive;
    }

    public boolean isWalking() {
        return isWalking;
    }

    public double getHealth() {
        return health;
    }

    private void setHealth(double health) {
        if (health >= 0 && health <= 100)
            this.health = health;
        if(health <= 0) {
            this.health = 0;
            this.isAlive = false;
        }
        if (health >= 100)
            this.health = 100;
        checkIfDead();
    }

    private void checkIfDead() {
        if (isHero && this.health == 0) {
            Main.perdre();
        }
        if(!isHero && this.health == 0){
            Main.increaseScore();
        }
    }

    public void speedUp() {

        this.speed = oldSpeed * 1.5;
    }

    public void speedDown() {
        this.speed = oldSpeed;
    }

    public void attack() {
        isAttacking = true;
        attackOpacity = 1.0f;
        double initialRadius = 10;
        double maxRadius = width * 1000;

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            double currentRadius = initialRadius;

            @Override
            public void run() {
                attackOpacity -= 0.1f;
                currentRadius += 10;

                if (attackOpacity <= 0 || currentRadius >= maxRadius) {
                    attackOpacity = 0;
                    isAttacking = false;
                    timer.cancel();
                }

                attackRadius = currentRadius;
            }
        }, 0, 100);
        Rectangle2D.Double anitcipatedMove = new Rectangle2D.Double();
        anitcipatedMove.setRect(super.getHitBox().getX() + speed, super.getHitBox().getY()+speed, super.getHitBox().getWidth(), super.getHitBox().getHeight());

        for (DynamicSprite sprite : PhysicsEngine.getDynamicSpriteList()) {
            if (isHero && !sprite.isHero && sprite.intersect(anitcipatedMove)) {
                sprite.setHealth(sprite.getHealth()-this.attackValue);
            }
        }
    }
    
}

