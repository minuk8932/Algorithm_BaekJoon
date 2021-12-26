import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Boj9025 {

    private static final int INF = 1_000_000_000;
    private static final String NEW_LINE = "\n";

    private static List<Integer>[] path;
    private static int[] dist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while(T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            init(n);

            int[][] inputs = new int[n + 1][n + 1];
            for(int i = 0; i < n; i++) {
                Arrays.fill(inputs[i], INF);
            }

            while(m-- > 0) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                inputs[from][to] = Math.min(cost, inputs[from][to]);
                inputs[to][from] = inputs[from][to];
                path[from].add(to);
                path[to].add(from);

                if (from == s) dist[to] = cost;
                if (to == s) dist[from] = cost;
            }

            sb.append(search(n, s, t, inputs)).append(NEW_LINE);
        }

        br.close();
        System.out.println(sb.toString());
    }

    private static int search(int n, int src, int snk, int[][] inputs) {
        boolean[] visit = new boolean[n + 1];
        int current = src;

        for (int node = 0; node < n; node++) {
            visit[current] = true;

            for (int next: path[node]) {
                if (next == src) continue;

                if (dist[current] < inputs[current][next]) {
                    if (dist[next] >= dist[current]) continue;
                    dist[next] = dist[current];
                }
                else {
                    if(dist[next] >= inputs[current][next]) continue;
                    dist[next] = inputs[current][next];
                }
            }

            int nextNode = 1;
            while (nextNode <= n && visit[nextNode]) {
                nextNode++;
            }

            for (int next = nextNode; next <= n; next++) {
                if (visit[next] || dist[nextNode] > dist[next]) continue;
                nextNode = next;
            }

            current = nextNode;
        }

        return dist[snk];
    }

    private static void init(int n) {
        dist = new int[n + 1];
        path = new ArrayList[n + 1];

        for(int i = 0; i <= n; i++){
            dist[i] = -1;
            path[i] = new ArrayList<>();
        }
    }
}
