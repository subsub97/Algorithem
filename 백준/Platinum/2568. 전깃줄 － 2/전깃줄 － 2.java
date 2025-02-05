import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] eline = new int[500_001];
    static int[] eline2 = new int[500_001];
    static int[] arr;
    static int[] lis;
    static int[] record;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        lis = new int[N];
        int max = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int right = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken());


            eline[right] = left;
            eline2[left] = right;
            max = Math.max(max, left);
            max = Math.max(max, right);
        }

        int cnt = 0;

        for (int i = 0; i <= max; i++) {
            if(eline[i] != 0) {
                arr[cnt++] = eline[i];
            }
        }

        int lIdx = 0;
        int aIdx = 1;

        ArrayList<Integer> list = new ArrayList<>();
        record = new int[N];
        lis[0] = arr[0];
        record[0] = 1;

        while(aIdx < N) {
            if(lis[lIdx] < arr[aIdx]) {
                lis[++lIdx] = arr[aIdx];
                record[aIdx] = lIdx+1;
            }
            else{
                int idx = binarySearch(0, lIdx, arr[aIdx]);
                lis[idx] = arr[aIdx];
                record[aIdx] = idx+1;
            }
            aIdx++;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(N - lIdx - 1 + "\n");

        int maxLen = lIdx+1;
        for(int i = N-1 ; i >= 0 ; i--) {
            if(record[i] == maxLen){
                maxLen--;
                continue;
            }
            list.add(eline2[arr[i]]);
        }

        Collections.sort(list);
        for(Integer i : list) {
            sb.append(i + "\n");
        }
        System.out.print(sb);

    }

    static int binarySearch(int left, int right, int target) {
        int mid;

        while(left < right) {
            mid = (left + right) / 2;

            if(lis[mid] < target) {
                left = mid + 1;
            }
            else{
                right = mid;
            }
        }
        return right;
    }
}
