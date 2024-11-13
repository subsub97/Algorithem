import java.util.*;

class Solution {
    static Queue<Integer> q1 = new LinkedList<>();
    static Queue<Integer> q2 = new LinkedList<>();
    
    public int solution(int[] queue1, int[] queue2) {
        int answer = -1;
        //한번의 팝과 한번의 인설트를 합쳐서 1회
        long sum = 0;
        long qq1 = 0;
        long qq2 = 0;
        
        for(int i = 0; i < queue1.length; i++) {
            q1.add(queue1[i]);
            q2.add(queue2[i]);
            
            qq1 += queue1[i];
            qq2 += queue2[i];
        }
        
        sum = qq1 + qq2;
        
        int cnt = 0;
        int ansCnt = 0; 
        
        while(cnt++ < 310000) {
            if(qq1 == sum/2 || qq2 == sum/2) {
                answer = ansCnt;
                break;
            }
            
            if(qq1 > qq2) {
                int v = q1.poll();
                q2.add(v);
                
                qq1 -= v;
                qq2 += v;
            }
            else{
                int v = q2.poll();
            
                q1.add(v);
                
                qq1 += v;
                qq2 -= v;
            }

            ansCnt++;
        }
        
        
        return answer;
    }
}