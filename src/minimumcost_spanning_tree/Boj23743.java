package minimumcost_spanning_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 23743번: 방 탈출
 *
 * @see https://www.acmicpc.net/problem/23743
 *
 */
public class Boj23743 {

    private static int[] parent;
    private static Queue<Node> warf;

    private static class Node {
        int node1;
        int node2;
        long cost;

        public Node(int node1, int node2, long cost) {
            this.node1 = node1;
            this.node2 = node2;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        warf = new PriorityQueue<>(Comparator.comparingLong(node -> node.cost));

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            warf.offer(new Node(a, b, c));
        }

        parent = new int[N + 1];
        parent[0] = -1;

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            warf.offer(new Node(i, 0, Long.parseLong(st.nextToken())));
            parent[i] = -1;
        }

        System.out.println(kruskal());
    }

    /**
     *
     * MST: zero is exit
     *
     * @return
     */
    private static long kruskal() {
        long total = 0;

        while(!warf.isEmpty()) {
            Node current = warf.poll();

            if(merged(current.node1, current.node2)) continue;
            total += current.cost;
        }

        return total;
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
