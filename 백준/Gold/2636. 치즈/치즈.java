import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int M;
    static int[][] grid;
    static boolean[][] visited;
    static int ans = 100000;
    static Queue<Pair> q = new LinkedList<Pair>();

    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        grid = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int time =0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                answer = 0;
                visited = new boolean[N][M];
                q.add(new Pair(0,0));
                bfs();
                if(answer == 0) break;
                if(answer != 0)
                    time++;
                    ans = answer;
            }
            if(answer == 0) break;
        }
        System.out.println(time);
        System.out.println(ans);
    }

    public static void bfs() {
        int[] drs = {1, -1, 0, 0};
        int[] dcs = {0, 0, 1, -1};

        while (!q.isEmpty()) {

            Pair p = q.poll();

            for (int i = 0; i < 4; i++) {
                int nR = p.r + drs[i];
                int nC = p.c + dcs[i];

                if(canMelt(nR,nC)) {
                    answer++;
                    grid[nR][nC] = grid[nR][nC] -1;
                    visited[nR][nC] = true;
                    continue;
                }

                if (canMove(nR,nC)) {
                    visited[nR][nC] = true;
                    q.add(new Pair(nR,nC));
                }

            }
        }

    }


    public static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

    public static boolean canMelt(int r, int c) {
        if(!inRange(r,c)) return false;
        if(visited[r][c]) return false;
        if(grid[r][c] == 0) return false;
        return true;
    }

    public static boolean canMove(int r, int c) {
        if(!inRange(r,c)) return false;
        if(visited[r][c]) return false;
        if(grid[r][c] == 1) return false;
        return true;
    }

    static class Pair {
        int r;
        int c;

        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }


}
