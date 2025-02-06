import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long N;
    static int M;
    static long MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Long.parseLong(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        long[][] C = new long[M][M];

        C[0][0] = C[0][M-1] = 1;
        for (int k = 0; k < M - 1; k++) C[1 + k][k] = 1;
        C = matrixCalc(C, N);

        System.out.println(C[0][0]);
    }
    private static long[][] matrixCalc(long[][] A, long n) {
        if(n <= 1) {
            return A;
        }

        long[][] temp = new long[M][M];
        for(int i = 0; i < M; i++) {
            for(int j = 0; j < M; j++) {
                temp[i][j] = A[i][j];
            }
        }

        long[][] mulA = matrixCalc(A, n/2);

        long[][] result = new long[M][M];
        for(int i = 0; i < M; i++) {
            for(int j = 0; j < M; j++) {
                long sum = 0;
                for(int k = 0; k < M; k++) {
                    sum = (sum + (mulA[i][k] * mulA[k][j]) % MOD) % MOD;
                }
                result[i][j] = sum;
            }
        }

        if((n % 2) == 1) {
            long[][] finalResult = new long[M][M];
            for(int i = 0; i < M; i++) {
                for(int j = 0; j < M; j++) {
                    long sum = 0;
                    for(int k = 0; k < M; k++) {
                        sum = (sum + (result[i][k] * temp[k][j]) % MOD) % MOD;
                    }
                    finalResult[i][j] = sum;
                }
            }
            return finalResult;
        }

        return result;
    }
}

