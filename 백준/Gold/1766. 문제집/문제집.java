import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Main {
    // 13:56

    /**
     * 각 정점에 진입 차수를 만들고 진입 차수가 0인 것을 차례대로 뺸다.
     * 근데 가능하면 쉬운 것 부터 풀어야한다.
     *
     * 즉, 진입 차수가 0이면서 숫자가 낮은 것 부터
     */

    static int N,M,cnt;
    static int[] inDegree;
    static ArrayList[] relation;
    static StringBuilder sb = new StringBuilder();
    static boolean[] solved;
    static PriorityQueue<Integer> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        inDegree = new int[N];
        relation = new ArrayList[N];

        for (int i = 0; i < N; i++) {
            relation[i] = new ArrayList();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int before = Integer.parseInt(st.nextToken()) - 1;
            int after = Integer.parseInt(st.nextToken()) - 1;

            relation[before].add(after);
            inDegree[after] += 1;
        }

        cnt = 0;
        solved = new boolean[N];

        boolean flag = false;
        for (int i = 0; i < N; i++) {

            // 문제를 풀 수 있는 경우
            if(inDegree[i] == 0) {
                if(flag) {
                    pq.add(i);
                    continue;
                }
                if(!flag) {
                    solved[i] = true;
                    sb.append((i+1) + " ");
                    cnt++;
                    flag = true;
                }

                List<Integer> myRelation = relation[i];

                for (Integer idx : myRelation) {
                    inDegree[idx]--;

                    if(inDegree[idx] == 0) {
                        pq.add(idx);
                    }
                }
            }
        }

        while(!pq.isEmpty()) {

            //가장 쉬운 문제부터 풀기
            int cur = pq.poll();
            if(solved[cur]) continue;
            solved[cur] = true;
            sb.append((cur+1) + " ");
            cnt++;

            ArrayList<Integer> myRelation = relation[cur];

            for (Integer idx : myRelation) {
                if(solved[idx]) continue;
                inDegree[idx]--;

                if(inDegree[idx] == 0) {
                    pq.add(idx);
                }
            }

        }

        System.out.println(sb);
    }


    private static void dfs(int i) {
        // 나랑 연관된거 dfs로 해결하기
        List<Integer> myRelation = relation[i];

        for (Integer idx : myRelation) {
            inDegree[idx]--;

            if(inDegree[idx] == 0 && idx < i) {
                sb.append(idx + 1).append(" ");
                solved[idx] = true;
                cnt++;

                dfs(idx);
            }
        }
        return;
    }
}
/*
*
* for문 한번 돌면서 0 인
* 애들을 풀면서 연관 선행 문제 조건이 해결된 경우
* heap에다가 넣는다.
*
 */