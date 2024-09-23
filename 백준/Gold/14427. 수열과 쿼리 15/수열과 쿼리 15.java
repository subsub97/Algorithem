import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int Q;
    static int[][] segeTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        // Tree 초기 사이즈 세팅
        int k = (int) Math.pow(2, Math.ceil(Math.log(N) / Math.log(2)));
        int size =  k * 2;

        segeTree = new int[size][2];

        st = new StringTokenizer(br.readLine());

        int index = 1;
        for (int i = k; i < k + N; i++) {
            segeTree[i][0] = Integer.parseInt(st.nextToken());
            segeTree[i][1] = index++;
        }

        init(1, k);
        st = new StringTokenizer(br.readLine());
        Q = Integer.parseInt(st.nextToken());

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int q = Integer.parseInt(st.nextToken());

            if(q == 2) {
                System.out.println(segeTree[1][1]);
                continue;
            }

            int idx = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());
            update(idx, val,k-1);
        }

    }

    public static void init(int nodeIndex, int k) {
        if(nodeIndex >= k) return;

        // 자식 노드 초기화
        init(2 * nodeIndex, k);
        init(2 * nodeIndex + 1, k);

        // 현재 노드 초기화
        int[] left = segeTree[nodeIndex * 2];
        int[] right = segeTree[nodeIndex * 2 + 1];

        if(left[0] <= right[0] || right[0] == 0) {
            segeTree[nodeIndex][0] = left[0];
            segeTree[nodeIndex][1] = left[1];
        } else {
            segeTree[nodeIndex][0] = right[0];
            segeTree[nodeIndex][1] = right[1];
        }

    }

    public static void update(int index , int value, int k) {
        int updateIndex = index + k;
        segeTree[updateIndex][0] = value;

        while(updateIndex != 1) {
            updateIndex /= 2;
            int[] left = segeTree[updateIndex * 2];
            int[] right = segeTree[updateIndex * 2 + 1];

            if(left[0] <= right[0] || right[0] == 0) {
                segeTree[updateIndex][0] = left[0];
                segeTree[updateIndex][1] = left[1];
            } else {
                segeTree[updateIndex][0] = right[0];
                segeTree[updateIndex][1] = right[1];
            }
        }
    }
}
