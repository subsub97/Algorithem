import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[][] grid;
    static ArrayDeque<Node> q = new ArrayDeque<>();
    static int[] drs = {1, -1, 0, 0};
    static int[] dcs = {0, 0, -1, 1};
    static int dr, dc;
    static Pair[] beads = new Pair[2];
    static boolean[][][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        grid = new int[N][M];
        visited = new boolean[N][M][N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = line.charAt(j);
                if (c == '#') {
                    grid[i][j] = -1;
                } else if (c == '.') {
                    grid[i][j] = 0;
                } else if (c == 'O') {
                    dr = i;
                    dc = j;
                } else if (c == 'B') {
                    grid[i][j] = 0;
                    beads[0] = new Pair(i, j);
                } else {
                    grid[i][j] = 0;
                    beads[1] = new Pair(i, j);
                }
            }
        }

        visited[beads[1].r][beads[1].c][beads[0].r][beads[0].c] = true;
        q.add(new Node(1, beads));
        boolean breakPoint = false;
        int ans = 0;

        while (!q.isEmpty() && !breakPoint) {
            Node currentNode = q.poll();

            for (int i = 0; i < 4; i++) {
                Pair[] curPairs = new Pair[2];
                for (int j = 0; j < currentNode.beads.length; j++) {
                    curPairs[j] = new Pair(currentNode.beads[j].r, currentNode.beads[j].c);
                }

                int fBead = getFirstBead(i, curPairs);
                int sBead = (fBead + 1) % 2;
                beads[fBead] = curPairs[fBead];
                beads[sBead] = curPairs[sBead];
                boolean result1 = moveBead(i, curPairs[fBead], fBead);
                boolean result2 = moveBead(i, curPairs[sBead], sBead);

                if (result1 && result2) continue;
                else if (result1 && fBead == 1 || result2 && sBead == 1) {
                    breakPoint = true;
                    ans = 1;
                    break;
                } else if (result1 && fBead == 0 || result2 && sBead == 0) continue;
                else {
                    if (currentNode.cnt + 1 > 10) continue;
                    int r1 = curPairs[1].r, c1 = curPairs[1].c;
                    int r2 = curPairs[0].r, c2 = curPairs[0].c;
                    if (!visited[r1][c1][r2][c2]) {
                        visited[r1][c1][r2][c2] = true;
                        q.add(new Node(currentNode.cnt + 1, curPairs));
                    }
                }
            }
        }
        System.out.println(ans);
    }

    static private boolean moveBead(int dir, Pair bead, int num) {
        int r = bead.r;
        int c = bead.c;

        while (true) {
            r = r + drs[dir];
            c = c + dcs[dir];

            if (!canMove(r, c, num)) return false;

            bead.r = r;
            bead.c = c;
            if (r == dr && c == dc) {
                bead.r = -1;
                bead.c = -1;
                return true;
            }
        }
    }

    static private boolean canMove(int r, int c, int num) {
        if (grid[r][c] == -1) return false;
        if (num == 0) {
            if (r == beads[1].r && c == beads[1].c) return false;
        }
        if (num == 1) {
            if (r == beads[0].r && c == beads[0].c) return false;
        }
        return true;
    }

    static private int getFirstBead(int dir, Pair[] pairs) {
        if (dir == 0) {
            if (pairs[0].r > pairs[1].r) return 0;
            return 1;
        }
        if (dir == 1) {
            if (pairs[0].r > pairs[1].r) return 1;
            return 0;
        }
        if (dir == 2) {
            if (pairs[0].c > pairs[1].c) return 1;
            return 0;
        }
        if (dir == 3) {
            if (pairs[0].c > pairs[1].c) return 0;
            return 1;
        }
        return 2;
    }

    static class Node {
        int cnt;
        Pair[] beads = new Pair[2];

        Node(int cnt, Pair[] beads) {
            this.cnt = cnt;
            for (int i = 0; i < beads.length; i++) {
                this.beads[i] = new Pair(beads[i].r, beads[i].c);
            }
        }
    }

    static class Pair {
        int r;
        int c;

        Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
