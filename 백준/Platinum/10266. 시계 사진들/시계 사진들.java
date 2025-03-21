import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static final int OFFSET = 360_000;
    static int[] arr1;
    static int[] arr2;

    static int[] f1;
    static int[] f2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        arr1 = new int[N];
        arr2 = new int[N];
        f1 = new int[N];
        f2 = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr2[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr1);
        Arrays.sort(arr2);

        for(int i = 1; i < N; i++) {
            f1[i-1] = arr1[i] - arr1[i-1];
            f2[i-1] = arr2[i] - arr2[i-1];
        }

        f1[N - 1] = OFFSET - arr1[N - 1] + arr1[0];
        f2[N - 1] = OFFSET - arr2[N - 1] + arr2[0];

        int[] failure = new int[N];

        int j = 0;

        for(int i = 1; i < N; i++) {
            while(j != 0 && f2[i] != f2[j]) {
                j = failure[j - 1];
            }

            if(f2[i] == f2[j]) j++;

            failure[i] = j;
        }

        j = 0;
        boolean flag = false;

        for(int i = 0; i < N*2; i++) {

            while(j != 0 && f1[i % N] != f2[j]) {
                j = failure[j - 1];
            }

            if(f1[i % N] == f2[j]) j++;

            if(j == N) {
                System.out.println("possible");
                flag = true;
                break;
            }

        }

        if(!flag) System.out.println("impossible");


    }
}