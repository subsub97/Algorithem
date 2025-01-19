import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static Node[] segTree;

    static class Node {
        int idx;
        int value;

        Node(int idx, int value) {
            this.idx = idx;
            this.value = value;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        //segTree 배열 만들기
        int h = (int) Math.ceil((Math.log(N) / Math.log(2))) + 1;
        int offset = (int) Math.pow(2, h-1) - 1;
        segTree = new Node[(int)Math.pow(2,h)];

        int[] initArr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++) {
            initArr[i] = Integer.parseInt(st.nextToken());
        }

        //세그먼트 초기 값 갱신
        initLeafNode(initArr, h -1 );
        init(1, h - 1);

        int cmdCnt = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int i =0; i < cmdCnt; i++) {
            st = new StringTokenizer(br.readLine());

            int cmd = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(cmd == 1) {
                updateTree(b, a + offset ,a + offset);
            }
            else{
                sb.append(query(a + offset, b + offset) + "\n");
            }
        }
        System.out.println(sb.toString());

        //1_000_000_000
    }

    public static void initLeafNode(int[] initArr ,int h) {
        int startIdx = (int) Math.pow(2, h);
        int idx = 1;

        for(int i = 1; i < startIdx; i++) {
            segTree[i] = new Node(idx, (int) (1e9));
        }

        for(int i = startIdx; i < startIdx * 2; i++) {
            if(i > startIdx + N-1) {
                segTree[i] = new Node(idx++, (int) 1e9);
                continue;
            }

            segTree[i] = new Node(idx, initArr[idx++ -1]);
        }
    }

    public static void init(int idx, int h) {
        if(idx >= (int) Math.pow(2, h)) return;

        init(idx * 2, h);
        init(idx * 2 + 1, h);

        Node curNode = segTree[idx];

        Node c1 = segTree[idx * 2];
        Node c2 = segTree[idx * 2 + 1];

        if(c1.value <= c2.value) {
            curNode.value = c1.value;
            curNode.idx = c1.idx;
        }
        else{
            curNode.value = c2.value;
            curNode.idx = c2.idx;
        }


    }

    public static void updateTree(int newValue, int curIdx, int updateIdx) {
        if(curIdx < 1) return;

        if(curIdx == updateIdx) {
            segTree[curIdx].value = newValue;
            updateTree(newValue, curIdx / 2, updateIdx );
            return;
        }

        Node curNode = segTree[curIdx];

        Node c1 = segTree[curIdx * 2];
        Node c2 = segTree[curIdx * 2 + 1];

        if(c1.value <= c2.value) {
            curNode.value = c1.value;
            curNode.idx = c1.idx;
        }
        else{
            curNode.value = c2.value;
            curNode.idx = c2.idx;
        }
        updateTree(newValue, curIdx / 2, updateIdx);
    }

    public static int query(int startIdx, int endIdx) {
        Node ansNode = new Node(0, (int) (1e9) + 1);

        while(startIdx <= endIdx) {
            if(startIdx % 2 == 1) {
                if(ansNode.value > segTree[startIdx].value) {
                    ansNode.value =  segTree[startIdx].value;
                    ansNode.idx = segTree[startIdx].idx;
                }
                if(ansNode.value == segTree[startIdx].value && ansNode.idx > segTree[startIdx].idx) {
                    ansNode.idx = segTree[startIdx].idx;
                }
            }

            if(endIdx % 2 == 0) {
                if(ansNode.value > segTree[endIdx].value) {
                    ansNode.value =  segTree[endIdx].value;
                    ansNode.idx = segTree[endIdx].idx;
                }
                if(ansNode.value == segTree[endIdx].value && ansNode.idx > segTree[endIdx].idx) {
                    ansNode.idx = segTree[endIdx].idx;
                }
            }
            startIdx = (startIdx + 1) / 2;
            endIdx = (endIdx - 1) / 2;
        }
        return ansNode.idx;
    }
}
