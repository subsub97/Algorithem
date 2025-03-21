import java.util.*;
import java.io.*;

public class Main {
    static int N, K;
    static int[] arr;
    static PriorityQueue<Integer> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if(i > 0) {
                pq.add(arr[i] - arr[i-1]);
            }
        }

        long sum = 0;

        while(pq.size() > K - 1) {
            sum += pq.poll();
        }
        System.out.println(sum);
    }
}