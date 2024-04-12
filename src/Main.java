import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        // String fileName = "code.txt";
        Path path = Paths.get("src","code.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(path.toString()))) {
            String line;
            int lineNumber = 1;
            while ((line = br.readLine()) != null) {
                loopType(line, lineNumber);
                lineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loopType(String line, int lineNumber) {
        
    }
}