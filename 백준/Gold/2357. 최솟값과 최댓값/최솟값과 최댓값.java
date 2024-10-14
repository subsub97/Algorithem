import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static long[] numbers;
    static long[][] segTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        numbers = new long[N];

        for(int i = 0; i < N; i++) {
            numbers[i] = Long.parseLong(br.readLine());
        }



        //최소 세그트리 크기 구하기
        int height = (int) Math.ceil(Math.log(N) / Math.log(2)) + 1;
        int maxSize = (int) Math.pow(2, height);
        segTree = new long[maxSize][3];
        int idx = 0;

        for(int i = maxSize / 2; i < maxSize / 2 + N; i++) {
            long curValue = numbers[idx++];

            segTree[i][0] = curValue;
            segTree[i][1] = curValue;
            segTree[i][2] = idx;
        }

        init(1, maxSize/2);

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int startIdx = Integer.parseInt(st.nextToken());
            int endIdx = Integer.parseInt(st.nextToken());
            int gap = (int)Math.pow(2, height-1)-1;

            long[] result = query(startIdx,endIdx,1, 1, maxSize / 2);
            sb.append(result[0] + " " + result[1] + "\n");
        }
        System.out.println(sb.toString());
    }

    public static void init(int nodeIdx, int k ) {
        if(nodeIdx >= k) return;

        // 자식 노드 초기화
        init(2 * nodeIdx, k);
        init(2 * nodeIdx + 1, k);

        //현재 노드 초기화
        long[] left = segTree[nodeIdx * 2];
        long[] right = segTree[nodeIdx * 2 + 1];

        if(right[0] != 0){
            segTree[nodeIdx][0] = Math.min(left[0], right[0]);
        }
        else{
            segTree[nodeIdx][0] = left[0];
        }
        segTree[nodeIdx][1] = Math.max(left[1], right[1]);
    }

    public static long[] query(int left, int right, int node, int start, int end) {
        long[] arr = new long[2];
        arr[0] =  Long.MAX_VALUE;
        arr[1] =  0;

        if(left > end || right < start) return arr;
        if (start >= left && right >= end) {
            return new long[] {segTree[node][0], segTree[node][1]};
        }

        int mid = (start + end) >> 1;
        long[] tmep1 = query(left, right, node << 1, start, mid);
        long[] tmep2 = query(left, right, (node << 1) + 1, mid + 1, end);

        arr[0] = Math.min(arr[0], tmep1[0]);
        arr[0] = Math.min(arr[0], tmep2[0]);
        arr[1] = Math.max(arr[1], tmep1[1]);
        arr[1] = Math.max(arr[1], tmep2[1]);

        return arr;
    }
}
