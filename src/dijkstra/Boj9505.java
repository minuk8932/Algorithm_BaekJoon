package dijkstra;

import common.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 9505번: 엔터프라이즈호 탈출
 *
 * @see https://www.acmicpc.net/problem/9505
 *
 */
public class Boj9505 {

    private static final String NEW_LINE = "\n";
    private static final char ENTERPRISE = 'E';
    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1;
    private static final int INF = 1_000_000_000;

    private static HashMap<Character, Integer> battleShip;
    private static char[][] map;
    private static int H, W;
    private static Point<Integer, Integer> start;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        while(T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            battleShip = new HashMap<>();
            while(K-- > 0) {
                st = new StringTokenizer(br.readLine());
                battleShip.put(st.nextToken().charAt(0), Integer.parseInt(st.nextToken()));
            }

            map = new char[H][W];
            for(int i = 0; i < H; i++) {
                String input = br.readLine();

                for(int j = 0; j < W; j++) {
                    map[i][j] = input.charAt(j);
                    if(map[i][j] != ENTERPRISE) continue;

                    start = new Point.Builder(i, j).cost(0).build();
                }
            }

            sb.append(dijkstra()).append(NEW_LINE);
        }

        System.out.println(sb);
    }

    private static int dijkstra() {
        int[][] cost = new int[H][W];
        for(int i = 0; i < H; i++) {
            Arrays.fill(cost[i], INF);
        }

        int answer = INF;

        PriorityQueue<Point<Integer, Integer>> pq = new PriorityQueue<>(
            Comparator.comparingInt(Point::getCost)
        );

        pq.offer(start);
        cost[start.getRow()][start.getCol()] = 0;

        while(!pq.isEmpty()) {
            Point<Integer, Integer> current = pq.poll();

            if(cost[current.getRow()][current.getCol()] < current.getCost()) continue;

            for (final int[] DIRECTION: DIRECTIONS) {
                int nextRow = current.getRow() + DIRECTION[ROW];
                int nextCol = current.getCol() + DIRECTION[COL];

                if(outOfRange(nextRow, nextCol)) return cost[current.getRow()][current.getCol()];

                if(map[nextRow][nextCol] == ENTERPRISE) continue;
                int nextCost = battleShip.get(map[nextRow][nextCol]);

                if(cost[nextRow][nextCol] <= cost[current.getRow()][current.getCol()]
                    + nextCost) continue;
                cost[nextRow][nextCol] = cost[current.getRow()][current.getCol()]
                    + nextCost;

                pq.offer(new Point.Builder(nextRow, nextCol).cost(cost[nextRow][nextCol]).build());
            }
        }

        return answer;
    }

    private static boolean outOfRange(int row, int col) {
        return row < 0 || col < 0 || row >= H || col >= W;
    }
}
