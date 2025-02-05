import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] grid = new int[19][19];
    static int status = 0;
    static int[] drs = {1, 0, -1,1, -1, 1, 0 , -1};
    static int[] dcs = {-1,-1, -1,0, 0, 1, 1, 1};
    static int ansR=30;
    static int ansC=30;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 0; i < 19; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 19; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {
                if(grid[i][j] == 0) continue;
                for(int dir = 0; dir < 8; dir++) {
                    canWin(i, j, dir);
                }
            }
        }

        if(status == 0) {
            System.out.println(status);
        }
        else{
            System.out.println(status);
            System.out.println((ansR +1) +" " +(ansC+1));
        }
    }

    public static boolean canWin(int r, int c, int dir) {
        int curStone = grid[r][c];
        if(inRange(r - drs[dir],c - dcs[dir])) {
            if (grid[r][c] == grid[r - drs[dir]][c - dcs[dir]]) {
                return false;
            }
        }

        for(int i = 0; i < 5; i++ ) {
            int nR = r + drs[dir];
            int nC = c + dcs[dir];

            if(i == 4) {
                if(!inRange(nR,nC) || !isSame(nR,nC, curStone)) {
                    if(ansC > c) {
                        ansR = r;
                        ansC = c;
                        status = curStone;
                    }
                    if(ansC == c) {
                        if(ansR > r) {
                            ansR = r;
                        }
                    }
                    return true;
                }
                return false;
            }
            if(inRange(nR,nC) && isSame(nR,nC,curStone)) {
                r = nR;
                c = nC;
            }
            else{
                return false;
            }
        }
        return false;
    }

    public static boolean isSame(int r, int c, int stone) {
        return stone == grid[r][c];
    }

    public static boolean inRange(int r, int c) {
        return r >= 0 && r < 19 && c >= 0 && c < 19;
    }
}
