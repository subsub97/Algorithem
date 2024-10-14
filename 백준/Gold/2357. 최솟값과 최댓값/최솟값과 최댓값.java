public class Main {
    static int N, M;
    static long[] numbers;
    static long[][] segTree;

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();

        N = read();
        M = read();

        numbers = new long[N];

        for(int i = 0; i < N; i++) {
            numbers[i] = read();
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
            int startIdx = read();
            int endIdx = read();
            int gap = (int)Math.pow(2, height-1)-1;

            long[] result = query(startIdx + gap,endIdx + gap);
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

    public static long[] query(int startIdx, int endIdx) {
        long[] arr = new long[2];
        arr[0] =  Long.MAX_VALUE;
        arr[1] =  0;

        while(startIdx <= endIdx) {
            if(startIdx % 2 == 1) {
                //해당 노드를 선택한다.
                arr[0] = Math.min(arr[0], segTree[startIdx][0]);
                arr[1] = Math.max(arr[1], segTree[startIdx][1]);
            }
            startIdx = (startIdx + 1) / 2;
            if(endIdx % 2 == 0) {
                arr[0] = Math.min(arr[0], segTree[endIdx][0]);
                arr[1] = Math.max(arr[1], segTree[endIdx][1]);

            }
            endIdx = (endIdx - 1) / 2;
        }

        return arr;
    }
        private static int read() throws Exception {
        int d, o;
        boolean negative = false;
        d = System.in.read();

        if (d == '-') {
            negative = true;
            d = System.in.read();
        }
        o = d & 15;

        while ((d = System.in.read()) > 32)
            o = (o << 3) + (o << 1) + (d & 15);

        return negative? -o:o;
    }
}
