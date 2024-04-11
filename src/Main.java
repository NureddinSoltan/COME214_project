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
                analyze_comment(line, lineNumber/*, br*/);
                lineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // This Function analyze a lines of input and checks if they are comments or not
    public static void analyze_comment(String line, int line_num/*, BufferedReader br*/) /*throws IOException*/ {
        if (line.startsWith("//") || line.startsWith("/*") && line.endsWith("*/")) {
            if(line.startsWith("//")) {
                System.out.println("Line --> " + line_num + ": is a single-line comment");
            } else{
                System.out.println("Line --> " + line_num + ": is a multi-line comment");
            }
        } else if ((line.startsWith("/*") && !(line.endsWith("*/"))) || (!line.startsWith("/*") && (line.endsWith("*/")))){
            System.out.println("Line --> " + line_num + ": is not a valid multi-line comment");
        } else if (line.startsWith("/")) {
            System.out.println("Line --> " + line_num + ": is not a single-line comment");
        }
        else {
            System.out.println("Line --> " + line_num + ": is not a comment");
        }
//        else if (line.trim().startsWith("/*")) {
//            System.out.println("Line --> " + line_num + ": is a beginning of a multi-line comment");
//            // ? check if the comment ends on the same line */
//            if (line.trim().endsWith("*/")) {
//                System.out.println(
//                        "Line --> " + line_num + ": is the end of multi-line comment stared on the same line :) ehh ");
//            } else {
//                // ? keep it rooling till it hits the end of the multi-line comment
//                int end_line_num = line_num;
//                while ((line = br.readLine()) != null) {
//                    end_line_num++;
//                    if (line.trim().endsWith("*/")) {
//                        System.out.println(
//                                "Line --> " + end_line_num + ": is the end of multi-line comment stared on line "
//                                        + line_num);
//                        break;
//                    }
//                }
//            }
//        }
    }
}
