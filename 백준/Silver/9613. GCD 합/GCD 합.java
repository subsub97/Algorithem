import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long ans =0;
            int cnt = Integer.parseInt(st.nextToken());
            int[] arr = new int[cnt];

            for (int j = 0; j < cnt; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }

            // 조합 구하기
            for (int j = 0; j < cnt; j++) {
                ans += simulate(arr, j);
            }
            System.out.println(ans);
        }
    }

    private static int simulate(int[] arr, int startIdx) {
        int sum = 0;
        int a = arr[startIdx];

        for (int i = startIdx + 1; i < arr.length; i++) {
            int b = arr[i];

            // GCD 계산
            int tempA = a;
            int tempB = b;

            if(tempA < tempB) {
                int temp = 0;
                temp = tempA;
                tempA = tempB;
                tempB = temp;
            }
            while (tempB != 0) {
                int temp = tempA % tempB;
                tempA = tempB;
                tempB = temp;
            }
            sum += tempA;
        }

        return sum;
    }
}