import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static int[][] dp;
    static Fruit[] fArr;

    static class Fruit {
        int f;
        int s;
        int c;

        Fruit(int f, int s, int c) {
            this.f = f;
            this.s = Math.min(s,f);
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 농장 크기
        M = Integer.parseInt(st.nextToken()); // 최대 기한 M일
        K = Integer.parseInt(st.nextToken()); // K가지 종류

        fArr = new Fruit[K];
        dp = new int[M + 1][K]; // M일 K개 종류

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());

            int f = Integer.parseInt(st.nextToken() );
            int s = Integer.parseInt(st.nextToken() );
            int c = Integer.parseInt(st.nextToken() );

            fArr[i] = new Fruit(f, s, c);
        }

        /*
        현재 자기 위치로 올 수 있는게
       1.  K개 과일중 A일 전에서 오는거
       2. B일전 자기 자신을 심어두고 오는 경우
       3. 아무것도 안하는 경우 (이전 최고 값 사용)
        */

        int max = 0;
        // K개 식물 심어 놓기
       for(int i = 0; i < K; i++) {
            int first = fArr[i].f;
            if(first > M) continue;
            dp[first][i] = fArr[i].c;

            max = Math.max(dp[first][i] , max);
       }

        for (int i = 1; i <= M; i++) {
            for (int j = 0; j < K; j++) {
                if(dp[i][j] == 0) continue;

                for(int k = 0; k < K; k++) {
                    // 자기 자신은 s를 이용
                    int next = -1;
                    if(j == k) {
                        //자기 자신인 경우
                        next = fArr[k].s + i;
                    }
                    // 내꺼 뽑고 다른거 심기
                    else{
                        next = fArr[k].f + i;
                    }

                    if(next > M) continue;

                    dp[next][k] = Math.max(dp[next][k],dp[i][j] + fArr[k].c);
                    max = Math.max(dp[next][k], max);
                }
            }
        }

        System.out.println(max * N);
    }
}