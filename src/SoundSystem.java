import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

import static enumerations.StringConstants.*;

public class SoundSystem {

    private static Clip backgroundMusicClip;

    public static void playOuchSound() {
        playSound(AUDIO_PATH.getValue()+OUCH_SOUND.getValue());
    }

    public static void playBonusSound() {
        playSound(AUDIO_PATH.getValue()+BONUS_SOUND.getValue());
    }

    public static void playDeadSound() {
        playSound(AUDIO_PATH.getValue()+DEAD_SOUND.getValue());
    }

    public static void playBackgroundMusic() {
        try {
            if (backgroundMusicClip != null && backgroundMusicClip.isRunning()) {
                backgroundMusicClip.stop();
            }
            File audioFile = new File(AUDIO_PATH.getValue()+BG_SOUND.getValue());
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            backgroundMusicClip = AudioSystem.getClip();
            backgroundMusicClip.open(audioStream);

            backgroundMusicClip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    backgroundMusicClip.setFramePosition(0);
                    backgroundMusicClip.start();
                }
            });

            backgroundMusicClip.loop(Clip.LOOP_CONTINUOUSLY);
            backgroundMusicClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void playSound(String filePath) {
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e.getMessage());
        }
    }
}
