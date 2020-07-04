package dijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 9323번: 무임 승차
 *
 * @see https://www.acmicpc.net/problem/9323
 *
 */
public class Boj9323 {
    private static ArrayList<Train>[] path;

    private static final String NEW_LINE = "\n";
    private static final double INF = 1_000_000_000;
    private static final int FREE = 0, PAY = 1;

    private static class Train implements Comparable<Train>{
        int node;
        double percent;
        double distance;

        public Train(int node, double percent, double distance) {
            this.node = node;
            this.percent = percent;
            this.distance = distance;
        }

        public Train(int node, double distance) {
            this.node = node;
            this.distance = distance;
        }

        @Override
        public int compareTo(Train t) {
            return this.distance < t.distance ? -1: 1;
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
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            path = new ArrayList[n];
            for(int i = 0; i < n; i++) {
                path[i] = new ArrayList<>();
            }

            while(m-- > 0) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;
                double c = Double.parseDouble(st.nextToken()) / 100.0;
                int d = Integer.parseInt(st.nextToken());

                path[a].add(new Train(b, c, d));
                path[b].add(new Train(a, c, d));
            }

            sb.append(dijkstra(n, start, end, s, p, y)).append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }

    private static String dijkstra(int n, int start, int end, int base, int per, int fine) {
        double[][] cost = new double[2][n];
        Arrays.fill(cost[FREE], INF);
        Arrays.fill(cost[PAY], INF);

        PriorityQueue<Train> pq = new PriorityQueue<>();
        pq.offer(new Train(start, 0));
        pq.offer(new Train(start, base));
        cost[FREE][start] = 0;
        cost[PAY][start] = base;

        while(!pq.isEmpty()) {
            Train current = pq.poll();

            for(Train next: path[current.node]) {
                double expectFree = Math.min(cost[PAY][current.node]
                        , cost[FREE][current.node]) + (fine + per * next.distance) * next.percent;      // next free ride, get min prev free or paid
                double expectPay = Math.min(cost[PAY][current.node] + next.distance * per
                        , cost[FREE][current.node] + next.distance * per + base);                       // next pay ride, get min prev free or paid

                if(cost[FREE][next.node] > expectFree){
                    cost[FREE][next.node] = expectFree;
                    pq.offer(new Train(next.node, cost[FREE][next.node]));
                }

                if(cost[PAY][next.node] > expectPay){
                    cost[PAY][next.node] = expectPay;
                    pq.offer(new Train(next.node, cost[PAY][next.node]));
                }
            }
        }

        return String.format("%.2f", Math.round(Math.min(cost[0][end], cost[1][end]) * 100) / 100.0);
    }
}
