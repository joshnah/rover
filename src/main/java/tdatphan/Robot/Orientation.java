package tdatphan.Robot;

import tdatphan.exeception.UnknownOrientationExeception;

public enum Orientation {
    NORTH('N'),
    WEST('W'),
    SOUTH('S'),
    EAST('E');

    private final char orientationChar;

    Orientation(char orientationChar) {
        this.orientationChar = orientationChar;
    }

    public String getIcon() {
        return switch (this) {
            case NORTH -> "^";
            case WEST  -> "<";
            case SOUTH -> "v";
            case EAST  -> ">";
            default -> throw new UnknownOrientationExeception();
        };
    }

    public static Orientation fromChar(char orientationChar) {
        return switch (orientationChar){
            case 'N' -> NORTH;
            case 'W' -> WEST;
            case 'S' -> SOUTH;
            case 'E' -> EAST;
            default -> throw new UnknownOrientationExeception(orientationChar);
        };
    }

    public char getChar() {
        return orientationChar;
    }
}
