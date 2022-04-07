import common.Node;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Boj9025 {

    private static final String NEW_LINE = "\n";
    private static final int INF = 1_000_000_000;

    private static List<Node<Integer, Integer>>[] path;

    private static int[][] input;
    private static int[] dist;
    private static int[] parent;

    private static PriorityQueue<Node<Integer, Integer>> pq;

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

            while(m-- > 0) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken()) - 1;
                int to = Integer.parseInt(st.nextToken()) - 1;
                int cost = Integer.parseInt(st.nextToken());

                input[from][to] = Math.min(input[from][to], cost);
                input[to][from] = input[from][to];
            }

            offline();
            mst();

            sb.append(dijkstra(s, t)).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static void offline() {
        for(int from = 0; from < input.length; from++) {
            for(int to = from + 1; to < input.length; to++) {
                if(input[from][to] == INF) continue;
                pq.offer(new Node.Builder(from).another(to).cost(-input[from][to]).build());
            }
        }
    }

    private static int dijkstra(int src, int snk) {
        int answer = INF;

        pq.offer(new Node.Builder(src).cost(0).build());
        dist[src] = 0;

        while(!pq.isEmpty()) {
            Node<Integer, Integer> current = pq.poll();

            for(Node<Integer, Integer> next: path[current.getNode()]) {
                if(dist[next.getNode()] <= dist[current.getNode()] + current.getCost()) continue;
                dist[next.getNode()] = dist[current.getNode()] + current.getCost();

                answer = Math.min(answer, next.getCost());
                if(next.getNode() == snk) return answer;

                pq.offer(new Node.Builder(next.getNode()).cost(dist[next.getNode()]).build());
            }
        }

        return answer;
    }

    private static void mst() {
        while(!pq.isEmpty()) {
            Node<Integer, Integer> current = pq.poll();

            if(merged(current.getNode(), current.getAnother())) continue;
            path[current.getNode()].add(
                new Node.Builder(current.getAnother())
                    .cost(-current.getCost())
                    .build());

            path[current.getAnother()].add(
                new Node.Builder(current.getNode())
                    .cost(-current.getCost())
                    .build());
        }
    }

    private static int find(int x) {
        if(parent[x] < 0) return x;
        return parent[x] = find(parent[x]);
    }

    private static boolean merged(int x, int y) {
        x = find(x);
        y = find(y);

        if(x == y) return true;

        if(parent[x] < parent[y]) {
            parent[x] += parent[y];
            parent[y] = x;
        }
        else {
            parent[y] += parent[x];
            parent[x] = y;
        }

        return false;
    }

    private static void init(int n) {
        path = new ArrayList[n];
        parent = new int[n];
        dist = new int[n];
        input = new int[n][n];

        pq = new PriorityQueue<>(Comparator.comparingInt(Node::getCost));

        for(int i = 0; i < n; i++){
            path[i] = new ArrayList<>();
            parent[i] = -1;
            dist[i] = INF;
            Arrays.fill(input[i], INF);
        }
    }
}
