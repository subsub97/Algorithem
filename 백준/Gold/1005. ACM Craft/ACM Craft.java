import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int T,N,K;
    static int[] inDegree;
    static int[] dp;
    static int[] costs;
    static int ans;
    static ArrayList<ArrayList<Integer>> edges;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());

            inDegree = new int[N + 1];
            dp = new int[N + 1];
            edges = new ArrayList<>();

            costs = new int[N + 1];
            edges.add(new ArrayList<>());

            for (int i = 1; i <= N; i++) {
                costs[i] = Integer.parseInt(st.nextToken());
                edges.add(new ArrayList<>());

            }

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());

                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                edges.get(from).add(to);

                inDegree[to]++;
            }

            int target = Integer.parseInt(br.readLine());
            ans = 0;
            Queue<Integer> q = new LinkedList<>();

            for(int i = 1; i <= N; i++) {
                if(inDegree[i] == 0) {
                    q.add(i);
                }
            }

            while(!q.isEmpty()) {
                int cur = q.poll();

                ans = Math.max(ans, costs[cur]);
                for(int i = 0; i < edges.get(cur).size(); i++) {
                    //연결된 간선으로 이동
                    int from = edges.get(cur).get(i);
                    inDegree[from]--;
                    // cur -> from 비용

                    dp[from] = Math.max(dp[from], dp[cur] + costs[cur]);


                    if(from == target) {
                        ans = Math.max(ans,dp[from] + costs[from]);
                    }

                    if(inDegree[from] == 0) {
                        q.add(from);
                        if(from == target) {
                            ans = Math.max(ans,dp[from] + costs[from]);
                            q = new LinkedList<>();
                            break;
                        }
                    }
                }
            }
            sb.append(ans).append("\n");
        }
        System.out.print(sb);
    }



}
