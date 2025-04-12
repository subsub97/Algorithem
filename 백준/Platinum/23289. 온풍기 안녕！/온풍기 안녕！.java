import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int[][] grid;
    static boolean[][][] walls;
    static int[][] cache;
    static boolean[][] visit;

    static int R;
    static int C;
    static int K;

    static class Node {
        int x;
        int y;
        int dir;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }


        public Node(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    static ArrayList<Node> kNode = new ArrayList<>();
    static ArrayList<Node> heater = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        grid = new int[R][C];
        cache = new int[R][C];
        walls = new boolean[5][R + 1][C + 1];

        for(int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j  = 0; j < C; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                if(grid[i][j] == 5) {
                    grid[i][j] = 0;
                    kNode.add(new Node(i, j));
                }
                else if(grid[i][j] > 0) {
                    heater.add(new Node(i, j, grid[i][j]));
                    grid[i][j] = 0;
                }
            }
        }

        int wallCnt = Integer.parseInt(br.readLine());
        int answer = 0;

        for(int i = 0; i < wallCnt; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());

            if(dir == 1) {
                walls[1][x-1][y] = true;
                walls[2][x-1][y - 1] = true;
                continue;
            }

            walls[3][x - 2][y-1] = true;
            walls[4][x - 1][y-1] = true;
        }
        while(true) {
            blow();

            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    tempControl(i, j);
                }
            }

            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    grid[i][j] += cache[i][j];
                    cache[i][j] = 0;
                }
            }
            coolDown();
            answer++;
            if(answer > 100) {
                System.out.println(answer);
                break;
            }
            boolean flag = true;

            for (Node n : kNode) {
                if (grid[n.x][n.y] >= K) continue;
                flag = false;
            }
            if (flag) {
                System.out.println(answer);
                break;
            }
        }

    }

    public static void blow() {
        // 0 패딩 하나랑 오른쪽, 왼쪽 ,위, 아래 방향

        int[] drs = {0, 0, 0, -1, 1};
        int[] dcs = {0, 1, -1, 0, 0};

        for(int i = 0; i < heater.size(); i++) {
            visit = new boolean[R][C];

            Node currentHeater = heater.get(i);
            int curDir = currentHeater.dir;
            int nextR  = currentHeater.x;
            int nextC  = currentHeater.y;

            nextR += drs[curDir];
            nextC += dcs[curDir];

            if(canMove(nextR, nextC, curDir)) {
                grid[nextR][nextC] += 5;
            }

            if(curDir == 1 || curDir == 2) {
                spreadAir(nextR, nextC, curDir, currentHeater.x, 4);
            }
            else{
                spreadAir(nextR, nextC, curDir, currentHeater.y, 4);
            }


        }
    }

    public static void spreadAir(int r, int c, int dir,int center ,int p) {
        visit[r][c] = true;
        if(p <= 0) return;
        // 중간을 제외한 경우에는 위 또는 아래만 확인한다.
        // 중간인 경우는 상 중 하 다 확인한다.
        if(dir == 1) {
                if (canMove(r - 1, c, 3) && canMove(r - 1, c + 1, 1)) {
                    if (!visit[r - 1][c + 1]) {
                        grid[r - 1][c + 1] += p;
                        spreadAir(r - 1, c + 1, dir, center, p - 1);
                    }
                }

                // 중
                if (canMove(r, c + 1 , dir)) {
                    if(!visit[r][c + 1]) {
                        grid[r][c + 1] += p;
                        spreadAir(r, c + 1, dir, center, p - 1);
                    }
                }

                if (canMove(r + 1, c, 4) && canMove(r + 1, c + 1, 1)) {
                    if (!visit[r + 1][c + 1]) {
                        grid[r + 1][c + 1] += p;
                        spreadAir(r + 1, c + 1, dir, center, p - 1);
                    }
                }

        }

        else if(dir == 2) {

                //상,중,하 다 가는 경우
                if (canMove(r - 1, c, 3) && canMove(r - 1, c - 1, 2)) {
                    if (!visit[r - 1][c - 1]) {
                        grid[r - 1][c - 1] += p;
                        spreadAir(r - 1, c - 1, dir, center, p - 1);
                    }
                }

                // 중
                if (canMove(r, c -1 , dir)) {
                    if(!visit[r][c - 1]) {
                        grid[r][c - 1] += p;
                        spreadAir(r, c - 1, dir, center, p - 1);
                    }
                }

                if (canMove(r + 1, c, 4) && canMove(r + 1, c - 1, 2)) {
                    if (!visit[r + 1][c - 1]) {
                        grid[r + 1][c - 1] += p;
                        spreadAir(r + 1, c - 1, dir, center, p - 1);
                    }
                }

        }

        else if(dir == 3) {

                //좌,중,우 다 가는 경우
                if (canMove(r, c - 1, 2) && canMove(r - 1, c - 1, 3)) {
                    if (!visit[r - 1][c - 1]) {
                        grid[r - 1][c - 1] += p;
                        spreadAir(r - 1, c - 1, dir, center, p - 1);
                    }
                }

                // 중
                if (canMove(r - 1 , c, dir)) {
                    if (!visit[r - 1][c]) {
                        grid[r - 1][c] += p;
                        spreadAir(r - 1, c, dir, center, p - 1);
                    }
                }

                if (canMove(r, c + 1, 1) && canMove(r - 1, c + 1, 3)) {
                    if (!visit[r - 1][c + 1]) {
                        grid[r - 1][c + 1] += p;
                        spreadAir(r - 1, c + 1, dir, center, p - 1);
                    }
                }

        }

        else if(dir == 4) {
            //좌,중,우 다 가는 경우
            if (canMove(r, c - 1, 2) && canMove(r + 1, c - 1, 4)) {
                if (!visit[r + 1][c - 1]) {
                    grid[r + 1][c - 1] += p;
                    spreadAir(r + 1, c - 1, dir, center, p - 1);
                }
            }

            // 중
            if (canMove(r+1 , c, dir)) {
                if(!visit[r + 1][c]) {
                    grid[r + 1][c] += p;
                    spreadAir(r + 1, c, dir, center, p - 1);
                }
            }

            if (canMove(r , c + 1 , 1) && canMove(r + 1, c + 1,4)) {
                if(!visit[r + 1][c+1]) {
                    grid[r + 1][c + 1] += p;
                    spreadAir(r + 1, c + 1, dir, center, p - 1);
                }
            }
        }

    }

    public static boolean inRange(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    public static boolean canMove(int r, int c, int dir) {
        if(!inRange(r, c)) return false;
        //벽이 있는 경우 이동 불가
        if(walls[dir][r][c]) return false;


        return true;
    }

    public static void tempControl(int r, int c) {
        //  1: 우 , 2 :좌, 3: 상, 4 : 하
        int[] drs = {0, 0, 0, -1, 1};
        int[] dcs = {0, 1, -1, 0, 0};

        int curTemp = grid[r][c];

        for(int i = 1; i < 5; i++) {
            int nextR = r + drs[i];
            int nextC = c + dcs[i];

            if (canMove(nextR, nextC, i)) {
                //이동 가능한 경우면 온도 비교 가능한 경우임
                if(curTemp > grid[nextR][nextC]) {
                    int gap = (curTemp - grid[nextR][nextC]) / 4;
                    cache[r][c] -= gap;
                    cache[nextR][nextC] += gap;
                }
            }
        }
    }

    public static void coolDown() {
        for(int i = 0; i < R; i++) {
            if(grid[i][0] > 0) grid[i][0]--;
            if(grid[i][C-1] > 0) grid[i][C-1]--;
        }

        for(int i = 1; i < C-1; i++) {
            if(grid[0][i] > 0) grid[0][i]--;
            if(grid[R-1][i] > 0) grid[R-1][i]--;
        }
    }


}
