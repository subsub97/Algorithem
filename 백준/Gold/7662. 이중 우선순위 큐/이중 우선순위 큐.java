import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int T, K;
    static int cnt;
    static HashMap<Integer, Integer> map;
    static PriorityQueue<Integer> minHeap;
    static PriorityQueue<Integer> maxHeap;

    // 20:04
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {

            st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());

            cnt = 0;
            map = new HashMap<>();
            minHeap = new PriorityQueue<>();
            maxHeap = new PriorityQueue<>((a, b) -> b.compareTo(a));

            for (int k = 0; k < K; k++) {
                st = new StringTokenizer(br.readLine());

                String cmd = st.nextToken();
                int n = Integer.parseInt(st.nextToken());

                if(cmd.equals("I")) {
                    minHeap.add(n);
                    maxHeap.add(n);
                    cnt++;
                    map.put(n, map.getOrDefault(n, 0) + 1);
                }else{
                    if(cnt == 0) continue;
                    // 삭제인 경우
                    if(n == 1) {

                        while(!maxHeap.isEmpty()) {
                            int max  = maxHeap.poll();
                            int curCnt = map.get(max);
                            if(curCnt > 0) {
                                map.put(max, curCnt - 1);
                                cnt--;
                                break;
                            }
                        }
                    } else {
                        while(!minHeap.isEmpty()) {
                            int min  = minHeap.poll();
                            int curCnt = map.get(min);
                            if(curCnt > 0) {
                                map.put(min, curCnt - 1);
                                cnt--;
                                break;
                            }
                        }
                    }
                }
            }

            // 최댓값, 최솟값 출력하기
            if(cnt == 0) {
                sb.append("EMPTY").append("\n");
            }else{
                int maxValue = 0;
                int minValue = 0;

                while(!maxHeap.isEmpty()) {
                    maxValue  = maxHeap.poll();

                    if(map.get(maxValue) > 0) {
                        break;
                    }
                }

                while(!minHeap.isEmpty()) {
                    minValue  = minHeap.poll();

                    if(map.get(minValue) > 0) {
                        break;
                    }
                }

                sb.append(maxValue).append(" ").append(minValue).append("\n");
            }
        }
        System.out.println(sb);
    }

    // 데이터 삽입 연산
    // 데이터 삭제 연산

    // 정수 값이 우선순위를 의미함
    // 1. 우선순위가 가장 높은거
    // 2. 우선순위가 가장 낮은거


    // Q에 연산을 수행하고 최종적으로 Q에 저장된 데이터 중 최댓값과 최솟값을 출력

    /**
     * LAZY하게 -1인 경우
     * 최소힙에 peek 값을 삭제
     * peek 값을 삭제하기 전에 map에서 해당 값이 있는지 확인한다.
     * 존재하는 경우
     *  1. map 에서 cnt -1 한다.
     *  존재하지 않는 경우
     *  1. peek() poll한다.
     *
     *  최대힙도 동일하게
     *
     */
}