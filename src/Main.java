import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Main {
    private final static String imagePath = "./img/";
    private final static String dataPath = "./data/";
    private static int levelNumber = 1;

    private static JFrame displayZoneFrame;
    private static GameEngine gameEngine;
    private static RenderEngine renderEngine;
    private static PhysicsEngine physicsEngine;

    public Main() {
        displayZoneFrame = new JFrame();
        displayZoneFrame.setTitle("Dungeon");
        displayZoneFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        loadNextLevel();

        displayZoneFrame.setVisible(true);
        displayZoneFrame.setSize(400, 600);
    }

    public static void increaseLevel() {
        levelNumber++;
    }

    public static int getLevelNumber() {
        return levelNumber;
    }

    public static String getImagePath() {
        return imagePath;
    }

    public static void main(String[] args) {
        new Main();
    }

    public static void loadNextLevel() {
        try {
            Playground newLevel = new Playground(dataPath + "level" + levelNumber + ".txt");
            DynamicSprite hero = new DynamicSprite(200, 300, 48, 50, ImageIO.read(new File(imagePath + "heroTileSheetLowRes.png")));

            renderEngine = new RenderEngine();
            physicsEngine = new PhysicsEngine();
            gameEngine = new GameEngine(hero);

            Timer renderTimer = new Timer(50, (time) -> renderEngine.update());
            Timer gameTimer = new Timer(50, (time) -> gameEngine.update());
            Timer physicTimer = new Timer(50, (time) -> physicsEngine.update());

            renderTimer.start();
            gameTimer.start();
            physicTimer.start();

            physicsEngine.setEnvironment(newLevel.getSolidSpriteList());
            renderEngine.addToRenderList(newLevel.getSpriteList());
            renderEngine.addToRenderList(hero);
            physicsEngine.addToMovingSpriteList(hero);

            displayZoneFrame.getContentPane().removeAll();
            displayZoneFrame.getContentPane().add(renderEngine);
            displayZoneFrame.addKeyListener(gameEngine);
            displayZoneFrame.revalidate();
            displayZoneFrame.repaint();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}