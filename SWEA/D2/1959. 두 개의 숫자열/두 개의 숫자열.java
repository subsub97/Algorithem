import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        int T,N,M;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        for(int l =1; l <= T; l++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            int temp = 0;

            int[] arr1 = new int[N];
            int[] arr2 = new int[M];

            if(N > M) {
                temp = M;

                M = N;
                N = temp;

                arr1 = new int[N];
                arr2 = new int[M];

                st = new StringTokenizer(br.readLine());

                for(int i =0; i < M; i++) {
                    arr2[i] = Integer.parseInt(st.nextToken());
                }

                st = new StringTokenizer(br.readLine());

                for(int i =0; i < N; i++) {
                    arr1[i] = Integer.parseInt(st.nextToken());
                }
            }

            else{
                st = new StringTokenizer(br.readLine());

                for(int i =0; i < N; i++) {
                    arr1[i] = Integer.parseInt(st.nextToken());
                }

                st = new StringTokenizer(br.readLine());

                for(int i =0; i < M; i++) {
                    arr2[i] = Integer.parseInt(st.nextToken());
                }
            }



            int answer = -99999999;
            sb.append("#" + l + " ");

            int startIdx = 0;


            for (int j = 0; j <= M - N; j++) {
                int curSum = 0;
                for (int k = 0; k < N; k++) {
                    curSum += arr1[k] * arr2[startIdx + k];
                }
                startIdx++;
                answer = Math.max(answer, curSum);
            }
            sb.append(answer + "\n");
        }
        System.out.println(sb.toString());


    }
}
