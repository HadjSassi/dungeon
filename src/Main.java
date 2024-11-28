import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Main extends JPanel{
    private final static String imagePath = "./img/";
    private final static String dataPath = "./data/";
    private static int levelNumber = 1;
    private static int score = 0;
    private static Difficulty difficulty = Difficulty.EASY;
    private static JFrame displayZoneFrame;

    private static AiEngine aiEngine;
    private static GameEngine gameEngine;
    private static RenderEngine renderEngine;
    private static PhysicsEngine physicsEngine;

    private static Timer renderTimer;
    private static Timer gameTimer;
    private static Timer aiTimer;
    private static Timer physicTimer;

    private static double oldHeroHealth = 100;
    private static double oldMonsterHealth = 100;

    public Main() {
        if (displayZoneFrame == null) {
            displayZoneFrame = new JFrame();
            displayZoneFrame.setTitle("Dungeon");
            displayZoneFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            displayZoneFrame.setSize(400, 600);
            displayZoneFrame.setVisible(true);
        }
        displayZoneFrame.getContentPane().removeAll();
        displayZoneFrame.getContentPane().add(new WelcomeScreen(displayZoneFrame));
        displayZoneFrame.revalidate();
        displayZoneFrame.repaint();
    }

    public static RenderEngine getRenderEngine() {
        return renderEngine;
    }

    public static void setOldHeroHealth(double oldHeroHealth) {
        Main.oldHeroHealth = oldHeroHealth;
    }

    public static void increaseLevel() {
        levelNumber++;
    }

    public static Difficulty getDifficulty(){
        return Main.difficulty;
    }

    public static void setDifficulty(Difficulty difficulty){
        Main.difficulty = difficulty;
    }

    public static int getLevelNumber() {
        return levelNumber;
    }

    public static String getImagePath() {
        return imagePath;
    }

    public static void loadNextLevel() {
        try {
            Playground newLevel = new Playground(dataPath + "level" + levelNumber + ".txt");
            DynamicSprite hero = new DynamicSprite(60, 300, 48, 50, ImageIO.read(new File(imagePath + "heroTileSheetLowRes.png")), "Hero", oldHeroHealth, true);
            DynamicSprite monster = new DynamicSprite(60, 450, 48, 50, ImageIO.read(new File(imagePath + "monsterTileSheetLowRes.png")), "Monster",
                    false, levelNumber, 10, 100, Direction.SOUTH, false, 10*difficulty.getDifficultyValue());

            renderEngine = new RenderEngine();
            physicsEngine = new PhysicsEngine();
            gameEngine = new GameEngine(hero);
            aiEngine = new AiEngine(monster, hero);

            // Stop existing timers, if any
            if (renderTimer != null) {
                renderTimer.stop();
            }
            if (gameTimer != null) {
                gameTimer.stop();
            }
            if (aiTimer != null) {
                aiTimer.stop();
            }
            if (physicTimer != null) {
                physicTimer.stop();
            }

            renderTimer = new Timer(50, (time) -> renderEngine.update());
            gameTimer = new Timer(50, (time) -> gameEngine.update());
            aiTimer = new Timer(50, (time) -> aiEngine.update());
            physicTimer = new Timer(50, (time) -> physicsEngine.update());
            renderTimer.start();
            gameTimer.start();
            aiTimer.start();
            physicTimer.start();

            physicsEngine.setEnvironment(newLevel.getSolidSpriteList());
            renderEngine.addToRenderList(newLevel.getSpriteList());
            renderEngine.addToRenderList(hero);
            renderEngine.addToRenderList(monster);
            physicsEngine.addToMovingSpriteList(hero);
            physicsEngine.addToMovingSpriteList(monster);

            displayZoneFrame.getContentPane().removeAll();
            displayZoneFrame.getContentPane().add(renderEngine);
            displayZoneFrame.addKeyListener(gameEngine);
            displayZoneFrame.setFocusable(true);
            displayZoneFrame.requestFocus();
            displayZoneFrame.revalidate();
            displayZoneFrame.repaint();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void gagner() {
        if (gameTimer != null) {
            gameTimer.stop();
        }
        if (physicTimer != null) {
            physicTimer.stop();
        }
        if (renderTimer != null) {
            renderTimer.stop();
        }
        int choice = JOptionPane.showOptionDialog(null, "Congratulation, you escpaed the maze! with a score "+score+" !",
                "Congratulation", JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null,
                new Object[]{"Exit", "Retry"}, "Retry");

        if (choice == JOptionPane.YES_OPTION) {
            System.exit(0);
        } else {
            levelNumber = 1;
            loadNextLevel();
        }
    }

    public static void perdre() {
        if (gameTimer != null) {
            gameTimer.stop();
        }
        if (physicTimer != null) {
            physicTimer.stop();
        }
        if (renderTimer != null) {
            renderTimer.stop();
        }
        int choice = JOptionPane.showOptionDialog(null, "Ouch!, you Lost unfortunately!",
                "The End!", JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null,
                new Object[]{"Exit", "Retry"}, "Retry");

        if (choice == JOptionPane.YES_OPTION) {
            System.exit(0);
        } else {
            levelNumber = 1;
            oldHeroHealth = 100;
            loadNextLevel();
        }
    }

    public static int getScore() {
        return score;
    }

    public static void setScore(int newScore) {
        score = newScore;
    }

    public static void main(String[] args) {
        new Main();
    }

    public static void increaseScore() {
        score++;
    }
}