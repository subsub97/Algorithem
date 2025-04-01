import java.util.*;
import java.io.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int L , C ;
    static char[] alphabets;
    static char[] ans;
    static boolean[] visited = new boolean[26];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        alphabets = new char[C];
        ans = new char[L];

        // 주어지는 문자에서 최소 모음과 자음을 분리해서 받는다.
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < C; i++) {
            char cur = st.nextToken().charAt(0);

            alphabets[i] = cur;
        }

        Arrays.sort(alphabets);

        dfs(0, 0, 0, 0);
        System.out.println(sb);


    }

    private static void dfs(int d,int idx, int v, int c) {
        if(d == L) {
            if(v == 0 || c < 2) return;
            simulate();
            return;
        }

        for(int i = idx; i < C; i++) {
            if((L - d > (2-c) && isVowel(alphabets[i]) )|| (isVowel(alphabets[i]) && c >= 2)) {
                //모음 넣어도 되는 경우
                ans[d] = alphabets[i];
                visited[alphabets[i] - 'a'] = true;
                dfs(d+1, i + 1, v + 1, c);
                visited[alphabets[i] - 'a'] = false;
                continue;
            }

            // 자음
            if(isVowel(alphabets[i])) continue;
            ans[d] = alphabets[i];
            visited[alphabets[i] - 'a'] = true;
            dfs(d+1, i + 1, v, c + 1);
            visited[alphabets[i] - 'a'] = false;
        }
    }

   private static void simulate() {
       Arrays.sort(ans);
       for(int i =0; i < L; i++) {
           sb.append(ans[i]);
       }
       sb.append('\n');
   }

   private static boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'o' || c == 'u' || c== 'i';
   }
}
