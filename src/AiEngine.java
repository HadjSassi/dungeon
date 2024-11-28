import enumerations.Direction;
import interfaces.Engine;

import static enumerations.NumericalConstants.*;

public class AiEngine implements Engine {

    private DynamicSprite monster;
    private DynamicSprite hero;
    private boolean catching;

    public AiEngine(DynamicSprite monster, DynamicSprite hero) {
        this.monster = monster;
        this.hero = hero;
        this.catching = true;
    }


    @Override
    public void update() {
        if (monster.isAlive()) {
            double monsterX = monster.getX();
            double monsterY = monster.getY();
            double heroX = hero.getX();
            double heroY = hero.getY();

            double deltaX = heroX - monsterX;
            double deltaY = heroY - monsterY;
            if (DISTANCE_MONSTER_TO_ATTACK.getNumericalValue() >= Math.abs(deltaX)
                    && DISTANCE_MONSTER_TO_ATTACK.getNumericalValue() >= Math.abs(deltaY)) {
                //todo stop the monster to change direction or to stop from moving
                catching = false;
                monster.setDirection(hero.getDirection());
            } else if (catching && Math.abs(deltaX) > Math.abs(deltaY)) {
                if (deltaX > INITIAL_DIRECTION.getNumericalValue()) {
                    monster.setDirection(Direction.EAST);
                } else {
                    monster.setDirection(Direction.WEST);
                }
            } else {
                if (deltaY > INITIAL_DIRECTION.getNumericalValue()) {
                    monster.setDirection(Direction.SOUTH);
                } else {
                    monster.setDirection(Direction.NORTH);
                }
            }
            if (!monster.isWalking()) {
                changeBlockedDirection();
            }
        }

    }


    private void changeBlockedDirection() {
        Direction currentDirection = monster.getDirection();
        Direction newDirection;

        if (currentDirection == Direction.NORTH || currentDirection == Direction.SOUTH) {
            newDirection = Math.random() < RANDOM_DECISION_VALUE.getNumericalValue() ? Direction.EAST : Direction.WEST;
        } else {
            newDirection = Math.random() < RANDOM_DECISION_VALUE.getNumericalValue() ? Direction.NORTH : Direction.SOUTH;
        }

        monster.setDirection(newDirection);
    }

}