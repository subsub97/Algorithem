import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int MOD = 1_000_000_000;
    static long[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        dp = new long[101][10][1 << 10];

        // 0은 맨 앞에 올 수 없기 때문에 0을 제외한 마지막 수 초기화
        for(int i = 1; i < 10; i++) {
            dp[1][i][1 << i] = 1;
        }

        // N 자리일 때 계단 수 구하기
        for(int i = 2; i <= N; i++) {
            for(int j = 0; j < 10; j++) {
                //j는 마지막 자리 수를 의미한다.
                int used = (1 << j);
                for(int k = 1; k < (1 << 10); k++) {
                    //현재 i자리를 만들기 위해서  i - 1 자리에 어떤 수들로 구성했는지 체크
                    int newUsed = used | k;
                    if(j == 0) {
                        //마지막이 0인 경우에는 앞에 1만 올 수 있음
                        // i-1번째에서 1로 끝나느 수뒤에 j를 붙이면 계단 수를 이어갈 수 있다.
                        dp[i][j][newUsed] += dp[i - 1][1][k];
                    }
                    else if(j == 9) {
                        dp[i][j][newUsed] += dp[i - 1][8][k];
                    }
                    else{
                        dp[i][j][newUsed] += (dp[i - 1][j - 1][k] + dp[i - 1][j + 1][k]);
                    }
                    dp[i][j][newUsed] %= MOD;
                }
            }
        }


        long ans = 0;
        for(int i = 0; i < 10; i++) {
            ans += dp[N][i][1023];
            ans %= MOD;
        }

        System.out.println(ans);
    }
}
