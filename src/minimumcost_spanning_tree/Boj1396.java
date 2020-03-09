package minimumcost_spanning_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 1396번: 크루스칼의 공
 *
 * @see https://www.acmicpc.net/problem/1396/
 *
 */
public class Boj1396 {
    private static ArrayList<Integer>[] tree;
    private static int[][] parent;
    private static int[] deep;
    private static int[] set;
    private static int[] cost;
    private static int[] count;
    private static boolean[] visit;
    private  static PriorityQueue<MST> pq = new PriorityQueue<>();

    private static int n, m, N;
    private static final String NEW_LINE = "\n";
    private static final String SPACE = " ";

    private static class MST implements Comparable<MST> {
        int node1;
        int node2;
        int cost;

        public MST(int node1, int node2, int cost){
            this.node1 = node1;
            this.node2 = node2;
            this.cost = cost;
        }

        @Override
        public int compareTo(MST m) {
            return this.cost < m.cost ? -1: 1;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        init();

        while(m-- > 0){
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());

            pq.offer(new MST(node1, node2, cost));
        }

        for(int i = n; i < N; i++){             // additional nodes
            MST current = pq.poll();

            int x = find(current.node1);
            int y = find(current.node2);
            if(x == y) continue;

            tree[i].add(x);                      // if they are set, tied with additional node
            tree[i].add(y);
            count[i] = count[x] + count[y];

            cost[i] = current.cost;

            int a = find(i);

            merge(a, x);
            merge(a, y);
        }

        for (int i = N - 1; i >= 0; i--) {      // set depth, may be forest
            if (deep[i] != 0) continue;
            dfs(N - 1, 0);
        }

        connecting();

        int Q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(Q-- > 0){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            if(find(x) != find(y)){
                sb.append(-1).append(NEW_LINE);
                continue;
            }

            int ancestor = lca(x, y);
            sb.append(cost[ancestor]).append(SPACE).append(count[ancestor]).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static void init(){
        N = n + m;
        tree = new ArrayList[N];

        parent = new int[N][21];
        count = new int[N];
        deep = new int[N];
        set = new int[N];
        cost = new int[N];
        visit = new boolean[N];

        for(int i = 0; i < N; i++){
            set[i] = -1;
            tree[i] = new ArrayList<>();
            count[i] = 1;
        }
    }

    private static int find(int x){
        if(set[x] < 0) return x;
        return set[x] = find(set[x]);
    }

    private static void merge(int x, int y){
        if(x == y) return;
        set[x] += set[y];
        set[y] = x;
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

    private static int lca(int x, int y){
        if(deep[x] > deep[y]){
            int tmp = x;
            x = y;
            y = tmp;
        }

        for(int i = 20; i >= 0; i--){                       // match level
            int jump = 1 << i;
            if(deep[y] - deep[x] >= jump) y = parent[y][i];
        }

        if(x == y) return x;

        for(int i = 20; i >= 0; i--){                       // find lca
            if(parent[x][i] == parent[y][i]) continue;
            x = parent[x][i];
            y = parent[y][i];
        }

        return parent[x][0];
    }
}
