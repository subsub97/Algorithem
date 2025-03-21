import java.util.*;
import java.io.*;

public class Main {
	static int[][] grid;
	static int n;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		grid = new int[n][n]; 
		
		StringBuilder sb = new StringBuilder();
		
		printStar(0,0,n);
		
		for (int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(grid[i][j] == 1) {
					sb.append("*");
				}
				else {
					sb.append(" ");
				}
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
		
	}
	
	public static void printStar(int r, int c, int n) {
		if(n == 3) {
			grid[r][c] = 1;
			for(int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if(i == 1 && j == 1) continue;
					grid[r + i][c+j] = 1;
				}
			}
			return;
		}
		
		for(int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if(i == 1 && j==1) continue;
			
				printStar(r + (n/3 * i), c + (n/3 * j), n/3);
			}
		}

	}
	
		
}

