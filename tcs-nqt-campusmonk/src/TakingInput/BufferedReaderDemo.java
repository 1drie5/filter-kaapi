package TakingInput;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

public class BufferedReaderDemo {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 1. String input
        System.out.print("Enter your name: ");
        String name = br.readLine();

        // 2. Integer input
        System.out.print("Enter your age: ");
        int age = Integer.parseInt(br.readLine().trim());

        // 3. Double input
        System.out.print("Enter your GPA: ");
        double gpa = Double.parseDouble(br.readLine().trim());

        // 4. Character input
        System.out.print("Enter your grade (A/B/C/D): ");
        char grade = br.readLine().trim().charAt(0);

        // 5. Boolean input
        System.out.print("Are you a hostel student? (true/false): ");
        String boolInput = br.readLine().trim().toLowerCase();
        boolean isHosteler = boolInput.equals("true") || boolInput.equals("yes") || boolInput.equals("1");

        // 6. Multiple values on one line (space separated)
        System.out.print("Enter two numbers separated by space: ");
        String[] parts = br.readLine().trim().split(" ");
        int a = Integer.parseInt(parts[0]);
        int b = Integer.parseInt(parts[1]);

        // 7. Array input with brackets: {1,2,3} or [1,2,3] or (1,2,3)
        System.out.print("Enter your marks as an array like {85,90,78}: ");
        String arrayInput = br.readLine().trim();
        if ((arrayInput.startsWith("{") && arrayInput.endsWith("}")) ||
                (arrayInput.startsWith("[") && arrayInput.endsWith("]")) ||
                (arrayInput.startsWith("(") && arrayInput.endsWith(")"))) {
            arrayInput = arrayInput.substring(1, arrayInput.length() - 1);
        }
        String[] tokens = arrayInput.split(",");
        ArrayList<Integer> list = new ArrayList<>();
        for (String token : tokens) {
            list.add(Integer.parseInt(token.trim()));
        }

        // --- Print all inputs ---
        System.out.println("\n===== You Entered =====");
        System.out.println("Name        : " + name);
        System.out.println("Age         : " + age);
        System.out.println("GPA         : " + gpa);
        System.out.println("Grade       : " + grade);
        System.out.println("Is Hosteler : " + isHosteler);
        System.out.println("Two nums    : " + a + " and " + b);
        System.out.println("Marks       : " + list);

        br.close();
    }
}