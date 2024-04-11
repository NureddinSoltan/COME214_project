import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String fileName = "src/code.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineNumber = 1;
            while ((line = br.readLine()) != null) {
                // Add your function
                //yourFunction(line, lineNumber);
                lineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void identifyLineLoops(String line, int lineNumber) {
        System.out.println("Line no: " + lineNumber + " -->" + line);

        String whileLoopPattern = "^\\s*while\\s*\\(.\\)\\s\\{.*$";
        String forLoopPattern = "^\\s*for\\s*\\(.\\)\\s\\{.*$";

        // // Compile regex patterns
        // Pattern whilePattern = Pattern.compile(whileLoopPattern);
        // Pattern forPattern = Pattern.compile(forLoopPattern);

        // // Match patterns
        // Matcher whileMatcher = whilePattern.matcher(line);
        // Matcher forMatcher = forPattern.matcher(line);

        // // Check if the line belongs to a while loop
        // if (whileMatcher.matches()) {
        //     System.out.println("Line no: " + lineNumber + " --> This line belongs to a while loop.");
        // }
        // // Check if the line belongs to a for loop
        // if (forMatcher.matches()) {
        //     System.out.println("Line no: " + lineNumber + " --> This line belongs to a for loop.");
        // }
    }
}