import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N,M;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int left =0;
        int right = 0;
        int ans = Integer.MAX_VALUE;

        while(right < N && left < N) {
            int cur = arr[right] - arr[left];
            if(cur >= M) {
                ans = Math.min(ans, cur);
            }

            if(cur < M) {
                right++;
            }else{
                left++;
            }
        }

        System.out.println(ans);
    }

}