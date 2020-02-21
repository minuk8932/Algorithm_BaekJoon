package back_tracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 18428번: 감시 피하기
 *
 * @see https://www.acmicpc.net/problem/18428/
 *
 */
public class Boj18428 {
    private static boolean flag;
    private static int N;

    private static boolean[] used;
    private static int[] perm;

    private static ArrayList<Point> teacher = new ArrayList<>();

    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {0, -1}, {-1, 0}};
    private static final int ROW = 0, COL = 1;

    private static final char EMPTY = 'X';
    private static final char BLOCK = 'O';
    private static final char TEACHER = 'T';
    private static final char STUDENT = 'S';

    private static class Point{
        int row;
        int col;

        public Point(int row, int col){
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        char[][] aisle = new char[N][N];
        perm = new int[3];

        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                if((aisle[i][j] = st.nextToken().charAt(0)) == TEACHER) teacher.add(new Point(i, j));       // teacher
            }
        }

        int size = N * N;

        for(int i = 0; i < size; i++) {
            used = new boolean[size];
            used[i] = true;
            backTracking(aisle, i, 0);          // block positioned
            if(flag) break;
        }

        System.out.println(flag ? "YES": "NO");
    }

    private static void backTracking(char[][] map, int current, int count){
        if(flag) return;
        perm[count] = current;

        if(count == 2){
            flag = bfs(map);                        // is possible
            return;
        }

        for(int next = current + 1; next < used.length; next++){
            if(used[next]) continue;
            used[next] = true;

            backTracking(map, next, count + 1);
            used[next] = false;
        }
    }

    private static boolean bfs(char[][] map){
        char[][] arr = new char[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                arr[i][j] = map[i][j];
            }
        }

        for(int i = 0; i < perm.length; i++){
            int row = perm[i] / N;
            int col = perm[i] % N;

            if(arr[row][col] != EMPTY) return false;
            arr[row][col] = BLOCK;
        }

        for(Point t: teacher){
            for(final int[] DIRECTION: DIRECTIONS){
                int nextRow = t.row + DIRECTION[ROW];
                int nextCol = t.col + DIRECTION[COL];

                while(isInRange(nextRow, nextCol)) {
                    if(arr[nextRow][nextCol] == BLOCK) break;
                    if(arr[nextRow][nextCol] == STUDENT) return false;

                    nextRow += DIRECTION[ROW];
                    nextCol += DIRECTION[COL];
                }
            }
        }

        return true;            // avoid
    }

    private static boolean isInRange(int row, int col){
        return row >= 0 && row < N && col >= 0 && col < N;
    }
}
