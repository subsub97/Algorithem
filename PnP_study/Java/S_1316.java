import java.util.Scanner;
public class S_1316 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] dp = new int[n+3];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 1;

        for(int i=4;i <= n; i++){
            dp[i] = dp[i-1] + 1;
            if(i%3 ==0) dp[i] = Math.min(dp[i],dp[i/3] +1);
            if(i % 2 ==  0) dp[i] = Math.min(dp[i],dp[i/2] + 1);

        }

        System.out.println(dp[n]);
    }
}
