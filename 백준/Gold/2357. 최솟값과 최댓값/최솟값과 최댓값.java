import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static Node[] segTree;
    static int start;
    static final Node EMPTY = new Node(Integer.MAX_VALUE, -1); // 재사용용 노드

    static class Node {
        int min, max;

        public Node(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 세그먼트 트리의 시작 인덱스 계산
        int size = 1;
        while (size < N) size <<= 1;
        start = size;
        segTree = new Node[start << 1];

        // 초기화: 모든 노드를 기본값으로 설정
        for (int i = 0; i < segTree.length; i++) {
            segTree[i] = new Node(Integer.MAX_VALUE, -1);
        }

        // 입력 최적화
        StringBuilder inputBuilder = new StringBuilder();
        for (int i = 0; i < N; i++) {
            inputBuilder.append(br.readLine()).append(" ");
        }
        StringTokenizer nums = new StringTokenizer(inputBuilder.toString());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(nums.nextToken());
            segTree[start + i] = new Node(num, num);
        }

        // 트리 초기화
        init();

        // 쿼리 처리
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            Node result = query(1, 1, start, s, e);
            sb.append(result.min).append(" ").append(result.max).append("\n");
        }
        System.out.println(sb);
    }

    private static void init() {
        for (int i = start - 1; i > 0; i--) {
            Node left = segTree[i * 2];
            Node right = segTree[i * 2 + 1];
            segTree[i] = new Node(
                    Math.min(left.min, right.min),
                    Math.max(left.max, right.max)
            );
        }
    }

    private static Node query(int idx, int l, int r, int s, int e) {
        if (r < s || l > e) return EMPTY;
        if (s <= l && r <= e) return segTree[idx];

        int mid = (l + r) >> 1;
        Node left = query(idx * 2, l, mid, s, e);
        Node right = query(idx * 2 + 1, mid + 1, r, s, e);

        return new Node(
                Math.min(left.min, right.min),
                Math.max(left.max, right.max)
        );
    }
}
