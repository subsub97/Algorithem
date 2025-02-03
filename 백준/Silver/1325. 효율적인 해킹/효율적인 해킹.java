import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Main {
    static int N, M;
    static ArrayList<Integer>[] arr;
    static boolean isVisited[];
    static int max;
    static int[] outDegree;
    static int[] parent;
    static int[] cnt;
    static boolean[] used;
    static Set<Integer> cycleIdx = new HashSet<>();


    static private int dfs(int start) {
        isVisited[start] = true;

        int cnt = 0;

        for (int i : arr[start]) {
            if (isVisited[i]) continue;
            cnt  += dfs(i);
        }

        return cnt + 1;
    }


    public static void main(String[] args) throws Exception {
        N = read();
        M = read();


        outDegree = new int[N + 1];
        parent = new int[N + 1];
        cnt = new int[N+1];
        used = new boolean[N + 1];

        max = 0;

        // 신뢰 관계 입력
        arr = new ArrayList[N + 1];

        for (int i = 0; i < N + 1; i++) {
            arr[i] = new ArrayList<Integer>();
            parent[i] = i;
        }


        for (int i = 0; i < M; i++) {
            int a = read();
            int b = read();

            // a가 b를 신뢰한다면 a 진출차수 증가
            union(a,b);
            outDegree[a]++;
            arr[b].add(a);
        }

        ArrayList<Integer> result = new ArrayList<>();

        for(int i =1; i <= N; i++) {

            if(outDegree[i] == 0) {
                isVisited = new boolean[N + 1];
                int curMax = dfs(i);

                if(curMax > max ) {
                    max =curMax;
                    result = new ArrayList<>();
                    result.add(i);
                }
                else if(curMax == max) {
                    result.add(i);
                }
            }
            cnt[find(i)]++;
        }


        for(Integer idx : cycleIdx) {
            if(outDegree[find(idx)] == 0) continue;

            boolean[] checked = new boolean[N + 1];

            if(cnt[idx] >= max) {
                for(int j = 1; j <=N; j++) {
                    isVisited = new boolean[N + 1];

                    if(find(idx) == find(j) && !checked[j]){
                        int curMax = dfs(j);

                        if(curMax > max ) {
                            max = curMax;
                            result = new ArrayList<>();
                            ArrayList<Integer> cycleNodes = findCycleNodes(j);

                            for(Integer k : cycleNodes) {
                                checked[k] = true;
                            }

                            result.addAll(cycleNodes);
                        }
                        else if(curMax == max) {
                            ArrayList<Integer> cycleNodes = findCycleNodes(j);

                            for(Integer k : cycleNodes) {
                                checked[k] = true;
                            }

                            result.addAll(cycleNodes);
                        }
                    }
                }
            }
        }


//        for(Integer idx : cycleIdx) {
//            if(outDegree[find(idx)] == 0) continue;
//
//            if(cnt[idx] >= max) {
//
//                isVisited = new boolean[N + 1];
//
//                int curMax = dfs(idx);
//
//                if(curMax > max ) {
//                    max = curMax;
//                    result = new ArrayList<>();
//                    ArrayList<Integer> cycleNodes = findCycleNodes(idx);
//                    result.addAll(cycleNodes);
//                }
//                else if(curMax == max) {
//                    ArrayList<Integer> cycleNodes = findCycleNodes(idx);
//                    result.addAll(cycleNodes);
//                }
//
//            }
//        }


        StringBuilder sb = new StringBuilder();

        Collections.sort(result);

        for(Integer idx : result) {
            if(used[idx]) continue;
            used[idx] = true;
            sb.append(idx + " ");
        }

        System.out.println(sb);

    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if(x == y) {
            cycleIdx.add(x);
        }

        if(x != y) {
            if(x < y) parent[y] = x;
            else parent[x] = y;
        }
    }

    private static int find(int x ) {
        if(parent[x] == x) return x;
        return find(parent[x]);
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

        return negative ? -o : o;
    }

    static ArrayList<Integer> findCycleNodes(int start) {
        ArrayList<Integer> cycleNodes = new ArrayList<>();
        boolean[] visited = new boolean[N + 1];
        boolean[] inStack = new boolean[N + 1];
        ArrayList<Integer> path = new ArrayList<>();

        dfsForCycle(start, start, visited, inStack, path, cycleNodes);

        return cycleNodes;
    }

    static void dfsForCycle(int curr, int start, boolean[] visited, boolean[] inStack,
                            ArrayList<Integer> path, ArrayList<Integer> cycleNodes) {
        visited[curr] = true;
        inStack[curr] = true;
        path.add(curr);

        for(int next : arr[curr]) {
            if(!visited[next]) {
                dfsForCycle(next, start, visited, inStack, path, cycleNodes);
            }
            // start 노드로 돌아오는 사이클만 찾기
            else if(next == start && inStack[next] && cycleNodes.isEmpty()) {
                int startIdx = path.indexOf(next);
                for(int i = startIdx; i < path.size(); i++) {
                    cycleNodes.add(path.get(i));
                }
            }
        }

        path.remove(path.size() - 1);
        inStack[curr] = false;
    }

}