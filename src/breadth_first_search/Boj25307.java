package breadth_first_search;

import common.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 25307번: 시루의 백화점 구경
 *
 * @see https://www.acmicpc.net/problem/25307
 *
 */
public class Boj25307 {

    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1;
    private static final int MANNEQUIN = 3;
    private static final int PILLAR = 1;
    private static final int CHAIR = 2;
    private static final int START = 4;

    private static int[][] departmentStore;
    private static int N, M;
    private static ArrayList<Point<Integer, Integer>> mannequins = new ArrayList<>();
    private static Point<Integer, Integer> start;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        departmentStore = new int[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < M; j++) {
                departmentStore[i][j] = Integer.parseInt(st.nextToken());

                if(departmentStore[i][j] == MANNEQUIN)
                    mannequins.add(new Point.Builder(i, j).build());
                if(departmentStore[i][j] == START)
                    start = new Point.Builder(i, j).build();
            }
        }

        mannequinFills(K);
        System.out.println(findChair());
    }

    private static int findChair() {
        int[][] visit = new int[N][M];

        Queue<Point<Integer, Integer>> q = new ArrayDeque<>();
        q.offer(start);

        visit[start.getRow()][start.getCol()] = 1;

        while(!q.isEmpty()) {
            Point<Integer, Integer> current = q.poll();

            for(final int[] DIRECTION: DIRECTIONS) {
                int nextRow = current.getRow() + DIRECTION[ROW];
                int nextCol = current.getCol() + DIRECTION[COL];

                Point<Integer, Integer> next = new Point.Builder(nextRow, nextCol).build();

                if(OUT_OF_RANGE(next)) continue;
                if(departmentStore[next.getRow()][next.getCol()] == MANNEQUIN) continue;
                if(departmentStore[next.getRow()][next.getCol()] == PILLAR) continue;
                if(visit[next.getRow()][next.getCol()] != 0) continue;
                visit[next.getRow()][next.getCol()] = visit[current.getRow()][current.getCol()] + 1;

                if(departmentStore[next.getRow()][next.getCol()] == CHAIR)
                    return visit[next.getRow()][next.getCol()] - 1;

                q.offer(next);
            }
        }


        return -1;
    }

    private static void mannequinFills(int effectiveRange) {
        for(int row = 0; row < N; row++) {
            for(int col = 0; col < M; col++) {
                if(departmentStore[row][col] == MANNEQUIN) continue;
                Point current = new Point.Builder(row, col).build();

                for(Point<Integer, Integer> mannequin: mannequins) {
                    if(manhattanDistance(mannequin, current) > effectiveRange) continue;

                    departmentStore[row][col] = MANNEQUIN;
                    break;
                }
            }
        }
    }

    private static int manhattanDistance(Point<Integer, Integer> mannequin
        , Point<Integer, Integer> current) {
        return Math.abs(mannequin.getRow() - current.getRow())
            + Math.abs(mannequin.getCol() - current.getCol());
    }

    private static boolean OUT_OF_RANGE(Point<Integer, Integer> p) {
        return p.getRow() < 0 || p.getCol() < 0 || p.getRow() >= N || p.getCol() >= M;
    }
}
