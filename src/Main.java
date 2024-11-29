import enumerations.Difficulty;
import enumerations.Direction;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;
import java.util.Objects;

import static enumerations.StringConstants.*;
import static enumerations.NumericalConstants.*;

public class Main extends JPanel {
    private static int levelNumber = (int) INITIAL_LEVEL.getNumericalValue();
    private static int score = (int) INITIAL_VALUE.getNumericalValue();
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

    private static double oldHeroHealth = (int) TOTAL_PERCENTAGE.getNumericalValue();

    public Main() {
        if (displayZoneFrame == null) {
            displayZoneFrame = new JFrame();
            displayZoneFrame.setTitle(GAME_NAME.getValue());
            displayZoneFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            displayZoneFrame.setSize((int) WIDTH_SCREEN.getNumericalValue(), (int) HEIGHT_SCREEN.getNumericalValue());
            displayZoneFrame.setResizable(false);
            try {
                ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(IMAGE_PATH.getValue() + GAME_ICON.getValue())));
                displayZoneFrame.setIconImage(icon.getImage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            displayZoneFrame.setVisible(true);
        }
        displayZoneFrame.getContentPane().removeAll();
        displayZoneFrame.getContentPane().add(new WelcomeScreen());
        displayZoneFrame.revalidate();
        displayZoneFrame.repaint();
        SoundSystem.playBackgroundMusic();
    }

    public static void setOldHeroHealth(double oldHeroHealth) {
        Main.oldHeroHealth = oldHeroHealth;
    }

    public static void increaseLevel() {
        levelNumber++;
    }

    public static Difficulty getDifficulty() {
        return Main.difficulty;
    }

    public static void setDifficulty(Difficulty difficulty) {
        Main.difficulty = difficulty;
    }

    public static int getLevelNumber() {
        return levelNumber;
    }

    /*
     * This method initializes all the timers and creates the necessary objects to start the game in the desired level from the
     * level in the data folder
     * */
    public static void loadNextLevel() {
        try {
            Playground newLevel = new Playground(DATA_PATH.getValue() + LEVEL_NAME.getValue() + levelNumber + LEVEL_FILE_EXTENSION.getValue());
            DynamicSprite hero = new DynamicSprite(HERO_POSX.getNumericalValue(), HERO_POSY.getNumericalValue(),
                    HERO_WIDTH.getNumericalValue(), HERO_HEIGHT.getNumericalValue(),
                    ImageIO.read(Objects.requireNonNull(Main.class.getResourceAsStream(IMAGE_PATH.getValue() + HERO_IMAGE_FILE_NAME.getValue()))),
                    HERO_NAME.getValue(), oldHeroHealth, true);
            DynamicSprite monster = new DynamicSprite(MONSTER_POSX.getNumericalValue(), MONSTER_POSY.getNumericalValue(),
                    MONSTER_WIDTH.getNumericalValue(), MONSTER_HEIGHT.getNumericalValue(),
                    ImageIO.read(Objects.requireNonNull(Main.class.getResourceAsStream(IMAGE_PATH.getValue() + MONSTER_IMAGE_FILE_NAME.getValue()))),
                    MONSTER_NAME.getValue(),
                    false, levelNumber, (int) SPRITE_SHEET_NUMBER_OF_COLUMN.getNumericalValue(),
                    Direction.SOUTH, false, difficulty.getDifficultyValue());

            renderEngine = new RenderEngine();
            physicsEngine = new PhysicsEngine();
            gameEngine = new GameEngine(hero);
            aiEngine = new AiEngine(monster, hero);


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

            renderTimer = new Timer((int) DELAY_TIMER.getNumericalValue(), (time) -> renderEngine.update());
            gameTimer = new Timer((int) DELAY_TIMER.getNumericalValue(), (time) -> gameEngine.update());
            aiTimer = new Timer((int) DELAY_TIMER.getNumericalValue(), (time) -> aiEngine.update());
            physicTimer = new Timer((int) DELAY_TIMER.getNumericalValue(), (time) -> physicsEngine.update());
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

    /*
     * This method stops all the timers ans show a popup of wining the game with the score,
     * with the capability to exit or to retry the game
     * */
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
        int choice = JOptionPane.showOptionDialog(null, CONGRATS_MSG.getValue() + score,
                CONGRATS_TITLE.getValue(), JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null,
                new Object[]{EXIT.getValue(), RETRY.getValue()}, RETRY.getValue());

        if (choice == JOptionPane.YES_OPTION) {
            exit();
        } else {
            reset();
        }
    }

    /*
     * This method stops all the timers ans show a popup of losing the game, with the capability to
     * exit or to retry the game
     * */
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
        int choice = JOptionPane.showOptionDialog(null, LOST_MSG.getValue(),
                LOST_TITLE.getValue(), JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null,
                new Object[]{EXIT.getValue(), RETRY.getValue()}, RETRY.getValue());

        if (choice == JOptionPane.YES_OPTION) {
            exit();
        } else {
            reset();
        }
    }

    private static void exit() {
        System.exit((int) INITIAL_VALUE.getNumericalValue());
    }

    /*
     * This method resets the level to 1 and the hero health to 100 and relaunch the game with the same
     * difficulty
     * */
    private static void reset() {
        levelNumber = (int) INITIAL_LEVEL.getNumericalValue();
        oldHeroHealth = TOTAL_PERCENTAGE.getNumericalValue();
        RenderEngine.resetTime();
        loadNextLevel();
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