public enum Direction {
    EAST(3),
    NORTH(2),
    SOUTH(0),
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