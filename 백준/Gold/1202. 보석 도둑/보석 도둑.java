import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] jewelries = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            jewelries[i][0] = Integer.parseInt(st.nextToken()); // weight
            jewelries[i][1] = Integer.parseInt(st.nextToken()); // cost
        }

        int[] bags = new int[K];
        for (int i = 0; i < K; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }

        // 정렬
        Arrays.sort(jewelries, Comparator.comparingInt(j -> j[0])); // weight 오름차순
        Arrays.sort(bags); // bag 오름차순

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        long total = 0;
        int idx = 0;

        for (int i = 0; i < K; i++) {
            int bagWeight = bags[i];

            while (idx < N && jewelries[idx][0] <= bagWeight) {
                pq.add(jewelries[idx][1]); // cost만 저장
                idx++;
            }

            if (!pq.isEmpty()) {
                total += pq.poll(); // 가장 비싼 거 하나 꺼냄
            }
        }

        System.out.println(total);
    }
}
