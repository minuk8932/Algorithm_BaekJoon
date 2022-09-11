package minimumcost_spanning_tree;

import common.Node;
import java.io.*;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 13151번: Model Railroad
 *
 */
public class Boj13151 {

    private static int[] parent;
    private static PriorityQueue<Node<Integer, Long>> pq = new PriorityQueue<>(
        Comparator.comparingLong(Node::getCost)
    );

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        init(n);

        long previewCost = 0;
        while(m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            long c = Long.parseLong(st.nextToken());

            Node<Integer, Long> current =
                new Node.Builder<Integer, Long>(a).another(b).cost(c).build();

            pq.offer(current);

            l--;
            if(l < 0) continue;
            previewCost += c;
        }

        System.out.println(previewCost >= kruskal() && isAllConnected() ? "possible": "impossible");
    }

    private static boolean isAllConnected() {
        int count = 0;

        for(int p: parent) {
            if(p >= 0) continue;
            count++;
        }

        return count == 1;
    }

    private static long kruskal() {
        long cost = 0;

        while(!pq.isEmpty()) {
            Node<Integer, Long> current = pq.poll();
            if(merged(current.getNode(), current.getAnother())) continue;

            cost += current.getCost();
        }

        return cost;
    }

    private static void init(int n) {
        parent = new int[n];
        Arrays.fill(parent, - 1);
    }

    private static int find(int x) {
        if (parent[x] < 0) return x;
        return parent[x] = find(parent[x]);
    }

    private static boolean merged(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) return true;

        if (parent[x] < parent[y]) {
            parent[x] += parent[y];;
            parent[y] = x;
        }
        else {
            parent[y] += parent[x];
            parent[x] = y;
        }

        return false;
    }
}
