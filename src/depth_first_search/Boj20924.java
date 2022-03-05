package depth_first_search;

import common.Node;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.function.BiPredicate;

/**
 *
 * @author exponential-e
 * 백준 20924번: 트리의 기둥과 가지
 *
 * @see https://www.acmicpc.net/problem/20924
 *
 */
public class Boj20924 {

    private static List<Node>[] tree;

    private static int[] cost;

    private static int R;
    private static int giga = -1;
    private static int stem;
    private static boolean gigaFound;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken()) - 1;

        init(N);
        int loop = N - 1;

        while (loop-- > 0) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());

            tree[node1].add(new Node.Builder<Integer>(node2)
                    .cost(cost)
                    .build());
            tree[node2].add(new Node.Builder<Integer>(node1)
                    .cost(cost)
                    .build());
        }

        recursion(-1, new Node.Builder<Integer>(R)
                .cost(0)
                .build());
        costTracker(giga);

        System.out.println(stem + " " + getMax());
    }

    private static void costTracker(int current) {

        for(Node<Integer> next: tree[current]) {
            if(cost[next.getNode()] != -1) continue;
            cost[next.getNode()] = cost[current] + next.getCost();

            costTracker(next.getNode());
        }
    }

    private static int getMax() {
        int max = 0;

        for(int i = 0; i < cost.length; i++) {
            max = Math.max(cost[i], max);
        }

        return max;
    }

    /**
     *
     * Recursion
     *
     * line 85 ~ 89: when giga not checked
     * line 91: giga node finder
     *
     * @param prev
     * @param current
     */
    private static void recursion(int prev, Node<Integer> current) {
        if(!gigaFound){
            giga = current.getNode();
            stem += current.getCost();
            cost[current.getNode()] = 0;
        }

        gigaFound |= GIGA_FOUNDER.test(tree[current.getNode()].size(), current.getNode());

        for(Node next: tree[current.getNode()]) {
            if(next.getNode() == prev) continue;

            recursion(current.getNode(), next);
        }
    }

    private static void init(int n) {
        tree = new ArrayList[n];
        cost = new int[n];

        for(int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
            cost[i] = -1;
        }
    }

    private static final BiPredicate<Integer, Integer> GIGA_FOUNDER = (size, node) -> size > 2 || (size == 2 && node == R);
}
