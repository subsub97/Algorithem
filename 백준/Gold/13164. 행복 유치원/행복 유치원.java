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

        /*
            같은 조의 원생은 인접해 있어야한다.
            조별로 인원수가 같은 필요는 없다.

            티셔츠 맞출건데 비용은
            가장 키가 큰 원생과 가장 키가 작은 원생의 키 차이만큼 든다.
            최대한 비용 아끼고 싶음
            K개의 조에 대해 티 비용 합을 최소 만들기

            2 2 1 4
            3 그룹이면

            묶었을 때 gap 큰거는 최대한 혼자 써야함

            1 4 8 9 10 20

            3 3 1 1 10 => 2개 남겨야함

            1 1

         */
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i < N; i++) {
            pq.add(arr[i] - arr[i-1]);
        }

        long sum = 0;

        while(pq.size() > K - 1) {
            sum += pq.poll();
        }
        System.out.println(sum);
    }
}