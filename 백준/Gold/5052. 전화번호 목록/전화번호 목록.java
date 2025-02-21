import java.io.*;
import java.util.*;

public class Main {
    static int T,N;
    static TrieNode[] root;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for(int t = 1; t <= T; ++t){
            N = Integer.parseInt(br.readLine());
            root = new TrieNode[10];
            boolean flag = false;

            String[] s = new String[N];
            for(int i = 0; i < N; i++) {
                // 전화번호 길이는 최대 10자리
                // 목록에 있는 두 전화번호가 같은 경우는 없다.
                s[i] = br.readLine();
            }
            Arrays.sort(s);

            for(int i = 0; i < N; i++) {

                if(makeTrieTree(s[i], 0, root)) {
                    sb.append("NO").append("\n");
                    flag = true;
                    break;
                }
            }
            if(!flag) {
                sb.append("YES").append("\n");
            }
        }

        System.out.println(sb);

    }

    private static boolean makeTrieTree(String word, int idx, TrieNode[] arr) {
        if(idx == word.length()) {
            return false;
        }
            int i = word.charAt(idx) - '0';

            if(arr[i] == null) {
                arr[i] = new TrieNode();

                if(word.length() -1 == idx){
                    arr[i].isEnd = true;
                }
            }
            else{
                if(arr[i].isEnd) {
                    return true;
                }
            }

            return makeTrieTree(word, idx + 1, arr[i].children);

    }

    static class TrieNode {
        TrieNode[] children= new TrieNode[10];
        boolean isEnd;

        TrieNode() {
            isEnd = false;
        }
    }
}