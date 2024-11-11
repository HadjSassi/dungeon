import java.util.ArrayList;

public class PhysicsEngine implements Engine {

    private ArrayList<Sprite> environment;
    private ArrayList<DynamicSprite> movingSpriteList;

    public PhysicsEngine() {
        this.environment = new ArrayList<>();
        this.movingSpriteList = new ArrayList<>();
    }

    public void addToEnvironmentList(Sprite sprite) {
        if (!environment.contains(sprite))
            this.environment.add(sprite);
    }

    public void addToMovingSpriteList(DynamicSprite sprite) {
        if (!movingSpriteList.contains(sprite))
            this.movingSpriteList.add(sprite);
    }

    public void setEnvironment(ArrayList<Sprite> environment) {
        this.environment = environment;
    }


    @Override
    public void update() {
        for (DynamicSprite sprite : movingSpriteList) {
            sprite.moveIfPossible(environment);
        }
    }
}
