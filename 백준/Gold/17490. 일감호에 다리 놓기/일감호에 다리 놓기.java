import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static long K;
    static int[] dist;        // 각 강의동에서 와우도까지 필요한 돌 개수
    static boolean[] visited; // 방문 여부
    static boolean[][] isWorking;
    // isWorking[i][0] = true -> i→(i+1) 막힘(오른쪽)
    // isWorking[i][1] = true -> i→(i-1) 막힘(왼쪽)

    static int[] groups;   // 강의동이 어떤 그룹에 속하는지
    static int[] minDist;  // 각 그룹별로 필요한 돌의 최소값

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 강의동 수
        M = Integer.parseInt(st.nextToken()); // 공사구간 수
        K = Long.parseLong(st.nextToken());   // 가진 돌의 수

        dist      = new int[N];
        visited   = new boolean[N];
        groups    = new int[N];
        isWorking = new boolean[N][2];  // [i][0]: 오른쪽 막힘, [i][1]: 왼쪽 막힘
        minDist   = new int[N];
        Arrays.fill(minDist, Integer.MAX_VALUE);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            dist[i] = Integer.parseInt(st.nextToken());
        }

        // 공사 구간 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1; // 0-based
            int b = Integer.parseInt(st.nextToken()) - 1; // 0-based

            // a→a+1 막힘, b→b-1 막힘 (또는 그 반대) 구분
            int rightA = (a + 1) % N;
            if (b == rightA) {
                // a->오른쪽, b->왼쪽 막힘
                isWorking[a][0] = true;
                isWorking[b][1] = true;
            } else {
                // a->왼쪽, b->오른쪽 막힘
                // (문제에서 (a,b)는 항상 인접하므로, else면 b == (a-1+N)%N)
                isWorking[a][1] = true;
                isWorking[b][0] = true;
            }
        }

        // BFS로 연결 요소(그룹) 찾기
        int gIdx = 0;
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                bfs(i, gIdx);
                gIdx++;
            }
        }

        // gIdx = 그룹 개수
        // 문제 조건상, 원형에서 M이 1 이하이면 => 절대 2개 이상의 그룹으로 갈라질 수 없음 -> YES
        // 혹은 gIdx가 1이면 전부 연결되어 있으므로 YES
        if (gIdx <= 1 || M <= 1) {
            System.out.println("YES");
            return;
        }

        // 여러 그룹이라면, 각 그룹의 최소 dist 합산해서 K와 비교
        long needK = 0;
        for (int i = 0; i < gIdx; i++) {
            needK += minDist[i];
            if (needK > K) break; // 이미 초과면 NO
        }

        if (needK > K) {
            System.out.println("NO");
        } else {
            System.out.println("YES");
        }
    }

    /**
     * start번 건물부터 BFS 탐색해서 하나의 연결 그룹을 만든다.
     * groupIdx: 몇 번째 그룹인지
     */
    private static void bfs(int start, int groupIdx) {
        Queue<Integer> queue = new LinkedList<>();
        visited[start] = true;
        groups[start] = groupIdx;

        // 그룹의 최소 dist 갱신
        minDist[groupIdx] = Math.min(minDist[groupIdx], dist[start]);

        queue.offer(start);

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            // 혹시 dist[cur]가 더 작다면 갱신
            if (dist[cur] < minDist[groupIdx]) {
                minDist[groupIdx] = dist[cur];
            }

            // 오른쪽 이웃
            int right = (cur + 1) % N;
            // 이동 가능?
            if (!visited[right] && !isWorking[cur][0]) {
                visited[right] = true;
                groups[right] = groupIdx;
                queue.offer(right);
            }

            // 왼쪽 이웃
            int left = (cur - 1 + N) % N;
            if (!visited[left] && !isWorking[cur][1]) {
                visited[left] = true;
                groups[left] = groupIdx;
                queue.offer(left);
            }
        }
    }
}
