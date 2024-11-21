import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] cards;

    public static void main(String[] args) throws IOException {

        // 플레이어의 카드에 적힌 수가 다른 플레이어의 카드에 적힌 수로 나누어 떨어지면 패배
        // 둘 다 아닌 경우 무승부
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        cards = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        int maxNumber = 0;

        for(int i = 0; i < N; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
            maxNumber = Math.max(cards[i], maxNumber);
        }

        int[] scores = new int[maxNumber + 1];
        boolean[] isExist = new boolean[maxNumber + 1];

        for(int i = 0; i < N; i++) {
            isExist[cards[i]] = true;
        }

        for(int i = 0;  i < N; i++) {
            int curNumber = cards[i];

            for(int j = curNumber*2; j <= maxNumber; j += curNumber) {
                if(isExist[j]) {
                    scores[j] -= 1;
                    scores[curNumber] +=1;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < N; i++) {
            sb.append(scores[cards[i]] + " ");
        }

        System.out.print(sb.toString());


    }
}
