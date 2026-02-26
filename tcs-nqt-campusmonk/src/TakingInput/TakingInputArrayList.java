package TakingInput;

import java.util.Scanner;
import java.util.ArrayList;

public class TakingInputArrayList {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of elements: ");
        int n = sc.nextInt();

        ArrayList<Integer> list = new ArrayList<>();

        System.out.println("Enter " + n + " elements:");
        for (int i = 0; i < n; i++) {
            list.add(sc.nextInt());
        }

        System.out.println("ArrayList elements are:");
        for (int num : list) {
            System.out.print(num + " ");
        }

        sc.close();
    }
}
