import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[] arr = {4,7};
	static long[] acc;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		acc = new long[33];
		
		acc[0] = 0;
		acc[1] = 2;
		
		for(int i = 1 ; i < 33; i++) {
			acc[i] = acc[i-1] + (long)Math.pow(2,i);
		}
		
		N = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 30; i >= 0; i--) {
			if(N > acc[i] ) {
				// 작아진경우 사용
	
				//빼고 반보다 크냐 작냐로
				int cur = (int) Math.pow(2, i + 1);
				
				if(cur/2 >= N-acc[i]) {
					//4사용
					sb.append("4");
				}
				else {
					//7사용
					sb.append("7");
					N -= (int) Math.pow(2, i);
				}
				N -= (int) Math.pow(2, i);
				
			}
		}
		
		System.out.println(sb);
		
	}


}
