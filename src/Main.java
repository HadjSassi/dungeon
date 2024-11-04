import javax.swing.*;

//todo finish the main class's methods
public class Main {
    public final static String imagePath = "./img/";
    public final static String dataPath = "./data/";

    //I suppose that the triangle means by default
    JFrame displayZoneFrame;
    GameEngine gameEngine;
    RenderEngine renderEngine;
    PhysicsEngine physicsEngine;

    public Main(){
        Playground level1 = new Playground(dataPath+"level1.txt");
    }

    public static void main(String[] args) {
    }
}
