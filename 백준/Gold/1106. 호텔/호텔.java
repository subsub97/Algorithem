import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
// 19:08
    static int N, C;

    static int[][] dp = new int[101][100001];
    static int[][] cities = new int[101][1];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int cost = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());

            if (cities[cost][0] > count) {
                continue;
            }

            cities[cost][0] = count;
//            dp[cost][0] = count;
        }

        int ans = Integer.MAX_VALUE;

        for (int i = 1; i < 101; i++) {
            if(cities[i][0] == 0) continue;
            for (int j = 1; j <= 100000; j++) {
                if(i == 1) {
                    dp[i][j] = dp[i][j - 1] + cities[i][0];
                }
                else{
                    for (int k = 1; k <= i; k++) {
                        if(j - k < 0) {
                            dp[i][j] = Math.max(dp[i - k][j], dp[i][j-1]);

                        }
                        else{
                            dp[i][j] = Math.max(dp[i][j-k] + cities[k][0],Math.max(dp[i - 1][j - k] + cities[k][0], dp[i][j]));
                        }
                    }
                }
                if(dp[i][j] >= C) {
                    ans = Math.min(ans, j);
                }
            }
        }

        System.out.println(ans);

    }

}