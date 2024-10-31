import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[] liquid;
    static long ans = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        liquid = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++) {
            liquid[i] = Integer.parseInt(st.nextToken());
        }



        int[] answer = new int[2];
        answer[0] = liquid[0];
        answer[1] = liquid[N-1];

        for(int i = 0; i < N - 1; i++) {
            //여기서 left는 고정하고 이분탐색으로 짝꿍 찾기
            int s = i + 1;
            int e = N-1;

            while(s <= e) {
                int mid = (s + e) / 2;
                long curSum = (long) liquid[i] + liquid[mid];

                if(Math.abs(curSum) < ans) {
                    answer[0] = liquid[i];
                    answer[1] = liquid[mid];
                    ans = Math.abs(curSum);
                }

                if(curSum > 0) {
                    e = mid - 1;
                }
                else {
                    s = mid + 1;
                }
                }
            }

        System.out.println(answer[0] + " " + answer[1]);
    }

}
