import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        List<String> identifiers = new ArrayList<> (Arrays.asList(
            "if", "for", "while", "switch", "case", "break", "continue",
                "return", "try", "catch", "finally", "throw", "throws",
                "class", "interface", "enum", "public", "private",
                "protected", "static", "final", "void", "int", "long",
                "float", "double", "boolean", "char", "String"
        ));

        boolean foundIdentifier = false;

        for (String word : line.split(" ")) {
            if (identifiers.contains(word)){
                if (line.matches(".*\\b" + word + "\\b.*")) {
                    System.out.println("Line no: " + lineNumber + " --> " + word + ": is a built-in language construct");
                    foundIdentifier = true;
                } 
            }
            // else {
            //     System.out.println("Line no: " + lineNumber + " --> : no built-in language construct found");
            // } 
        }
        
        if (!foundIdentifier) {
            System.out.println("Line no: " + lineNumber + " --> : no built-in language construct found");
        }

        if (line.trim().startsWith("//") || line.trim().startsWith("/*") || line.trim().endsWith("*/")) {
            return;
        }  
    }

}