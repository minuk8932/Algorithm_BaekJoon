package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 1525번: 퍼즐
 *
 * @see https://www.acmcipc.net/problem/1525
 *
 */
public class Boj1525 {
    private static HashSet<Integer> visit = new HashSet<>();

    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1;
    private static final int END = 123_456_780;

    private static class Move {
        int[][] p;
        int count;

        public Move(int[][] p, int count) {
            this.p = p;
            this.count = count;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] puzzle = new int[3][3];

        for(int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j = 0; j < 3; j++) {
                puzzle[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(bfs(puzzle));
    }

    private static int bfs(int[][] p) {
        Queue<Move> q = new LinkedList<>();
        q.offer(new Move(p, 1));

        visit.add(visitCount(p));
        if(visit.contains(END)) return 0;          // already arranged

        while(!q.isEmpty()) {
            Move current = q.poll();

            for(int row = 0; row < 3; row++) {
                for(int col = 0; col < 3; col++) {
                    if(current.p[row][col] != 0) continue;

                    for (final int[] DIRECTION: DIRECTIONS) {
                        int nextRow = row + DIRECTION[ROW];
                        int nextCol = col + DIRECTION[COL];

                        if (outOfRange(nextRow, nextCol)) continue;
                        int[][] nextArr = copy(current.p);
                        nextArr[row][col] = nextArr[nextRow][nextCol];      // puzzle piece swap
                        nextArr[nextRow][nextCol] = 0;

                        int nextVisit = visitCount(nextArr);                // visit count checker
                        if(visit.contains(nextVisit)) continue;
                        visit.add(nextVisit);

                        if(nextVisit == END) return current.count;
                        q.offer(new Move(nextArr, current.count + 1));
                    }
                }
            }
        }

        return -1;
    }

    private static int[][] copy(int[][] p) {
        int[][] arr = new int[3][3];

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                arr[i][j] = p[i][j];
            }
        }

        return arr;
    }

    private static int visitCount(int[][] p) {
        int value = 0;

        int ten = 1;
        for(int i = 2; i >= 0; i--) {
            for(int j = 2; j >= 0; j--) {
                value += ten * p[i][j];
                ten *= 10;
            }
        }

        return value;
    }

    private static boolean outOfRange(int row, int col) {
        return row < 0 || col < 0 || row >= 3 || col >= 3;
    }
}
