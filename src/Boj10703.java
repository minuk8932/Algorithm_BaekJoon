import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 10703번: 유성
 *
 * @see https://www.acmicpc.net/problem/10703/
 *
 */
public class Boj10703 {
    private static int[] max, min;

    private static final char STAR = 'X';
    private static final char EMPTY = '.';
    private static final char BLOCK = '#';
    private static final String NEW_LINE = "\n";

    private static char[][] result;

    private static LinkedList<Point> star = new LinkedList<>();
    private static int interval;

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
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char[][] map = new char[N][M];
        result = new char[N][M];

        max = new int[M];
        min = new int[M];

        interval = N;
        Arrays.fill(min, N);
        Arrays.fill(max, -N);                   // both empty then offsetting

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            Arrays.fill(result[i], EMPTY);

            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);

                if (map[i][j] == STAR) {
                    star.add(new Point(i, j));
                    max[j] = i;
                }

                if (map[i][j] == BLOCK){
                    result[i][j] = BLOCK;

                    if(min[j] == N){
                        min[j] = i;
                        interval = Math.min(interval, min[j] - max[j] - 1);
                    }
                }
            }
        }

        System.out.println(recover(N, M));
    }

    private static String recover (int n, int m){
        while(!star.isEmpty()) {
            Point current = star.remove();
            result[current.row + interval][current.col] = STAR;         // falling
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                sb.append(result[i][j]);
            }
            sb.append(NEW_LINE);
        }

        return sb.toString();
    }
}
