import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Boj14283 {

    private static ArrayList<Integer>[] connection;
    private static int[][] flow;
    private static int[][] capacity;
    private static int[][] weight;

    private static int N;
    private static int S, T;
    private static int[] prev;

    private static Pair min;
    private static ArrayList<Pair> pairs;
    private static int result;

    private static class Pair {
        int prev;
        int current;
        int cost;

        public Pair(int prev, int current, int cost) {
            this.prev = prev;
            this.current = current;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        connection = new ArrayList[N];
        capacity = new int[N][N];
        flow = new int[N][N];
        weight = new int[N][N];
        pairs = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            connection[i] = new ArrayList<>();
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken()) - 1;
            int node2 = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());
            result += cost;

            linker(node1, node2, cost);
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken()) - 1;
        T = Integer.parseInt(st.nextToken()) - 1;

        networkFlow();
        System.out.println(result);
    }

    /**
     * linker
     *
     * line 63 ~ 64: bidirection capacity
     *
     * @param from
     * @param to
     * @param cap
     */
    private static void linker(int from, int to, int cap) {
        connection[from].add(to);
        connection[to].add(from);
        capacity[from][to] += cap;
        capacity[to][from] += cap;
        weight[from][to] += cap;
        weight[to][from] += cap;
    }

    /**
     * Edmonds karp
     *
     * @return
     */
    private static void networkFlow() {
        prev = new int[N];
        boolean[][] visit = new boolean[N][N];

        while (true) {
            Arrays.fill(prev, -1);

            Queue<Integer> q = new LinkedList<>();
            q.offer(S);

            while (!q.isEmpty()) {
                int current = q.poll();

                for (int next : connection[current]) {
                    if (capacity[current][next] <= flow[current][next]) continue;
                    if (prev[next] != -1) continue;

                    prev[next] = current;
                    q.offer(next);
                }
            }

            if (prev[T] == -1) break;

            int minFlow = Integer.MAX_VALUE;
            for (int i = T; i != S; i = prev[i]) {
                minFlow = Math.min(minFlow, capacity[prev[i]][i] - flow[prev[i]][i]);

                if (visit[prev[i]][i]) continue;
                visit[prev[i]][i] = true;
                pairs.add(new Pair(prev[i], i, weight[prev[i]][i]));
            }

            for (int i = T; i != S; i = prev[i]) {
                flow[prev[i]][i] += minFlow;
                flow[i][prev[i]] -= minFlow;
            }
        }

        while (true) {
            min = new Pair(-1, -1, Integer.MAX_VALUE);

            if (linked()) break;
            result -= min.cost;
            flow[min.prev][min.current] = 0;
        }
    }

    private static boolean linked() {
        for (Pair p : pairs) {
            if (flow[p.prev][p.current] <= 0) continue;
            Pair except = new Pair(p.prev, p.current, weight[p.prev][p.current]);
            Arrays.fill(prev, -1);

            Queue<Integer> q = new LinkedList<>();
            q.offer(S);

            while (!q.isEmpty()) {
                int current = q.poll();

                for (int next : connection[current]) {
                    if (flow[current][next] <= 0) continue;
                    if (prev[next] != -1) continue;
                    if (except.prev == current && except.current == next) continue;

                    prev[next] = current;
                    q.offer(next);
                }
            }

            if (prev[T] == -1) return true;
            if (min.cost > except.cost) min = new Pair(except.prev, except.current, except.cost);
        }

        return false;
    }
}
