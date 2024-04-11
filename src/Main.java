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
                checkIdentifiers(line, lineNumber);
                lineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void checkIdentifiers(String line, int lineNumber) {
        
        String[] identifiers = {"if", "for", "while", "switch", "case", "break", "continue",
                "return", "try", "catch", "finally", "throw", "throws",
                "class", "interface", "enum", "public", "private",
                "protected", "static", "final", "void", "int", "long",
                "float", "double", "boolean", "char", "String"};

        if (line.trim().startsWith("//") || line.trim().startsWith("/*") || line.trim().endsWith("*/")) {
            return;
        }

        boolean foundIdentifier = false;

        for (String identifier : identifiers) {
            if (line.matches(".*\\b" + identifier + "\\b.*")) {
                System.out.println("Line no: " + lineNumber + " --> " + identifier + ": is a built-in language construct");
                foundIdentifier = true;
            }
        }
    
        if (!foundIdentifier) {
            System.out.println("Line no: " + lineNumber + " --> : no built-in language construct found");
        }    
    }

}