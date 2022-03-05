import common.Node;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Boj9025 {

    private static final int INF = 1_000_000_000;
    private static final String NEW_LINE = "\n";

    private static List<Node>[] path;
    private static int[] dist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while(T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken()) - 1;
            int t = Integer.parseInt(st.nextToken()) - 1;

            init(n);

            int[][] inputs = new int[n][n];
            for(int i = 0; i < n; i++) {
                Arrays.fill(inputs[i], INF);
            }

            while(m-- > 0) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken()) - 1;
                int to = Integer.parseInt(st.nextToken()) - 1;
                int cost = Integer.parseInt(st.nextToken());

                inputs[from][to] = Math.min(cost, inputs[from][to]);
                inputs[to][from] = inputs[from][to];
            }

            for(int i = 0; i < n; i++) {
                for(int j = i + 1; j < n; j++) {
                    path[i].add(new Node.Builder(j).cost(inputs[i][j]).build());
                    path[j].add(new Node.Builder(i).cost(inputs[j][i]).build());
                }
            }

            dijkstra(s);
            sb.append(search(t)).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static int search(int snk) {
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node.Builder<Integer>(snk).cost(dist[snk]).build());

        int min = INF;

        while(!q.isEmpty()) {
            Node<Integer> current = q.poll();

            for (Node<Integer> next: path[current.getNode()]) {
                if(dist[next.getNode()] != dist[current.getNode()] - current.getCost()) continue;
                min = Math.min(min, -current.getCost());

                q.offer(new Node.Builder(next.getNode()).cost(dist[next.getNode()]).build());
            }
        }

        return min;
    }

    private static void dijkstra(int src) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(Node<Integer>::getCost));
        pq.offer(new Node.Builder(src).cost(0).build());
        Arrays.fill(dist, INF);

        dist[src] = 0;

        while(!pq.isEmpty()) {
            Node<Integer> current = pq.poll();
            if(dist[current.getNode()] < current.getCost()) continue;

            for(Node<Integer> next: path[current.getNode()]) {
                if(dist[next.getNode()] <= dist[current.getNode()] + next.getCost()) continue;
                dist[next.getNode()] = dist[current.getNode()] + next.getCost();

                pq.offer(new Node.Builder(next.getNode()).cost(dist[next.getNode()]).build());
            }
        }
    }

    private static void init(int n) {
        dist = new int[n];
        path = new ArrayList[n];

        for(int i = 0; i < n; i++){
            dist[i] = -1;
            path[i] = new ArrayList<>();
        }
    }
}
