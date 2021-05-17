package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 21736번: 헌내기는 친구가 필요해
 *
 * @see https://www.acmicpc.net/problem/21736
 *
 */
public class Boj21736 {

    private static Queue<Point> q = new LinkedList<>();
    private static char[][] map;
    private static boolean[][] visit;
    private static int N, M;

    private static final char BLOCK = 'X';
    private static final char PERSON = 'P';
    private static final char DO_YEON = 'I';

    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1;

    private static final String ISOLATION = "TT";

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
        visit = new boolean[N][M];
        for(int i = 0; i < N; i++) {
            String line = br.readLine();

            for(int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);

                if(map[i][j] == DO_YEON){
                    q.offer(new Point(i, j));
                    visit[i][j] = true;
                }
            }
        }

        int result = bfs();
        System.out.println(result == 0 ? ISOLATION: result);
    }

    private static int bfs() {
        int count = 0;

        while(!q.isEmpty()) {
            Point current = q.poll();

            for(final int[] DIRECTION: DIRECTIONS) {
                int nextRow = current.row + DIRECTION[ROW];
                int nextCol = current.col + DIRECTION[COL];

                if (outOfRange(nextRow, nextCol)) continue;
                if (visit[nextRow][nextCol] || map[nextRow][nextCol] == BLOCK) continue;
                visit[nextRow][nextCol] = true;

                if(map[nextRow][nextCol] == PERSON) count++;
                q.offer(new Point(nextRow, nextCol));
            }
        }

        return count;
    }

    private static boolean outOfRange(int row, int col) {
        return row < 0 || col < 0 || row >= N || col >= M;
    }
}
