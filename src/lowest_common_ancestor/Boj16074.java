package lowest_common_ancestor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 16074번: Mountaineers
 *
 * @see https://www.acmicpc.net/problem/16074
 *
 */
public class Boj16074 {

    private static int start;
    private static int N, M, size;
    private static PriorityQueue<Node> pq = new PriorityQueue<>();
    private static ArrayList<Edge>[] path;

    private static int[] parent;
    private static int[][] treeParent;
    private static int[][] cost;
    private static int[][] height;
    private static int[] deep;

    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1;
    private static final String NEW_LINE = "\n";

    private static class Node implements Comparable<Node>{
        int node1;
        int node2;
        int cost;

        public Node(int node1, int node2, int cost) {
            this.node1 = node1;
            this.node2 = node2;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node n) {
            return this.cost - n.cost;
        }
    }

    private static class Mountaineer {
        int from;
        int to;

        public Mountaineer(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }

    private static class Edge {
        int node;
        int cost;

        public Edge(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        size = N * M;

        height = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                height[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        graphModeling();
        kruskal();

        cost[start][0] = height[start / M][start % M];
        dfs(start, 0);
        connecting();

        StringBuilder sb = new StringBuilder();

        while(Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken()) - 1;
            int y1 = Integer.parseInt(st.nextToken()) - 1;
            int x2 = Integer.parseInt(st.nextToken()) - 1;
            int y2 = Integer.parseInt(st.nextToken()) - 1;

            Mountaineer mt = new Mountaineer(x1 * M + y1, x2 * M + y2);
            int LCA = lca(mt);
            int result = Math.max(height[x1][y1], height[x2][y2]);

            sb.append(Math.max(findMaxCost(deep[LCA], mt), result)).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    /**
     *
     * Find max value in path
     * find each nodes depth from lca & check all value of path
     *
     * @param depth
     * @param mt
     * @return
     */
    private static int findMaxCost(int depth, Mountaineer mt) {
        int max = 0;

        int x = mt.from;
        int y = mt.to;
        int xx = deep[x] - depth;
        int yy = deep[y] - depth;

        for( int p = 20; p >= 0; p--){
            int shift = 1 << p;

            if(shift <= xx){
                xx -= shift;

                max = Math.max(max, cost[x][p]);
                x = treeParent[x][p];
            }

            if(shift <= yy){
                yy -= shift;

                max = Math.max(max, cost[y][p]);
                y = treeParent[y][p];
            }
        }

        return max;
    }

    /**
     *
     * find LCA
     *
     * @param mt
     * @return
     */
    private static int lca(Mountaineer mt) {
        int x = mt.from;
        int y = mt.to;

        if(deep[x] > deep[y]){
            int tmp = x;
            x = y;
            y = tmp;
        }

        for(int i = 20; i >= 0; i--){
            int jump = 1 << i;
            if(deep[y] - deep[x] >= jump) y = treeParent[y][i];
        }

        if(x == y) return x;

        for(int i = 20; i >= 0; i--){
            if(treeParent[x][i] == treeParent[y][i]) continue;
            x = treeParent[x][i];
            y = treeParent[y][i];
        }

        return treeParent[x][0];
    }

    /**
     *
     * Make tree by kruskal & get first node for depth
     *
     */
    private static void kruskal() {
        start = -1;

        while(!pq.isEmpty()) {
            Node current = pq.poll();

            if(merged(current.node1, current.node2)) continue;
            if(start == -1) start = current.node1;
            path[current.node1].add(new Edge(current.node2, current.cost));
            path[current.node2].add(new Edge(current.node1, current.cost));
        }
    }

    /**
     *
     * Graph modeling by adjacent nodes
     *
     */
    private static void graphModeling() {
        parent = new int[size];
        treeParent = new int[size][21];
        deep = new int[size];
        path = new ArrayList[size];
        cost = new int[size][21];

        for(int i = 0; i < size; i++) {
            parent[i] = -1;
            deep[i] = -1;
            path[i] = new ArrayList<>();
        }

        for(int row = 0; row < N; row++) {
            for(int col = 0; col < M; col++) {
                int current = row * M + col;

                for(final int[] DIRECTION: DIRECTIONS) {
                    int adjRow = row + DIRECTION[ROW];
                    int adjCol = col + DIRECTION[COL];

                    if (outOfRange(adjRow, adjCol)) continue;
                    int adjacent = adjRow * M + adjCol;

                    pq.offer(new Node(current, adjacent, Math.max(height[adjRow][adjCol], height[row][col])));
                }
            }
        }
    }

    private static boolean outOfRange(int row, int col) {
        return row < 0 || row >= N || col < 0 || col >= M;
    }

    private static int find(int x) {
        if(parent[x] < 0) return x;
        return parent[x] = find(parent[x]);
    }

    private static boolean merged(int x, int y) {
        x = find(x);
        y = find(y);

        if(x == y) return true;

        if(parent[x] < parent[y]) {
            parent[x] += parent[y];
            parent[y] = x;
        }
        else {
            parent[y] += parent[x];
            parent[x] = y;
        }

        return false;
    }

    private static void dfs(int current, int depth){
        deep[current] = depth;

        for(Edge next: path[current]){
            if(deep[next.node] > 0) continue;
            treeParent[next.node][0] = current;
            cost[next.node][0] = Math.max(height[current / M][current % M], height[next.node / M][next.node % M]);

            dfs(next.node, depth + 1);
        }
    }

    private static void connecting(){
        for(int p = 1; p < 21; p++){
            for(int cur = 0; cur < size; cur++){
                treeParent[cur][p] = treeParent[treeParent[cur][p - 1]][p - 1];
                cost[cur][p] = Math.max(cost[cur][p - 1], cost[treeParent[cur][p - 1]][p - 1]);
            }
        }
    }
}
