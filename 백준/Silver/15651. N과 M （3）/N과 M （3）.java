import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N,M;
    static StringBuilder sb = new StringBuilder();
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[M + 1 ];
        dfs(1);
        System.out.println(sb);
    }

    private static void dfs(int depth) {
        if(depth > M) {
            append();
            return;
        }

        for(int i =1; i <= N; i++) {
            arr[depth-1] = i;
            dfs(depth+1);
        }
    }

    private static void append() {
        for(int i = 0; i < M; i++) {
            sb.append(arr[i]).append(' ');
        }
        sb.append("\n");
    }
}
