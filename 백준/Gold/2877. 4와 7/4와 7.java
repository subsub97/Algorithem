import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[] arr = {4,7};
    static long[] acc;
    
    public static void main(String[] args) throws IOException {
        acc = new long[32];
        
        acc[0] = 0;
        acc[1] = 2;
        
        for(int i = 1 ; i < 31; i++) {
            acc[i] = acc[i-1] + (long)Math.pow(2,i);
        }
        
        N = read();
        
        StringBuilder sb = new StringBuilder();
        
        for(int i = 30; i >= 0; i--) {
            if(N > acc[i] ) {
                // 작아진경우 사용
    
                // 빼고 반보다 크냐 작냐로
                int cur = (int) Math.pow(2, i + 1);
                
                if(cur/2 >= N-acc[i]) {
                    // 4 사용
                    sb.append("4");
                }
                else {
                    // 7 사용
                    sb.append("7");
                    N -= (int) Math.pow(2, i);
                }
                N -= (int) Math.pow(2, i);
            }
        }
        
        System.out.println(sb);
    }

    private static int read() throws IOException {
        int d, o = 0;
        
        while ((d = System.in.read()) <= 32); 
        
        do {
            o = (o << 3) + (o << 1) + (d & 15); // o = o * 10 + (d - '0')
        } while ((d = System.in.read()) >= '0' && d <= '9');
        
        return o;
    }
}
