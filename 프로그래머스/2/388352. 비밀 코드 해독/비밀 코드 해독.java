import java.util.*;
import java.io.*;

class Solution {
    static int N,Q_SIZE;
    static Node[] nodes;
    static int[] selected = new int[5];
    static int[][] sq;
    static int[] sans;
    static int realAns = 0;
    
        
    public int solution(int n, int[][] q, int[] ans) {
        int answer = 0;
        N = n;
        sq = q;
        sans = ans;
        
        // m번 시도 후, 비밀 코드로 가능한 정수 조합의 개수를 알고 싶다.
        // n까지의 서로 다른 정수 5개가 오름차순 비밀 코드
        
        // n까지 오름차순으로 주어지는 순열을 구한다.
        // 1 ,2 ,3 ,4,5 랑 2개 일치하고 
        // 6,7,8,9,10 이랑 3개 일치하고
        // 3,7,8,9,10 이랑 4개 일치하고
        // 2, 5, 7, 9, 10이랑 3개 일치하고
        // 3, 4, 5, 6, 7이랑 3개 일치하는 걸 찾으면 된다. 
        
        // 가장 빠른건 가장 겹치는게 많은거랑 먼저 일치하는 수를 만들고 
        // 필터링 ㄱㄱ 
        Q_SIZE = q.length;
        nodes = new Node[Q_SIZE];
        
        for(int i = 0; i < Q_SIZE; i++) {
            nodes[i] = new Node(q[i], ans[i]);
        }
        
        Arrays.sort(nodes);
        
        dfs(0,1);
        
        System.out.print(realAns);
        return realAns;
    }
    
    private boolean validSimulate() {
        for(int i = 0; i < Q_SIZE; i++) {
            
            int minDiff = 5 - nodes[i].cnt;
            
            int iIdx = 0;
            int jIdx = 0;
            int diff = 0;
            
            
            while(iIdx < 5 && jIdx < 5) {
                if(selected[iIdx] != nodes[i].arr[jIdx]){
                    if(selected[iIdx] > nodes[i].arr[jIdx]) {
                        jIdx++;
                    }
                    else{
                        iIdx++;
                    }
                } 
                else{    
                    iIdx++;
                    jIdx++;    
                    diff++;
                }
            }
            
            if(diff != nodes[i].cnt) return false;    
        }
        
        return true;
    }
    
    
    
    private void dfs(int depth, int idx) {
        if(depth == 5){
            //현재 선택한 수랑 비교하기
            if(validSimulate()) {

                realAns++;
            }
            return;
        }
        
        for(int i = idx; i <= N; i++) {
            selected[depth] = i;
            dfs(depth + 1, i + 1);
            selected[depth] = 0;
        }
    }
    
    class Node implements Comparable<Node>{
        int[] arr;
        int cnt;
        
        Node(int[] arr, int cnt) {
            this.arr = arr;
            this.cnt = cnt;
        }
        
        @Override
        public int compareTo(Node n2) {
            return n2.cnt - this.cnt ;
        }
    }
}