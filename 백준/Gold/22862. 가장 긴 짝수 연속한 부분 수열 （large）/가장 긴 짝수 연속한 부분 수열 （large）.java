import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N,K;
    static int[] S;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        S = new int[N];

        st = new StringTokenizer(br.readLine());



        for (int i = 0; i < N; i++) {
            S[i] = Integer.parseInt(st.nextToken());
        }

        // 왼쪽 포인터가 다른 짝수로 가면서 목숨을 늘려준다.
        int left = 0;
        int right = 0;
        int ans = 0;
        int cnt = 0;

        if(S[left] % 2 == 0) {
            ans = 1;
        }

        if(S[left] % 2 == 1) cnt++;

        while(right < N) {
            if(right + 1 == N ) break;

            int next = right + 1;

            if(S[next] % 2 == 0) {
                right++;
            }
            else if(left > right) {
                right = left;

                if(S[left] % 2 == 0) {
                    cnt = 0;
                }
                else{
                    cnt = 1;
                }
            }
            else{
                //홀수인 경우
                if(cnt < K) {
                    cnt++;
                    right++;
                }
                else{
                    if(S[left] % 2 != 0 ) {
                        cnt--;
                    }
                    left++;
                }

            }

            int len = right - left - cnt + 1;
            ans = Math.max(ans, len);
        }

        System.out.println(ans);

    }
}

