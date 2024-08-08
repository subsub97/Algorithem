class Solution {
    int SEC = 720;
    int MIN = 12;
    int HOUR = 1;
    int OFFSET = 43200;
    
public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int answer = 0;

        int hour = (h1 * 3600) + (m1 * 60) + (s1);
        int min = (m1 * 60 * 12 ) + (s1 * 12);
        int sec = s1 * SEC;
        boolean hourFlag = false;
        boolean minFlag = false;
        int a= 0;
        hour %= 43200;
    
        if(hour == sec || min == sec) {
            answer++;
        }
    
        // 모든 시 분 초 가 다른 경우 계속 반복
        while(h1 != h2 || m1 != m2 || s1 != s2) {
            s1++;
            if(s1 == 60) {
                m1++;
                s1 = 0;
            }
            if(m1 == 60) {
                h1++;
                m1 = 0;
            }

            if(sec < hour) hourFlag = true;
            if(sec < min) minFlag = true;

            hour = (hour + 1) % OFFSET;
            min = (min + MIN) % OFFSET;
            sec = (sec + SEC) % OFFSET;

            if(hourFlag && (sec >= hour || sec ==0)) {
                hourFlag = false;
                answer++;
                if(hour == min) {
                    minFlag = false;
                    continue;
                }
            }
            if(minFlag && (sec >= min ||sec == 0)) {
                minFlag = false;
                answer++;
            }

        }
        

        return answer;
    }
}