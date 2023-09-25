import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.LinkedList;

public class S2_1260 {
    //방문 체크
    private static boolean[] d_visited = new boolean[10001];
    private static boolean[] b_visited = new boolean[10001];
    private static ArrayList<ArrayList<Integer>> twoDList = new ArrayList<>();
    private static Queue<Integer> q = new LinkedList<>();


    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);

        int n, k, v, s, e;
        n = sc.nextInt();
        k = sc.nextInt();
        v = sc.nextInt();


        ArrayList<Integer> dfs_order = new ArrayList<>();
        ArrayList<Integer> bfs_order = new ArrayList<>();

        //정점의 개수만큼 2차원 리스트 만들기
        for (int i = 0; i < n; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            twoDList.add(row);
        }

        for (int i = 0; i < k; i++) {
            s = sc.nextInt() - 1;
            e = sc.nextInt() - 1;
            //양방향이니까 각각 추가해주기
            twoDList.get(s).add(e);
            twoDList.get(e).add(s);
        }
        for (int i = 0; i < n; i++) {
            Collections.sort(twoDList.get(i));
        }
        dfs(v - 1);
        System.out.println();
        b_visited[v - 1] = true;
        q.add(v - 1);
        bfs();



    }

    private static void dfs(int cur_v) {
        d_visited[cur_v] = true;
        System.out.print((cur_v + 1) + " ");

        for (int i = 0; i < twoDList.get(cur_v).size(); i++) {
            if (d_visited[twoDList.get(cur_v).get(i)] == false) {
                dfs(twoDList.get(cur_v).get(i));
            }
        }
    }

    private static void bfs() {
        while (q.size() > 0) {
            int cur_v = q.poll();
            System.out.print((cur_v+1) + " ");
            for (int i = 0; i < twoDList.get(cur_v).size(); i++) {
                if (b_visited[twoDList.get(cur_v).get(i)] == false) {
                    q.add(twoDList.get(cur_v).get(i));
                    b_visited[twoDList.get(cur_v).get(i)] = true;
                }
            }
        }
    }
}

//1 2 3
//2 5 1
//3 4 1
//4 5 3
//5 4 2