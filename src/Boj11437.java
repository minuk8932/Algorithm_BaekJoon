import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 11437번: LCA
 *
 * @see https://www.acmicpc.net/problem/11437/
 *
 */
public class Boj11437 {
    private static ArrayList<Integer>[] tree;
    private static int[][] parent;
    private static int[] deep;
    private static boolean[] visit;
    private static int N;

    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        tree = new ArrayList[N];
        visit = new boolean[N];
        parent = new int[N][20];                // [current][parentLevel]
        deep = new int[N];
        for(int i = 0; i < N; i++){
            tree[i] = new ArrayList<>();
        }

        StringTokenizer st = null;
        int loop = N - 1;
        while(loop-- > 0){
            st = new StringTokenizer(br.readLine());
            int node1= Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;

            tree[node1].add(node2);
            tree[node2].add(node1);
        }

        dfs(0, 0);                  // find adjacent parent & depth
        connecting();                             // find adjacent parent & connecting

        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine());

        while(M-- > 0){
            st = new StringTokenizer(br.readLine());
            int node1= Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;

            sb.append(lca(node1, node2) + 1).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static void dfs(int current, int depth){
        visit[current] = true;
        deep[current] = depth;

        for(int next: tree[current]){
            if(visit[next]) continue;

            parent[next][0] = current;
            dfs(next, depth + 1);
        }
    }

    private static void connecting(){
        for(int p = 1; p < 20; p++){
            for(int cur = 0; cur < N; cur++){
                parent[cur][p] = parent[parent[cur][p - 1]][p - 1];
            }
        }
    }

    private static int lca(int x, int y){
        if(deep[x] > deep[y]) {                 // find the deeper
            int tmp = x;
            x = y;
            y = tmp;
        }

        for(int i = 19; i >= 0; i--){
            int jump = 1 << i;
            if(deep[y] - deep[x] >= jump) y = parent[y][i];     // can path compressed?
        }

        if(x == y) return x;                                    // get lca ?

        for(int i = 19; i >= 0; i--){
            if(parent[x][i] != parent[y][i]) {                  // if they no same, find ancestor to upper level
                x = parent[x][i];
                y = parent[y][i];
            }
        }

        return parent[x][0];                                    // get lca
    }
}
