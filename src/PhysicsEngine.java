import java.util.ArrayList;

//todo finish the PhysicsEngine Class' methods
public class PhysicsEngine implements Engine{

    private ArrayList<Sprite> environment;
    private ArrayList<DynamicSprite> movingSpriteList;

    public PhysicsEngine() {
        this.environment = new ArrayList<>();
        this.movingSpriteList = new ArrayList<>();
    }

    public void addToEnvironmentList(Sprite sprite){
        this.environment.add(sprite);
    }

    public void addToMovingSpriteList(DynamicSprite sprite){
        this.movingSpriteList.add(sprite);
    }

    public void setEnvironment(ArrayList<Sprite> environment){
        this.environment = environment;
    }


    @Override
    public void update() {

    }
}
