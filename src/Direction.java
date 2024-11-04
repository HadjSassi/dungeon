public enum Direction {
    EAST(2),
    NORTH(0),
    SOUTH(3),
    WEST(1);

    private final int frameLineNumber;

    Direction(int frameLineNumber) {
        this.frameLineNumber = frameLineNumber;
    }

    public int getFrameLineNumber() {
        return frameLineNumber;
    }

}

//this Direction Enumeration is done