import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] liquid = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        for(int i = 0; i < N; i++) {
            liquid[i] = Integer.parseInt(st.nextToken());
        }
        
        int[] answer = new int[2];
        long minSum = Long.MAX_VALUE;
        
        // 왼쪽 용액을 기준으로 반복
        for(int i = 0; i < N-1; i++) {
            int left = i + 1;
            int right = N - 1;
            
            while(left <= right) {
                int mid = (left + right) / 2;
                long sum = (long)liquid[i] + liquid[mid];
                
                if(Math.abs(sum) < minSum) {
                    minSum = Math.abs(sum);
                    answer[0] = liquid[i];
                    answer[1] = liquid[mid];
                }
                
                if(sum < 0) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        
        System.out.println(answer[0] + " " + answer[1]);
    }
}