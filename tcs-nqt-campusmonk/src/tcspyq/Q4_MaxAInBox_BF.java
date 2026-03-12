package tcspyq;
import java.util.*;

public class Q4_MaxAInBox_BF {
    public static int maxAqua(String s , int l){
        if (l <= 0) return 0;
        int n = s.length();
        if (n == 0) return 0;

        int maxSoFar = 0;

        for(int i = 0; i < n; i += l){
            int countInBox = 0;

            for(int j = i; j < i + l && j < n; j++){
                if(s.charAt(j) == 'a'){
                    countInBox++;
                }
            }
            maxSoFar = Math.max(maxSoFar, countInBox);
        }
        return maxSoFar;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int l = sc.nextInt();
        sc.close();

        int ans = maxAqua(s, l);
        System.out.println(ans);
    }
}