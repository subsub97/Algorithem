import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N ;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N];

        int ans = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        for (int i = 0; i < N; i++) {
            int left = 0;
            int right = N - 1;

            while(left < right) {
                int current = arr[left] + arr[right];
                if(left == i) {
                    left++;
                    continue;
                }
                if(right == i) {
                    right--;
                    continue;
                }
                if(current == arr[i]) {
                    // 좋다를 만족하는 경우
                    ans++;
                    break;
                }
                if(current < arr[i]) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        System.out.println(ans);
    }
}