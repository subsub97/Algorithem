import java.util.*;
import java.io.*;

public class Main {
    static int H,W,ans;
    static int[][] grid;
    static Node[][] nodes;
    static boolean[][] visited;


    static int[] drs = {1, 1, 1, 0, 0, -1, -1, -1};
    static int[] dcs = {1, 0, -1, -1, 1, -1, 0, 1};

    static ArrayDeque<Pair> q = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        grid = new int[H][W];
        nodes = new Node[H][W];
        visited = new boolean[H][W];

        for(int i = 0; i < H; i++ ) {
            String line = br.readLine();
            for(int j = 0; j < W; j++) {
                char a = line.charAt(j);
                if(a == '.') {
                    grid[i][j] = -1;
                }
                else{
                    grid[i][j] = a - '0';

                    if(grid[i][j] != 9) {
                        q.add(new Pair(i, j));
                    }
                }
            }
        }
        int size = q.size();

        for(int i = 0; i < size; i++) {
            Pair p = q.poll();

            if(init(p.r, p.c)){
                visited[p.r][p.c] = true;
                q.add(new Pair(p.r, p.c));
            }
        }

        while(!q.isEmpty()) {
            size = q.size();
            ArrayList<Pair> pairs = new ArrayList<>();

            for(int i = 0; i < size; i++) {
                // 모래성이 무너지는 경우
                Pair p = q.poll();
                pairs.add(p);
            }
            if (pairs.size() > 0)
                ans++;

            for(int i = 0; i < pairs.size(); i++) {
                Pair p = pairs.get(i);

                propagation(p.r, p.c);
            }
        }

        System.out.println(ans);
    }

    static private void propagation(int r, int c) {
        for(int i = 0; i < 8; i++) {
            int nr = r + drs[i];
            int nc = c + dcs[i];


            if(nodes[nr][nc] != null && !visited[nr][nc]) {

                if(nodes[nr][nc].cnt < grid[nr][nc]) {
                    nodes[nr][nc].cnt++;
                    nodes[nr][nc].time = nodes[r][c].time + 1;
                }

                if(nodes[nr][nc].cnt >= grid[nr][nc] && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.add(new Pair(nr, nc));
                }
            }
        }

    }

    static private boolean init(int r, int c) {
        int cnt = 0;

        if(grid[r][c] == -1) return false;

        for(int i = 0; i < 8; i++) {
            int nr = r + drs[i];
            int nc = c + dcs[i];
            if(grid[nr][nc] == -1) cnt++;
        }
        nodes[r][c] = new Node(cnt, 1);

        if(cnt >= grid[r][c]) return true;
        return false;
    }

    static class Pair {
        int r, c;

        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static class Node {
        int cnt;
        int time;

        Node(int cnt, int time) {
            this.cnt = cnt;
            this.time = time;
        }
    }


}

