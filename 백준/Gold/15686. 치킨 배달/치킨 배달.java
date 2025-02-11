import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N,M,ans;
    static int[][] grid;
    static ArrayList<Shop> shops = new ArrayList<>();
    static ArrayList<House> houses = new ArrayList<>();
    static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        grid = new int[N][N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                if(grid[i][j] == 1) {
                    //집
                    houses.add(new House(i,j,houses.size()));
                }
                else if(grid[i][j] == 2) {
                    shops.add(new Shop(i,j,shops.size()));
                }
            }
        }

        //풀이방법
        /*
        1,2 번을 고른 경우
        1,3 번을 고른 경우
        2,3 번을 고른 경우

        암튼 최소 구하기

        각 집에서 치킨집 거리 구해두고
        min 값인 조합으로 사용하기
         */
        ans = (int) 1e9;
        dfs(0, 0);

        System.out.println(ans);

    }

    private static void dfs(int idx,int depth) {
        if(depth == M) {
            simulate();
            return;
        }

        for(int i = idx; i < shops.size(); i++) {
            list.add(i);
            dfs(i + 1,depth + 1);
            list.remove(list.size() - 1);
        }
    }

    private static void simulate() {
        //거리 계산하기
        int sum = 0;

        for(int i = 0; i < houses.size(); i++) {
            int temp = (int) 1e9;
            int hr = houses.get(i).r;
            int hc = houses.get(i).c;

            for(int j = 0; j < list.size(); j++) {
                //list에 담긴 집을 꺼내면서 비교하면서 최소를 선택
                int sr = shops.get(list.get(j)).r;
                int sc = shops.get(list.get(j)).c;
                temp = Math.min(Math.abs(hr - sr) + Math.abs(hc - sc), temp);
            }
            //이렇게 구해진 temp는 집과 가장 가까운 거리
            sum += temp;
        }
        ans = Math.min(ans, sum);
    }


    private static class Shop {
        int r;
        int c;
        int idx;

        public Shop(int r, int c, int idx) {
            this.r = r;
            this.c = c;
            this.idx = idx;
        }
    }

    private static class House {
        int r;
        int c;
        int idx;

        public House(int r, int c, int idx) {
            this.r = r;
            this.c = c;
            this.idx = idx;
        }
    }
 }


