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

        for (String identifier : identifiers) {
            if (line.matches(".*\\b" + identifier + "\\b.*")) {
                System.out.println("Line no: " + lineNumber + " --> " + identifier + " is a built-in language construct");
                break;
            }
        }
                /* String pattern = "\\b(?:" + String.join("|", identifiers) + ")\\b";
        Pattern r = Pattern.compile(pattern);

        Matcher m = r.matcher(line);
        while (m.find()) {
            System.out.println("Line no: " + lineNumber + " --> " + m.group() + " is a built-in language construct");
        }*/

    }

}