import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    final static String[] varPrimitiveTypes = { "String", "int", "double", "boolean", "char", "long", "float", "byte", "short" };
    final static   String[] comparisonOperators = { "==", "!=", ">", ">=", "<", "<=" };
    
    // Join the comparison operators into a regex group
    final static String comparisonOperatorsRegex = "(" + String.join("|", comparisonOperators) + ")";
    final static String primitiveTypes = "(" + String.join("|", varPrimitiveTypes) + ")";
    final static String variable_NameRegex = "\\b[a-zA-Z_][a-zA-Z0-9_]*\\b";

    public static void main(String[] args) {
        System.out.println("=====================================Testing comments=====================================");
        Path path = Paths.get("src", "commentTest.txt");

        StringBuilder content = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(path.toString()))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");  // Append each line with a newline
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        analyze_comments(content.toString());


        System.out.println("=====================================Testing function headers and identifiers=====================================");
        // test function headers with the file functionTest.txt
        Path path2 = Paths.get("src", "functionTest.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(path2.toString()))){

            String line;
            int lineNumber = 1;
            while ((line = br.readLine()) != null) {
                isFunctionHeader(line, lineNumber);

                lineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("=====================================Testing identifiers=====================================");
        Path path3 = Paths.get("src", "identifiersTest.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(path3.toString()))){
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

    public static void analyze_comments(String content) {
        // detect single line comments with regex
        String singleLineComment = "//.*";
        Pattern patternSingle = Pattern.compile(singleLineComment);
        Matcher matcherSingle = patternSingle.matcher(content);

        while (matcherSingle.find()) {
            System.out.println("Single-line comment found: " + matcherSingle.group());
        }

        // detect multi-line comments with regex, using DOTALL flag
        String multiLineComment = "/\\*.*?\\*/";
        Pattern patternMulti = Pattern.compile(multiLineComment, Pattern.DOTALL);
        Matcher matcherMulti = patternMulti.matcher(content);

        while (matcherMulti.find()) {
            System.out.println("Multi-line comment found: " + matcherMulti.group());
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

    public static void checkIdentifiers(String line, int lineNumber) {
        List<String> identifiers = new ArrayList<>(Arrays.asList(
                "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class",
                "const", "continue", "default", "do", "double", "else", "enum", "extends", "final",
                "finally", "float", "for", "goto", "if", "implements", "import", "instanceof", "int",
                "interface", "long", "native", "new", "null", "package", "private", "protected", "public",
                "return", "short", "static", "strictfp", "super", "switch", "synchronized", "this",
                "throw", "throws", "transient", "try", "void", "volatile", "while"));
        // Ignoring the comments lines
        if (line.trim().startsWith("//") || line.trim().startsWith("/*") || line.trim().endsWith("*/")) {
            return;
        }
        // Flag: use it for the the no built message
        boolean foundIdentifier = false;

        for (String word : line.split(" ")) {
            if (identifiers.contains(word)) {
                if (line.matches(".*\\b" + word + "\\b.*")) {
                    System.out
                            .println("Line no: " + lineNumber + " --> " + word + ": is a built-in language construct");
                    foundIdentifier = true;
                }
            }
        }
        if (!foundIdentifier) {
            System.out.println("Line no: " + lineNumber + " --> : no built-in language construct found");
        }
    }
    public static void isFunctionHeader(String line, int lineNumber) {
        // Define 3 arrays of all types
        String[] dataTypes = {"Byte", "Short", "Integer", "Long", "Float", "Double", "Boolean", "Character", "String",
        "ArrayList", "HashSet", "Set", "LinkedList", "TreeSet", "TreeMap", "Stack", "Queue","int", "double", "boolean", 
        "char", "long", "float", "byte", "short"};

        // Modifiers Regex
        String modifiersRegex = "^((public|private|protected|\\s)*\\s*)(static\\s+)?";

        // ReturnT types Regex
        String returnTypeRegex = "(" + String.join("|", dataTypes) + "|void|" + String.join("|", dataTypes) + ")(\\[\\]|<(" + String.join("|", dataTypes) + ")>)?\\s+";

        // Function name & variables Regex
        String function_variable_NameRegex = "\\b[a-zA-Z_][a-zA-Z0-9_]*\\b";

        // Parameters Regex
        String parameters = "\\s*\\(\\s*((" + String.join("|", dataTypes) + "|" + String.join("|", dataTypes)
                + ")(\\[\\]|<" + String.join("|", dataTypes) + ">)?+\\s" + function_variable_NameRegex + "\\s*(,\\s*("
                + String.join("|", dataTypes) + "|" + String.join("|", dataTypes)
                + ")(\\[\\]|<" + String.join("|", dataTypes) + ">)?+\\s" + function_variable_NameRegex + "\\s*)*)?\\)\\s*$";

        // Merged Regex
        String regex = modifiersRegex + returnTypeRegex + function_variable_NameRegex + parameters;

        if (line.matches(regex)) {
            System.out.println("Line no: " + lineNumber + " --> " + line.trim() + " is a method declaration");
        } else {
            System.out.println(
                    "Line no: " + lineNumber + " --> " + line.trim() + " is not a method declaration");
        }
    }


}