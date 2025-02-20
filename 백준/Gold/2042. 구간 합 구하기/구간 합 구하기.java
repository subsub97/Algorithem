import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static long[] segTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int h = (int) Math.ceil((Math.log(N) / Math.log(2)));

        segTree = new long[1 << (h + 1)];

        for(int i = 0; i < N; i++) {
            segTree[(1 << h) + i] = Long.parseLong(br.readLine());
        }

        //TODO 쿼리 입력 받기
        initSegTree(1, h);

        for(int i =0; i < M+K; i++) {
            st = new StringTokenizer(br.readLine());

            int cmd = Integer.parseInt(st.nextToken());
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());

            if(cmd == 1) {
                // update
                updateSegTree(b, 1, 1, (1 << h), a, a);
            }
            else{
                System.out.println(query(1, 1, (1 << h), a, b));
            }
        }
    }


    private static long initSegTree(int idx, int h) {
        if(idx >= (1 << h)) return segTree[idx];
        return segTree[idx] =  initSegTree(idx * 2, h) + initSegTree(idx * 2 + 1, h);
    }

    private static void updateSegTree(long num,int idx,int s, int e, long l, long r) {
        //update l,r 을 찾으러 ㄱㄱ

        // 범위 밖인지?
        if(e < l || s > r) return;

        //범위 내부인지?
        if(l <= s && e <= r) {
            //내가 찾던 곳?
            segTree[idx] = num;
            return;
        }

        int mid = (s + e) / 2;

        updateSegTree(num, idx * 2, s, mid, l, r);
        updateSegTree(num, idx * 2 + 1, mid + 1, e, l, r);

        segTree[idx] = segTree[idx * 2] + segTree[idx * 2 + 1];
    }


    private static long query(int idx, int s, int e, long l, long r) {
        if(e < l || s > r) return 0L;

        if(l <= s && e <= r) {
            // 선택하고자하는 구간이라면
            return segTree[idx];
        }

        int mid = (s + e) / 2;

        return query(idx * 2, s, mid, l,r) + query(idx * 2 + 1, mid + 1, e,l,r);
    }

}