package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 19238번: 스타트 택시
 *
 * @see https://www.acmicpc.net/problem/19238/
 *
 */
public class Boj19238 {
    private static boolean[][] arrived;

    private static int[][] cost;
    private static int[][] map;
    private static int N;

    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1;
    private static final int CIPHER = 100;

    private static ArrayList<Guest> guests = new ArrayList<>();
    private static Point taxi = new Point(0, 0);

    private static class Point {
        int row;
        int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private static class Guest implements Comparable<Guest>{
        Point start;
        Point end;

        public Guest(Point start, Point end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Guest g) {
            return makeCost(cost[this.start.row][this.start.col], this.start) < makeCost(cost[g.start.row][g.start.col], g.start) ? -1: 1;
        }

        private static int makeCost (int cost, Point p) {
            return CIPHER * CIPHER * cost + p.row * CIPHER + p.col;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        arrived = new boolean[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        taxi = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);

        while(M-- > 0 ){
            st = new StringTokenizer(br.readLine());
            guests.add(new Guest(new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1)
                    , new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1)));
        }

        System.out.println(startTaxi(G));
    }

    private static int startTaxi(int g) {
        int loop = guests.size();

        while(loop-- > 0) {
            Guest target = bfs(taxi);                                   // find the nearest guest
            arrived[target.start.row][target.start.col] = true;

            int dist = cost[target.start.row][target.start.col] - 1;
            if(dist == -1) return dist;

            g -= dist;
            if (g < 0) return -1;

            dist = operation(target.start, target.end);                 // path cost
            if(dist == -1) return dist;

            g -= dist;
            if(g < 0) return -1;

            g += dist * 2;
            taxi = target.end;
        }

        return g;
    }

    private static int operation(Point s, Point e) {
        int[][] cost = new int[N][N];

        Queue<Point> q = new LinkedList<>();
        q.offer(s);

        cost[s.row][s.col] = 1;

        while(!q.isEmpty()){
            Point current = q.poll();

            for(final int[] DIRECTION: DIRECTIONS) {
                int nextRow = current.row + DIRECTION[ROW];
                int nextCol = current.col + DIRECTION[COL];

                if(outOfRange(nextRow, nextCol)) continue;
                if(cost[nextRow][nextCol] != 0 || map[nextRow][nextCol] == 1) continue;
                cost[nextRow][nextCol] = cost[current.row][current.col] + 1;

                if(nextRow == e.row && nextCol == e.col) return cost[nextRow][nextCol] - 1;

                q.offer(new Point(nextRow, nextCol));
            }
        }

        return -1;
    }

    private static Guest bfs(Point start) {
        cost = new int[N][N];

        Queue<Point> q = new LinkedList<>();
        q.offer(start);

        cost[start.row][start.col] = 1;

        while(!q.isEmpty()) {
            Point current = q.poll();

            for(final int[] DIRECTION: DIRECTIONS) {
                int nextRow = current.row + DIRECTION[ROW];
                int nextCol = current.col + DIRECTION[COL];

                if(outOfRange(nextRow, nextCol)) continue;
                if(cost[nextRow][nextCol] != 0 || map[nextRow][nextCol] == 1) continue;
                cost[nextRow][nextCol] = cost[current.row][current.col] + 1;

                q.offer(new Point(nextRow, nextCol));
            }
        }

        PriorityQueue<Guest> pq = new PriorityQueue<>();
        for(Guest g: guests) {
            if(arrived[g.start.row][g.start.col]) continue;
            pq.offer(g);
        }

        return pq.poll();
    }

    private static boolean outOfRange(int row, int col) {
        return row < 0 || row >= N || col < 0 || col >= N;
    }
}
