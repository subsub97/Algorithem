import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N,M,H;
    static boolean[][] board;
    static boolean isEnd = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new boolean[M][N + 1];

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());

            board[r][c] = true;
        }

        // 내려보고 불가능하면 하나 설치하기
        if (down()) {
            isEnd = true;
            System.out.println("0");
        }
        else{
            //설치하고 down을 반복
            for(int i = 1; i < 4; i++) {
                back(0,1,0,i);
            }
        }
        if(!isEnd){
            System.out.println(-1);
        }
    }

    public static void back(int r, int c, int level, int max) {
        if(isEnd) return;
        if(level == max )  {
            if(down()) {
                // 내려가면 끝냄
                isEnd = true;
                System.out.println(max);
            }
            return;
        }

        for(int i = r; i < M; i++) {
            if(i > r) c = 1;
            for(int j = c; j <  N; j++) {
                if(canLet(i,j)) {
                    board[i][j] = true;
                    back(i, j, level + 1, max);
                    board[i][j] = false;
                }
            }
        }
    }

    public static boolean canLet(int r, int c) {
//            if(board[r][c]) return false;
//            if(c == 1) {
//                if(board[r][c + 1]) return false;
//            }
//
//            else if(c == N - 1) {
//                if(board[r][c-1]) return false;
//            }

            if(board[r][c-1]) return false;
            if(board[r][c]) return false;
            if(board[r][c + 1]) return false;

        //자기랑 동일한 열은 오른쪽으로   // 작은거는 왼쪽으로
        return true;
    }

    public static boolean down() {
        for(int i = 1; i <= N; i++) {
            // 0번 레일부터 내려가기
            int curH = 0;
            int curC = i;

            while(curH < M) {
                if(curC == 1) {
                    // 왼쪽은 볼 필요 없음
                    if(board[curH][curC]) curC++;
                }

                else if(curC == N) {
                    //오른쪽은 볼 필요 없음
                    if(board[curH][curC - 1]) curC--;
                }

                else {
                    if(board[curH][curC]) {
                        curC++;
                        curH++;
                        continue;
                    }
                    if(board[curH][curC - 1]) curC--;
                }
                curH++;
            }
            if(curC != i) return false;
        }
        return true;
    }
}
