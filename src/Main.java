import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Main {
    public final static String imagePath = "./img/";
    public final static String dataPath = "./data/";

    //I suppose that the triangle means by default
    JFrame displayZoneFrame;
    GameEngine gameEngine;
    RenderEngine renderEngine;
    PhysicsEngine physicsEngine;

    public Main() throws Exception {
        Playground level1 = new Playground(dataPath+"level1.txt");
        DynamicSprite hero = new DynamicSprite(200,300,48,50,ImageIO.read(new File(imagePath+"heroTileSheetLowRes.png")));
        renderEngine = new RenderEngine();
        physicsEngine = new PhysicsEngine();
        gameEngine = new GameEngine(hero);

        displayZoneFrame = new JFrame();
        displayZoneFrame.setTitle("Dungeon");
        displayZoneFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Timer renderTimer = new Timer(50,(time)-> renderEngine.update());
        Timer gameTimer = new Timer(50,(time)-> gameEngine.update());
        Timer physicTimer = new Timer(50,(time)-> physicsEngine.update());

        renderTimer.start();
        gameTimer.start();
        physicTimer.start();

        displayZoneFrame.getContentPane().add(renderEngine);
        displayZoneFrame.setVisible(true);
        displayZoneFrame.setSize(400,600);
//        displayZoneFrame.setResizable(false);
//        displayZoneFrame.setLocationRelativeTo(null);
        renderEngine.addToRenderList(level1.getSpriteList());
        renderEngine.addToRenderList(hero);
        physicsEngine.addToMovingSpriteList(hero);
        physicsEngine.setEnvironment(level1.getSolidSpriteList());

        displayZoneFrame.addKeyListener(gameEngine);



    }

    public static void main(String[] args) {
        try {
            Main main = new Main();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
