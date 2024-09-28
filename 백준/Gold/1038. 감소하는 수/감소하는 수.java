import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine().trim());
        if(N < 10) {
            System.out.println(N);
            return;
        } else if(1023 <= N) {
            System.out.println(-1);
            return;
        }
        else if(1022 == N) {
            System.out.println("9876543210");
            return;
            }

        else{
            for(int i = 0; i < 10; i++) {
                backtracking(i, 1);
            }
        }

        Collections.sort(list);
        System.out.println(list.get(N));
    }

    public static void backtracking(int num, int len) {
        if(len  > 10) {
            return;
        }
        list.add(num);
        for(int i = 0; i < num % 10; i++) {
            backtracking((num * 10) + i , len + 1);
        }
    }
}