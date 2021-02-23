package network_flow;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 8992번: 집기 게임
 *
 * @see https://www.acmicpc.net/problem/8992
 *
 */
public class Boj8992 {

    private static int[][] capacity;
    private static int[][] cost;
    private static int[][] flow;

    private static int S, T, size, half;
    private static int flows, result;

    private static ArrayList<Integer>[] connection;

    private static final int INF = 1_000_000_000;
    private static final String NEW_LINE = "\n";
    private static final String SPACE = " ";

    private static class Coordinate {
        int idx;
        int from;
        int to;
        int fix;
        int cost;

        public Coordinate(int idx, int from, int to, int fix, int cost) {
            this.idx = idx;
            this.from = from;
            this.to = to;
            this.fix = fix;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while(T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            half = n + m;
            size = (half << 1) + 2;

            ArrayList<Coordinate> horizontal = new ArrayList<>();
            int node = 1;

            while(n-- > 0) {
                st = new StringTokenizer(br.readLine());
                int x1 = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int x2 = Integer.parseInt(st.nextToken());
                st.nextToken();
                int c = Integer.parseInt(st.nextToken());

                horizontal.add(new Coordinate(node++, Math.min(x1, x2), Math.max(x1, x2), y, c));
            }

            ArrayList<Coordinate> vertical = new ArrayList<>();
            while(m-- > 0) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y1 = Integer.parseInt(st.nextToken());
                st.nextToken();
                int y2 = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                vertical.add(new Coordinate(node++, Math.min(y1, y2), Math.max(y1, y2), x, c));
            }

            graphModeling(horizontal, vertical);
            flows = 0;
            result = 0;

            minimumCostMaximumFlow();
            sb.append(flows).append(SPACE).append(result).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    /**
     *
     * Graph Modeling
     *
     * line 121 ~ 129: If intersect then link, ref. Boj2311
     *
     * @param hors
     * @param vers
     */
    private static void graphModeling(ArrayList<Coordinate> hors, ArrayList<Coordinate> vers) {
        S = 0;
        T = size - 1;

        capacity = new int[size][size];
        flow = new int[size][size];
        cost = new int[size][size];
        connection = new ArrayList[size];
        for(int i = 0; i < size; i++) {
            connection[i] = new ArrayList<>();
        }

        for(int i = 1; i <= half; i++) {
            linker(S, i, 0, 1);
            linker(i + half, T, 0, 1);
        }

        for(Coordinate h: hors) {
            for(Coordinate v: vers) {
                if(outOfRange(h, v) || outOfRange(v, h)) continue;

                linker(h.idx, v.idx + half, -h.cost * v.cost, 1);
            }
        }
    }

    private static void linker(int from, int to, int c, int cap) {
        connection[from].add(to);
        connection[to].add(from);

        capacity[from][to] = cap;
        cost[from][to] = c;
        cost[to][from] = -c;
    }

    private static boolean outOfRange(Coordinate c1, Coordinate c2) {
        return c1.from > c2.fix || c1.to < c2.fix;
    }

    /**
     *
     * MCMF
     *
     * line 165 ~ 179: SPFA
     *
     */
    private static void minimumCostMaximumFlow() {
        int[] prev = new int[size];
        int[] dist = new int[size];

        while(true) {
            Arrays.fill(prev, -1);
            Arrays.fill(dist, INF);

            boolean[] inQ = new boolean[size];
            Queue<Integer> q = new LinkedList<>();
            q.offer(S);
            dist[S] = 0;

            inQ[S] = true;

            while(!q.isEmpty()) {
                int current = q.poll();
                inQ[current] = false;

                for(int next: connection[current]) {
                    if(dist[next] <= dist[current] + cost[current][next]) continue;
                    if(capacity[current][next] <= flow[current][next]) continue;
                    dist[next] = dist[current] + cost[current][next];
                    prev[next] = current;

                    if(inQ[next]) continue;
                    q.offer(next);
                    inQ[next] = true;
                }
            }

            if (prev[T] == -1) break;

            int mFlow = INF;
            for(int i = T; i != S; i = prev[i]) {
                mFlow = Math.min(mFlow, capacity[prev[i]][i] - flow[prev[i]][i]);
            }

            for(int i = T; i != S; i = prev[i]) {
                result -= cost[prev[i]][i];

                flow[prev[i]][i] += mFlow;
                flow[i][prev[i]] -= mFlow;
            }

            flows += mFlow;
        }
    }
}
