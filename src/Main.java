import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Main {
    public static void main(String[] args) {
        String fileName = "code.txt";
        // Path path = Paths.get("src","code.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineNumber = 1;
            while ((line = br.readLine()) != null) {
                PrintLoop(line, lineNumber);
                lineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void PrintLoop(String line,int lineNumber){
        String loopType = getLoopType(line);
        if (loopType.equals("while")) {
            System.out.println("Line " + lineNumber + ": Belongs to While Loop");
        } else if (loopType.equals("for")) {
            System.out.println("Line " + lineNumber + ": Belongs to For Loop");
        } else {
            System.out.println("Line " + lineNumber + ": Does not belong to either For or While Loop");
        }
    }


    public static String getLoopType(String line) {
        String whileLoopPattern = "^\\s*while\\s*\\(\\s*\\w+\\s*[<>!=]=?\\s*\\w+\\s*\\)\\s*.*$";
        String forLoopPattern = "^\\s*for\\s*\\(\\s*(?:(?:int|double|String|char|boolean|long|float|short|byte)\\s+)?\\w+\\s*=\\s*\\w+\\s*;\\s*\\w+\\s*[<>]=?\\s*\\w+\\s*;\\s*\\w+\\s*=\\s*\\w+(?:\\s*[+-/]\\s*\\d+)?\\s*\\)\\s*(?:\\{)?\\s*$";
        Pattern forPattern = Pattern.compile(forLoopPattern);
        Matcher forMatcher = forPattern.matcher(line);
        if (forMatcher.matches()) {
            return "for";
        }

        Pattern whilePattern = Pattern.compile(whileLoopPattern);
        Matcher whileMatcher = whilePattern.matcher(line);
        if (whileMatcher.matches()) {
            return "while";
        }
        
        return "none";
    }
}


