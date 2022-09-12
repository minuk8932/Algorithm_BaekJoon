package breadth_first_search;

import common.Point;
import java.io.*;
import java.util.*;
import java.util.function.Predicate;

/**
 *
 * @author exponential-e
 * 백준 25307번: 시루의 백화점 구경
 *
 * @see https://www.acmicpc.net/problem/25307
 *
 */
public class Boj25307 {

    private static final LinkedList<Point<Integer, Integer>> MANNEQUINS = new LinkedList<>();
    private static final int PILLAR = 1;
    private static final int SR = 4;
    private static final int CHAIR = 2;
    private static final int MANNEQUIN = 3;
    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1;

    private static Point<Integer, Integer> start;
    private static int[][] departmentStore;

    private static int N, M, K;
    private static final Predicate<Point<Integer, Integer>> OUT_OF_RANGE = point ->
        point.getRow() < 0 || point.getCol() < 0 || point.getRow() >= N || point.getCol() >= M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        departmentStore = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < M; j++) {
                departmentStore[i][j] = Integer.parseInt(st.nextToken());
                if(departmentStore[i][j] == SR)
                    start = new Point.Builder<Integer, Integer>(i, j).build();
                if(departmentStore[i][j] == MANNEQUIN)
                    MANNEQUINS.add(new Point.Builder<Integer, Integer>(i, j).build());
            }
        }

        mannequinFloodFill();

//        for(int i = 0; i < N; i++) {
//            for(int j = 0; j < M; j++) {
//                System.out.print(departmentStore[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();

        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Point<Integer, Integer>> q = new ArrayDeque<>();
        int[][] visit = new int[N][M];

        q.offer(start);
        visit[start.getRow()][start.getCol()] = 1;

        while(!q.isEmpty()) {
            Point<Integer, Integer> current = q.poll();

            for (final int[] DIRECTION: DIRECTIONS) {
                Point<Integer, Integer> next = new Point.Builder<Integer, Integer>
                    (current.getRow() + DIRECTION[ROW],
                        current.getCol() + DIRECTION[COL])
                    .build();

                if(OUT_OF_RANGE.test(next)) continue;
                if(departmentStore[next.getRow()][next.getCol()] == PILLAR) continue;
                if(departmentStore[next.getRow()][next.getCol()] == MANNEQUIN) continue;
                if(visit[next.getRow()][next.getCol()] != 0) continue;
                visit[next.getRow()][next.getCol()] =
                    visit[current.getRow()][current.getCol()] + 1;

                if(departmentStore[next.getRow()][next.getCol()] == CHAIR)
                    return visit[next.getRow()][next.getCol()] - 1;

                q.offer(next);
            }
        }

        return -1;
    }

    private static void mannequinFloodFill() {
        Queue<Point<Integer, Integer>> q = new ArrayDeque<>();
        int[][] visit = new int[N][M];

        while(!MANNEQUINS.isEmpty()) {
            Point<Integer, Integer> start = MANNEQUINS.remove();
            q.offer(start);

            visit[start.getRow()][start.getCol()] = 1;
        }

        while(!q.isEmpty()) {
            int size = q.size();

            while(size-- > 0) {
                Point<Integer, Integer> current = q.poll();
                if(visit[current.getRow()][current.getCol()] == K + 1) continue;

                for(final int[] DIRECTION: DIRECTIONS) {
                    Point<Integer, Integer> next = new Point.Builder<Integer, Integer>
                            (current.getRow() + DIRECTION[ROW],
                                current.getCol() + DIRECTION[COL])
                            .build();

                    if(OUT_OF_RANGE.test(next)) continue;
                    if(departmentStore[next.getRow()][next.getCol()] == PILLAR) continue;
                    if(departmentStore[next.getRow()][next.getCol()] == SR) continue;
                    if(visit[next.getRow()][next.getCol()] != 0) continue;
                    visit[next.getRow()][next.getCol()] =
                        visit[current.getRow()][current.getCol()] + 1;

                    departmentStore[next.getRow()][next.getCol()] = MANNEQUIN;

                    q.offer(next);
                }
            }
        }
    }
}
