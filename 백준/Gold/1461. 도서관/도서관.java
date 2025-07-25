import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Main {
    static int N,M;
    static int[] arr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        PriorityQueue<Integer> minusPq = new PriorityQueue<>();
        PriorityQueue<Integer> plusPq = new PriorityQueue<>( (a,b) -> b.compareTo(a));

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if(num < 0) minusPq.add(num);
            else plusPq.add(num);
        }


        /**
         * 가장 큰거부터 M개는 한번만
         *
         * 나머지는 값이 큰거부터 M개씩 2번
         */

        int left = 0;
        int right = N - 1;

        boolean flag = false;
        int ans = 0;

        while(!minusPq.isEmpty() || !plusPq.isEmpty()) {
            int peek;
            if(plusPq.isEmpty()) {
                peek = 0;
            }
            else{
                peek = plusPq.peek();
            }
            if(!minusPq.isEmpty() && Math.abs(minusPq.peek()) > Math.abs(peek)) {
                int num = Math.abs(minusPq.poll());
                if(!flag) {
                    ans -= num;
                    flag = true;
                }
                ans += num * 2;
                for (int i = 0; i < M - 1; i++) {
                    if(minusPq.isEmpty()) break;
                    minusPq.poll();
                }
            }
            else {
                int num = Math.abs(plusPq.poll());
                if(!flag) {
                    ans -= num;
                    flag = true;
                }
                ans += num * 2;

                for (int i = 0; i < M - 1; i++) {
                    if(plusPq.isEmpty()) break;
                    plusPq.poll();
                }
            }
        }

        System.out.println(ans);
    }
}