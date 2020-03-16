import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj5677 {
    private static ArrayList<Integer>[] tree;
    private static int[][] parent;
    private static int[] deep;
    private static int[] sets;

    private static int N;
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true){
            try {
                N = Integer.parseInt(br.readLine());
            }
            catch (Exception e){
                break;
            }

            init();
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++){
                int outbound = Integer.parseInt(st.nextToken()) - 1;
//                tree[i].add(outbound);
                tree[outbound].add(i);
            }

            for(int i = 0; i < N; i++) {
                if(deep[i] != Integer.MAX_VALUE) continue;
                deep[i] = 0;
                dfs(i, 0);
            }

//            for(int i = 0; i < N; i++){
//                System.out.print(deep[i] + " ");
//            }
//            System.out.println();

            connecting();

            int Q = Integer.parseInt(br.readLine());

            while(Q-- > 0){
                st = new StringTokenizer(br.readLine());
                int node1 = Integer.parseInt(st.nextToken()) - 1;
                int node2 = Integer.parseInt(st.nextToken()) - 1;

                if(find(node1) != find(node2)){
//                    sb.append(-1).append(NEW_LINE);
                    System.out.println(-1);
                }
                else {
                    int lca = LCA(node1, node2);
//                    System.out.println(node1 + " " + node2 + " " + lca);
//                    sb.append(deep[node1] + deep[node2] - 2 * deep[lca]).append(NEW_LINE);
                    System.out.println(deep[node1] + deep[node2] - 2 * deep[lca]);
                }
            }
        }

//        System.out.println(sb.toString());
    }

    private static void init(){
        tree = new ArrayList[N];
        parent = new int[N][21];
        deep = new int[N];
        sets = new int[N];

        for(int i = 0; i < N; i++){
            tree[i] = new ArrayList<>();
            sets[i] = -1;
            deep[i] = Integer.MAX_VALUE;
        }
    }

    private static int find(int x){
        if(sets[x] < 0) return x;
        return sets[x] = find(sets[x]);
    }

    private static void merge(int x, int y){
        x = find(x);
        y = find(y);

        if(x == y) return;

        if(sets[x] < sets[y]){
            sets[x] += sets[y];
            sets[y] = x;
        }
        else{
            sets[y] += sets[x];
            sets[x] = y;
        }
    }

    private static void dfs(int current, int depth){
        for(int next: tree[current]){
            if(deep[next] <= deep[current] + 1) continue;
            deep[next] = deep[current] + 1;

            parent[next][0] = current;
            merge(current, next);
            dfs(next, depth + 1);
        }
    }

    private static void connecting(){
        for(int p = 1; p < 21; p++){
            for(int cur = 0; cur < N; cur++){
                parent[cur][p] = parent[parent[cur][p - 1]][p - 1];
            }
        }
    }

    private static int LCA(int x, int y){
        if(deep[x] > deep[y]){
            int swap = x;
            x = y;
            y = swap;
        }

        for(int i = 20; i >= 0; i--){
            int jump = 1 << i;
            if(deep[y] - deep[x] >= jump) y = parent[y][i];
        }

        if(x == y) return x;

        for(int i = 20; i >= 0; i--){
            if(parent[x][i] == parent[y][i]) continue;

            x = parent[x][i];
            y = parent[y][i];
        }

        return parent[x][0];
    }
}
