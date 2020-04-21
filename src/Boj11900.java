import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj11900 {
    private static final String NEW_LINE = "\n";
    private static final int INF = 2_000_000_000;

    private static int N;
    private static int[] A;
    private static int[] dist;

    private static boolean[] flag = new boolean[3];

    private static class Node implements Comparable<Node> {
        int node;
        int cost;

        public Node(int node, int cost) {
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
        N = Integer.parseInt(br.readLine());
        A = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i < A.length; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            if (A[i] == 0) A[i] = INF;
        }

        makeGraph();

        StringBuilder sb = new StringBuilder();
        int Q = Integer.parseInt(br.readLine());
        int half = N / 2;

        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            int diff = s - e;
            if(diff < 0) diff += N;

            if(half == diff && N % 2 == 0){
                sb.append(Math.abs(dist[e] - dist[s])).append(NEW_LINE);
            }
            else {
                if (diff < half && A[diff] != INF) {                 // 역방향

                }
                else if (diff > half && A[diff] != INF) {            // 정방향

                }
                else {
                    sb.append(-1).append(NEW_LINE);
                }
            }

            sb.append(1).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static void makeGraph() {
        dist = new int[N];
        Arrays.fill(dist, INF);

        dist[0] = 0;
        int prev = 0;
        int size = N;

        while(size > 0) {
            int idx = 0;

            for(int i = 1; i < N; i++) {
                if(A[i] == INF) continue;

                dist[size - i] = Math.min(A[i] + dist[prev], dist[size - i]);
                prev = size - i;
                idx = i;
                break;
            }

            size -= idx;
        }

        dijkstra(0);

        for(int i = 0; i < N; i++) {
            System.out.print(dist[i] + " ");
        }
    }

    private static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        dist[start] = 0;

        while(!pq.isEmpty()) {
            Node current = pq.poll();

            for(int next = 0; next < N; next++) {
                if(current.node == next) continue;
                int diff = current.node - next;

                if(diff < 0) diff += N;
                if(A[diff] == INF) continue;

                if(dist[next] <= dist[current.node] + A[diff]) continue;
                dist[next] = dist[current.node] + A[diff];

                pq.offer(new Node(next, dist[next]));
            }
        }
    }
}
