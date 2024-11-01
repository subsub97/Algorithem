import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
    static int[] unions;

    static public class Edge implements Comparable<Edge>{
        int cost;
        int from;
        int to;

        Edge(int cost, int from, int to) {
            this.cost = cost;
            this.from = from;
            this.to = to;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        unions = new int[N];
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            unions[i] = i;
        }
        for(int i = 0; i < M; i++) {
            

            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());

            pq.add(new Edge(cost, from, to));
        }

        int sum = 0;
        while(!pq.isEmpty()) {
            Edge curEdge = pq.poll();
            if(cnt == N-1) break;
            if(find(curEdge.from) != find(curEdge.to)) {
                union(curEdge.from,curEdge.to);
                sum += curEdge.cost;
                cnt++;
            }
        }

        System.out.println(sum);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a > b) {
            unions[a] = b;
        } else {
            unions[b] = a;
        }
    }

    private static int find(int x) {
        if (unions[x] == x)
            return x;
        else
            return unions[x] = find(unions[x]);
    }
}
