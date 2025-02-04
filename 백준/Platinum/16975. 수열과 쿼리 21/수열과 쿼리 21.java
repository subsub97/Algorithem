import java.io.*;
import java.util.*;

public class Main {
    static int N,M;
    static long[] seg;
    static long[] temp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int size = (int) Math.ceil(Math.log(N)/Math.log(2)) + 1;

        seg = new long[1 << size];
        temp = new long[1 << size];

        for(int i =0; i < N; i++) {
            int a = Integer.parseInt(st.nextToken());
            seg[(1<<(size-1)) + i] = a;
        }

        init(1, 1 << (size-1));

        M = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        int start = (1 << size-1) - 1;

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int cmd = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken()) + start;

            if(cmd == 1) {
                //구간 업데이트
                int b = Integer.parseInt(st.nextToken()) + start;
                int k = Integer.parseInt(st.nextToken());

                query(a,b,k);
            }
            else {
                sb.append(find(a,start + 1) +"\n");
            }
        }

        System.out.println(sb);
    }

    private static void init(int idx, int size) {
        if(idx >= (size)) return;

        init(idx * 2, size);
        init(idx * 2 + 1,size);

        seg[idx] = seg[idx * 2] + seg[idx * 2 + 1];
    }

    // 이 메서드는 하나하나 원소들 값 올려줄 때 사용하기
    private static void update(int idx ,int left,int right,int updateIdx,int size) {
        // 구간의 최소 원소는 하지말고 해당 상위 구간에 업데이트를 해야할 듯 ?
        if(idx >= size*2) {
            return;
        }

        if(Math.abs(temp[idx]) > 0) {
            long addValue = temp[idx];
            long midValue = addValue / 2L;
            temp[idx] = 0;

            if(idx < size) {
                temp[idx * 2] += midValue;
                temp[idx * 2 + 1] += midValue;
            }
            seg[idx] += addValue;
        }

        if(idx < size) {
            if(left <= updateIdx && updateIdx <= (left+right)/2) {
                update(idx * 2, left, (left+right)/2, updateIdx, size);
            }
            else{
                update(idx * 2 + 1, (left+right)/2 + 1, right, updateIdx, size);
            }
        }


    }

    private static void query(int a, int b, long addValue) {
        long bValue = addValue;

        while(a <= b) {
            if(a % 2 == 1) {
                temp[a] += addValue;
            }

            addValue *= 2;
            a = (a + 1) / 2;

            if(b % 2 == 0) {
                temp[b] += bValue;
            }

            bValue *= 2;
            b = (b - 1)/ 2;
        }
    }



    private static long find(int idx, int start) {
        update(1, 1, start, idx-start+1, start);
        return seg[idx];
    }

}

