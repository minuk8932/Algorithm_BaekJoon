package network_flow;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 11495번: 격자 0 만들기
 *
 * @see https://www.acmicpc.net/problem/11495
 *
 */
public class Boj11495 {
    private static ArrayList<Node>[] connection;
    private static int S, T, size = 2_502;
    private static int total;
    private static int N, M;
    private static final int INF = 1_000_000_000;

    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1;

    private static final String NEW_LINE = "\n";

    private static class Node {
        int node1;
        int node2;
        int capacity;
        int flow;
        Node rev;

        public Node(int node1, int node2, int capacity) {
            this.node1 = node1;
            this.node2 = node2;
            this.capacity = capacity;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while(test-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            S = size - 2;
            T = size - 1;

            connection = new ArrayList[size];
            for (int i = 0; i < size; i++) {
                connection[i] = new ArrayList<>();
            }

            int[][] matrix = new int[N][M];
            total = 0;
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());

                for(int j = 0; j < M; j++) {
                    matrix[i][j] = Integer.parseInt(st.nextToken());
                    total += matrix[i][j];
                }
            }

            graphModeling(matrix);
            sb.append(networkFlow()).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    /**
     *
     * Graph Modeling
     *
     * link nodes like chess board
     * white lattice: link with S, black lattice: link with T
     *
     * @param m
     */
    private static void graphModeling(int[][] m) {
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                int current = row * M + col;

                if((row + col) % 2 == 1){
                    linker(current, T, m[row][col]);
                    continue;
                }

                linker(S, current, m[row][col]);

                for (final int[] DIRECTION: DIRECTIONS) {
                    int adjRow = row + DIRECTION[ROW];
                    int adjCol = col + DIRECTION[COL];

                    if(outOfRange(adjRow, adjCol)) continue;
                    int adjacent = adjRow * M + adjCol;

                    linker(current, adjacent, INF);
                }
            }
        }
    }

    private static boolean outOfRange(int row, int col) {
        return row < 0 || row >= N || col < 0 || col >= M;
    }

    /**
     *
     * Edmonds Karp
     *
     * line 159
     *      if Array A is  0 3
     *                     2 4
     *
     *      then, total is 9 and maximum flow is 4, remained {0 1}
     *      but, we have to check {0, 1} is flow 1
     *      so, the flow which tied with zero can represented -> the reamined: total - (flow * 2)
     *      result is total - (flow * 2) + flow, therefore [total - flow]
     *
     * @return
     */
    private static int networkFlow() {
        int result = 0;

        while (true) {
            Node[] prev = new Node[size];
            Queue<Integer> q = new LinkedList<>();
            q.offer(S);

            while (!q.isEmpty()) {
                int current = q.poll();

                for (Node node : connection[current]) {
                    int next = node.node2;

                    if (node.capacity <= node.flow) continue;
                    if (prev[next] != null) continue;
                    prev[next] = node;

                    q.offer(next);
                }
            }

            if(prev[T] == null) break;

            int minFlow = Integer.MAX_VALUE;
            for (int i = T; i != S; i = prev[i].node1) {
                minFlow = Math.min(minFlow, prev[i].capacity - prev[i].flow);
            }

            for (int i = T; i != S; i = prev[i].node1) {
                prev[i].flow += minFlow;
                prev[i].rev.flow -= minFlow;
            }

            result += minFlow;
        }

        return total - result;
    }

    private static void linker(int from, int to, int cap) {
        Node node1 = new Node(from, to, cap);
        Node node2 = new Node(to, from, 0);
        node1.rev = node2;
        node2.rev = node1;

        node1.flow = 0;
        node2.flow = 0;

        connection[from].add(node1);
        connection[to].add(node2);
    }
}
