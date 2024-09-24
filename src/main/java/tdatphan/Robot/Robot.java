package tdatphan.Robot;

import tdatphan.Rover;
import tdatphan.Plateau.Plateau;
import tdatphan.exeception.OutOfMapException;
import tdatphan.exeception.UnknownCommandException;
import tdatphan.exeception.UnknownOrientationExeception;

public class Robot implements Movable {

    private int currentX;
    private int currentY;
    private Orientation currentOrientation;

    public Robot(int x, int y, char orientationChar) {
        this.currentX = x;
        this.currentY = y;
        this.currentOrientation = Orientation.fromChar(orientationChar);
    }

    public void executeCommands(String commands, Plateau plateau, boolean logEnabled) {
        logInitialPosition(plateau, logEnabled);

        for (int i = 0; i < commands.length(); i++) {
            switch (commands.charAt(i)) {
                case 'M' -> moveForward();
                case 'L' -> turnLeft();
                case 'R' -> turnRight();
                default -> throw new UnknownCommandException(commands.charAt(i));
            }

            checkPositionValidity(plateau);
            logCommandExecution(commands.charAt(i), plateau, logEnabled);
        }
        printFinalPosition();
    }

    public int getCurrentX() {
        return this.currentX;
    }

    public int getCurrentY() {
        return this.currentY;
    }

    public Orientation getcurrentOrientation() {
        return this.currentOrientation;
    }

    @Override
    public void moveForward() {
        switch (currentOrientation) {
            case NORTH -> this.currentY++;
            case WEST -> this.currentX--;
            case SOUTH -> this.currentY--;
            case EAST -> this.currentX++;
            default -> throw new UnknownOrientationExeception();
        }
    }

    @Override
    public void turnLeft() {
        this.currentOrientation = switch (currentOrientation) {
            case NORTH -> Orientation.WEST;
            case WEST -> Orientation.SOUTH;
            case SOUTH -> Orientation.EAST;
            case EAST -> Orientation.NORTH;
            default -> throw new UnknownOrientationExeception();
        };
    }

    @Override
    public void turnRight() {
        this.currentOrientation = switch (currentOrientation) {
            case NORTH -> Orientation.EAST;
            case WEST -> Orientation.NORTH;
            case SOUTH -> Orientation.WEST;
            case EAST -> Orientation.SOUTH;
            default -> throw new UnknownOrientationExeception();
        };
    }

    private void checkPositionValidity(Plateau plateau) {
        if (plateau.isInvalidPosition(this.currentX, this.currentY)) {
            throw new OutOfMapException(this.currentX, this.currentY);
        }
    }

    private void logCommandExecution(char command, Plateau plateau, boolean logEnabled) {
        if (logEnabled) {
            System.out.println("Executing command: " + command + "\n" + plateau.getPlateauWithRobot(this)
                    + "\n" + "-------------------");
        }
    }

    private void logInitialPosition(Plateau plateau, boolean logEnabled) {
        if (logEnabled) {
            System.out.println(
                    "\n\nInitial position: " + this.currentX + " " + this.currentY + " " + this.currentOrientation + "\n"
                            + plateau.getPlateauWithRobot(this) + "-------------------");
        }
    }

    private void printFinalPosition() {
        System.out.println(this.currentX + " " + this.currentY + " " + this.currentOrientation.getChar());
    }

}
