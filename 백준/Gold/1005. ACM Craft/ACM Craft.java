import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int T, N, K;
    static int[] inDegree;
    static int[] dp;        // 각 건물까지 걸리는 최대 시간
    static int[] costs;     // 각 건물의 건설 시간
    static ArrayList<ArrayList<Integer>> graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            // 초기화
            inDegree = new int[N + 1];
            dp = new int[N + 1];
            costs = new int[N + 1];
            graph = new ArrayList<>();

            for (int i = 0; i <= N; i++) {
                graph.add(new ArrayList<>());
            }

            // 건설 시간 입력
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                costs[i] = Integer.parseInt(st.nextToken());
            }

            // 건설 순서 입력
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                graph.get(from).add(to);
                inDegree[to]++;
            }

            int target = Integer.parseInt(br.readLine());
            sb.append(topologicalSort(target)).append('\n');
        }
        System.out.print(sb);
    }

    static int topologicalSort(int target) {
        Queue<Integer> q = new LinkedList<>();

        // 시작점 찾기
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                q.add(i);
                dp[i] = costs[i];
            }
        }

        // 위상 정렬
        while (!q.isEmpty()) {
            int cur = q.poll();

            if (cur == target) break;

            for (int next : graph.get(cur)) {
                // 다음 건물까지의 최대 시간 갱신
                dp[next] = Math.max(dp[next], dp[cur] + costs[next]);
                if (--inDegree[next] == 0) {
                    q.add(next);
                }
            }
        }

        return dp[target];
    }
}
