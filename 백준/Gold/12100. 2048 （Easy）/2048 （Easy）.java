import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] grid;
    static int[] drs = {-1, 0, 1, 0};
    static int[] dcs = {0, -1, 0, 1};
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        grid = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, grid);

        System.out.println(ans);
    }

    /*
       상,하,좌,우로 밀어서 움직이는거 구현
     */

    private static void simulate(int[][] grid) {
        int maxBlock = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                maxBlock = Math.max(maxBlock, grid[i][j]);
            }
        }
        ans = Math.max(ans, maxBlock);
    }

    private static void dfs(int d,int[][] curGrid) {
        if(d == 5) {
            // TODO : max 체크하고 리턴
            simulate(curGrid);
            return;
        }

        int[][] temp = copyGrid(curGrid);

        for(int i = 0 ; i < 4; i++) {
            //상하좌우로 다 보내기
            int[][] result = move(i, temp);
            dfs(d+1, result);
        }
    }

    private static int[][] copyGrid(int[][] grid) {
        int[][] temp = new int[N][N];

        for(int i = 0 ; i < N; i++) {
            for(int j = 0; j < N; j++) {
                temp[i][j] = grid[i][j];
            }
        }

        return temp;
    }

    private static int[][] move(int dir,int[][] origin) {
        // 0상 , 1하  2좌  3우
        int[][] temp = new int[N][N];


        boolean flag = true;
        if(dir == 0) {
            for(int i = 0; i < N; i++) {
                ArrayDeque<Integer>  stack = new ArrayDeque<>();

                for(int j = 0; j < N; j++) {
                    if(origin[j][i] == 0) continue;
                    if(!stack.isEmpty() && stack.peek() == origin[j][i] && flag) {
                        stack.pop();
                        stack.push(origin[j][i] * 2);
                        flag = false;
                        continue;
                    }
                    flag = true;
                    stack.push(origin[j][i]);
                }

                int size = stack.size();

                for(int j = 0; j < size; j++) {
                    temp[j][i] = stack.pollLast();
                }
            }

        }

        else if(dir == 1) {
            for(int i = 0; i < N; i++) {
                ArrayDeque<Integer>  stack = new ArrayDeque<>();

                for(int j = N-1; j >= 0; j--) {
                    if(origin[j][i] == 0) continue;

                    if(!stack.isEmpty() && stack.peek() == origin[j][i] && flag) {
                        stack.pop();
                        stack.push(origin[j][i] * 2);
                        flag = false;
                        continue;
                    }
                    flag = true;
                    stack.push(origin[j][i]);
                }

                int size = stack.size();

                for(int j = N-1; j >= N-size; j--) {
                    temp[j][i] = stack.pollLast();
                }
            }
        }

        else if(dir == 2) {
            // 좌
            for(int i = 0; i < N; i++) {
                ArrayDeque<Integer>  stack = new ArrayDeque<>();

                for(int j = 0; j < N; j++) {
                    int curNum = origin[i][j];

                    if(curNum == 0) continue;
                    if(!stack.isEmpty() && stack.peek() == curNum && flag) {
                        stack.pop();
                        stack.push(curNum * 2);
                        flag = false;
                        continue;
                    }
                    flag = true;
                    stack.push(curNum);
                }

                int size = stack.size();

                for(int j = 0; j < size; j++) {
                    temp[i][j] = stack.pollLast();
                }
            }

        }

        else {
            for(int i = 0; i < N; i++) {
                ArrayDeque<Integer>  stack = new ArrayDeque<>();

                for(int j = N-1; j >= 0; j--) {
                    int curNum = origin[i][j];
                    if(curNum == 0) continue;
                    if(!stack.isEmpty() && stack.peek() == curNum && flag) {
                        stack.pop();
                        stack.push(curNum * 2);
                        flag = false;
                        continue;
                    }
                    flag = true;
                    stack.push(curNum);
                }

                int size = stack.size();

                for(int j = N-1; j >= N-size; j--) {
                    temp[i][j] = stack.pollLast();
                }
            }

        }
        return temp;
    }

    private boolean inRange(int r, int c) {
        return 0 <= r && r < N && c < N && c >= 0;
    }
}