import java.util.*;
import java.io.*;

public class Main {
	static String T;
	static String P;
	static int[] f;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = br.readLine();
		P = br.readLine();
		
		f = new int[P.length()];


		// 실패 함수를 만들어서
		// 몇번 가능한지 체크하자 , 나타나는 위치
				
		StringBuilder sb = new StringBuilder();
		
		// 패턴 내부에서 
		
		f[0] = -1;
		int j = -1;
		for (int i = 1; i < P.length(); i++) {
		    while(j != -1 && P.charAt(j+1) != P.charAt(i)) {
		        j = f[j];
		    }
		    if(P.charAt(j+1) == P.charAt(i)) {
		        j++;
		    }
		    f[i] = j;
		}
		// 몇번 등작하는지 확인하기
		int cnt = 0;
		j = -1;
		for (int i = 0; i < T.length(); i++) {
		    while(j != -1 && T.charAt(i) != P.charAt(j+1)) {
		        j = f[j];
		    }
		    if(T.charAt(i) == P.charAt(j+1)) {
		        j++;
		    }
		    if(j == P.length()-1) { // 패턴 전체가 매칭된 경우
		        cnt++;
		        sb.append(i - P.length() + 2).append(" ");  // 1-indexed 위치
		        j = f[j];
		    }
		}
		
		
		System.out.println(cnt);
		System.out.println(sb);
	}
}
