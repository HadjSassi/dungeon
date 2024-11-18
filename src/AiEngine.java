import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class AiEngine implements Engine {

    private DynamicSprite monster;
    private DynamicSprite hero;

    public AiEngine(DynamicSprite monster, DynamicSprite hero) {
        this.monster = monster;
        this.hero = hero;
    }


    @Override
    public void update() {
        double monsterX = monster.getX();
        double monsterY = monster.getY();
        double heroX = hero.getX();
        double heroY = hero.getY();

        double deltaX = heroX - monsterX;
        double deltaY = heroY - monsterY;

        if(5 > deltaX && 5 > deltaY){
            monster.setDirection(hero.getDirection());
        }
        else if (Math.abs(deltaX) > Math.abs(deltaY)) {
            if (deltaX > 0) {
                monster.setDirection(Direction.EAST);
            } else {
                monster.setDirection(Direction.WEST);
            }
        } else {
            if (deltaY > 0) {
                monster.setDirection(Direction.SOUTH);
            } else {
                monster.setDirection(Direction.NORTH);
            }
        }

    }

}

// normally this class is finished