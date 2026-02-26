package TakingInput;

import java.util.Scanner;

public class StringInputScanner {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter an integer: ");
        int n = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter a string: ");
        String input = sc.nextLine();

        System.out.println(n+" "+input);

        sc.close();
    }
}