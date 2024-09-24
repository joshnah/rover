import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GenerateLargeTestInput {
    public static void main(String[] args) {
        String fileName = "src/test/resources/inputFiles/largeMoves.txt";
        int mapWidth = 100;
        int mapHeight = 100;
        int initialX = 50;
        int initialY = 50;
        char initialOrientation = 'N';
        StringBuilder commands = new StringBuilder();

        // go in a circle 1000000 times
        for (int i = 0; i < 1000000; i++) {
            commands.append("LMRMRMRMLL");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(
                    mapWidth + " " + mapHeight + "\n" + initialX + " " + initialY + " " + initialOrientation + "\n");
            writer.write(commands.toString() + "\n");
            System.out.println("Test file largeMoves has been generated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
