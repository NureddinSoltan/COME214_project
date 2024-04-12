import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

    private static final Set<String> DATA_TYPES = new HashSet<>(Arrays.asList(
            "byte", "short", "int", "long", "float", "double", "boolean", "char",
            "Byte", "Short", "Integer", "Long", "Float", "Double", "Boolean", "Character", "String"));

    public static void main(String[] args) {
        // String fileName = "src/code.txt"; // this won't detect the 'code.txt' from my
        // end!
        String fileName = "./code.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineNumber = 1;
            while ((line = br.readLine()) != null) {

                analyze_comment(line, lineNumber, br);
                checkIdentifiers(line, lineNumber);
                isFunctionHeader(line, lineNumber);
                lineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // This Function analyze a lines of input and checks if they are comments or not
    // please leave (BufferedReader br & throws IOException):
    // needed for line 16 (line, lineNumber, br) & 35 (br.readLine)
    // removing'em won't make it work as expected, or won't work at all
    public static void analyze_comment(String line, int line_num, BufferedReader br) throws IOException {
        if (line.startsWith("//")) {
            System.out.println("Line " + line_num + ": is a single-line comment");
        } else if (line.startsWith("/*")) {
            if (line.contains("*/")) {
                System.out.println("Line " + line_num + ": is a single-line comment");
            } else {
                System.out.print("Line " + line_num + ": is a 'beginning' of a multi-line comment");
                int end_line_num = line_num;
                while ((line = br.readLine()) != null) {
                    end_line_num++;
                    if (line.trim().endsWith("*/")) {
                        System.out.println(" which 'ended' at line " + end_line_num);
                        break;
                    }
                }
            }
        } else {
            System.out.println("Line " + line_num + ": is not a comment");
        }
    }
}

// if (line.startsWith("//") || line.startsWith("/*") && line.endsWith("*/")) {
// if (line.startsWith("//")) {
// System.out.println("Line --> " + line_num + ": is a single-line comment");
// } else {
// System.out.println("Line --> " + line_num + ": is a multi-line comment");
// }
// } else if ((line.startsWith("/*") && !(line.endsWith("*/")))
// || (!line.startsWith("/*") && (line.endsWith("*/")))) {
// System.out.println("Line --> " + line_num + ": is not a valid multi-line
// comment");
// } else if (line.startsWith("/")) {
// System.out.println("Line --> " + line_num + ": is not a single-line
// comment");
// } else {
// System.out.println("Line --> " + line_num + ": is not a comment");
// }

// else if (line.trim().startsWith("/*")) {
// System.out.println("Line --> " + line_num + ": is a beginning of a multi-line
// comment");
// // ? check if the comment ends on the same line */
// if (line.trim().endsWith("*/")) {
// System.out.println(
// "Line --> " + line_num + ": is the end of multi-line comment stared on the
// same line :) ehh ");
// } else {
// // ? keep it rooling till it hits the end of the multi-line comment
// int end_line_num = line_num;
// while ((line = br.readLine()) != null) {
// end_line_num++;
// if (line.trim().endsWith("*/")) {
// System.out.println(
// "Line --> " + end_line_num + ": is the end of multi-line comment stared on
// line "
// + line_num);
// break;
// }
// }
// }
// }
    public static void checkIdentifiers(String line, int lineNumber) {
        List<String> identifiers = new ArrayList<>(Arrays.asList(
                "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class",
                "const", "continue", "default", "do", "double", "else", "enum", "extends", "final",
                "finally", "float", "for", "goto", "if", "implements", "import", "instanceof", "int",
                "interface", "long", "native", "new", "null", "package", "private", "protected", "public",
                "return", "short", "static", "strictfp", "super", "switch", "synchronized", "this",
                "throw", "throws", "transient", "try", "void", "volatile", "while"));


        if (line.trim().startsWith("//") || line.trim().startsWith("/*") || line.trim().endsWith("*/")) {
            return;
        }
        
        boolean foundIdentifier = false;

        for (String word : line.split(" ")) {
            if (identifiers.contains(word)) {
                if (line.matches(".*\\b" + word + "\\b.*")) {
                    System.out
                            .println("Line no: " + lineNumber + " --> " + word + ": is a built-in language construct");
                    foundIdentifier = true;
                }
            }
            // else {
            // System.out.println("Line no: " + lineNumber + " --> : no built-in language
            // construct found");
            // }
        }

        if (!foundIdentifier) {
            System.out.println("Line no: " + lineNumber + " --> : no built-in language construct found");
        }

    }


    public static void isFunctionHeader(String line, int lineNumber) {
        // Build regex for data types, including arrays
        String dataTypes = DATA_TYPES.stream()
                .map(dt -> dt + "(\\[\\s*\\])*") // Allow arrays
                .collect(Collectors.joining("|"));

        String dataTypeRegex = "(" + dataTypes + "|void)";

        String modifiers = "^\\s*(public\\s+|private\\s+|protected\\s+|static\\s+)*";
        String returnType = dataTypeRegex + "\\s+";
        String methodName = "[a-zA-Z0-9_]+";

        String parameters = "\\s*\\(\\s*((" + dataTypeRegex + "\\s+)+(\\[\\s*\\])*[a-zA-Z0-9_]+(\\s*,\\s*("
                + dataTypeRegex + "\\s+)+(\\[\\s*\\])*[a-zA-Z0-9_]+)*)?\\s*\\)";
        String regex = modifiers + returnType + methodName + parameters;

        if (line.matches(regex)) {
            System.out.println("Line no: " + lineNumber + " --> " + line.trim() + " is a method declaration");
        } else {
            System.out.println(
                    "Line no: " + lineNumber + " --> " + line.trim() + " is not a method declaration");
        }
    }
}
