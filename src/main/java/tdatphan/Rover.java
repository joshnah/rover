package tdatphan;
import java.io.FileReader;
import java.util.Scanner;

import tdatphan.Plateau.Plateau;
import tdatphan.Robot.Robot;


/* This is the main Rover controller class which will parses input file, instantiates objects and run the simulation  */
public class Rover {

    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("Usage: java rover.jar <input file> [-d] \n -d: enable debug mode");
            return;
        }
        boolean logEnabled = false;
        // Check for the -d option
        if (args.length > 1 && args[1].equals("-d")) {
            logEnabled = true;
        }

        String inputFile = args[0];

        Scanner scanner = new Scanner(new FileReader(inputFile));
        Plateau Plateau = new Plateau(scanner.nextInt(), scanner.nextInt());

        while (scanner.hasNext()) {
            parseRobotAndExecute(scanner, Plateau, logEnabled);
        }
        scanner.close();
    }

    public static Plateau parsePlateau(Scanner scanner) {
        int width = scanner.nextInt();
        int height = scanner.nextInt();
        return new Plateau(width, height);
    }

    public static void parseRobotAndExecute(Scanner scanner, Plateau Plateau, boolean logEnabled) {
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        char orientation = scanner.next().charAt(0);
        Robot robot = new Robot(x, y, orientation);

        scanner.nextLine();
        robot.executeCommands(scanner.nextLine(), Plateau, logEnabled);

    }

}
