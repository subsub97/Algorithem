import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int time,N,M;
    static int[][] grid;
    static int[][] airMap;
    static boolean[][] visited;
    static Queue<Pair> cheezes = new LinkedList<>();
    static Queue<Pair> temp = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        grid = new int[N][M];
        airMap = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                if(grid[i][j] == 1) {
                    //치즈가 위치 한 곳
                    cheezes.add(new Pair(i, j));
                }
            }
        }

        simulate();

        System.out.println(time);
    }

    public static void simulate() {

        while (!cheezes.isEmpty()) {
            visited = new boolean[N][M];
            // 외부공기 체크
            dfs(0,0);

            int size = cheezes.size();

            for (int i = 0; i < size; i++) {
                //치즈를 꺼낸다.
                Pair pair = cheezes.poll();

                if (canMelt(pair.row, pair.col)) {
                    //녹는 경우
                    grid[pair.row][pair.col] = 0;
                    continue;
                }

                cheezes.add(new Pair(pair.row, pair.col));
            }
            time++;
        }
    }

    public static boolean canMelt(int r, int c) {
        int[] drs = {-1, 0, 1, 0};
        int[] dcs = {0, -1, 0, 1};

        int cnt =0;

        for (int i = 0; i < 4; i++) {
            int nextR = r + drs[i];
            int nextC = c + dcs[i];

            if(inRange(nextR,nextC)) {
                if(airMap[nextR][nextC] == 2) {
                    // 외부 공기와 접촉하는 경우
                    cnt++;
                }
            }
        }

        if(cnt < 2) return false;
        return true;

    }

    public static void dfs(int r, int c) {
        airMap[r][c] = 2; //외부 공기
        visited[r][c] = true;

        int[] drs = {-1, 0, 1, 0};
        int[] dcs = {0, -1, 0, 1};


        for (int i = 0; i < 4; i++) {
            int nextR = r + drs[i];
            int nextC = c + dcs[i];
            if(isCheckAir(nextR,nextC)) dfs(nextR, nextC);

        }
    }

    public static boolean isCheckAir(int r, int c) {
        if(!inRange(r,c)) return false;
        if(grid[r][c] == 1) return false;
        if(visited[r][c]) return false;
        return true;
    }

    public static boolean inRange(int r, int c) {
        return r >= 0 && r < N &&  c >= 0 && c < M;
    }

    static class Pair {
        int row;
        int col;

        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
