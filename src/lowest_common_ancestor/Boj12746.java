package lowest_common_ancestor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 12746번: Traffic (small)
 *
 * @see  https://www.acmicpc.net/problem/12746/
 *
 */
public class Boj12746 {
    private static ArrayList<Integer>[] tree;
    private static int[][] parent;
    private static int[] deep;
    private static int[] cost;

    private static boolean[] visit;
    private static int N;

    private static final long CIPHER = 1_000_000L;
    private static final String SPACE = " ";
    private static int result = -1;
    private static long index;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        tree = new ArrayList[N];
        for(int i = 0; i < N; i++){
            tree[i] = new ArrayList<>();
        }

        parent = new int[N][21];
        deep = new int[N];
        visit = new boolean[N];
        cost = new int[N];

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

            cost[node1]++;
            cost[node2]++;
            cost[LCA(node1, node2)] -= 2;               // make prefix sum
        }

        System.out.println(getResult());
    }

    private static void dfs(int current, int depth){
        deep[current] = depth;
        visit[current] = true;

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

    private static int LCA(int x, int y){                       // LCA
        if(deep[x] > deep[y]){
            int tmp = x;
            x = y;
            y = tmp;
        }

        for(int i = 20; i >= 0; i--){
            int jump = 1 << i;
            if(deep[y] - deep[x] >= jump) y = parent[y][i];     // set same level
        }

        if(x == y) return x;

        for(int i = 20; i >= 0; i--){                           // find ancestor
            if(parent[x][i] == parent[y][i]) continue;
            x = parent[x][i];
            y = parent[y][i];
        }

        return parent[x][0];
    }

    private static String getResult(){
        StringBuilder sb = new StringBuilder();
        filling(0);

        return sb.append(index / CIPHER + 1).append(SPACE).append(index % CIPHER + 1).append(SPACE).append(result).toString();
    }

    private static void filling(int current){
        for (int next : tree[current]) {
            if (next == parent[current][0]) continue;                   // pass same node
            filling(next);                                              // search next;

            long idx = current * CIPHER + next;
            if (current > next) idx = next * CIPHER + current;          // make index

            if (cost[next] > result || cost[next] == result && idx < index) {       // find max
                result = cost[next];
                index = idx;
            }
            cost[current] += cost[next];                 // add cost bottom up
        }
    }
}