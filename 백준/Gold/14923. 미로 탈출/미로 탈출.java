import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;


public class Main {
    static int N, M;
    static int[][] maze;
    static boolean[][][] visited;
    static int[] drs = {1, -1, 0, 0};
    static int[] dcs = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        maze = new int[N][M];
        visited = new boolean[N][M][2];

        int sr, sc,er,ec;
        st = new StringTokenizer(br.readLine());

        sr = Integer.parseInt(st.nextToken()) - 1;
        sc = Integer.parseInt(st.nextToken()) - 1;

        st = new StringTokenizer(br.readLine());

        er = Integer.parseInt(st.nextToken()) - 1;
        ec = Integer.parseInt(st.nextToken()) - 1;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                maze[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(findShortestPath(sr, sc, er, ec));

    }

    static private int findShortestPath(int r, int c, int er, int ec) {
        ArrayDeque<Node> q = new ArrayDeque<>();
        Node start = new Node(r, c, 0,1);

        q.add(start);

        while(!q.isEmpty()) {
            Node curNode = q.poll();
            int cr = curNode.r;
            int cc = curNode.c;
            int cCnt = curNode.cnt;

            for(int i = 0; i < 4; i++) {
                int nr = cr + drs[i];
                int nc = cc + dcs[i];
                int nCnt = cCnt + 1;

                //부수고 이동 가능한 경우
                if(canMakePathAndMove(nr,nc,nCnt, curNode.skill)) {
                    visited[nr][nc][curNode.skill] = true;
                    q.add(new Node(nr, nc, nCnt, 0));
                    continue;
                }

                if (canMove(nr, nc, nCnt,curNode.skill)) {
                    visited[nr][nc][curNode.skill] = true;
                    if(nr == er && nc == ec) {
                        //도착
                        return nCnt;
                    }
                    q.add(new Node(nr, nc, nCnt, curNode.skill));
                }

            }
        }

        return -1;
    }

    static private boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

    static private boolean canMove(int r, int c, int cnt,int skill) {
        if(!inRange(r,c)) return false;
        if(visited[r][c][skill]) return false;
        if(maze[r][c] == 1) return false;
        return true;
    }

    static private boolean canMakePathAndMove(int r, int c, int cnt ,int skill) {
        if(skill == 0) return false;
        if(!inRange(r,c)) return false;
        if(maze[r][c] == 0) return false;
        if(visited[r][c][skill]) return false;

        return true;
    }

    static class Node {
        int r;
        int c;
        int cnt;
        int skill;

        public Node(int r, int c, int cnt, int skill) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.skill = skill;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "r=" + r +
                    ", c=" + c +
                    ", cnt=" + cnt +
                    ", skill=" + skill +
                    '}';
        }
    }
 }