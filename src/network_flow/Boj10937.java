package network_flow;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 10937번: 두부 모판 자르기
 *
 * @see https://www.acmicpc.net/problem/10937
 *
 */
public class Boj10937 {

    private static final int[][] TOFU = {{100, 70, 40, 0},
            {70, 50, 30, 0},
            {40, 30, 20, 0},
            {0, 0, 0, 0}};

    private static int N;

    private static ArrayList<Node>[] connection;
    private static int S, T, size = 2_502;

    private static final int INF = 1_000_000_000;

    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1;

    private static class Node {
        int node1;
        int node2;
        int cost;
        int capacity;
        int flow;
        Node rev;

        public Node(int node1, int node2, int cost, int capacity) {
            this.node1 = node1;
            this.node2 = node2;
            this.cost = cost;
            this.capacity = capacity;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        size = N * N + 2;
        S = size - 2;
        T = size - 1;

        char[][] board = new char[N][N];
        for(int i = 0; i < N; i++) {
            String line = br.readLine();

            for(int j = 0; j < N; j++) {
                char c = line.charAt(j);
                board[i][j] = c == 'F' ? 'D': c;
            }
        }

        graphModeling(board);
        System.out.println(minimumCostMaximumFlow());
    }

    /**
     *
     * MCMF
     *
     * line 90 ~ 106: SPFA
     *
     * @return
     */
    private static int minimumCostMaximumFlow() {
        Node[] prev;
        int[] weight = new int[size];
        int result = 0;

        while(true) {
            Arrays.fill(weight, -INF);
            boolean[] inQ = new boolean[size];
            prev = new Node[size];

            Queue<Integer> q = new LinkedList<>();

            q.offer(S);
            weight[S] = 0;
            inQ[S] = true;

            while(!q.isEmpty()) {
                int current = q.poll();
                inQ[current] = false;

                for(Node node: connection[current]) {
                    int next = node.node2;

                    if (weight[next] >= weight[current] + node.cost) continue;
                    if(node.capacity <= node.flow) continue;
                    weight[next] = weight[current] + node.cost;
                    prev[next] = node;

                    if(inQ[next]) continue;
                    q.offer(next);
                    inQ[next] = true;
                }
            }

            if(weight[T] < 0) break;

            int maxFlow = Integer.MAX_VALUE;
            for (int i = T; i != S; i = prev[i].node1) {
                maxFlow = Math.min(maxFlow, prev[i].capacity - prev[i].flow);
            }

            for (int i = T; i != S; i = prev[i].node1) {
                result += prev[i].cost * maxFlow;
                prev[i].flow += 1;
                prev[i].rev.flow -= 1;
            }
        }

        return result;
    }

    /**
     *
     * Graph Modeling
     *
     * line 145 ~ 161: make chess board connection :: S -> white, black -> T
     *
     * @param arr
     */
    private static void graphModeling(char[][] arr) {
        connection = new ArrayList[size];
        for(int i = 0; i < size; i++) {
            connection[i] = new ArrayList<>();
        }

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                int current = row * N + col;

                if((row + col) % 2 == 1){
                    linker(current, T, 0, 1);
                    continue;
                }

                linker(S, current, 0, 1);

                for (final int[] DIRECTION: DIRECTIONS) {
                    int adjRow = row + DIRECTION[ROW];
                    int adjCol = col + DIRECTION[COL];

                    if(outOfRange(adjRow, adjCol)) continue;
                    int adjacent = adjRow * N + adjCol;

                    linker(current, adjacent, TOFU[arr[row][col] - 'A'][arr[adjRow][adjCol] - 'A'], 1);
                    linker(adjacent, current, -TOFU[arr[row][col] - 'A'][arr[adjRow][adjCol] - 'A'], 0);
                }
            }
        }
    }

    private static boolean outOfRange(int row, int col) {
        return row < 0 || row >= N || col < 0 || col >= N;
    }

    private static void linker(int from, int to, int cost, int cap) {
        Node node1 = new Node(from, to, cost, cap);
        Node node2 = new Node(to, from, -cost, 0);
        node1.rev = node2;
        node2.rev = node1;

        node1.flow = 0;
        node2.flow = 0;

        connection[from].add(node1);
        connection[to].add(node2);
    }
}
