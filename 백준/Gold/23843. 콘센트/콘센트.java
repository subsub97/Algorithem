import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static int N;
    public static int M;

    public static PriorityQueue<Integer> pq = new PriorityQueue<Integer>();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Integer[] numbers = new Integer[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int curNumber = Integer.parseInt(st.nextToken());
            numbers[i] = curNumber;
        }

        Arrays.sort(numbers, Collections.reverseOrder());

        // size를 M개로 강제하고 돌려가면서 넣어보자
        for (int i = 0; i < N; i++) {
            if(pq.size() < M) {
                pq.add(numbers[i]);
            }
            else{
                Integer num = pq.poll();
                pq.add(num + numbers[i]);
            }
        }

        int answer = 0;

        for (int i = 0; i < M; i++) {
            Integer curNum = pq.poll();

            if(curNum == null) continue;

            answer = Math.max(answer, curNum);
        }

        System.out.println(answer);
    }
}
