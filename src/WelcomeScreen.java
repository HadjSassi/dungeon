import javax.swing.*;
import java.awt.*;

public class WelcomeScreen extends JPanel {

    public WelcomeScreen(JFrame frame) {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Welcome to Dungeon!", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10));

        JButton easyButton = new JButton("Easy");
        JButton mediumButton = new JButton("Medium");
        JButton hardButton = new JButton("Hard");

        easyButton.addActionListener(e -> {
            Main.setDifficulty(Difficulty.EASY);
            RenderEngine.setRemainingTime(60);
            startGame(frame);
        });

        mediumButton.addActionListener(e -> {
            Main.setDifficulty(Difficulty.MEDIUM);
            RenderEngine.setRemainingTime(50);
            startGame(frame);
        });

        hardButton.addActionListener(e -> {
            Main.setDifficulty(Difficulty.HARD);
            RenderEngine.setRemainingTime(40);
            startGame(frame);
        });

        buttonPanel.add(easyButton);
        buttonPanel.add(mediumButton);
        buttonPanel.add(hardButton);
        add(buttonPanel, BorderLayout.CENTER);
    }

    private void startGame(JFrame frame) {
        Main.setOldHeroHealth(100);
        Main.setScore(0);
        Main.loadNextLevel();
    }
}
