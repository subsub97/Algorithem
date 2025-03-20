import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] grid;
    // dp[i][j]는 i행 j열에 도달할 때의 최적 경로 값
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        grid = new int[N][M];
        dp = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 첫 번째 행은 왼쪽에서 오른쪽으로만 이동 가능
        dp[0][0] = grid[0][0];
        for (int j = 1; j < M; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }

        // 두 번째 행부터 마지막 행까지 처리
        for (int i = 1; i < N; i++) {
            // 왼쪽에서 오른쪽으로 이동하는 경우를 위한 임시 배열
            int[] left = new int[M];
            left[0] = dp[i - 1][0] + grid[i][0];
            for (int j = 1; j < M; j++) {
                left[j] = Math.max(left[j - 1], dp[i - 1][j]) + grid[i][j];
            }

            // 오른쪽에서 왼쪽으로 이동하는 경우를 위한 임시 배열
            int[] right = new int[M];
            right[M - 1] = dp[i - 1][M - 1] + grid[i][M - 1];
            for (int j = M - 2; j >= 0; j--) {
                right[j] = Math.max(right[j + 1], dp[i - 1][j]) + grid[i][j];
            }

            // 현재 행의 각 열에서는 두 방향 중 더 큰 값을 선택
            for (int j = 0; j < M; j++) {
                dp[i][j] = Math.max(left[j], right[j]);
            }
        }

        System.out.println(dp[N - 1][M - 1]);
    }
}
