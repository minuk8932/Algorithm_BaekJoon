package dijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 16227번: 의약품 수송
 *
 * @see https://www.acmicpc.net/problem/16227/
 *
 */
public class Boj16227 {
    private static ArrayList<Node>[] path;
    private static int[][] cost;

    private static final int INF = 1_000_000_000;
    private static final int THRESHOLD = 100;
    private static final int WASH = 5;

    private static class Node implements Comparable<Node>{
        int node;
        int cost;
        int time;

        public Node(int node, int cost, int time) {
            this.node = node;
            this.cost = cost;
            this.time = time;
        }

        @Override
        public int compareTo(Node n) {
            return this.cost < n.cost ? -1: 1;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        path = new ArrayList[N + 2];
        cost = new int[N + 2][THRESHOLD + 1];
        for(int i = 0; i < path.length; i++) {
            path[i] = new ArrayList<>();
            Arrays.fill(cost[i], INF);
        }

        while(K-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            path[u].add(new Node(v, t, 0));
            path[v].add(new Node(u, t, 0));
        }

        System.out.println(delivery(0, N + 1));
    }

    private static int delivery(int S, int T) {
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.offer(new Node(S, 0, 0));

        cost[0][0] = 0;

        while(!q.isEmpty()){
            Node current = q.poll();

            if(current.cost > cost[current.node][current.time]) continue;
            if(current.node == T) return current.cost;

            for(Node next: path[current.node]) {
                int passCost = next.cost;

                if(passCost <= THRESHOLD && cost[next.node][passCost] > current.cost + next.cost + WASH) {      // washing
                    cost[next.node][passCost] = current.cost + passCost + WASH;
                    q.offer(new Node(next.node, cost[next.node][passCost], passCost));
                }

                passCost += current.time;
                if(passCost <= THRESHOLD && cost[next.node][passCost] > current.cost + next.cost) {             // move
                    cost[next.node][passCost] = current.cost + next.cost;
                    q.offer(new Node(next.node, cost[next.node][passCost], passCost));
                }
            }
        }

        return 0;
    }
}
