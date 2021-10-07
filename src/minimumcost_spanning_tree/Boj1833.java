package minimumcost_spanning_tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 1833번: 고속철도 설계하기
 *
 * @see https://www.acmicpc.net/problem/1833
 *
 */
public class Boj1833 {

    private static int[] parent;
    private static Queue<HighWay> pq;

    private static int total;

    private static final String SPACE = " ";
    private static final String NEW_LINE = "\n";

    private static class HighWay {
        int city1;
        int city2;
        int cost;

        public HighWay(int city1, int city2, int cost) {
            this.city1 = city1;
            this.city2 = city2;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        init(N);

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j = 0; j < N; j++) {
                int cost = Integer.parseInt(st.nextToken());
                if(i == j) continue;

                if(cost < 0) {
                    total -= cost;
                    merged(i, j);
                    continue;
                }

                pq.offer(new HighWay(i, j, cost));
            }
        }

        total >>= 1;
        System.out.println(kruskal());
    }

    private static String kruskal() {
        List<HighWay> ways = new LinkedList<>();
        int count = 0;

        while(!pq.isEmpty()) {
            HighWay current = pq.poll();

            if(merged(current.city1, current.city2)) continue;
            total += current.cost;
            count++;

            ways.add(new HighWay(current.city1 + 1, current.city2 + 1, 0));
        }

        StringBuilder sb = new StringBuilder();
        sb.append(total).append(SPACE).append(count).append(NEW_LINE);

        while(count-- > 0) {
            HighWay current = ways.remove(0);
            sb.append(current.city1).append(SPACE).append(current.city2).append(NEW_LINE);
        }

        return sb.toString();
    }

    private static void init(int n) {
        parent = new int[n];
        Arrays.fill(parent, -1);

        pq = new PriorityQueue<>(Comparator.comparingInt(highWay -> highWay.cost));
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
}
