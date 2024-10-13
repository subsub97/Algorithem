import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static long[] numbers;
    static long[] segTree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        numbers = new long[N];

        for (int i = 0; i < N; i++) {
            numbers[i] = Long.parseLong(br.readLine());
        }

        int height = (int) Math.ceil(Math.log(N) / Math.log(2)) + 1;
        int maxSize = (int) Math.pow(2, height);
        segTree = new long[maxSize];

        initSegmentTree(1, 0, N - 1);

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            long cmd = Long.parseLong(st.nextToken());
            long startIdx = Long.parseLong(st.nextToken()) - 1;
            long endIdx = Long.parseLong(st.nextToken());

            if (cmd == 1) {
                updateSegmentTree(1, 0, N - 1, (int)startIdx, endIdx);
            } else {
                long result = sumSection(1, 0, N - 1, (int)startIdx, (int)endIdx - 1);
                System.out.println(result);
            }
        }
    }

    public static long initSegmentTree(int node, int start, int end) {
        if (start == end) {
            return segTree[node] = numbers[start];
        } else {
            int mid = (start + end) / 2;
            return segTree[node] = initSegmentTree(node * 2, start, mid) + initSegmentTree(node * 2 + 1, mid + 1, end);
        }
    }

    public static void updateSegmentTree(int node, int start, int end, int idx, long newValue) {
        if (start == idx && end == idx) {
            segTree[node] = newValue;
        } else {
            int mid = (start + end) / 2;
            if (start <= idx && idx <= mid) {
                updateSegmentTree(node * 2, start, mid, idx, newValue);
            } else {
                updateSegmentTree(node * 2 + 1, mid + 1, end, idx, newValue);
            }
            segTree[node] = segTree[node * 2] + segTree[node * 2 + 1];
        }
    }

    public static long sumSection(int node, int start, int end, int left, int right) {
        if (left > end || right < start) {
            return 0;
        }
        if (left <= start && end <= right) {
            return segTree[node];
        }
        int mid = (start + end) / 2;
        return sumSection(node * 2, start, mid, left, right) + sumSection(node * 2 + 1, mid + 1, end, left, right);
    }
}
