import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String fileName = "src/code.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineNumber = 1;
            while ((line = br.readLine()) != null) {
                // Add your function
                //yourFunction(line, lineNumber);
                isFunctionHeader(line, lineNumber);
                lineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    public static void isFunctionHeader(String line, int lineNumber) {
        // Define an array of all types
        String[] types = {"void", "String", "int", "double", "boolean", "char", "long", "float", "byte", "short"};
        String[] primitiveTypes = {"String", "int", "double", "boolean", "char", "long", "float", "byte", "short"};
    
        // Extracting the function header (method signature) without the body
        int bodyIndex = line.indexOf('{');
        String header = bodyIndex != -1 ? line.substring(0, bodyIndex).trim() : line.trim();
        
        // TODO:
        // parameters type should be correct and primitive types
        // Parameters name Duplication
    
        // Regular expression pattern for a function declaration                                                                 //Exactly from here start the () parameters       
        String regex = "^((public|private|protected|\\s)*\\s*)(static\\s+)?(" + String.join("|", types) + ")(\\[\\])?\\s+\\w+\\s*\\(\\s*((" + String.join("|", primitiveTypes) + ")(\\[\\])?+\\s+\\w+\\s*(,\\s*(" + String.join("|", primitiveTypes) + ")(\\[\\])?+\\s+\\w+\\s*)*)?\\)\\s*$";
    
        // Check if the function header matches any of the patterns
        if (header.matches(regex)) {
            System.out.println("Line no: " + lineNumber + " --> " + " is a valid function header");
        } else {
            System.out.println("Line no: " + lineNumber + " --> " + " is not a valid function header");
        }
    }

}
