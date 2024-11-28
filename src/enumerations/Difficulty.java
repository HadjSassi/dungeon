package enumerations;

public enum Difficulty {
    EASY(1),
    MEDIUM(5),
    HARD(10);

    private int difficultyValue;

    Difficulty(int difficultyValue) {
        this.difficultyValue = difficultyValue;
    }

    public int getDifficultyValue() {
        return difficultyValue;
    }
}