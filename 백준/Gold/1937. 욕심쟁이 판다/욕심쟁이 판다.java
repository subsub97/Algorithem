import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] grid;
    static int[][] dp;
    static PriorityQueue<Node> pq = new PriorityQueue<>();

    static class Node implements Comparable<Node>{
        int r;
        int c;
        int cost;

        public Node(int r, int c, int cost) {
            this.r = r;
            this.c = c;
            this.cost =cost;
        }

        @Override
        public int compareTo(Node n) {
            return this.cost - n.cost;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        grid = new int[N][N];
        dp = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                pq.add(new Node(i, j, grid[i][j]));
            }
        }

        int max = 0;
        int[] drs = {1, -1, 0, 0};
        int[] dcs = {0, 0, 1, -1};

        while(!pq.isEmpty()){
            Node cur = pq.poll();
            int curValue = grid[cur.r][cur.c];

            for(int i =0; i < 4; i++) {
                int nr = cur.r + drs[i];
                int nc = cur.c + dcs[i];

                if(inRange(nr,nc) && curValue < grid[nr][nc]) {
                    dp[nr][nc] = Math.max(dp[nr][nc], dp[cur.r][cur.c] + 1);
                    max = Math.max(max, dp[nr][nc]);
                }
            }
        }

        System.out.println(max + 1);

    }

    private static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}