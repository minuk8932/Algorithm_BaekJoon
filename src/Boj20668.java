import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj20668 {

    private static ArrayList<Node>[] path;
    private static long[] dist;
    private static long[] result;

    private static final int CIPHER = 20_000;
    private static final long INF = Long.MAX_VALUE;
    private static final long TEN = 10_000_000L;

    private static final String DOT = ".";

    private static class Node implements Comparable<Node> {
        int node;
        long velo;

        public Node(int node, long velo) {
            this.node = node;
            this.velo = velo;
        }

        @Override
        public int compareTo(Node n) {
            return this.velo < n.velo ? -1 : 1;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        path = new ArrayList[N + CIPHER * 20];
        dist = new long[path.length];
        for (int i = 0; i < path.length; i++) {
            path[i] = new ArrayList<>();
            dist[i] = INF;
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken()) + 1;
            int B = Integer.parseInt(st.nextToken()) + 1;
            long L = Long.parseLong(st.nextToken());
            long K = Long.parseLong(st.nextToken());

            L *= TEN;

            for(int limit = 1; limit <= 10; limit++) {
                int node = (CIPHER * limit) + A;

                if (limit <= K) {
                    builder(node, (CIPHER * limit) + B, L / limit);
                }
                if (limit < K) {
                    builder(node, (CIPHER * (limit + 1)) + B, L / (limit + 1));
                }
                if (1 < limit && limit <= K + 1) {
                    builder(node, (CIPHER * (limit - 1)) + B, L / (limit - 1));
                }
            }
        }

        dijkstra(CIPHER + 2);
        System.out.println(maker(N));
    }

    private static void builder(int from, int to, long velocity){
        path[from].add(new Node(to, velocity));
        path[to].add(new Node(from, velocity));
    }

    private static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            if (dist[current.node] < current.velo) continue;

            for (Node next : path[current.node]) {
                if (dist[next.node] <= next.velo + dist[current.node]) continue;
                dist[next.node] = next.velo + dist[current.node];
                pq.offer(new Node(next.node, dist[next.node]));
            }
        }
    }

    private static String maker(int n) {
        result = new long[11];
        long min = INF;
        for (int i = 1; i < result.length; i++) {
            int idx = n + 1 + (CIPHER * i);
            min = Math.min(min, dist[idx]);
        }

        long head = min / TEN;
        long tail = min % TEN;

        result[0] = head;
        for (int i = 1; i < result.length; i++) {
            tail *= 10L;
            head = tail / TEN;
            tail %= TEN;

            result[i] = head;
        }

        int carry = 0;
        if (result[10] >= 5) carry = rounding();

        StringBuilder sb = new StringBuilder();
        sb.append(result[0] + carry).append(DOT);
        for (int i = 1; i <= 9; i++) {
            sb.append(result[i]);
        }

        return sb.toString();
    }

    private static int rounding() {
        for (int i = 9; i > 0; i--) {
            result[i] += 1L;
            if(result[i] != 10) return 0;

            result[i] = 0;
        }

        return 1;
    }
}
