import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long N;
    static int M;
    static long[] dp;
    static long MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Long.parseLong(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        long[][] C = createMatrix();
        C = matrixCalc(C, N);

        System.out.println(C[0][0]);
    }
    private static long[][] matrixCalc(long[][] A, long n) {
        if(n <= 1) {
            return A;
        }

        // 현재 행렬 상태 저장
        long[][] temp = new long[M][M];
        for(int i = 0; i < M; i++) {
            for(int j = 0; j < M; j++) {
                temp[i][j] = A[i][j];
            }
        }

        // n/2 번 거듭제곱 계산
        long[][] mulA = matrixCalc(A, n/2);

        // 행렬 곱셈 계산 (mulA * mulA)
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

        // n이 홀수일 경우 한 번 더 곱하기
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

//    private static long[][] metrixCalc(long[][] A, long n) {
//        if(n <= 1) {
//            return A;
//        }
//
//        long tA11 = A[0][0];
//        long tA12 = A[0][1];
//        long tA21 = A[1][0];
//        long tA22 = A[1][1];
//
//        long[][] mulA = metrixCalc(A, n /2);
//
//        long a11 = (A[0][0] * A[0][0] + A[0][1] * A[1][0]) % MOD;
//        long a12 = (A[0][0] * A[0][1] + A[0][1] * A[1][1]) % MOD;
//        long b11 = (A[1][0] * A[0][0] + A[1][1] * A[1][0]) % MOD;
//        long b12 = (A[1][0] * A[0][1] + A[1][1] * A[1][1]) % MOD;
//
//        mulA[0][0] = a11;
//        mulA[0][1] = a12;
//        mulA[1][0] = b11;
//        mulA[1][1] = b12;
//
//        if(((n % 2) ==0)) {
//            return mulA;
//        }
//        else{
//            a11 = mulA[0][0] * tA11 + mulA[0][1] * tA12;
//            a12 = mulA[0][0] * tA12 + mulA[0][1] * tA22;
//            b11 = mulA[1][0] * tA11 + mulA[1][1] * tA21;
//            b12 = mulA[1][0] * tA12 + mulA[1][1] * tA22;
//
//            mulA[0][0] = a11 % MOD;
//            mulA[0][1] = a12 % MOD;
//            mulA[1][0] = b11 % MOD;
//            mulA[1][1] = b12 % MOD;
//            return mulA;
//        }
//    }

    public static long[][] createMatrix() {
        long[][] matrix = new long[M][M];

        matrix[0][0] = 1;
        matrix[0][M-1] = 1;

        for(int i = 1; i < M; i++) {
            matrix[i][i-1] = 1;
        }

        return matrix;
    }
}

