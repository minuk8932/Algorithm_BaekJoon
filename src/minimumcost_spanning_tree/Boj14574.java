package minimumcost_spanning_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 14574번: 헤븐스 키친
 *
 * @see https://www.acmicpc.net/problem/14574
 *
 */
public class Boj14574 {
    private static int[] parent;
    private static boolean[] visit;

    private static PriorityQueue<Node> pq = new PriorityQueue<>();
    private static ArrayList<Integer>[] path;
    private static StringBuilder sb = new StringBuilder();

    private static final String SPACE = " ";
    private static final String NEW_LINE = "\n";

    private static class Chief {
        int skill;
        int popular;

        public Chief(int skill, int popular) {
            this.skill = skill;
            this.popular = popular;
        }
    }

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
            return this.cost > n.cost ? -1: 1;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Chief[] chiefs = new Chief[N];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            chiefs[i] = new Chief(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        init(N, chiefs);
        mst();

        System.out.println(sb.toString());
    }

    private static void mst(){
        long cost = 0;

        while(!pq.isEmpty()) {
            Node current = pq.poll();
            if(merged(current.node1, current.node2)) continue;

            path[current.node1].add(current.node2);
            path[current.node2].add(current.node1);
            cost = cost + current.cost;
        }

        sb.append(cost).append(NEW_LINE);
        recursion(0);
    }

    private static void recursion(int current) {
        visit[current] = true;

        for (int next: path[current]) {             // make list of match
            if(visit[next]) continue;

            recursion(next);
            sb.append(current + 1).append(SPACE).append(next + 1).append(NEW_LINE);
        }
    }

    private static void init(int n, Chief[] c) {
        parent = new int[n];
        path = new ArrayList[n];
        visit = new boolean[n];

        for(int i = 0; i < n; i++) {
            parent[i] = -1;
            path[i] = new ArrayList<>();
        }

        for(int i = 0; i < n; i++) {                // make relation
            for(int j = i + 1; j < n; j++) {
                pq.offer(new Node(i, j, (c[i].popular + c[j].popular) / (Math.abs(c[i].skill - c[j].skill))));
            }
        }
    }

    private static int find(int x) {
        if(parent[x] < 0) return x;
        else return parent[x] = find(parent[x]);
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
}
