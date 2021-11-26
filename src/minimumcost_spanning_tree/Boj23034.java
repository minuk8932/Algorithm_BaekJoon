package minimumcost_spanning_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 23034번: 조별 과제 멈춰!
 *
 * @see https://www.acmicpc.net/problem/23034
 *
 */
public class Boj23034 {

    private static Queue<Group> pq = new PriorityQueue<>();
    private static List<Group>[] path;

    private static int[] parent;
    private static int[][] except;

    private static int N;

    private static final String NEW_LINE = "\n";

    private static class Group {
        int node;
        int node1;
        int cost;

        public Group(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }

        public Group(int node, int node1, int cost) {
            this.node = node;
            this.node1 = node1;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        init();
        pq = new PriorityQueue<>(Comparator.comparingInt(grp -> grp.cost));

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken()) - 1;
            int B = Integer.parseInt(st.nextToken()) - 1;
            int C = Integer.parseInt(st.nextToken());

            pq.offer(new Group(A, B, C));
        }

        int total = mst();
        for(int i = 0; i < N; i++) {
            bfs(i);
        }

        StringBuilder sb = new StringBuilder();
        int Q = Integer.parseInt(br.readLine());

        while(Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            sb.append(total - except[x][y]).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    /**
     *
     * BFS
     *
     * line 101: Save max path for remove
     *
     * @param start
     */
    private static void bfs(int start) {
        boolean[] visit = new boolean[N];
        visit[start] = true;

        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);

        while(!q.isEmpty()) {
            int current = q.poll();

            for(Group next: path[current]) {
                if(visit[next.node]) continue;
                visit[next.node] = true;

                except[start][next.node] = Math.max(Math.max(except[start][next.node], except[start][current]), next.cost);
                q.offer(next.node);
            }
        }
    }

    private static int mst() {
        int cost = 0;

        while (!pq.isEmpty()) {
            Group current = pq.poll();

            if(merged(current.node1, current.node)) continue;
            cost += current.cost;
            path[current.node1].add(new Group(current.node, current.cost));
            path[current.node].add(new Group(current.node1, current.cost));
        }

        return cost;
    }

    private static void init() {
        parent = new int[N];
        except = new int[N][N];

        path = new ArrayList[N];

        for(int i = 0; i < N; i++) {
            parent[i] = -1;
            path[i] = new ArrayList<>();
        }
    }

    private static int find(int x) {
        if (parent[x] < 0) return x;
        return parent[x] = find(parent[x]);
    }

    private static boolean merged(int x, int y) {
        x = find(x);
        y = find(y);

        if(x == y) return true;

        if (parent[x] < parent[y]) {
            parent[x] += parent[y];
            parent[y] = x;
        }
        else {
            parent[y] += parent[x];
            parent[x] = y;
        }

        return false;
    }
}
