package back_tracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 19236번: 청소년 상어
 *
 * @see https://www.acmicpc.net/problem/19236/
 *
 */
public class Boj19236 {
    private static final int[][] DIRECTIONS = {{0, 0}, {-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}};
    private static final int ROW = 0, COL = 1;
    private static final int SHARK = -1;
    private static final int[] CHANGER = {0, 2, 3, 4, 5, 6, 7, 8, 1};

    private static int result;
    private static int[][] map;
    private static Point[] fish = new Point[17];
    private static boolean[] survived = new boolean[17];

    private static class Point {
        int row;
        int col;
        int dir;

        public Point(int row, int col, int dir) {
            this.row = row;
            this.col = col;
            this.dir = dir;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[4][4];

        for(int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j = 0; j < 4; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                fish[map[i][j]] = new Point(i, j, Integer.parseInt(st.nextToken()));
                survived[map[i][j]] = true;
            }
        }

        search();
        System.out.println(result);
    }

    private static void search() {
        survived[map[0][0]] = false;            // eat 0, 0
        int eat = map[0][0];
        map[0][0] = SHARK;

        recursion(new Point(0, 0, fish[eat].dir), eat, 1);
    }

    public static void recursion(Point current, int total, int count) {
        int[][] cMap = copyMap();
        Point[] cFishes = copyFish();

        moving();                       // fish move

        for(int i = 1; i <= 4; i++) {   // shark move
            int nextRow = current.row + (DIRECTIONS[current.dir][0] * i);
            int nextCol = current.col + (DIRECTIONS[current.dir][1] * i);
            if(outOfRange(nextRow, nextCol)) break;

            if(map[nextRow][nextCol] == 0) continue;
            map[current.row][current.col] = 0;          // eat

            int next = map[nextRow][nextCol];
            map[nextRow][nextCol] = SHARK;
            survived[next] = false;

            recursion(new Point(nextRow, nextCol, fish[next].dir), total + next, count + 1);
            survived[next] = true;
            map[nextRow][nextCol] = next;
            map[current.row][current.col] = SHARK;         // backTracking
        }

        copyMap(cMap);
        copyFish(cFishes);

        result = Math.max(total, result);
    }


    public static void moving() {
        for(int i = 1; i < survived.length; i++) {
            if(!survived[i]) continue;
            Point current = fish[i];
            
            int nextDir = current.dir;
            int nextRow = current.row, nextCol = current.col;

            boolean flag = false;

            for(int d = 0; d < 8; d++) {
                nextRow = current.row + DIRECTIONS[nextDir][ROW];
                nextCol = current.col + DIRECTIONS[nextDir][COL];

                if (outOfRange(nextRow, nextCol) || map[nextRow][nextCol] == SHARK) {
                    nextDir = CHANGER[nextDir];                             // just change direction
                }
                else {
                    flag = true;
                    break;
                }
            }

            if(!flag) continue;

            int temp = map[nextRow][nextCol];
            map[nextRow][nextCol] = map[current.row][current.col];          // change position
            map[current.row][current.col] = temp;

            fish[i] = new Point(nextRow, nextCol, nextDir);
            if(temp != 0) fish[temp] = new Point(current.row, current.col, fish[temp].dir);
        }
    }

    private static boolean outOfRange(int row, int col) {
        return row < 0 || col < 0 || row >= 4 || col >= 4;
    }

    private static void copyMap(int[][] make) {
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                map[i][j] = make[i][j];
            }
        }
    }

    private static void copyFish(Point[] p) {
        for(int i = 1; i < fish.length; i++) {
            fish[i] = p[i];
        }
    }

    private static int[][] copyMap() {
        int[][] arr = new int[4][4];

        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                arr[i][j] = map[i][j];
            }
        }

        return arr;
    }

    private static Point[] copyFish() {
        Point[] arr = new Point[17];

        for(int i = 1; i < fish.length; i++) {
            arr[i] = fish[i];
        }

        return arr;
    }
}
