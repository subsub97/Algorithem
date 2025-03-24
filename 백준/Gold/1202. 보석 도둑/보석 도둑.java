import java.io.*;
import java.util.*;

public class Main {
    static class Jewelry {
        int weight;
        int cost;

        Jewelry(int weight, int cost) {
            this.weight = weight;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 보석 수
        int K = Integer.parseInt(st.nextToken()); // 가방 수

        Jewelry[] jewelries = new Jewelry[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            jewelries[i] = new Jewelry(weight, cost);
        }

        int[] bags = new int[K];
        for (int i = 0; i < K; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }

        // 정렬
        Arrays.sort(jewelries, Comparator.comparingInt(j -> j.weight)); // 무게 오름차순
        Arrays.sort(bags); // 가방 무게 오름차순

        // 가격 기준 최대 힙
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        long answer = 0;
        int idx = 0;

        for (int i = 0; i < K; i++) {
            int bagCapacity = bags[i];

            // 현재 가방에 담을 수 있는 보석들 전부 PQ에 넣기
            while (idx < N && jewelries[idx].weight <= bagCapacity) {
                pq.add(jewelries[idx].cost);
                idx++;
            }

            // 가장 비싼 보석을 담는다
            if (!pq.isEmpty()) {
                answer += pq.poll();
            }
        }

        System.out.println(answer);
    }
}
