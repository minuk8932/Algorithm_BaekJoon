package minimumcost_spanning_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 20010번: 악덕 영주 혜유
 *
 * @see https://www.acmicpc.net/problem/20010
 *
 */
public class Boj20010 {
    private static PriorityQueue<Village> pq = new PriorityQueue<>();
    private static ArrayList<Node>[] path;
    private static int[] parent;
    private static int[] visit;
    private static int N;

    private static final String NEW_LINE = "\n";

    private static class Village implements Comparable<Village>{
        int node1;
        int node2;
        int cost;

        public Village(int node1, int node2, int cost) {
            this.node1 = node1;
            this.node2 = node2;
            this.cost = cost;
        }

        @Override
        public int compareTo(Village v) {
            return this.cost - v.cost;
        }
    }

    private static class Node {
        int node;
        int cost;

        public Node(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        init();

        while(K-- > 0) {
            st = new StringTokenizer(br.readLine());
            pq.offer(new Village(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())
                , Integer.parseInt(st.nextToken())));
        }

        System.out.println(getCost());
    }

    private static String getCost() {
        StringBuilder sb = new StringBuilder();
        sb.append(mst()).append(NEW_LINE).append(longestCost());

        return sb.toString();
    }

    private static int mst() {
        int cost = 0;

        while(!pq.isEmpty()) {
            Village current = pq.poll();
            if(merged(current.node1, current.node2)) continue;

            path[current.node1].add(new Node(current.node2, current.cost));     // make graph by mst
            path[current.node2].add(new Node(current.node1, current.cost));
            cost += current.cost;
        }

        return cost;
    }

    private static int longestCost() {
        int max = 0;

        bfs(0);                         // find deepest node

        int index = -1;
        for(int i = 0; i < N; i++) {
            if(visit[i] > max) {
                max = visit[i];
                index = i;
            }
        }

        bfs(index);                         // get tree's diameter

        for(int v: visit) {
            max = Math.max(v, max);
        }

        return max - 1;
    }

    private static void bfs(int s) {
        Queue<Node> q = new LinkedList<>();
        visit = new int[N];

        q.offer(new Node(s, 1));
        visit[s] = 1;

        while(!q.isEmpty()) {
            Node current = q.poll();

            for (Node next: path[current.node]) {
                if(visit[next.node] != 0) continue;
                visit[next.node] = visit[current.node] + next.cost;

                q.offer(new Node(next.node, visit[next.node]));
            }
        }
    }

    private static void init() {
        parent = new int[N];
        path = new ArrayList[N];

        for (int i = 0; i < N; i++) {
            parent[i] = -1;
            path[i] = new ArrayList<>();
        }
    }

    private static int find(int x) {
        if (parent[x] < 0) return x;
        return parent[x] = find(parent[x]);
    }

    private static boolean merged (int x, int y) {
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
}
