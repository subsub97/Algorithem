import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static Node[] segTree;
    private static final Node EMPTY = new Node(Integer.MAX_VALUE, -1);

    static class Node {
        int min;
        int max;

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

        int h = (int) (Math.ceil(Math.log(N) / Math.log(2)));

        int start = 2 << (h-1);
        int size = 2 << h;

        segTree = new Node[size];

        for (int i = start; i < start *2 ; i++) {
            if(i >= start + N) {
                segTree[i] = EMPTY;
                continue;
            }
            int num = Integer.parseInt(br.readLine());
            segTree[i] = new Node(num, num);
        }

        //init(1, start);
        for (int i = start - 1; i > 0; i--) {
            Node left = segTree[i * 2];
            Node right = segTree[i * 2 + 1];
            segTree[i] = new Node(
                    Math.min(left.min, right.min),
                    Math.max(left.max, right.max)
            );
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            Node result = query(1, s, e, 1, start);
            sb.append(result.min).append(" ").append(result.max).append("\n");
        }
        System.out.println(sb);
    }

    private static Node init(int idx, int baseIdx) {
        if(idx >= baseIdx) {
            if(segTree[idx] == null) {
                segTree[idx] = EMPTY;
            }
            return segTree[idx];
        }

        int min = Math.min(init(idx * 2, baseIdx).min, init(idx * 2 + 1, baseIdx).min);
        int max = Math.max(init(idx * 2, baseIdx).max, init(idx * 2 + 1, baseIdx).max);

        return segTree[idx] = new Node(min, max);
    }

    private static Node query(int idx, int s, int e , int l, int r) {
        // s, e 내가 찾고 싶은 구간
        // l, r 현재 선택한 구간

        if(r < s || l > e) {
            return EMPTY;
        }
        // 찾는 구간인 경우
        if(s <= l && r <= e) {
            return segTree[idx];
        }

        int mid = (l + r) /2;

        Node leftNode = query(idx * 2, s, e, l, mid);
        Node rightNode = query(idx * 2 + 1, s, e, mid + 1, r);
        return new Node(Math.min(leftNode.min, rightNode.min), Math.max(leftNode.max, rightNode.max));
    }
}