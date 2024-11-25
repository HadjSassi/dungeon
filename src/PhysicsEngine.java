import java.util.ArrayList;

public class PhysicsEngine implements Engine {

    private static ArrayList<Sprite> environment;
    private static ArrayList<DynamicSprite> movingSpriteList;

    public PhysicsEngine() {
        environment = new ArrayList<>();
        movingSpriteList = new ArrayList<>();
    }

    public void addToEnvironmentList(Sprite sprite) {
        if (!environment.contains(sprite))
            environment.add(sprite);
    }

    public void addToMovingSpriteList(DynamicSprite sprite) {
        if (!movingSpriteList.contains(sprite))
            movingSpriteList.add(sprite);
    }

    public void setEnvironment(ArrayList<Sprite> environment) {
        PhysicsEngine.environment = environment;
    }

    public static ArrayList<DynamicSprite> getDynamicSpriteList() {
        return movingSpriteList;
    }

    @Override
    public void update() {
        for (DynamicSprite sprite : movingSpriteList) {
            sprite.moveIfPossible(environment,movingSpriteList);
        }
    }
}
