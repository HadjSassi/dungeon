import enumerations.Direction;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static enumerations.NumericalConstants.*;

public class DynamicSprite extends SolidSprite {
    private boolean isWalking;
    private double speed;
    private double oldSpeed;
    private int spriteSheetNumberOfColumn;
    private Direction direction;
    private double health;
    private boolean isHero;
    private boolean isAttacking;
    private float attackOpacity;
    private double attackRadius;
    private double attackValue;
    private boolean isAlive;

    public DynamicSprite(double x, double y, double width, double height, Image image, String name, double health, boolean isHero) {
        super(x, y, width, height, image, name);
        this.isWalking = false;
        this.speed = 5;
        this.oldSpeed = 5;
        this.spriteSheetNumberOfColumn = 10;
        this.direction = Direction.EAST;
        this.isHero = isHero;
        this.health = health;
        this.isAttacking = false;
        this.attackOpacity = 1.0f;
        this.attackValue = 30;
        this.isAlive = true;
    }

    public DynamicSprite(double x, double y, double width, double height, Image image, String name, boolean isWalking, double speed, int spriteSheetNumberOfColumn, Direction direction, boolean isHero, double attackValue) {
        super(x, y, width, height, image, name);
        this.isWalking = isWalking;
        this.oldSpeed = speed;
        this.speed = speed;
        this.spriteSheetNumberOfColumn = spriteSheetNumberOfColumn;
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
        if (health > INITIAL_VALUE.getNumericalValue()) {
            Graphics2D g2d = (Graphics2D) graphics;

            int barWidth = (int) width;
            int barHeight = (int) BAR_HEIGHT.getNumericalValue();
            int healthBarX = (int) x;
            int healthBarY = (int) y - barHeight;

            int healthBarWidth = (int) (barWidth * (health / TOTAL_PERCENTAGE.getNumericalValue()));

            graphics.setColor(Color.GRAY);
            graphics.fillRect(healthBarX, healthBarY, barWidth, barHeight);

            graphics.setColor(Color.RED);
            graphics.fillRect(healthBarX, healthBarY, healthBarWidth, barHeight);

            int dynamicSpriteImage = (int) INITIAL_VALUE.getNumericalValue();
            if (isWalking) {
                double dynamicTimeBetweenFrame = Math.max(MAXIMUM_SPEED.getNumericalValue() / oldSpeed, MAXIMUM_SPEED.getNumericalValue() / speed);
                dynamicSpriteImage = (int) (System.currentTimeMillis() / dynamicTimeBetweenFrame % spriteSheetNumberOfColumn);

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
                this.setHealth(this.health - Main.getLevelNumber() * Main.getDifficulty().getDifficultyValue());
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
                                Main.setOldHeroHealth(this.health);
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

    public boolean isAlive() {
        return isAlive;
    }

    public boolean isWalking() {
        return isWalking;
    }

    public double getHealth() {
        return health;
    }

    /*
     * This method modify the attribute health, but in conditions and check if the character is dead or not
     * */
    private void setHealth(double health) {
        if (health >= MINIMUM_HEALTH.getNumericalValue() && health <= MAXIMUM_HEALTH.getNumericalValue()) {
            if (this.health > health)
                SoundSystem.playOuchSound();
            else {
                SoundSystem.playBonusSound();
            }
            this.health = health;

        }
        if (health <= MINIMUM_HEALTH.getNumericalValue()) {
            this.health = MINIMUM_HEALTH.getNumericalValue();
            this.isAlive = false;
            SoundSystem.playDeadSound();
        }
        if (health >= MAXIMUM_HEALTH.getNumericalValue())
            this.health = MAXIMUM_HEALTH.getNumericalValue();
        checkIfDead();
    }

    /*
     * This method stops the game if the hero lost, and increases the score an enemy killed
     * */
    private void checkIfDead() {
        if (isHero && this.health == MINIMUM_HEALTH.getNumericalValue()) {
            Main.perdre();
        }
        if (!isHero && this.health == MINIMUM_HEALTH.getNumericalValue()) {
            Main.increaseScore();
        }
    }

    public void speedUp() {
        this.speed = oldSpeed * SPEED_COEFFICIENT.getNumericalValue();
    }

    public void speedDown() {
        this.speed = oldSpeed;
    }

    /*
     * This method calculate a threaten radius that can hurt an enemy if is in this radius
     * */
    public void attack() {
        isAttacking = true;
        attackOpacity = (float) ATTACK_OPACITY.getNumericalValue();
        double initialRadius = INITIAL_ATTACK_RADIUS.getNumericalValue();
        double maxRadius = width * MAXIMUM_ATTACK_RADIUS.getNumericalValue();

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            double currentRadius = initialRadius;

            @Override
            public void run() {
                attackOpacity -= (float) ATTACK_FADE_STEP.getNumericalValue();
                currentRadius += (float) ATTACK_RADIUS_STEP.getNumericalValue();

                if (attackOpacity <= INITIAL_VALUE.getNumericalValue() || currentRadius >= maxRadius) {
                    attackOpacity = (float) INITIAL_VALUE.getNumericalValue();
                    isAttacking = false;
                    timer.cancel();
                }

                attackRadius = currentRadius;
            }
        }, (long) INITIAL_VALUE.getNumericalValue(), (long) TOTAL_PERCENTAGE.getNumericalValue());
        Rectangle2D.Double anitcipatedMove = new Rectangle2D.Double();
        anitcipatedMove.setRect(super.getHitBox().getX() + speed, super.getHitBox().getY() + speed, super.getHitBox().getWidth(), super.getHitBox().getHeight());

        for (DynamicSprite sprite : PhysicsEngine.getDynamicSpriteList()) {
            if (isHero && !sprite.isHero && sprite.intersect(anitcipatedMove)) {
                sprite.setHealth(sprite.getHealth() - this.attackValue);
            }
        }
    }


}

