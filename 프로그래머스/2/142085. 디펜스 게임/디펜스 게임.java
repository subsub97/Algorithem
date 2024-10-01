import java.util.*;

class Solution {
    static PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
    
    public int solution(int n, int k, int[] enemy) {
        int answer = -1;
        
        int worker = n;
        int ticket = k;
        
        for(int i = 0; i < enemy.length; i++) {
            int curEnemy = enemy[i];
            pq.add(curEnemy);
            
            if(worker < curEnemy) {
                if(ticket--  > 0) {
                    worker += pq.poll();
                    worker -= curEnemy;
                }
                
                else{
                    answer = i;                
                    break;
                }
            }
            else {
                worker -= curEnemy;
            }
        }
        
        if(answer == -1) {
            answer = enemy.length;
        }
        return answer;
    }
}