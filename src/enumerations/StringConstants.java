package enumerations;

public enum StringConstants {
    GAME_NAME("Dungeon"),
    GREETINGS("Welcome to Dungeon!"),
    EASY("Easy"),
    MEDIUM("Medium"),
    HARD("Hard"),
    FIGHT_SOUND("fight.wav"),
    OUCH_SOUND("ouch.wav"),
    BONUS_SOUND("bonus.wav"),
    DEAD_SOUND("dead.wav"),
    BG_SOUND("background_music.wav"),
    IMAGE_PATH("./img/"),
    DATA_PATH("./data/"),
    AUDIO_PATH("./audio/"),
    LEVEL_NAME("level"),
    LEVEL_FILE_EXTENSION(".txt"),
    HERO_IMAGE_FILE_NAME("heroTileSheetLowRes.png"),
    MONSTER_IMAGE_FILE_NAME("monsterTileSheetLowRes.png"),
    HERO_NAME("Hero"),
    MONSTER_NAME("Monster"),
    TREE_NAME("Tree"),
    GRASS_NAME("Grass"),
    ROCK_NAME("Rock"),
    TRAP_NAME("Trap"),
    DOOR_NAME("Door"),
    GATE_NAME("Gate"),
    HEART_NAME("Heart"),
    TREE_INITIAL("T"),
    GRASS_INITIAL(" "),
    ROCK_INITIAL("R"),
    TRAP_INITIAL("H"),
    DOOR_INITIAL("."),
    PASSING_DOOR_INITIAL("E"),
    GATE_INITIAL("G"),
    CONGRATS_MSG("Congratulation, you escpaed the maze! with a score "),
    CONGRATS_TITLE("Congratulation !"),
    LOST_MSG("Ouch!, you Lost unfortunately!"),
    LOST_TITLE("The End!"),
    EXIT("Exit"),
    RETRY("Retry"),
    SCORE("Score: "),
    FPS("FPS: "),
    TIME("Time: "),
    GRASS_IMAGE("grass.png"),
    ROCK_IMAGE("rock.png"),
    TRAP_IMAGE("trap.png"),
    TREE_IMAGE("tree.png"),
    DOOR_IMAGE("door.png"),
    GATE_IMAGE("gate.png"),
    HEART_IMAGE("heart.png"),
    LEVEL_HEADER("+[{"),
    POLICE_NAME("Arial"),
    LEVEL_HEADER_PATTERN("\\{(\\d+),(\\d+)\\}");

    private final String value;
    StringConstants(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }

}
