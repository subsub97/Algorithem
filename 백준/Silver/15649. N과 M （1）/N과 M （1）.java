import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int M;
    static int[] arr;
    static boolean[] used;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr= new int[M];
        used = new boolean[N+1];
        dfs(1);
        System.out.println(sb.toString());
    }

    public static void dfs(int depth) {
        if(depth > M) {
            simulate();
            return;
        }

        for(int i = 1; i <= N; i++) {
            if(used[i]) continue;
            arr[depth-1] =i;
            used[i] = true;
            dfs(depth+1);
            used[i] = false;
        }
    }

    public static void simulate() {
        for(int i =0; i < arr.length; i++) {
            sb.append(arr[i] + " ");
        }
        sb.append("\n");
    }
}
