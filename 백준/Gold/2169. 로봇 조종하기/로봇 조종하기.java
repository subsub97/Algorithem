import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N,M;
    static int[][] grid;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        grid = new int[N][M];
        dp = new int[N][M][2];

        for (int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                Arrays.fill(dp[i][j], -100000000);

            }

        }

        for(int i = 0 ; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(i == 0) {
                    if(j == 0) {
                        dp[i][j][0] = grid[i][j];
                        dp[i][j][1] = grid[i][j];
                        continue;
                    }
                    dp[i][j][0] = dp[i][j - 1][0] + grid[i][j];
                    dp[i][j][1] = dp[i][j - 1][1] + grid[i][j];
                }

                else if(i == N -1) {
                    if(j == 0) {
                        // 맨 왼쪽, 맨 오른쪽 처리하기
                        dp[i][j][0] = dp[i-1][j][0] + grid[i][j];
                        dp[i][j][1] = dp[i-1][j][1] + grid[i][j];
                        dp[i][M - 1][1] = Math.max(dp[i-1][M - 1][0], dp[i-1][M - 1][1]) + grid[i][j];
                        continue;

                    }
                    dp[i][j][0] = Math.max(Math.max(dp[i][j-1][0],dp[i-1][j][0]),dp[i-1][j][1]) + grid[i][j];
                    dp[i][j][1] = Math.max(Math.max(dp[i][j-1][1],dp[i-1][j][1]),dp[i-1][j][0]) + grid[i][j];
                }

                else{
                    if(j == 0) {
                        // 왼 -> 오
                        dp[i][j][0] = Math.max(dp[i-1][j][0], dp[i-1][j][1]) + grid[i][j];
                        dp[i][M - 1][1] = Math.max(dp[i-1][M - 1][0], dp[i-1][M - 1][1]) + grid[i][M -1];
                        continue;
                    }
                    dp[i][j][0] = Math.max(Math.max(dp[i-1][j][0], dp[i][j-1][0]),dp[i-1][j][1]) + grid[i][j];
                    dp[i][M-j-1][1] = Math.max(Math.max(dp[i-1][M-j-1][1], dp[i][M - j-1 + 1][1]),dp[i-1][M - j - 1][0]) + grid[i][M-j-1];
                }
            }
        }

        System.out.println(Math.max(dp[N-1][M-1][0], dp[N-1][M-1][1]));
    }



}
