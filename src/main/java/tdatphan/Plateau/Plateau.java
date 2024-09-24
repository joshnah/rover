package tdatphan.Plateau;

import tdatphan.Robot.Robot;

public class Plateau {
    private int width;
    private int height;

    public Plateau(int maxX, int maxY) {
        this.width = maxX+1;
        this.height = maxY +1;
    }

    public String getPlateauWithRobot(Robot robot) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                if (robot.getCurrentX() == j && this.getYOnMap(robot.getCurrentY()) == i) {
                    str.append(robot.getcurrentOrientation().getIcon());
                } else {
                    str.append('.');
                }
                str.append(' ');
            }
            str.append('\n');
        }
        return str.toString();
    }

    public boolean isInvalidPosition(int x, int y) {
        y = this.getYOnMap(y);
        return x < 0 || x >= this.width || y < 0 || y >= this.height;
    }

    /*
     * (0,0) is the bottom left corner of the map, so we need to invert the y on the
     * map
     */
    private int getYOnMap(int y) {
        return this.height - y - 1;
    }
}
