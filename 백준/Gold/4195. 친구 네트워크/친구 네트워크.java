import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static int F;
    static int[] group;
    static int[] friends;
    static Map<String, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        F = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int f = 0; f < F; f++) {
            int cnt = Integer.parseInt(br.readLine());
            int size = 0;

            group = new int[cnt * 2];
            friends = new int[cnt * 2];

            for(int i = 0; i < cnt * 2; i++) {
                group[i] = i;
                friends[i] = 1;
            }


            for(int i = 0; i < cnt; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                String p1 = st.nextToken();
                String p2 = st.nextToken();


                int v1 = map.getOrDefault(p1, size);
                if(v1 == size) {
                    map.put(p1, size++);
                }

                int v2 = map.getOrDefault(p2, size);
                
                if(v2 == size) {
                    map.put(p2, size++);
                }
                
                int a = find(v1), b = find(v2);

                if(a > b) {
                    int temp = a;
                    a = b;
                    b = temp;
                }
                if(a != b) {
                    friends[a] += friends[b];
                }


                union(v1,v2);
                sb.append(friends[a]).append("\n");
            }

        }
        System.out.println(sb);
    }

    private static void union(int a , int b) {
        a = find(a);
        b = find(b);

        if(a > b) {
            int temp = a;
            a = b;
            b = temp;
        }

        group[b] = a;
    }

    private static int find(int a) {
        if(group[a] == a) return a;

        return group[a] = find(group[a]);
    }


}

