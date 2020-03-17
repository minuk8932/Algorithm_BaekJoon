import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj18670 {
    private static ArrayList<Integer>[] tree;
    private static int[][] parent;
    private static int[] deep;
    private static int[] prev;
    private static int[] post;
    private static int[] tied;
    private static boolean[] visit;

    private static int N;
    private static final String COLORING = "+";
    private static final String NEW_LINE = "\n";

    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while(T-- > 0){
            N = Integer.parseInt(br.readLine());

            init();

            int loop = N - 1;
            while(loop-- > 0){
                StringTokenizer st = new StringTokenizer(br.readLine());
                int node1 = Integer.parseInt(st.nextToken());
                int node2 = Integer.parseInt(st.nextToken());

                tree[node1].add(node2);
                tree[node2].add(node1);
            }

            dfs(0, 0);
            connecting();

            int prevNode = -1;
            int Q = Integer.parseInt(br.readLine());

            while(Q-- > 0){
                StringTokenizer st = new StringTokenizer(br.readLine());
                String cmd = st.nextToken();
                int node = Integer.parseInt(st.nextToken());

                if(cmd.equals(COLORING)){
                    if(prevNode != -1) post[prevNode] = node;
                    prev[node] = prevNode;

                    int lca = LCA(prev[node] == -1 ? node: tied[prev[node]], node);
                    tied[node] = lca;

                    sb.append(lca).append(NEW_LINE);
                    prevNode = node;
                }
                else{
                    if(prev[node] == -1 && post[node] == -1){
                        sb.append(node).append(NEW_LINE);
                        continue;
                    }

                    int lca = LCA(tied[prev[node] == -1 ? post[node]: prev[node]], prev[node] == -1 ? post[node]: prev[node]);
                    sb.append(lca).append(NEW_LINE);
                    prevNode = prev[node];
                }
            }
        }

        System.out.println(sb.toString());
    }

    private static void init(){
        tree = new ArrayList[N];
        parent = new int[N][21];
        deep = new int[N];
        visit = new boolean[N];
        prev = new int[N];
        post = new int[N];
        tied = new int[N];

        for(int i = 0; i < N; i++){
            tree[i] = new ArrayList<>();
            prev[i] = -1;
            post[i] = -1;
            tied[i] = -1;
        }
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
