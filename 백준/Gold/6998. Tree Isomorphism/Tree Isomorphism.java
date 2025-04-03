import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;


public class Main {
    static int N;
    static ArrayList<Node> tree1;
    static ArrayList<Node> tree2;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            tree1 = new ArrayList<>();
            tree2 = new ArrayList<>();
            int level = 0;
            int idx = 0;
            boolean flag = false;
            int p = 0;
            int max1 = 0;
            int max2 = 0;
            while(st.hasMoreTokens()){
                char c = st.nextToken().charAt(0);
                max1 = Math.max(max1, level);
                if(c == '#') {
                    level--;
                    if(flag){
                        p = tree1.get(p).p;
                    }
                    else{
                        flag = true;
                        p = tree1.get(tree1.size() - 1).p;
                    }
                    continue;
                }

                if(level != 0) {
                    if(!flag) {
                        p = tree1.get(tree1.size() - 1).idx;

                    }

                    tree1.get(p).child++;
                    tree1.add(new Node(level++, c , p , idx++));
                    flag = false;
                }
                else{
                    tree1.add(new Node(level++, c ,  -1, idx++));
                }

            }

            st = new StringTokenizer(br.readLine());

            level = 0;
            idx = 0;
            flag = false;
            p = 0;

            while(st.hasMoreTokens()){
                char c = st.nextToken().charAt(0);
                max2 = Math.max(max2, level);
                if(c == '#') {
                    level--;
                    if(flag){
                        p = tree2.get(p).p;
                    }
                    else{
                        flag = true;
                        p = tree2.get(tree2.size() - 1).p;
                    }
                    continue;
                }

                if(level != 0) {
                    if(!flag) {
                        p = tree2.get(tree2.size() - 1).idx;

                    }
                    tree2.get(p).child++;
                    tree2.add(new Node(level++, c , p , idx++));
                    flag = false;
                }
                else{
                    tree2.add(new Node(level++, c ,  -1, idx++));
                }
            }
            boolean con = true;
            // 동형 체크
            if(max1 != max2) {
                // 동형 아님
                con = false;

            } else{
                ArrayList<Integer>[] g1 = new ArrayList[max1];
                ArrayList<Integer>[] g2 = new ArrayList[max2];

                for(int k =0 ; k < max1; k++) {
                    g1[k] = new ArrayList<>();
                    g2[k] = new ArrayList<>();
                }

                for(Node n : tree1){
                    int lev = n.level;

                    g1[lev].add(n.child);
                }
                for(Node n : tree2){
                    int lev = n.level;
                    g2[lev].add(n.child);
                }



                for(int k =0 ; k < max1; k++) {
                    Collections.sort(g1[k]);
                    Collections.sort(g2[k]);
                    if(!con) break;
                    if(g1[k].size() != g2[k].size()){
                        //동형 아님
                        con = false;
                    }
                    else{
                        for(int ii = 0; ii < g1[k].size(); ii++){
                            if(g1[k].get(ii) != g2[k].get(ii)){
                                //동형 아님
                                con = false;
                                break;
                            }
                        }
                    }
                }
                
            }
            if(con) {
                //동형
                sb.append("The two trees are ").append("isomorphic.").append("\n");
            }
            else{
                sb.append("The two trees are ").append("not isomorphic.").append("\n");
            }



        }
        System.out.println(sb);


    }

    static class Node {
        int level;
        char alpa;
        int child;
        int p;
        int idx;

        Node(int l, char a, int p, int i) {
            level = l;
            alpa = a;
            child = 0;
            this.p = p;
            idx = i;
        }
    }
}