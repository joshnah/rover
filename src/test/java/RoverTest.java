import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import tdatphan.Rover;
import tdatphan.exeception.OutOfMapException;
import tdatphan.exeception.UnknownCommandException;

public class RoverTest {

    @Test
    public void testWithErrorOfPlateau() throws Exception {
        String inputFilePath = "src/test/resources/inputFiles/testOutOfMap.txt";
        assertThrows(OutOfMapException.class, () -> {
            runTest(inputFilePath, "");
        });
    }

    @Test
    public void testWithInvalidCommand() throws Exception {
        String inputFilePath = "src/test/resources/inputFiles/testInvalidCommand.txt";
        assertThrows(UnknownCommandException.class, () -> {
            runTest(inputFilePath, "");
        });
    }

    @Test
    public void testWithInputFiles() throws Exception {
        // Test cases with expected output
        Map<String, String> testCases = new HashMap<>();
        testCases.put("src/test/resources/inputFiles/testBasic5x5.txt", "1 3 N\n5 1 E\n");
        testCases.put("src/test/resources/inputFiles/testBoundary.txt", "5 5 N\n5 5 E\n");
        testCases.put("src/test/resources/inputFiles/testComplexMovements.txt", "1 2 W\n");

        for (Map.Entry<String, String> entry : testCases.entrySet()) {
            runTest(entry.getKey(), entry.getValue());
        }
    }

    @Test
    public void testWithLargeInputFile() throws Exception {
        String inputFilePath = "src/test/resources/inputFiles/largeMoves.txt";
        String expectedOutput = "50 50 N\n";

        GenerateLargeTestInput.main(null);

        runTest(inputFilePath, expectedOutput);
        
        Path path = Paths.get(inputFilePath);
        Files.delete(path);
    }

    private void runTest(String inputFilePath, String expectedOutput) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        String[] args = { inputFilePath };
        Rover.main(args);
        System.setOut(originalOut);

        String actualOutput = outputStream.toString();
        assertEquals(expectedOutput, actualOutput);
    }

}