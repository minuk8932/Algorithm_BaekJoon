import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Boj23354 {

    private static boolean[] visit;
    private static int[][] cost;
    private static int[][] map;
    private static int[][] dist;
    private static int size;
    private static int N;

    private static List<Point> deserter = new ArrayList<>();
    private static List<Integer> permutation = new ArrayList<>();

    private static final int ARMY_BASE = -1;
    private static final int DESERTER = 0;
    private static final int INF = 2_000_000_000;

    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1;

    private static class Point {
        int row;
        int col;
        int val;

        public Point(int row, int col, int val) {
            this.row = row;
            this.col = col;
            this.val = val;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        List<Point> temp = new ArrayList<>();
        map = new int[N][N];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == ARMY_BASE) deserter.add(new Point(i, j, 0));
                if (map[i][j] == DESERTER) temp.add(new Point(i, j, 0));
            }
        }

        for(Point t: temp) {
            deserter.add(t);
        }

        size = deserter.size();
        dist = new int[size][size];

        dijkstra();

        for(int i = 1; i < size; i++) {
            visit = new boolean[size];
            recursion(i, 0, i);
        }

        System.out.println(dp());
    }

    private static int dp() {
        int min = INF;

        for (int p: permutation) {
            int from = p % 10;
            int cost = dist[0][from];
            p /= 10;

            while(p > 0) {
                int to = p % 10;
                cost += dist[from][to];
                System.out.println(from + " " + to);

                p /= 10;
                from = to;
            }

            cost += dist[from][0];
            min = Math.min(cost, min);
        }

        return min == INF ? 0: min;
    }

    private static void dijkstra() {
        cost = new int[N][N];

        for(int i = 0; i < dist.length; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        for(int one = 0; one < size; one++) {
            for(int other = one + 1; other < size; other++) {
                dist[one][other] = Math.min(search(one, other), dist[one][other]);
                dist[other][one] = dist[one][other];
            }
        }
    }

    private static int search(int from, int to) {
        for(int i = 0; i < N; i++) {
            Arrays.fill(cost[i], INF);
        }

        Queue<Point> pq = new PriorityQueue<>(Comparator.comparingInt(x -> x.val));
        pq.offer(deserter.get(from));
        cost[from][to] = 0;

        while(!pq.isEmpty()) {
            Point current = pq.poll();
            if(current.val > cost[current.row][current.col]) continue;

            for(final int[] DIRECTION: DIRECTIONS) {
                int nextRow = current.row + DIRECTION[ROW];
                int nextCol = current.col + DIRECTION[COL];

                if (outOfRange(nextRow, nextCol)) continue;

                int nextcost =  map[nextRow][nextCol] == -1 ? 0 :map[nextRow][nextCol];
                if (cost[nextRow][nextCol] <= current.val + nextcost) continue;
                cost[nextRow][nextCol] = current.val + nextcost;

                pq.offer(new Point(nextRow, nextCol, cost[nextRow][nextCol]));
            }
        }

        return cost[deserter.get(to).row][deserter.get(to).col];
    }

    private static boolean outOfRange(int row, int col) {
        return row < 0 || col < 0 || row >= N || col >= N;
    }

    private static void recursion(int current, int count, int value) {
        if(count == size - 2) {
            permutation.add(value);
            return;
        }

        visit[current] = true;

        for(int next = 1; next < size; next++) {
            if (visit[next]) continue;

            recursion(next, count + 1, value * 10 + next);
            visit[next] = false;
        }
    }
}
