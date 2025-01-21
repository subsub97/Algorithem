import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[] segTree;
	
	public static void main(String[] args) throws Exception{
		N = read();
		
		int h = (int) Math.ceil((Math.log(N) / Math.log(2)));
		
		int maxSize = (int) Math.pow(2, h + 1);
		int startIdx = (1 << h); 
				
		segTree = new int[maxSize];
		
		int cnt = 0;
		for(int i = startIdx; i < maxSize; i++ ) {
			if(cnt++ < N) {
				segTree[i]  = read();
			}
			else {
				segTree[i]  = (int) (1e9);
			}
			
		}
		
		initSegTree(1,startIdx);
		
		int t = read();
		
		StringBuilder sb = new StringBuilder();
		
		for(int i =0; i < t; i++) {	
			int cmd = read();
			
			int a = read();
			int b = read();
			
			if(cmd == 1) {
				//update 
				update(b,a - 1 + startIdx, a - 1 + startIdx);
			}
			else {
				if(a > b) {
					int temp = a;
					b= a;
					a= temp;
				}
				sb.append(query(a - 1 + startIdx,b - 1 + startIdx) + "\n");
				
			}
		}
		
		System.out.println(sb.toString());
	}
	
	public static void initSegTree(int curN, int n) {
		if(curN >= n) {
			return;
		}
		
		initSegTree(curN * 2, n);
		initSegTree(curN * 2 + 1, n);
		
		segTree[curN] = Math.min(segTree[curN * 2], segTree[curN * 2 + 1]);
	}
	
	public static void update(int newValue, int curNode, int startNode) {
		if(curNode == 1) return;
		
		if(curNode == startNode) {
			segTree[curNode] = newValue;
			update(newValue, curNode/2, startNode);
			return;
		}
		
		segTree[curNode] = Math.min(segTree[curNode * 2], segTree[curNode * 2 + 1]);
		
		update(newValue, curNode/2, startNode);
	}
	
	public static int query(int startIdx, int endIdx) {
		int ans = (int)(1e9);
		
		while(startIdx <= endIdx) {
			if(startIdx % 2 == 1) {
				ans = Math.min(ans, segTree[startIdx]);
			}
			
			startIdx = (startIdx + 1) / 2;
			
			if(endIdx % 2 == 0) {
				ans = Math.min(ans,  segTree[endIdx]);
			}
			
			endIdx = (endIdx - 1) /2;
		}
		return ans;
	}
    
    private static int read() throws Exception {
            int d, o;
            boolean negative = false;
            d = System.in.read();

            if (d == '-') {
                negative = true;
                d = System.in.read();
            }
            o = d & 15;
            while ((d = System.in.read()) > 32)
                o = (o << 3) + (o << 1) + (d & 15);

            return negative? -o:o;
        }
	


}
