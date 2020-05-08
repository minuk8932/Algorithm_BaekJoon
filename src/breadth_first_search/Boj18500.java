package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 18500번: 미네랄 2
 *
 * @see https://www.acmicpc.net/problem/18500/
 *
 */
public class Boj18500 {
    private static int R, C;
    private static boolean[][] mineral;

    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1;
    private static final char M = 'x', E = '.', NEW_LINE = '\n';
    private static final int CIPHER = 100;

    private static PriorityQueue<Integer> pq;

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
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        mineral = new boolean[R][C];
        for(int i = 0; i < R; i++) {
            String line = br.readLine();

            for(int j = 0; j < C; j++) {
                mineral[i][j] = line.charAt(j) == M;
            }
        }

        int N = Integer.parseInt(br.readLine());
        int[] spear = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            spear[i] = R - Integer.parseInt(st.nextToken());
        }

        System.out.println(throwAndFalling(spear));
    }

    private static String throwAndFalling(int[] spear) {
        int col = 0;
        int adder = 1;

        for(int row: spear){
            remove(new Point(row, col), adder);
            Point target = search();

            if(target.row != -1 && target.col != -1) {
                boolean[][] visit = new boolean[R][C];
                pq = new PriorityQueue<>();

                bfs(target, visit, true);
                pushing();
            }

            col ^= (C - 1);
            adder *= -1;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                sb.append(mineral[i][j] ? M: E);
            }

            sb.append(NEW_LINE);
        }

        return sb.toString();
    }

    /**
     *
     * remove left | right
     */
    private static void remove (Point start, int add) {
        while(start.col >= 0 && start.col < C) {
            if(mineral[start.row][start.col]) {
                mineral[start.row][start.col] = false;
                return;
            }

            start.col += add;
        }
    }

    private static Point search() {
        boolean[][] visit = new boolean[R][C];

        for(int row = 0; row < R; row++) {
            for(int col = 0; col < C; col++) {
                if(visit[row][col] || !mineral[row][col]) continue;
                if(!bfs(new Point(row, col), visit, false)) return new Point(row, col);
            }
        }

        return new Point(-1, -1);
    }

    /**
     *
     * @param saved: if it is true, then save minerals that has to fall
     */
    private static boolean bfs(Point start, boolean[][] visit, boolean saved) {
        boolean flag = false;

        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(start.row, start.col));
        if(saved) pq.offer(-start.row * CIPHER - start.col);

        visit[start.row][start.col] = true;
        if(isRounding(start.row)) flag = true;

        while(!q.isEmpty()){
            Point current = q.poll();

            for(final int[] DIRECTION: DIRECTIONS) {
                int nextRow = current.row + DIRECTION[ROW];
                int nextCol = current.col + DIRECTION[COL];

                if(outOfRange(nextRow, nextCol)) continue;
                if(visit[nextRow][nextCol] || !mineral[nextRow][nextCol]) continue;
                visit[nextRow][nextCol] = true;
                if(saved) pq.offer(-nextRow * CIPHER - nextCol);

                if(isRounding(nextRow)) flag = true;
                q.offer(new Point(nextRow, nextCol));
            }
        }

        return flag;
    }

    /**
     *
     * find min falling cost whole minerals & falling them
     */
    private static void pushing() {
        Queue<Integer> retention = new LinkedList<>();
        int min = R;

        while(!pq.isEmpty()) {
            int current = -pq.poll();
            int row = current / CIPHER;
            int col = current % CIPHER;

            retention.offer(current);
            mineral[row][col] = false;

            for(int nextRow = row; nextRow < R; nextRow++) {
                if(!mineral[nextRow][col]){
                    if(nextRow == R - 1) min = Math.min(min, R - 1 - row);
                    continue;
                }

                min = Math.min(min, nextRow - row - 1);
                break;
            }
        }

        while(!retention.isEmpty()) {
            int current = retention.poll();
            int row = current / CIPHER;
            int col = current % CIPHER;

            mineral[row + min][col] = true;
        }
    }

    private static boolean outOfRange(int row, int col) {
        return row < 0 || row >= R || col < 0 || col >= C;
    }

    private static boolean isRounding(int row) {
        return row == R - 1;
    }
}
