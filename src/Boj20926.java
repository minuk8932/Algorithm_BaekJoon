import common.Point;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

public class Boj20926 {

    private static final char TERRA = 'T';
    private static final char ROCK = 'R';
    private static final char HOLE = 'H';
    private static final char EXIT = 'E';

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
            String input = br.readLine();

            for(int j = 0; j < W; j++) {
                char component = input.charAt(j);
                Point p = Point.pointWithCost(i, j, 0);

                if(HOLE == component) {
                    holes.add(INDEX_ENCODER.apply(p));
                }
                else if(TERRA == component) {
                    start = p;
                }
                else if(EXIT == component) {
                    end = p;
                }
                else if(ROCK == component) {
                    rocks.add(INDEX_ENCODER.apply(p));
                }
                else {
                    map[p.getRow()][p.getCol()] = component - '0';
                }
            }
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        int[][] visit = visitInitiate();
        int answer = INF;

        Queue<Point> pq = new ArrayDeque<>();
        pq.offer(start);

        visit[start.getRow()][start.getCol()] = 0;

        while(!pq.isEmpty()) {
            Point current = pq.poll();

            for(final int[] DIRECTION: DIRECTIONS) {
                int nextRow = current.getRow() + DIRECTION[ROW];
                int nextCol = current.getCol() + DIRECTION[COL];
                int nextCost = 0;

                Point next = Point.pointWithCost(nextRow, nextCol, 0);

                boolean arrived = false;
                boolean blocked = false;

                while(!OUT_OF_RANGE.test(next)
                        && !holes.contains(INDEX_ENCODER.apply(next))) {

                    nextCost += map[nextRow][nextCol];
                    arrived = IS_END.test(end, next);
                    blocked = rocks.contains(INDEX_ENCODER.apply(next));
                    if(arrived || blocked) break;

                    nextRow += DIRECTION[ROW];
                    nextCol += DIRECTION[COL];
                    next = Point.pointWithCost(nextRow, nextCol, 0);
                }

                if(blocked) {
                    next = Point.pointWithCost(nextRow - DIRECTION[ROW], nextCol - DIRECTION[COL], 0);
                }

                if(!arrived && !blocked) continue;
                if(visit[next.getRow()][next.getCol()] <= visit[current.getRow()][current.getCol()] + nextCost) continue;
                visit[next.getRow()][next.getCol()] = visit[current.getRow()][current.getCol()] + nextCost;

                if(arrived) answer = Math.min(visit[next.getRow()][next.getCol()], answer);

                pq.offer(Point.pointWithCost(next.getRow(), next.getCol(), visit[next.getRow()][next.getCol()]));
            }
        }

        return answer == INF ? -1: answer;
    }

    private static int[][] visitInitiate() {
        int[][] arr = new int[H][W];

        for(int i = 0; i < arr.length; i++) {
            Arrays.fill(arr[i], INF);
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