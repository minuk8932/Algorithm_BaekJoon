package dijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.IntStream;

/**
 *
 * @author exponential-e
 * 백준 22865번: 가장 먼 곳
 *
 * @see https://www.acmicpc.net/problem/22865
 *
 */
public class Boj22865 {

    private static List<Node> home = new ArrayList<>();
    private static List<Node>[] path;
    private static int[][] cost;
    private static int A, B, C;

    private static final int INF = 2_000_000_000;

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
        Function<String, Integer> parser = Integer::parseInt;
        int N = parser.apply(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        A = parser.apply(st.nextToken()) - 1;
        B = parser.apply(st.nextToken()) - 1;
        C = parser.apply(st.nextToken()) - 1;

        init(N);
        int M = parser.apply(br.readLine());

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int D = parser.apply(st.nextToken()) - 1;
            int E = parser.apply(st.nextToken()) - 1;
            int L = parser.apply(st.nextToken());

            path[D].add(new Node(E, L));
            path[E].add(new Node(D, L));
        }

        BiFunction<Integer, Integer, int[][]> array = (p, n) -> new int[p][n];
        cost = array.apply(3, N);
        for(int i = 0; i < 3; i++) {
            Arrays.fill(cost[i], INF);
        }

        dijkstra(A, cost[0]);
        dijkstra(B, cost[1]);
        dijkstra(C, cost[2]);

        getMin();

        home.stream().min((x, y) -> {
            if (x.cost == y.cost) return x.node - y.node;
            return y.cost - x.cost;
        })
        .map(n -> n.node + 1)
        .ifPresent(System.out::println);
    }

    /**
     *
     * Get min
     *
     * line 91: A or B or C is same with target ? then, continue
     *
     */
    private static void getMin() {
        int[] arr = {A, B, C};
        int len = cost[0].length;

        for(int i = 0; i < cost[0].length; i++) {
            final int index = i;
            if(IntStream.of(arr).anyMatch(value -> value == index)) continue;

            Node min = new Node(len, INF);
            for(int j = 0; j < arr.length; j++) {
                if(min.cost < cost[j][i]) continue;

                if(min.cost == cost[j][i]) min.node = Math.min(min.node, i);
                else min.node = i;

                min.cost = cost[j][i];
            }

            home.add(new Node(min.node, min.cost == INF ? -1: min.cost));
        }
    }

    private static void dijkstra(int start, int[] cost){
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.cost));
        pq.offer(new Node(start, 0));
        cost[start] = 0;

        while(!pq.isEmpty()) {
            Node current = pq.poll();
            if(current.cost > cost[current.node]) continue;

            for(Node next: path[current.node]) {
                if(cost[next.node] <= cost[current.node] + next.cost) continue;
                cost[next.node] = cost[current.node] + next.cost;

                pq.offer(new Node(next.node, cost[next.node]));
            }
        }
    }

    private static void init(int n) {
        path = new ArrayList[n];

        for(int i = 0; i < n; i++) {
            path[i] = new ArrayList<>();
        }
    }
}
