package TakingInput;

import java.util.Scanner;

public class CharInputScanner {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Single character input
        System.out.print("Enter a character: ");
        char ch = sc.nextLine().trim().charAt(0);

        // Character array input
        System.out.print("Enter a word to store as character array: ");
        String word = sc.nextLine().trim();
        char[] charArray = word.toCharArray();

        // Display
        System.out.println("\n===== You Entered =====");
        System.out.println("Character       : " + ch);
        System.out.print("Character Array : ");
        for (char c : charArray) {
            System.out.print(c + " ");
        }
        System.out.println();
        System.out.println("Array Length    : " + charArray.length);

        sc.close();
    }
}