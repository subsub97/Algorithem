import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.StringTokenizer;

public class Main {
    //0 윗면, 1 아랫면, 2 앞면, 3 뒷면 4,왼쪽, 5 오른쪽
    static char[][][] grid3d;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        init();

        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < T; t++) {
            init();

            int cnt = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < cnt; i++) {
                String order = st.nextToken();
                char a = order.charAt(0);
                char b = order.charAt(1);

                if(a == 'U'){
                    if(b=='+'){
                        rotate(0);
                        turn(0);
                    }else {
                        for (int j = 0; j < 3; j++) {
                            rotate(0);
                        }
                        reverseTurn(0);
                    }
                }
                if(a == 'D'){
                    if(b=='+'){
                        rotate(1);
                        turn(1);
                    }else {
                        for (int j = 0; j < 3; j++) {
                            rotate(1);
                        }
                        reverseTurn(1);
                    }
                }
                if(a =='F') {
                    if(b=='+'){
                        rotate(2);
                        turn(2);
                    }else {
                        for (int j = 0; j < 3; j++) {
                            rotate(2);
                        }
                        reverseTurn(2);
                    }
                }
                if(a =='B') {
                    if(b=='+'){
                        rotate(3);
                        turn(3);
                    }else {
                        for (int j = 0; j < 3; j++) {
                            rotate(3);
                        }
                        reverseTurn(3);
                    }
                }
                if(a =='L') {
                    if(b=='+'){
                        rotate(4);
                        turn(4);
                    }else {
                        for (int j = 0; j < 3; j++) {
                            rotate(4);
                        }
                        reverseTurn(4);
                    }
                }
                if(a =='R'){
                    if(b=='+'){
                        rotate(5);
                        turn(5);
                    }else {
                        for (int j = 0; j < 3; j++) {
                            rotate(5);
                        }
                        reverseTurn(5);
                    }
                }
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    sb.append(grid3d[0][i][j]);
                }
                sb.append("\n");
            }

        }
        System.out.println(sb);





    }

    private static void init() {
        grid3d = new char[6][3][3];
        char color;
        // 1 w , 2 : y , 3: r, 4: o ,5: g ,6:b
        for (int dir = 0; dir < 6; dir++) {
            if (dir == 0)
                color = 'w';
            else if (dir == 1)
                color = 'y';
            else if (dir == 2)
                color = 'r';
            else if (dir == 3)
                color = 'o';
            else if (dir == 4)
                color = 'g';
            else
                color = 'b';
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    grid3d[dir][i][j] = color;
                }
            }
        }
    }

    private static void rotate(int dir) {
        char[][] temp = new char[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                temp[j][i] = grid3d[dir][2 - i][j];
            }
        }

        copyArr(dir, temp);
    }

    private static void copyArr(int dir, char[][] temp) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid3d[dir][i][j] = temp[i][j];
            }
        }
    }

    //0 윗면, 1 아랫면, 2 앞면, 3 뒷면 4,왼쪽, 5 오른쪽

    // +는 시계방향 , - 는 반시계 방향
    // 어디 방향에서 진행하는지 중요

    private static void turn(int dir) {
        char[] temp = new char[3];

        if(dir == 0) {
            // 윗면을 바라보고 돌린다.
            // 바닥은 그대로
            //왼 -> 뒷 -> 오 -> 앞
            for (int i = 0; i < 3; i++) {
                temp[i] = grid3d[2][0][i];
            }

            for (int i = 0; i < 3; i++) {
                grid3d[2][0][i] = grid3d[5][0][i];
            }
            for (int i = 0; i < 3; i++) {
                grid3d[5][0][i] = grid3d[3][0][i];
            }
            for (int i = 0; i < 3; i++) {
                grid3d[3][0][i] = grid3d[4][0][i];
            }
            for (int i = 0; i < 3; i++) {
                grid3d[4][0][i] = temp[i];
            }
        } else if (dir == 1) {
            // 아랫면을 바라보고 돌리는 경우
            // 윗면은 그대로
            for (int i = 0; i < 3; i++) {
                temp[i] = grid3d[2][2][2-i];
            }

            for (int i = 0; i < 3; i++) {
                grid3d[2][2][2-i] = grid3d[4][2][2-i];
            }
            for (int i = 0; i < 3; i++) {
                grid3d[4][2][2-i] = grid3d[3][2][2-i];
            }
            for (int i = 0; i < 3; i++) {
                grid3d[3][2][2-i] = grid3d[5][2][2-i];
            }
            for (int i = 0; i < 3; i++) {
                grid3d[5][2][2-i] = temp[i];
            }
        } else if (dir == 2) {
            // 앞
            // 앞 뒷면은 그대로
            for (int i = 0; i < 3; i++) {
                temp[i] = grid3d[0][2][i];
            }

            for (int i = 0; i < 3; i++) {
                grid3d[0][2][i]  = grid3d[4][2-i][2];
            }

            for (int i = 0; i < 3; i++) {
                grid3d[4][2-i][2]  = grid3d[1][2][i];
            }

            for (int i = 0; i < 3; i++) {
                grid3d[1][2][i] = grid3d[5][i][0];
            }
            for (int i = 0; i < 3; i++) {
                grid3d[5][i][0] = temp[i];
            }
        } else if (dir == 3) {
            // 뒷
            // 앞 뒤 그래도
            // 왼 오 바뀜

            for (int i = 0; i < 3; i++) {
                temp[i] = grid3d[0][0][i];
            }

            for (int i = 0; i < 3; i++) {
                grid3d[0][0][i]  = grid3d[5][i][2];
            }

            for (int i = 0; i < 3; i++) {
                grid3d[5][i][2]  = grid3d[1][0][i];
            }

            for (int i = 0; i < 3; i++) {
                grid3d[1][0][i] = grid3d[4][2-i][0];
            }
            for (int i = 0; i < 3; i++) {
                grid3d[4][2-i][0] = temp[i];
            }
        } else if (dir == 4) {
            // 왼쪽
            //뒷 -> 윗 -> 앞 -> 바 -> 뒷
            for (int i = 0; i < 3; i++) {
                temp[i] = grid3d[0][i][0];
            }

            for (int i = 0; i < 3; i++) {
                grid3d[0][i][0] = grid3d[3][2-i][2];
            }

            for (int i = 0; i < 3; i++) {
                grid3d[3][2-i][2] = grid3d[1][2-i][2];
            }

            for (int i = 0; i < 3; i++) {
                grid3d[1][2-i][2] = grid3d[2][i][0];
            }

            for (int i = 0; i < 3; i++) {
                grid3d[2][i][0] = temp[i];
            }
        }
        else {
            // 오른쪽
            //윗 -> 뒷 -> 바 -> 앞 -> 윗
            for (int i = 0; i < 3; i++) {
                temp[i] = grid3d[0][i][2];
            }

            for (int i = 0; i < 3; i++) {
                grid3d[0][i][2] = grid3d[2][i][2];
            }

            for (int i = 0; i < 3; i++) {
                grid3d[2][i][2] = grid3d[1][2-i][0];
            }

            for (int i = 0; i < 3; i++) {
                grid3d[1][2-i][0] = grid3d[3][2 -  i][0];
            }

            for (int i = 0; i < 3; i++) {
                grid3d[3][2 - i][0] = temp[i];
            }
        }
    }

    //0 윗면, 1 아랫면, 2 앞면, 3 뒷면 4,왼쪽, 5 오른쪽
    private static void reverseTurn(int dir) {
        char[] temp = new char[3];

        if(dir == 0) {
            // 윗
            // 뒷 -> 왼 -> 앞 -> 오
            for (int i = 0; i < 3; i++) {
                temp[i] = grid3d[2][0][i];
            }

            for (int i = 0; i < 3; i++) {
                //앞을 왼쪽으로 채움
                grid3d[2][0][i] = grid3d[4][0][i];
            }

            for (int i = 0; i < 3; i++) {
                //왼쪽을 뒷면으로 채움
                grid3d[4][0][i] = grid3d[3][0][i];
            }

            for (int i = 0; i < 3; i++) {
                grid3d[3][0][i] = grid3d[5][0][i];
            }

            for (int i = 0; i < 3; i++) {
                grid3d[5][0][i] = temp[i];
            }
        } else if (dir == 2) {
            //앞
            for (int i = 0; i < 3; i++) {
                temp[i] = grid3d[4][2-i][2];
            }

            for (int i = 0; i < 3; i++) {
                grid3d[4][2 - i][2] = grid3d[0][2][i];
            }

            for (int i = 0; i < 3; i++) {
                grid3d[0][2][i] = grid3d[5][i][0];
            }
            for (int i = 0; i < 3; i++) {
                grid3d[5][i][0] = grid3d[1][2][i];
            }

            for (int i = 0; i < 3; i++) {
                grid3d[1][2][i] = temp[i];
            }
        } else if (dir == 1) {
            //바닥
            // 왼 -> 뒷 -> 오 -> 앞
            for (int i = 0; i < 3; i++) {
                temp[i] = grid3d[4][2][2-i];
            }

            for (int i = 0; i < 3; i++) {
                grid3d[4][2][2 - i] = grid3d[2][2][2 - i];
            }

            for (int i = 0; i < 3; i++) {
                grid3d[2][2][2 - i] = grid3d[5][2][2-i];
            }

            for (int i = 0; i < 3; i++) {
                grid3d[5][2][2-i] = grid3d[3][2][2-i];
            }

            for (int i = 0; i < 3; i++) {
                grid3d[3][2][2-i] = temp[i];
            }

        } else if (dir == 3) {
            for (int i = 0; i < 3; i++) {
                temp[i] = grid3d[4][2-i][0];
            }
            for (int i = 0; i < 3; i++) {
                grid3d[4][2-i][0] = grid3d[1][0][i];
            }

            for (int i = 0; i < 3; i++) {
                grid3d[1][0][i] = grid3d[5][i][2];
            }

            for (int i = 0; i < 3; i++) {
                grid3d[5][i][2] = grid3d[0][0][i];
            }

            for (int i = 0; i < 3; i++) {
                grid3d[0][0][i] = temp[i];
            }

        } else if(dir == 4) {
            // 왼쪽, 오른쪽은 안바뀜
            for(int i = 0; i < 3; i++) {
                temp[i] = grid3d[0][i][0];
            }

            for(int i = 0; i < 3; i++) {
                grid3d[0][i][0] = grid3d[2][i][0];
            }

            for(int i = 0; i < 3; i++) {
                grid3d[2][i][0] = grid3d[1][2-i][2];
            }

            for(int i = 0; i < 3; i++) {
                grid3d[1][2-i][2] = grid3d[3][2-i][2];
            }

            for(int i = 0; i < 3; i++) {
                grid3d[3][2-i][2] = temp[i];
            }
        }
        else{
            for (int i = 0; i < 3; i++) {
                temp[i] = grid3d[0][2-i][2];
            }

            for (int i = 0; i < 3; i++) {
                grid3d[0][2-i][2] = grid3d[3][i][0];
            }

            for (int i = 0; i < 3; i++) {
                grid3d[3][i][0] = grid3d[1][i][0];
            }

            for (int i = 0; i < 3; i++) {
                grid3d[1][i][0] = grid3d[2][2-i][2];
            }

            for (int i = 0; i < 3; i++) {
                grid3d[2][2-i][2] = temp[i];
            }
        }
    }
    //0 윗면, 1 아랫면, 2 앞면, 3 뒷면 4,왼쪽, 5 오른쪽
}