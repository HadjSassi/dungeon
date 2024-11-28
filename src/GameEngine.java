import enumerations.Direction;
import interfaces.Engine;

import javax.sound.sampled.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import static enumerations.StringConstants.*;

public class GameEngine implements KeyListener, Engine {

    private DynamicSprite hero;

    public GameEngine(DynamicSprite hero) {
        this.hero = hero;
    }

    private void playFightSound() {
        try {
            File audioFile = new File(AUDIO_PATH.getValue() + FIGHT_SOUND.getValue());
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e.getMessage());
        }
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

}
