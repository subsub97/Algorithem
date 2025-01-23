import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N,M,R;
    static int[][] grid;
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        
        grid = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }


//        turn(0,3,0,3); // 왼상 왼 우끝
        int maxSize = (M + N) * 2;
        int r1 = -1;
        int c1 = M;
        int r2 = -1;
        int c2 = N;

        while(r1 < c1 && r2 < c2) {
            r1++;
            c1--;
            r2++;
            c2--;
            int mod = ((c1 + 1 -r1) + (c2 + 1 - r1)) * 2  - 4;
            if(mod == 0) break;
            int newR = R % mod;

            if(!(r1 < c1 && r2 < c2)) break;
            for (int i = 0; i < newR; i++) {
                turn(r1, c1, r2, c2);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void turn(int s1, int e1, int s2, int e2) {
        int temp = grid[s1][s2];

        for(int i = s1; i < e1; i++) {
            grid[s1][i] = grid[s1][i + 1];
        }

        for(int i = s1; i < e2 ; i++) {
            grid[i][e1] = grid[i + 1][e1];
        }

        for(int i = e1;  i > s2; i--) {
            grid[e2][i] = grid[e2][i - 1];
        }

        for(int i = e2; i > s1; i--) {
            grid[i][s2] = grid[i-1][s2];
        }

        grid[s1 + 1][s2] = temp;
    }
}
