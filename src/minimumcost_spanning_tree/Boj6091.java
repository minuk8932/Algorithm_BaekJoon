package minimumcost_spanning_tree;

import common.Node;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 6091번: 핑크 플로이드
 *
 * @see https://www.acmicpc.net/problem/6091
 *
 */
public class Boj6091 {

    private static Queue<Node<Integer>> pq;
    private static List<Integer>[] tree;
    private static int[] parent;

    private static final String SPACE = " ";
    private static final String NEW_LINE = "\n";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        pq = new PriorityQueue<>(Comparator.comparing(Node::getCost));
        init(N);

        for(int one = 0; one < N - 1; one++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int another = one + 1; another < N; another++) {
                int cost = Integer.parseInt(st.nextToken());
                pq.offer(new Node.Builder(one)
                        .cost(cost)
                        .another(another)
                .build());
            }
        }

        mst();
        System.out.println(treePrinter());
    }

    private static String treePrinter() {
        StringBuilder sb = new StringBuilder();

        for(int node = 0; node < tree.length; node++) {
            Collections.sort(tree[node]);
            sb.append(tree[node].size()).append(SPACE);

            for(int adj: tree[node]) {
                sb.append(adj + 1).append(SPACE);
            }

            sb.append(NEW_LINE);
        }

        return sb.toString();
    }

    private static void mst() {
        while(!pq.isEmpty()) {
            Node current = pq.poll();
            if(merged(current.getNode(), current.getAnother())) continue;

            tree[current.getNode()].add(current.getAnother());
            tree[current.getAnother()].add(current.getNode());
        }
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

    private static int find(int x) {
        if(parent[x] < 0) return x;
        return parent[x] = find(parent[x]);
    }

    private static void init(int n) {
        tree = new ArrayList[n];
        parent = new int[n];

        for(int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
            parent[i] = -1;
        }
    }
}
