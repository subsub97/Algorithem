import java.util.*;

class Solution {
    static ArrayList<Integer>[] graph;
    static Queue<Pair> q = new LinkedList<>();
    static boolean[] visited;
    
    static int maxDepth = 0;
    static int tempAns = 0;
    public int solution(int n, int[][] edge) {
        int answer = 0;
        
        graph = new ArrayList[n];
        visited = new boolean[n];
        
        for(int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();            
        }
        
        int edgeSize = edge.length;
        
        for(int i = 0; i < edgeSize; i++) {
            int v1 = edge[i][0] - 1;
            int v2 = edge[i][1] - 1;
            
            graph[v1].add(v2);
            graph[v2].add(v1); 
        }
        
        // 최대 깊이 찾기
        q.add(new Pair(0,1));
        visited[0] = true;
        
        while(!q.isEmpty()) {
            Pair p = q.poll();
            
            if(p.depth > maxDepth) {
                tempAns = 0;
                tempAns++;
                
                maxDepth = p.depth;
            }
            else if(p.depth == maxDepth) {
                tempAns++;
            }
            
            for(int i = 0; i < graph[p.vertex].size(); i++) {
                int nextVertex = graph[p.vertex].get(i);
                
                if(!visited[nextVertex]) {
                    visited[nextVertex] = true;    
                    Pair nP = new Pair(nextVertex, p.depth + 1);
                    q.add(nP);
                }
            }
        }
    
        answer = tempAns;
        return answer;
    }
    
    static class Pair{
        int vertex;
        int depth;
        
        Pair(int v, int d) {
            this.vertex = v;
            this.depth = d;
        }
    }
}