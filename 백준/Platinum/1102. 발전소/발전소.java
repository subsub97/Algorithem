import java.util.*;
import java.io.*;

public class Main {

    static int N, P;
    static int[][] cost;
    static int[] dp;
    static int init = 0;
    static int ans = (int) 1e9;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        cost = new int[N][N];
        dp = new int[1 << N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //DP도 초기값 갱신해둬야하는거 아님?
        Arrays.fill(dp, (int) 1e9);

        String s = br.readLine();
        int cnt = 0;

        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == 'Y') {
                init |= (init | (1 << i));
                cnt++;
            }
        }

        P = Integer.parseInt(br.readLine());

        dp[init] = 0;

        //TODO : 최초 켜져있는 발전소 수 체크하기


        if(cnt >= P) {
            System.out.println(0);
        }else{
            // DP 사용방법
            // DP 한번 갱신한 값이 있으면 들어가서 바텀으로 쌓아가자
            for(int d = init ; d < (1 << N); d++) {
                // 최초 켜져 있는 발전소를 i라고하자
                if(dp[d] == (int) 1e9) continue;
                cnt = 0;

                for(int i = 0; i < N; i++) {
                    if( ((d >> i) & 1) == 1) cnt++;
                }

                for(int i = 0; i < N; i++) {
                    if( ((d >> i) & 1) != 1) continue;

                    // 최초 발전소가 켜져있는 경우
                    // 여기서부터 시작해서 모든 발전소를 켜는 최소비용을 구해보자
                    for(int j = 0; j < N; j++) {
                        // 켜져있지 않으면 이동하면서 DP를 최소 값으로 만들 수 있겠지?
                        if(((init >> j & 1) == 1)) continue;
                        dp[d | (1 << j)]  = Math.min(dp[d | (1 << j)] , cost[i][j] + dp[d]);

                        if(cnt >= P) {
                            ans = Math.min(ans, dp[d | (1 << i)]);
                        }
                    }
                }
            }
            if(ans == (int) 1e9) System.out.println(-1);
            else{
                System.out.println(ans);
            }

        }

    }


}
