import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N,M;
    static int[][] grid;
    static int[][] counts;
    static boolean[][] visited;

    static int[] drs = {0, 1, 0, -1};
    static int[] dcs = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        grid = new int[N][M];
        counts = new int[N][M];
        visited = new boolean[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0);

        System.out.println(counts[0][0]);


    }

    static private boolean inRange(int r, int c) {
        return r >= 0 &&  r < N && c >= 0 && c < M;
    }

    static private boolean canMove(int r, int c, int preScore) {
        if(!inRange(r, c)) return false;
        if(preScore <= grid[r][c]) return false;
        if(visited[r][c] && counts[r][c] == 0) return false;
        return true;
    }

    static private boolean dfs(int r, int c) {
        if(r == N - 1 && c == M - 1) {
            counts[r][c] = 1;
            return true;
        }

        visited[r][c] = true;

        int curScore = grid[r][c];
        int pathCnt = 0;
        boolean result = false;

        for(int i = 0; i < 4; i++) {
            int nr = r + drs[i];
            int nc = c + dcs[i];

            if (canMove(nr, nc, curScore) ) {
                // 만약 방문했던 이력이 있는 경우
                if (counts[nr][nc] != 0) {
                    pathCnt += counts[nr][nc];
                    result = true;
                } else {
                    if (dfs(nr, nc)) {
                        result = true;
                        pathCnt += counts[nr][nc];
                    }
                }
            }
        }
        counts[r][c] = pathCnt;
        return result;
    }
}

