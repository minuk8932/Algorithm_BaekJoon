package minimumcost_spanning_tree;

import common.Node;
import java.io.*;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 10661번: Median Tree
 *
 * @see https://www.acmicpc.net/problem/10661
 *
 */
public class Boj10661 {

    private static final String NEW_LINE = "\n";
    private static PriorityQueue<Node<Integer, Integer>> pq;
    private static int[] parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            if(n == 0 && m == 0) break;

            init(n);

            pq = new PriorityQueue<>(Comparator.comparingInt(Node::getCost));
            while(m-- > 0) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken()) - 1;
                int t = Integer.parseInt(st.nextToken()) - 1;
                int c = Integer.parseInt(st.nextToken());

                pq.offer(new Node.Builder<Integer, Integer>(s).another(t).cost(c).build());
            }

            sb.append(kruskal()).append(NEW_LINE);
        }

        System.out.println(sb);
    }

    private static int kruskal() {
        ArrayList<Integer> costs = new ArrayList<>();

        while(!pq.isEmpty()) {
            Node<Integer, Integer> current = pq.poll();
            if(merged(current.getNode(), current.getAnother())) continue;

            costs.add(current.getCost());
        }

        int size = costs.size();
        return costs.get(size >> 1);
    }

    private static void init(int n) {
        parent = new int[n];
        Arrays.fill(parent, -1);
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
