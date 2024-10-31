import java.io.*;
import java.util.*;

public class Main
{
    static int N;
    static int Q;
    static long[] segTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        //세그트리를 만들어줘야한다.
        int h = (int) Math.ceil(Math.log(N) / Math.log(2)) + 1;
        int maxSize = (int) Math.pow(2, h);

        segTree = new long[maxSize + 1];
        st = new StringTokenizer(br.readLine());

        for(int i = maxSize / 2; i < maxSize / 2  + N; i++) {
            segTree[i] = Integer.parseInt(st.nextToken());
        }

        init(1, maxSize / 2);



        for(int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken()) + (maxSize/2)-1 ;
            int e = Integer.parseInt(st.nextToken()) + (maxSize/2)-1;
            if(s > e) {
                int temp = s;
                s = e;
                e = temp;
            }
            System.out.println(query(s,e));
            int idx = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            segTree[idx + (maxSize/2)-1 ] = value;
            update((idx + (maxSize / 2) - 1) / 2);
        }
    }

    public static void init(int curNode, int maxSize) {
        if(curNode >= maxSize) return;

        init(curNode * 2, maxSize);
        init(curNode * 2 + 1, maxSize);

        segTree[curNode] = segTree[curNode * 2] + segTree[curNode * 2 + 1];
    }

    public static long query(int startIdx, int endIdx) {
        long sum = 0;

        while(startIdx <= endIdx) {
            if(startIdx % 2 == 1) {
                // 왼쪽 자식 노드 선택
                sum += segTree[startIdx];
            }
            startIdx = (startIdx + 1) / 2;

            if(endIdx % 2 == 0) {
                // 오른쪽 자식 노드를 골라야하는 경우
                sum += segTree[endIdx];
            }
            endIdx = (endIdx -1) / 2;
        }

        return sum;
    }

    //TODO: Update 쿼리 만들어야함
    public static void update(int idx) {
        if(idx <= 0) return;
        segTree[idx] = segTree[idx * 2] + segTree[idx * 2 + 1];
        update(idx / 2);
    }
}
