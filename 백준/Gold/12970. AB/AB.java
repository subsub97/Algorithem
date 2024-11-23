import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();

        int half =  N /2;
        int maxSize = (N - half) * half;
        if (K > maxSize) {
            System.out.println(-1);
        }
        else{
            int curCnt =  0;

            int aArr[] = new int[50];
            int bArr[] = new int[50];
            bArr[0] = N;

            int idx = 0;

            while(curCnt != K) {
                int temp = 0;

                for (int i = 0; i <= idx; i++) {
                    if(i == idx) {
                        temp += (aArr[i] + 1) * (bArr[i] - 1);
                    }
                    else{
                        temp += (aArr[i]) * (bArr[i] - 1);
                    }
                }

                if(temp > K) {
                    sb.append('B');


                    bArr[idx + 1] = bArr[idx] - 1;

                    idx++;
                }
                else{
                    sb.append('A');

                    for (int i = 0; i <= idx; i++) {
                        if(i == idx) {
                            aArr[i]++;
                            bArr[i]--;
                        }
                        else{
                            bArr[i]--;
                        }
                    }
                    curCnt = temp;
                }
            }

            while(sb.length() < N) {
                sb.append('B');
            }
        }
        System.out.println(sb.toString());
    }
}
