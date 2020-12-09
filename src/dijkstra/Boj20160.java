package dijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 20160번: 야쿠르트 아줌마 야쿠르트 주세요
 *
 * @see https://www.acmicpc.net/problem/20160
 *
 */
public class Boj20160 {
    private static ArrayList<Node>[] path;
    private static long[] consumer;
    private static long[] producer;
    private static HashMap<Long, Long> data = new HashMap<>();

    private static final long CIPHER = 10_000L;
    private static final long INF = 1_000_000_000_000L;

    private static class Node implements Comparable<Node>{
        int node;
        long cost;

        public Node(int node, long cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node n) {
            return this.cost < n.cost ? -1: 1;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        path = new ArrayList[V];
        consumer = new long[V];
        producer = new long[V];
        for(int i = 0; i < V; i++) {
            path[i] = new ArrayList<>();
            consumer[i] = INF;
            producer[i] = -1;
        }

        while(E-- > 0) {
            st = new StringTokenizer(br.readLine());
            long u = Long.parseLong(st.nextToken()) - 1;
            long v = Long.parseLong(st.nextToken()) - 1;
            long w = Long.parseLong(st.nextToken());

            long idx = u * CIPHER + v;
            long idx1 = v * CIPHER + u;

            if(data.containsKey(idx) || data.containsKey(idx1)){
                data.put(idx, Math.min(w, data.get(idx)));
                data.put(idx1, Math.min(w, data.get(idx1)));
            }
            else{
                data.put(idx, w);
                data.put(idx1, w);
            }
        }

        for(long key: data.keySet()) {              // duplicated node removed
            int u = (int) (key / CIPHER);
            int v = (int) (key % CIPHER);

            path[u].add(new Node(v, data.get(key)));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken()) - 1;
        producer[start] = 0;

        for(int i = 0; i < 9; i++) {
            int end = Integer.parseInt(st.nextToken()) - 1;

            long result = dijkstra(start, end);      // get the shortest distance of yogurt seller
            if(result == -1) continue;

            producer[end] = Math.max(result + producer[start], producer[end]);      // accumulation
            start = end;
        }

        bfs(Integer.parseInt(br.readLine()) - 1);
        System.out.println(compare());
    }

    private static int compare() {
        for(int i = 0; i < producer.length; i++) {                      // buy yogurt possibility
            if(producer[i] == -1 || consumer[i] == INF) continue;
            if(producer[i] < consumer[i]) continue;
            return i + 1;
        }

        return -1;
    }

    private static void bfs(int start) {                // consumer move
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(start, 0));

        consumer[start] = 0;

        while(!q.isEmpty()) {
            Node current = q.poll();

            for(Node next: path[current.node]) {
                if(consumer[next.node] <= consumer[current.node] + next.cost) continue;
                consumer[next.node] = consumer[current.node] + next.cost;

                q.offer(new Node(next.node, consumer[next.node]));
            }
        }
    }

    private static long dijkstra(int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        long[] dist = new long[producer.length];
        Arrays.fill(dist, INF);

        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()) {
            Node current = pq.poll();
            if(dist[current.node] < current.cost) continue;

            for(Node next: path[current.node]) {
                if(dist[next.node] <= dist[current.node] + next.cost) continue;
                dist[next.node] = dist[current.node] + next.cost;

                pq.offer(new Node(next.node, dist[next.node]));
            }
        }

        return dist[end] == INF ? -1L: dist[end];
    }
}
