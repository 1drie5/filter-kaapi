package TakingInput;

import java.util.ArrayList;
import java.util.Scanner;

public class TakingInputCommaSeparated {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter comma separated elements: ");

        String input = sc.nextLine();
        // "1,2,3,4,5"
        String[] ch = input.split(",");

        ArrayList<Integer> arr = new ArrayList<>();

        for(String token: ch){
            int num = Integer.parseInt(token);
            arr.add(num);
        }

        System.out.println("Elements are:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        sc.close();
    }
}