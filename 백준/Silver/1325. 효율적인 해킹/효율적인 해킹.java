import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Main {
    static int N, M;
    static ArrayList<Integer>[] arr;
    static boolean isVisited[];
    static int max;
    static int cntArr[];

    static void DFS(int start) {
        isVisited[start] = true;
        for (int i : arr[start]) {
            if (isVisited[i]) continue;
            cntArr[i]++;
            DFS(i);
        }
    }

    static void BFS(int start) {
        Queue<Integer> que = new ArrayDeque<Integer>();
        que.add(start);
        isVisited[start] = true;

        while (!que.isEmpty()) {
            int now = que.poll();
            for (int i : arr[now]) {
                if (isVisited[i]) continue;
                cntArr[i]++;
                isVisited[i] = true;
                que.add(i);
            }
        }
    }

    private static int read() throws Exception {
        int d, o;
        boolean negative = false;
        d = System.in.read();
        if (d == '-') {
            negative = true;
            d = System.in.read();
        }

        o = d & 15;
        while ((d = System.in.read()) > 32)
            o = (o << 3) + (o << 1) + (d & 15);

        return negative ? -o : o;
    }

    public static void main(String[] args) throws Exception {
        N = read();
        M = read();

        isVisited = new boolean[N + 1];
        cntArr = new int[N + 1];

        // 신뢰 관계 입력
        arr = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) arr[i] = new ArrayList<Integer>();

        for (int i = 0; i < M; i++) {
            int a = read();
            int b = read();
            arr[a].add(b);
        }

        // 1번부터 N번까지 search
        for (int i = 1; i < N + 1; i++) {
            isVisited = new boolean[N + 1];
            //DFS(i); // 메모리↓ 시간↑
            BFS(i); // 메모리↑ 시간↓
        }

        // 해킹할 수 있는 최댓값 찾기
        for (int i = 1; i < N + 1; i++) {
            if (max < cntArr[i]) max = cntArr[i];
        }

        // 최댓값인 컴퓨터 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < N + 1; i++) {
            if (max == cntArr[i]) sb.append(i).append(" ");
        }
        System.out.println(sb);
    }
}