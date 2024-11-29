import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static enumerations.StringConstants.*;

public class SoundSystem {

    private static Clip backgroundMusicClip;

    public static void playOuchSound() {
        playSound(AUDIO_PATH.getValue() + OUCH_SOUND.getValue());
    }

    public static void playBonusSound() {
        playSound(AUDIO_PATH.getValue() + BONUS_SOUND.getValue());
    }

    public static void playFightSound() {
        playSound(AUDIO_PATH.getValue() + FIGHT_SOUND   .getValue());
    }

    public static void playDeadSound() {
        playSound(AUDIO_PATH.getValue() + DEAD_SOUND.getValue());
    }

    /*
     * This method plays a background music while the game is on.
     * */
    public static void playBackgroundMusic() {
        try {
            if (backgroundMusicClip != null && backgroundMusicClip.isRunning()) {
                backgroundMusicClip.stop();
            }
            InputStream audioStream = SoundSystem.class.getResourceAsStream(AUDIO_PATH.getValue() + BG_SOUND.getValue());
            if (audioStream == null) {
                throw new IOException();
            }

            byte[] audioBytes = audioStream.readAllBytes();

            InputStream byteArrayInputStream = new ByteArrayInputStream(audioBytes);
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(byteArrayInputStream);

            backgroundMusicClip = AudioSystem.getClip();
            backgroundMusicClip.open(inputStream);

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

    /*
     * This method plays a music given the path of the audio clip
     * */
    private static void playSound(String filePath) {
        try {
            InputStream audioStream = SoundSystem.class.getResourceAsStream(filePath);
            if (audioStream == null) {
                throw new IOException();
            }

            byte[] audioBytes = audioStream.readAllBytes();

            InputStream byteArrayInputStream = new ByteArrayInputStream(audioBytes);
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(byteArrayInputStream);

            Clip clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e.getMessage());
        }
    }
}
