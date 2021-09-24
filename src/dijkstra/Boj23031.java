package dijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Function;

/**
 *
 * @author exponential-e
 * 백준 23031번: 후다닥 이겨 츄르를 받자!
 *
 * @see https://www.acmicpc.net/problem/23031
 *
 */
public class Boj23031 {

    private static List<Train>[][] path;
    private static int[][] dist = new int[10][50];

    private static final int INF = 1_000_000_000;
    private static final boolean[][][][] TRANSFER = new boolean[10][50][10][50];
    private static final String NEW_LINE = "\n";

    private static class Train {
        int line;
        int station;
        int cost;

        public Train(int line, int station, int cost) {
            this.line = line;
            this.station = station;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = PARSE.apply(br.readLine());
        path = new ArrayList[N][50];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int line = 0; line < N; line++) {
            int size = PARSE.apply(st.nextToken());

            for(int station = 0; station < size; station++) {
                path[line][station] = new ArrayList<>();
            }

            for(int station = 1; station < size; station++) {
                path[line][station].add(new Train(line, station - 1, 1));
                path[line][station - 1].add(new Train(line, station, 1));
            }
        }

        int M = PARSE.apply(br.readLine());
        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int P1 = PARSE.apply(st.nextToken()) - 1;
            int P2 = PARSE.apply(st.nextToken()) - 1;
            int Q1 = PARSE.apply(st.nextToken()) - 1;
            int Q2 = PARSE.apply(st.nextToken()) - 1;

            TRANSFER[P1][P2][Q1][Q2] = TRANSFER[Q1][Q2][P1][P2] = true;

            path[P1][P2].add(new Train(Q1, Q2, 0));
            path[Q1][Q2].add(new Train(P1, P2, 0));
        }

        StringBuilder sb = new StringBuilder();
        int K = PARSE.apply(br.readLine());

        while(K-- > 0 ){
            st = new StringTokenizer(br.readLine());
            int T = PARSE.apply(st.nextToken());
            int U1 = PARSE.apply(st.nextToken()) - 1;
            int U2 = PARSE.apply(st.nextToken()) - 1;
            int V1 = PARSE.apply(st.nextToken()) - 1;
            int V2 = PARSE.apply(st.nextToken()) - 1;

            sb.append(dijkstra(new Train(U1, U2, 0), new Train(V1, V2, 0), T)).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    /**
     *
     * Dijkstra
     *
     * @param from
     * @param to
     * @param tranCost
     * @return
     */
    private static int dijkstra(Train from, Train to, int tranCost) {
        for(int i = 0; i < dist.length; i++){
            Arrays.fill(dist[i], INF);
        }

        PriorityQueue<Train> pq = new PriorityQueue<>(Comparator.comparingInt(x -> x.cost));
        dist[from.line][from.station] = 0;

        pq.offer(from);

        while(!pq.isEmpty()) {
            Train current = pq.poll();
            if(current.cost > dist[current.line][current.station]) continue;

            for(Train next: path[current.line][current.station]) {
                int cost = dist[current.line][current.station]
                        + (TRANSFER[current.line][current.station][next.line][next.station] ? tranCost: next.cost);
                if(dist[next.line][next.station] <= cost) continue;
                dist[next.line][next.station] = cost;

                pq.offer(new Train(next.line, next.station, dist[next.line][next.station]));
            }
        }

        return dist[to.line][to.station];
    }

    private static final Function<String, Integer> PARSE = Integer::parseInt;

}
