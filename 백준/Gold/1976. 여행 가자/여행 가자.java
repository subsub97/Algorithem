import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] uf;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        uf = new int[n];
        map = new int[n][n];

        for(int i = 0; i < n; i++) {
            uf[i] = i;
        }

        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] == 1) {
                    //연결된 경우
                    union(i,j);
                }
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] destination = new int[m];

        for(int i = 0; i < m; i++) {
            destination[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        String result = "YES";

        for(int i = 1; i < m; i++) {
            if(find(destination[i-1]) != find(destination[i])) {
                result = "NO";
                break;
            }
        }

        System.out.println(result);
    }

    public static int find(int x) {
        if(uf[x] == x)
            return x;
        return uf[x] = find(uf[x]);
    }

    public static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if(rootA > rootB) {
            uf[rootA] = rootB;
        }
        else{
            uf[rootB] = rootA;
        }
    }
}
