import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static int V, P;
    static long L,min;
    static long[] arr;
    static ArrayList<Integer> ans = new ArrayList<>();
    static HashMap<Long,Boolean> visited;
    static long[] result;
    static ArrayList<Integer> list = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        L = Long.parseLong(st.nextToken());

        min = Long.MAX_VALUE;

        arr = new long[V];
        result = new long[P];
        visited = new HashMap<>();
        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < V; i++) {
            long a = Long.parseLong(st.nextToken());
            arr[i] = a;
            list.add(i);
//            if(visited.getOrDefault(a,false)) continue;
//            visited.put(a,true);
//            list.add(i);
        }

        dfs(0, 0);

        StringBuilder sb = new StringBuilder();
        sb.append(min).append("\n");
        for(int i = 0; i < P; i++) {
            if(i < P - 1) sb.append(result[i]).append(" ");
            else{
                sb.append(result[i]);
            }
        }

        System.out.println(sb);


    }

    private static void dfs(int depth, int idx) {
        if(depth == P) {
            // simulate
            simulate();
            return;
        }

        for(int i = idx; i < list.size(); i++) {
            ans.add(list.get(i));
            dfs(depth + 1, i + 1);
            ans.remove(ans.size() - 1);
        }
    }

    private static void simulate() {
        long[] tmp = new long[V];
        Arrays.fill(tmp, Long.MAX_VALUE);

        // 각 마을 j에 대해 모든 우체국 i와의 거리 중 최소값을 구함
        for (int i = 0; i < P; i++) {
            for (int j = 0; j < V; j++) {
                long dist = Math.abs(arr[ans.get(i)] - arr[j]);
                dist = Math.min(dist, L - dist); // 원형 거리
                tmp[j] = Math.min(tmp[j], dist); // 최소 거리 저장
            }
        }

        long total = 0;
        for (int j = 0; j < V; j++) {
            total += tmp[j];
        }

        // 최소값 갱신
        if (total < min) {
            min = total;
            for (int i = 0; i < P; i++) {
                result[i] = arr[ans.get(i)];
            }
        }
    }


}