import java.util.*;

class Solution {
    int[] cnt;
    Map<String, Integer> map = new HashMap<>();
    int[] gp;
    int[][] memo;
    
    public int solution(String[] friends, String[] gifts) {
        //21:48
        int answer = 0;
        int n = friends.length;
        
        gp = new int[n];
        cnt = new int[n];
        memo = new int[n][n];
        
        int idx = 0;
        
        for(String s : friends) {
            map.put(s, idx++);
        }
        
        for(int i = 0; i < gifts.length; i++) {
            String[] cur = gifts[i].split(" ");
            int a = map.get(cur[0]);
            int b = map.get(cur[1]);
            
            gp[a]++;
            gp[b]--;
            
            memo[a][b]++;   
        }
        
        // 선물을 많이 받는 사람 찾기
        for(int i = 0; i < n; i++) {
            
            for(int j = 0; j < n; j++) {
                if(i == j) continue;
                
                int ap = memo[i][j];
                int bp = memo[j][i];
                
                //선물 받을 사람 찾기 
                if(ap > bp) {
                    cnt[i]++;
                }
                else if (ap < bp) continue;
                else {                 
                    // 선물지수 비교
                    if(gp[i] > gp[j]) cnt[i]++;
                }
            }
        }
        for(int i = 0; i < n; i++) {
            System.out.print(cnt[i]  + " ");
        }
        Arrays.sort(cnt);
        answer = cnt[cnt.length - 1];
        
        return answer;
    }
}