
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N,M,C;
    static int[][][] dp;
    static int[] jewelry;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        // 가방, 무게, 선택한 보석
        dp = new int[M][C + 1][(1 << N+1)];
        jewelry = new int[N + 1];

        st = new StringTokenizer(br.readLine());

        for(int i = 1; i <= N; i++) {
            jewelry[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0, 0);

        System.out.println(dp[0][0][0]);
    }

    public static int dfs(int bag, int selected, int weight) {
        if(bag == M || (selected - 1 == (1 << N -1) -1)) return 0;

        if (dp[bag][weight][selected] != 0) {
            return dp[bag][weight][selected];
        }

        int cnt = 0;
        for(int i = 1; i <= N; i++) {
            // 이미 선택한 경우
            if((selected & (1 << i)) != 0) continue;

            if(jewelry[i] + weight <= C){
                cnt = Math.max(cnt,dfs(bag , selected | (1 << i), jewelry[i] + weight) + 1);
            }
            if(jewelry[i] <= C && bag + 1 < M) {
                cnt = Math.max(cnt, dfs(bag + 1, selected | (1 << i), jewelry[i]) + 1);
            }

        }
        dp[bag][weight][selected] = cnt;

        return cnt;
    }
}
