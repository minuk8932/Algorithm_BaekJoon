import common.Point;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

public class Boj20926 {

    private static final String TERRA = "T";
    private static final String ROCK = "R";
    private static final String HOLE = "H";
    private static final String EXIT = "E";

    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int ROW = 0;
    private static final int COL = 1;
    private static final int INF = 1_000_000_000;

    private static int[][] map;
    private static int H;
    private static int W;

    private static Point start;
    private static Point end;
    private static Set<Integer> holes = new HashSet<>();
    private static Set<Integer> rocks = new HashSet<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][W];
        for(int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < W; j++) {
                String component = st.nextToken();
                Point p = Point.pointWithCost(i, j, 0);

                if(HOLE.equals(component)) {
                    holes.add(INDEX_ENCODER.apply(p));
                }
                else if(TERRA.equals(component)) {
                    start = p;
                }
                else if(EXIT.equals(component)) {
                    end = p;
                }
                else if(ROCK.equals(component)) {
                    rocks.add(INDEX_ENCODER.apply(p));
                }
                else {
                    map[p.getRow()][p.getCol()] = Integer.parseInt(st.nextToken());
                }
            }
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        int[][][] visit = visitInitiate();

        Queue<Point> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.getCost()));
        pq.offer(start);

        visit[4][start.getRow()][start.getCol()] = 0;

        while(!pq.isEmpty()) {
            Point current = pq.poll();

            for(int dir = 0; dir < 4; dir++) {
                int nextRow = current.getRow() + DIRECTIONS[dir][ROW];
                int nextCol = current.getCol() + DIRECTIONS[dir][COL];
                int nextCost = 0;

                Point next = Point.pointWithDirection(nextRow, nextCol, 0);
                boolean arrived = false;

                while(!OUT_OF_RANGE.test(next)
                        && !holes.contains(INDEX_ENCODER.apply(next))) {

                    nextCost += map[nextRow][nextCol];
                    arrived = rocks.contains(INDEX_ENCODER.apply(next)) || IS_END.test(end, next);
                    if(arrived) break;

                    nextRow += DIRECTIONS[dir][ROW];
                    nextCol += DIRECTIONS[dir][COL];
                }

                if(!arrived) continue;
                if(visit[dir][nextRow][nextCol] <= visit[current.ge][nextRow][nextCol])
            }
        }



        return 0;
    }

    private static int[][][] visitInitiate() {
        int[][][] arr = new int[5][H][W];

        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr[i].length; j++) {
                Arrays.fill(arr[i][j], INF);
            }
        }

        return arr;
    }

    private static final Function<Point, Integer> INDEX_ENCODER = p ->
            p.getRow() * W + p.getCol();

    private static final Predicate<Point> OUT_OF_RANGE = (p) ->
            p.getRow() < 0 || p.getRow() >= H || p.getCol() < 0 || p.getCol() >= W;

    private static final BiPredicate<Point, Point> IS_END = (p1, p2) ->
            p1.getRow() == p2.getRow() && p1.getCol() == p2.getCol();
}