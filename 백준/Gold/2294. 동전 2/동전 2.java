import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] coins = new int[n];
        int[] dp = new int[100_001];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            coins[i] = (Integer.parseInt(st.nextToken()));
        }

        for (int i = 1; i <= m; i++) {
            dp[i] = 100_000_000;
        }

        for(int i = 0; i < n; i++) {
            for(int j = coins[i]; j <= m; j++) {
                dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
            }
        }

        int answer = dp[m];
        if(answer == 100_000_000) {
            System.out.println(-1);
        }else{
            System.out.println(answer);
        }
    }
}
