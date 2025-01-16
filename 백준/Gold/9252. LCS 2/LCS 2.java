import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String a = br.readLine();
        String b = br.readLine();

        if(a.length() < b.length()) {
            String temp = a;
            a = b;
            b = temp;
        }

        int maxLen = a.length();
        int ans = 0;

        int[][] dp = new int[maxLen + 1][b.length() + 1];

        for(int i = 1; i <= a.length(); i++) {
            char curAlpa = a.charAt(i - 1);
            for(int j = 1; j <= b.length(); j++) {
                char compareAlpa = b.charAt(j -1);

                if(curAlpa == compareAlpa) {
                    if(i == 0 || j == 0) {
                        dp[i][j] = 1;
                    }
                    else{
                        dp[i][j] = dp[i-1][j-1] + 1;
                    }
                    ans = Math.max(ans, dp[i][j]);
                }
                else{
                    if(j == 0) {
                        dp[i][j] = 0;
                        if( i != 0 ) {
                            dp[i][j] = dp[i - 1][j];
                        }
                        continue;
                    }
                    if(i != 0) {
                        dp[i][j] = Math.max(dp[i][j - 1],dp[i-1][j]);
                        continue;
                    }
                    dp[i][j] = dp[i][j-1];
                }
            }
        }
        System.out.println(ans);

        StringBuilder sb = new StringBuilder();
        int r = a.length();
        int c = b.length();

        while(r > 0 && c > 0) {
            if( r == 0 || c == 0 ) break;

            if(dp[r][c] == dp[r-1][c]) {
                r--;
            }
            else if(dp[r][c] == dp[r][c-1]) {
                c--;
            }
            else{
                sb.append(a.charAt(r - 1));
                r--;
                c--;
            }
        }


        System.out.println(sb.reverse().toString());

    }
}
