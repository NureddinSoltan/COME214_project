import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String fileName = "src/testCode.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineNumber = 1;
            while ((line = br.readLine()) != null) {
                // Add your function
                //yourFunction(line, lineNumber);
                isFunction(line, lineNumber);
                lineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    public static void isFunction(String line, int lineNumber) {
        String regex = "^(public|private|protected|static|\\s)*[a-zA-Z0-9<>\\[\\]]*\\s+[a-zA-Z0-9_]+\\s*\\(.*\\)\\s*\\{?\\s*$";
        if (line.matches(regex)) {
            System.out.println("Line no: " + lineNumber + " --> " + line.trim() + " is a method declaration");
        } 
    }

}
