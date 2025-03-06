import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static String y = "YES";
    static String n = "NO";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String vega = "(100+1+|01)+";
        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < t; i++) {
            String spread = br.readLine();
            if (spread.matches(vega)) {
                sb.append("YES").append("\n");
            } else
                sb.append("NO").append("\n");
        }
        System.out.println(sb);
    }
}