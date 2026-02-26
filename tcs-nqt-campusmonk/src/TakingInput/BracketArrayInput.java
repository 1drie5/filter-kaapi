package TakingInput;

import java.util.ArrayList;
import java.util.Scanner;

public class BracketArrayInput {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter elements in {1,2,3} or [1,2,3] or (1,2,3) format: ");

        String input = sc.nextLine().trim();

        // Handle any type of bracket: {}, [], ()
        if ((input.startsWith("{") && input.endsWith("}")) ||
                (input.startsWith("[") && input.endsWith("]")) ||
                (input.startsWith("(") && input.endsWith(")"))) {
            input = input.substring(1, input.length() - 1);
        }

        String[] ch = input.split(",");

        ArrayList<Integer> arr = new ArrayList<>();

        for (String token : ch) {
            int num = Integer.parseInt(token.trim());
            arr.add(num);
        }

        System.out.println("Elements are:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        sc.close();
    }
}