import java.util.*;
// 10:29 해설보고 풀이함

class Solution {
    int[] in;
    int[] out;
    
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        
        
        int idx = 0;
        for(int i = 0; i < edges.length; i++) {
            idx = Math.max(idx, Math.max(edges[i][0], edges[i][1]));
        }
        
        in = new int[idx + 1];
        out = new int[idx + 1];
        
        for(int i = 0; i < edges.length; i++) {
            int oe = edges[i][0];
            int ie = edges[i][1];
            
            out[oe]++;
            in[ie]++;
        }
        
        for(int i = 0; i < edges.length; i++) {
            // 생성 찾기
            if(out[i] - in[i] > 1) {
                answer[0] = i;
                break;
            }
                        


        }
        
        for(int i = 1; i < idx + 1; i++) {
            if(out[i] - in[i] > 1) {
                answer[0] = i;
                break;
            }
        }
        
        for(int i = 1; i < idx + 1; i++) {            
            if(out[i] >= 2 && in[i] >= 2) answer[3]++;
            if(out[i] == 0 && in[i] > 0) answer[2]++;
        }
        
        answer[1] = out[answer[0]] - answer[2] - answer[3];
        return answer;
    }
}