import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static class Pair {
        int r;
        int c;

        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static class Dice {
        int top;
        int bottom;
        int front;
        int back;
        int left;
        int right;
        int dir;
        Pair pos  = new Pair(0, 0);

        public Dice(int top, int bottom, int front, int back, int left, int right, int dir) {
            this.top = top;
            this.bottom = bottom;
            this.front = front;
            this.back = back;
            this.left = left;
            this.right = right;
            this.dir = dir;
        }

        public void roll() {
            if(dir == 0) {
                moveE();
            }
            else if(dir == 1) {
                moveS();
            }
            else if(dir == 2){
                moveW();
            }
            else{
                moveN();
            }
        }

        public void moveE() {
            int temp = this.bottom;

            bottom = right;
            right = top;
            top = left;
            left = temp;
        }

        public void moveW() {
            int temp = this.bottom;

            bottom = left;
            left = top;
            top = right;
            right = temp;
        }

        public void moveS() {
            int temp = bottom;

            bottom = front;
            front = top;
            top = back;
            back = temp;
        }

        public void moveN() {
            int temp = bottom;

            bottom = back;
            back = top;
            top = front;
            front = temp;
        }
    }

    static int N,M,K;
    static int[][] grid;
    static int[] drs = {0, 1, 0, -1};
    static int[] dcs = {1, 0, -1, 0};
    static Dice dice;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        grid = new int[N][K];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < K; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int sum = 0;
        dice = new Dice(1, 6, 5, 2, 4, 3, 0);

        for (int i = 0; i < M; i++) {

            int nr = dice.pos.r + drs[dice.dir];
            int nc = dice.pos.c + dcs[dice.dir];

            // 격자를 벗어나는 경우
            if(!inRange(nr,nc)) {
                dice.dir = (dice.dir + 2) % 4;

                nr = dice.pos.r + drs[dice.dir];;
                nc = dice.pos.c + dcs[dice.dir];;
            }
            dice.roll();

            dice.pos.r = nr;
            dice.pos.c = nc;
            dice.dir = getDir(nr, nc);
            sum += calcScore(nr, nc);

        }
        System.out.println(sum);

    }

    // bfs로 인접한 번호 찾기
    private static int calcScore(int r, int c) {
        ArrayDeque<Pair> q = new ArrayDeque<>();
        int num = grid[r][c];

        boolean[][] vis = new boolean[N][K];

        q.add(new Pair(r, c));
        vis[r][c] = true;
        int cnt = 1;

        while (!q.isEmpty()) {
            Pair p = q.poll();
            r = p.r;
            c = p.c;

            for(int i = 0; i < 4; i++) {
                int nr = r + drs[i];
                int nc = c + dcs[i];

                if(inRange(nr,nc) && grid[nr][nc] == num && !vis[nr][nc]) {
                    q.add(new Pair (nr,nc));
                    vis[nr][nc] = true;
                    cnt++;
                }
            }
        }

        return cnt * num;
    }

    private static int getDir(int r, int c) {
        int num = grid[r][c];

        if(num < dice.bottom) {
            dice.dir = (dice.dir + 1) % 4;
        }
        else if(num == dice.bottom) {
            dice.dir = dice.dir;
        }
        else{
            dice.dir = (dice.dir + 3) % 4;

        }
        return dice.dir;
    }

    private static boolean inRange(int r, int c) {
        return r >= 0 && r  < N && c >= 0 && c < K;
    }

    // 바닥 번호 체크하고 방향 바꾸기
}
