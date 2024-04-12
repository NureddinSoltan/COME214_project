import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    final static String[] varPrimitiveTypes = { "String", "int", "double", "boolean", "char", "long", "float", "byte", "short" };
    final static   String[] comparisonOperators = { "==", "!=", ">", ">=", "<", "<=" };
    
        // Join the comparison operators into a regex group
        final static String comparisonOperatorsRegex = "(" + String.join("|", comparisonOperators) + ")";
        final static String primitiveTypes = "(" + String.join("|", varPrimitiveTypes) + ")";
        final static String variable_NameRegex = "\\b[a-zA-Z_][a-zA-Z0-9_]*\\b";
    public static void main(String[] args) {
        

        // String fileName = "code.txt";
        Path path = Paths.get("src","code.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(path.toString()))) {
            String line;
            int lineNumber = 1;
            while ((line = br.readLine()) != null) {
                foorLoopType(line, lineNumber);
                whileLoopType(line, lineNumber);
                lineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void foorLoopType(String line, int lineNumber) {
        // Modified forLoopPattern to use backreferences to ensure the same variable name is used
        String forLoopPattern = "^\\s*for\\s*\\(\\s*" + primitiveTypes + "\\s+(" + variable_NameRegex + ")\\s*=\\s*\\d+\\s*;\\s*\\2\\s*" + comparisonOperatorsRegex + "\\s*\\d+\\s*;\\s*(\\2\\s*(\\+\\+|--)|\\s*(\\+\\+|--)\\s*\\2|\\2\\s*[\\+\\-\\*\\/]=\\s*\\d+|\\2\\s*=\\s*\\2\\s*[\\+\\-\\*\\/]\\s*\\d+)\\)\\s*.*";

        // Check if the line matches the for loop pattern
        if (line.matches(forLoopPattern)) {
            System.out.println("Line no: " + lineNumber + " --> " + line.trim() + " --> is a valid for loop");
        } else {
            System.out.println("Line no: " + lineNumber + " --> " + line.trim() + " --> is not a valid for loop");
        }
    }
    public static void whileLoopType(String line, int lineNumber) {
        // Modified forLoopPattern to use backreferences to ensure the same variable name is used
        String whileLoopPattern = "^\\s*while\\s*\\(\\s*+(" + variable_NameRegex + ")\\s*" + comparisonOperatorsRegex + "\\s*(\\d|\\w)+\\s*\\)\\s*.*";
        
        // Check if the line matches the for loop pattern
        if (line.matches(whileLoopPattern)) {
            System.out.println("Line no: " + lineNumber + " --> " + line.trim() + " --> is a valid for loop");
        } else {
            System.out.println("Line no: " + lineNumber + " --> " + line.trim() + " --> is not a valid for loop");
        }
    }
}