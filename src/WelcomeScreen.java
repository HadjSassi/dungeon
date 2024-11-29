import enumerations.Difficulty;

import javax.swing.*;
import java.awt.*;

import static enumerations.NumericalConstants.*;
import static enumerations.StringConstants.*;

public class WelcomeScreen extends JPanel {

    public WelcomeScreen() {
        setLayout(new BorderLayout());

        JLabel title = new JLabel(GREETINGS.getValue(), JLabel.CENTER);
        title.setFont(new Font(POLICE_NAME.getValue(), Font.BOLD, (int) POLICE_SIZE_0.getNumericalValue()));
        add(title, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout((int) ROWS.getNumericalValue(),(int) COLS.getNumericalValue(),
                (int) HGAP.getNumericalValue(), (int) VGAP.getNumericalValue()));

        JButton easyButton = new JButton(EASY.getValue());
        JButton mediumButton = new JButton(MEDIUM.getValue());
        JButton hardButton = new JButton(HARD.getValue());

        easyButton.addActionListener(e -> {
            Main.setDifficulty(Difficulty.EASY);
            RenderEngine.setRemainingTime((int) ONE_MINUTE.getNumericalValue());
            startGame();
        });

        mediumButton.addActionListener(e -> {
            Main.setDifficulty(Difficulty.MEDIUM);
            RenderEngine.setRemainingTime((int) FIFTY_SECOND.getNumericalValue());
            startGame();
        });

        hardButton.addActionListener(e -> {
            Main.setDifficulty(Difficulty.HARD);
            RenderEngine.setRemainingTime((int) FOURTY_SECOND.getNumericalValue());
            startGame();
        });

        buttonPanel.add(easyButton);
        buttonPanel.add(mediumButton);
        buttonPanel.add(hardButton);
        add(buttonPanel, BorderLayout.CENTER);
    }

    private void startGame() {
        Main.setOldHeroHealth(TOTAL_PERCENTAGE.getNumericalValue());
        Main.setScore((int) INITIAL_VALUE.getNumericalValue());
        Main.loadNextLevel();
    }
}
