import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj12746 {
    private static ArrayList<Integer>[] tree;
    private static int[][] parent;
    private static int[] deep;
    private static int[] count;
    private static boolean[] visit;
    private static int[] target;

    private static int N;
    private static final String SPACE = " ";

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        tree = new ArrayList[N];
        parent = new int[N][21];
        deep = new int[N];
        visit = new boolean[N];
        count = new int[N];
        target = new int[N];

        for(int i = 0; i < N; i++){
            tree[i] = new ArrayList<>();
        }

        int loop = N - 1;
        while(loop-- > 0){
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;

            tree[node1].add(node2);
            tree[node2].add(node1);
        }

        dfs(0, 0);
        connecting();

        while(Q-- > 0){
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;

            target[lca(node1, node2)] = Math.min(node1, node2);
        }

        System.out.println(getMax());
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

    private static void connecting() {
        for(int p = 1; p < 21; p++){
            for(int c = 0; c < N; c++){
                parent[c][p] = parent[parent[c][p - 1]][p - 1];
            }
        }
    }

    private static int lca(int x, int y){
        if(deep[x] > deep[y]){
            int tmp = x;
            x = y;
            y = tmp;
        }

        for(int i = 20; i >= 0; i--){
            int jump = 1 << i;
            if(deep[y] - deep[x] >= jump){
                y = parent[y][i];
            }
        }

        if(x == y) return x;

        for(int i = 20; i >= 0; i--){
            if(parent[x][i] == parent[y][i]) continue;
            x = parent[x][i];
            y = parent[y][i];
        }

        return parent[x][0];
    }

    private static String getMax(){
        int max = 0;
        int[] node = new int[2];

        for(int c = 0; c < N; c++){
            System.out.println(target[c]);
        }

        for(int i = 0; i < N; i++){
            if(max < count[i]) max = count[i];
        }

        int idx = 0;
        for(int i = 0; i < N; i++){
            if(idx == 2) break;
            if(max == count[i]){
                node[idx++] = (i + 1);
            }
        }

        StringBuilder sb = new StringBuilder();
        return sb.append(node[0]).append(SPACE).append(node[1]).append(SPACE).append(max).toString();
    }
}
