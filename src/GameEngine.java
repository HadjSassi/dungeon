import javax.sound.sampled.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

public class GameEngine implements KeyListener, Engine {

    private DynamicSprite hero;

    public GameEngine(DynamicSprite hero) {
        this.hero = hero;
    }


    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                hero.setDirection(Direction.NORTH);
                break;
            case KeyEvent.VK_DOWN:
                hero.setDirection(Direction.SOUTH);
                break;
            case KeyEvent.VK_LEFT:
                hero.setDirection(Direction.WEST);
                break;
            case KeyEvent.VK_RIGHT:
                hero.setDirection(Direction.EAST);
                break;
            case KeyEvent.VK_SHIFT:
                hero.speedUp();
                break;
            case KeyEvent.VK_SPACE:
                hero.attack();
                playFightSound();
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            hero.speedDown();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }


    @Override
    public void update() {

    }

    private void playFightSound() {
        try {
            File audioFile = new File("./audio/fight.wav"); // Replace with your file path
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("Error loading or playing fight sound: " + e.getMessage());
        }
    }


}

// normally this class is finished