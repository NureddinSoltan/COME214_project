import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String fileName = "./code.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineNumber = 1;
            while ((line = br.readLine()) != null) {
                // Add your function
                // yourFunction(line, lineNumber);
                analyze_comment(line, lineNumber, br); // Khalid AbuHamad - 200209977
                lineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     *
     * Khalid AbuHamad 200209977
     * ? check line for a (single/multi) line comment
     *
     */
    public static void analyze_comment(String line, int line_num, BufferedReader br) throws IOException {
        if (line.trim().startsWith("//")) {
            System.out.println("Line --> " + line_num + ": is a single-line comment");
        } else if (line.trim().startsWith("/*")) {
            System.out.println("Line --> " + line_num + ": is a beginning of a multi-line comment");
            // ? check if the comment ends on the same line */
            if (line.trim().endsWith("*/")) {
                System.out.println(
                        "Line --> " + line_num + ": is the end of multi-line comment stared on the same line :) ehh ");
            } else {
                // ? keep it rooling till it hits the end of the multi-line comment
                int end_line_num = line_num;
                while ((line = br.readLine()) != null) {
                    end_line_num++;
                    if (line.trim().endsWith("*/")) {
                        System.out.println(
                                "Line --> " + end_line_num + ": is the end of multi-line comment stared on line "
                                        + line_num);
                        break;
                    }
                }
            }
        }
    }
}
