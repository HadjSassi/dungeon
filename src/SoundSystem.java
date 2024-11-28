import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundSystem {

    private static Clip backgroundMusicClip;

    public static void playOuchSound() {
        playSound("./audio/ouch.wav");
    }

    public static void playDeadSound() {
        playSound("./audio/dead.wav");
    }

    public static void playBackgroundMusic() {
        try {
            if (backgroundMusicClip != null && backgroundMusicClip.isRunning()) {
                backgroundMusicClip.stop();
            }
            File audioFile = new File("./audio/background_music.wav");
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
            System.out.println("Error loading or playing background music: " + e.getMessage());
        }
    }

    public static void stopBackgroundMusic() {
        if (backgroundMusicClip != null && backgroundMusicClip.isRunning()) {
            backgroundMusicClip.stop();
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
            System.out.println("Error loading or playing sound: " + e.getMessage());
        }
    }
}
