/*
* This enumeration is used to stock all the numerical values in a constants
* So we can change this class just to play with the values of the game.
* The values are in double, since it can be casted to all the other numerical values!
* */
package enumerations;

public enum NumericalConstants {

    DISTANCE_MONSTER_TO_ATTACK(4),
    WIDTH_SCREEN(400),
    HEIGHT_SCREEN(600),
    SCORE_POSX(0),
    SCORE_POSY(30),
    FPS_POSX(10),
    FPS_POSY(20),
    TIME_POSX(140),
    TIME_POSY(20),
    HERO_POSX(60),
    HERO_POSY(300),
    HERO_WIDTH(48),
    HERO_HEIGHT(50),
    MONSTER_POSX(60),
    MONSTER_POSY(450),
    MONSTER_WIDTH(48),
    MONSTER_HEIGHT(50),
    SPRITE_SHEET_NUMBER_OF_COLUMN(10),
    TIME_BETWEEN_FRAME(100),
    DELAY_TIMER(50),
    INITIAL_LEVEL(1),
    TOTAL_PERCENTAGE(100.0),
    RANDOM_DECISION_VALUE(0.5),
    BAR_HEIGHT(5),
    SPEED_COEFFICIENT(1.5),
    INITIAL_ATTACK_RADIUS(10),
    BONUS_VALUE(30),
    ATTACK_OPACITY(1.0),
    DAMAGE(1.0),
    MAXIMUM_ATTACK_RADIUS(1000),
    ATTACK_RADIUS_STEP(10),
    ATTACK_FADE_STEP(0.1),
    MAXIMUM_SPEED(500),
    MAXIMUM_HEALTH(100),
    MINIMUM_HEALTH(0),
    INITIAL_VALUE(0),
    ONE_MINUTE(60),
    FIFTY_SECOND(50),
    FOURTY_SECOND(40),
    ONE_SECOND(1000),
    POLICE_SIZE(20),
    POLICE_SIZE_0(24),
    ROWS(3),
    COLS(1),
    HGAP(10),
    VGAP(10),
    INITIAL_DIRECTION(0);

    private final double numericalValue;

    NumericalConstants(double numericalValue) {
        this.numericalValue = numericalValue;
    }

    public double getNumericalValue() {
        return numericalValue;
    }
}
