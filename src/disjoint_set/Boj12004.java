package disjoint_set;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 12004번: Closing the farm(silver)
 *
 * @see https://www.acmicp.net/problem/12004
 *
 */
public class Boj12004 {

    private static int start;
    private static int N;
    private static int[] parent;

    private static List<Integer>[] graph;
    private static List<Integer> offline = new ArrayList<>();

    private static final int HOLDER = 0;
    private static final String CONNECTED = "YES\n";
    private static final String UNCONNECTED = "NO\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        init();

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            graph[node1].add(node2);
            graph[node2].add(node1);
        }

        StringBuilder sb = new StringBuilder();
        int query = N;

        while(query-- > 0) {
            int barn = Integer.parseInt(br.readLine());
            offline.add(barn);
            start = barn;
        }

        while(!offline.isEmpty()) {
            sb.append(fullyConnected());
            merge(HOLDER, offline.remove(0));
        }

        System.out.println(sb.toString());
    }

    /**
     *
     * Connection check
     *
     * @return
     */
    private static String fullyConnected() {
        boolean[] visit = new boolean[N + 1];

        Queue<Integer> q = new LinkedList<>();
        q.offer(start);

        visit[start] = true;
        int target = find(HOLDER);

        while(!q.isEmpty()) {
            int current = q.poll();

            for(int next: graph[current]) {
                int np = find(next);
                if(target == np) continue;
                if(visit[next]) continue;
                visit[next] = true;

                q.offer(next);
            }
        }

        for(int i = 1; i < visit.length; i++) {
            if(visit[i] || target == find(i)) continue;
            return UNCONNECTED;
        }

        return CONNECTED;
    }

    private static int find(int x) {
        if (parent[x] < 0) return x;
        return parent[x] = find(parent[x]);
    }

    private static void merge(int x, int y) {
        x = find(x);
        y = find(y);

        if(x == y) return;

        if(parent[x] < parent[y]) {
            parent[x] += parent[y];
            parent[y] = x;
        }
        else {
            parent[y] += parent[x];
            parent[x] = y;
        }
    }

    private static void init() {
        parent = new int[N + 1];
        graph = new ArrayList[N + 1];

        for(int i = 0; i < parent.length; i++) {
            parent[i] = -1;
            graph[i] = new ArrayList<>();
        }
    }
}
