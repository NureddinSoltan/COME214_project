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
        String[] varPrimitiveTypes = {"String", "int", "double", "boolean", "char", "long", "float", "byte", "short"};
        String[] comparisonOperators = {"==", "!=", ">", ">=", "<", "<="};
        String[] ogicalOperators = {"&&", "||", "!"};
        // Extracting the function header (method signature) without the body
        int bodyIndex = line.indexOf('{');
        String header = bodyIndex != -1 ? line.substring(0, bodyIndex).trim() : line.trim();
        
        String forLoopPattern = "^\\s*for\\s*\\(\\s*(" + String.join("|", varPrimitiveTypes)+"\\s+)\\w+\\s*=\\s*\\w+\\s*;\\s*\\w+\\s*[<>]=?\\s*\\w+\\s*;\\s*\\w+\\s*=\\s*\\w+(?:\\s*\\+\\s*\\d+)?\\s*\\)\\s*(?:\\{)?\\s*$";
    }
}