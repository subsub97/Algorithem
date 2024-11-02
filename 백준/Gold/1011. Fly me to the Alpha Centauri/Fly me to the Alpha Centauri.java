import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a =  Integer.parseInt(st.nextToken());
            int b =  Integer.parseInt(st.nextToken());
            if(a > b) {
                int temp = a;
                a = b;
                b = temp;
            }
            int distance = b- a;
            int max = (int)Math.sqrt(distance);

            if(max == Math.sqrt(distance)) {
                print(2 * max - 1);
            }

            else if(distance <= (int)Math.pow(max,2) + max) {
                print(2 * max);
            }
            else{
                print(2 * max + 1);
            }
        }

    }

    public static void print(int num) {
        System.out.println(num);
    }
}
