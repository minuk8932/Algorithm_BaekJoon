package dijkstra;

import common.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 20926번: 얼음 미로
 *
 * @see https://www.acmicpc.net/problem/20926
 *
 */
public class Boj20926 {

    private static final char TERRA = 'T';
    private static final char ROCK = 'R';
    private static final char HOLE = 'H';
    private static final char EXIT = 'E';

    private static final int ROCK_INT = 'R';
    private static final int HOLE_INT = 'H';
    private static final int EXIT_INT = 'E';

    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int ROW = 0;
    private static final int COL = 1;
    private static final int INF = 1_000_000_000;

    private static int[][] map;
    private static int H;
    private static int W;
    private static int answer = INF;

    private static Point start;
    private static Point end;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][W];
        for(int i = 0; i < H; i++) {
            String input = br.readLine();

            for(int j = 0; j < W; j++) {
                char component = input.charAt(j);
                Point<Integer, Integer> p
                    = new Point.Builder<Integer, Integer>(i, j).cost(0).build();

                if(TERRA == component) {
                    start = p;
                }
                else if(EXIT == component) {
                    end = p;
                    map[p.getRow()][p.getCol()] = EXIT_INT;
                }
                else if(HOLE == component) {
                    map[p.getRow()][p.getCol()] = HOLE_INT;
                }
                else if(ROCK == component) {
                    map[p.getRow()][p.getCol()] = ROCK_INT;
                }
                else {
                    map[p.getRow()][p.getCol()] = component - '0';
                }
            }
        }

        dijkstra();
        System.out.println(answer == INF ? -1: answer);
    }

    /**
     *
     * Dijkstra
     *
     * Time limit so hard, waste
     *
     */
    private static void dijkstra() {
        boolean[][] visit = new boolean[H][W];

        Queue<Point<Integer, Integer>> pq = new PriorityQueue<>(Comparator.comparingInt(Point::getCost));
        pq.offer(start);

        while(!pq.isEmpty()) {
            Point<Integer, Integer> current = pq.poll();

            if(visit[current.getRow()][current.getCol()]) continue;
            visit[current.getRow()][current.getCol()] = true;

            for(final int[] DIRECTION: DIRECTIONS) {
                Point<Integer, Integer> next
                    = sliding(new Point
                        .Builder<Integer, Integer>(current.getRow(), current.getCol())
                        .cost(current.getCost()).build()
                        , DIRECTION[ROW]
                        , DIRECTION[COL]);
                if(next == null) continue;

                pq.offer(next);
            }
        }
    }

    private static Point sliding(Point<Integer, Integer> current, int drow, int dcol){
        Point<Integer, Integer> next = new Point
            .Builder<Integer, Integer>(current.getRow(), current.getCol())
            .cost(0).build();
        int cost = 0;
        boolean flag = false;

        while(true) {
            next = new Point
                .Builder<Integer, Integer>(next.getRow() + drow, next.getCol() + dcol)
                .cost(0).build();

            if(outOfRange(next)) break;
            if(map[next.getRow()][next.getCol()] == HOLE_INT) break;
            if(flag = (map[next.getRow()][next.getCol()] == ROCK_INT)) break;
            if(map[next.getRow()][next.getCol()] == EXIT_INT){
                answer = Math.min(answer, cost + current.getCost());
                break;
            }

            cost += map[next.getRow()][next.getCol()];
        }

        next = new Point.Builder<Integer, Integer>(
                next.getRow() - drow,
                next.getCol() - dcol)
            .cost(cost + current.getCost()).build();

        if(!flag || (current.getRow() == next.getRow() && current.getCol() == next.getCol())) return null;
        return next;
    }

    private static boolean outOfRange(Point<Integer, Integer> p) {
        return p.getRow() < 0 || p.getRow() >= H || p.getCol() < 0 || p.getCol() >= W;
    }

}