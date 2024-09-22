import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int M;
    static int N;
    static int H;

    static int[][][] box;
    static boolean[][][] visited;
    static boolean[][][] tempVisited;
    static int visitCount = 0;
    static int answer = 0;

    static Queue<Spot> q = new LinkedList<>();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        box = new int[H][M][N];
        visited = new boolean[H][M][N];

        for (int h = 0; h < H; h++) {
            for(int r = 0; r < M; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    box[h][r][c] = Integer.parseInt(st.nextToken());
                }
            }
        }

        simulate();
        if(answer == 0 && checkZero()) {
            System.out.println(0);
            System.exit(0);
        }
        if(!checkZero()) {
            System.out.println(-1);
        }
        else{
            System.out.println(answer);
        }
    }

    public static boolean checkZero() {
        for (int h = 0; h < H; h++) {
            for (int r = 0; r < M; r++) {
                for (int c = 0; c < N; c++) {
                    if(box[h][r][c] == 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void simulate() {
        initTomato();

        while (!q.isEmpty()) {
            Spot spot = q.poll();
            visited[spot.h][spot.r][spot.c] = true;
            box[spot.h][spot.r][spot.c] = 1;
            spreadTomato(spot.h, spot.r, spot.c, spot.depth);
        }
    }

    public static void initTomato() {
        for (int h = 0; h < H; h++) {
            for (int r = 0; r < M; r++) {
                for (int c = 0; c < N; c++) {
                    if(box[h][r][c] == 1) {
                        visited[h][r][c] = true;
                        q.add(new Spot(h,r,c));
                    }
                }
            }
        }
    }

    public static void spreadTomato(int h, int r, int c,int depth) {
        visited[h][r][c] = true;
        visitCount++;
        answer = Math.max(answer, depth);

        int[] drs = {1, -1, 0, 0, 0, 0};
        int[] dcs = {0, 0, 1, -1, 0, 0};
        int[] dhs = {0, 0, 0, 0, 1, -1};

        for(int dir = 0; dir < 6; dir++) {
            int dh = h + dhs[dir];
            int dr = r + drs[dir];
            int dc = c + dcs[dir];

            if(canAffect(dh, dr, dc)) {
                Spot newSpot = new Spot(dh, dr, dc);
                newSpot.depth = depth + 1;
                q.add(newSpot);
                visited[dh][dr][dc] = true;
            }
        }

    }

    private static boolean canAffect(int h, int r, int c) {
        if(h < 0 || h >= H || r < 0 || r >=  M|| c < 0 || c >= N ) return false;
        if(box[h][r][c] == -1 || box[h][r][c] == 1) return false;
        if(visited[h][r][c]) return false;

        return true;
    }

    static class Spot {
        int h;
        int r;
        int c;
        int depth = 0;

        public Spot(int h,int r,int c) {
            this.h = h;
            this.r = r;
            this.c = c;
        }
    }
}