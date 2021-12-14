import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.BiPredicate;

public class Boj23817 {

    private static final char RESTAURANT = 'K';
    private static final char START = 'S';
    private static final char BLOCK = 'X';

    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1;

    private static char[][] map;
    private static int N, M;
    private static Point start;
    private static List<Point> restuarants = new ArrayList<>();

    private static class Point {
        int row;
        int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for(int i = 0; i < N; i++) {
            String line = br.readLine();

            for(int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
                if(map[i][j] == START) start = new Point(i, j);
            }
        }

//        System.out.println(isReachable() ? searching(): -1);
    }

    private static boolean isReachable() {
        Queue<Point> q = new LinkedList<>();
        boolean[][] visit = new boolean[N][M];

        q.offer(start);
        visit[start.row][start.col] = true;

        while(!q.isEmpty()) {
            Point current = q.poll();

            for(final int[] DIRECTION: DIRECTIONS) {
                int nextRow = current.row + DIRECTION[ROW];
                int nextCol = current.col + DIRECTION[COL];

                if(OUT_OF_RANGE.test(nextRow, nextCol)) continue;
                if(map[nextRow][nextCol] == BLOCK || visit[nextRow][nextCol]) continue;
                visit[nextRow][nextCol] = true;

                if(map[nextRow][nextCol] == RESTAURANT) restuarants.add(new Point(nextRow, nextCol));
                q.offer(new Point(nextRow, nextCol));
            }
        }

        return restuarants.size() >= 5;
    }

    private static final BiPredicate<Integer, Integer> OUT_OF_RANGE = (row, col) ->
            row < 0 || row >= N || col < 0 || col >= M;
}
