import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int T;
    static int ans, total;
    static int[] dist;

    static class Edge {
        int a;
        int b;
        int cost;

        public Edge(int a, int b, int cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }
    }

    static List<Edge>[] edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());


        for (int t  = 0; t < T; t++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken()) - 1; // 해킹 당하는 컴퓨터

            dist = new int[n];

            for (int i = 0; i < n; i++) {
                dist[i] = (int) 1e9;
            }

            edges = new ArrayList[n];

            for (int i = 0; i < n; i++) {
                edges[i] = new ArrayList<>();
            }

            // 의존성 개수
            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;
                int cost = Integer.parseInt(st.nextToken());
                
                edges[b].add(new Edge(b, a, cost));
            }

            di(c);
            int max = 0;
            ans = 0;
            for (int i = 0; i < n; i++) {
                if(dist[i] == (int) (1e9)) continue;
                ans++;
                max = Math.max(max, dist[i]);
            }

            System.out.println(ans +" "+max);
        }
    }
    static class Node {
        int idx;
        int cost;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }

    private static void di(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>(){
            @Override
            public int compare(Node n1, Node n2) {
                return Integer.compare(n1.cost, n2.cost);
            }
        });

        dist[start] = 0;

        // 시작점과 연결된 간선을 모두 넣는다.
        pq.add(new Node(start, 0));

        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            // 지금꺼낸 Node가 최신이 맞는지 체크
            if(cur.cost != dist[cur.idx]) continue;

            //이미 dist 저장된 값, 지금 갱신할 값 구하기
            for (Edge edge : edges[cur.idx]) {
                int next = edge.b;
                int nextCost = edge.cost + dist[cur.idx];

                if(nextCost < dist[next]) {
                    dist[next] = nextCost;
                    pq.add(new Node(next, nextCost));
                }
            }
        }
    }


}