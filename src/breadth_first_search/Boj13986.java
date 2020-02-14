package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 13986번: Gravity
 *
 * @see https://www.acmicpc.net/problem/13986/
 *
 */
public class Boj13986 {
    private static final char BALL = 'o';
    private static final char BLOCK = '#';
    private static final char EMPTY = '.';
    private static final char NEW_LINE = '\n';

    private static final int ROW = 1, COL = 0;
    private static ArrayList<Point> start = new ArrayList<>();

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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char[][] map = new char[N][M];
        for(int i = 0; i < N; i++){
            String line = br.readLine();

            for(int j = 0; j < M; j++){
                map[i][j] = line.charAt(j);
            }
        }

        for(int i = N - 1; i >= 0; i--){                            // make priority
            for(int j = M - 1; j >= 0; j--){
                if(map[i][j] == BALL) start.add(new Point(i, j));
            }
        }

        gravity(N, M, map);
    }

    private static void gravity(int n, int m, char[][] arr){
        for(Point p: start){
            Queue<Point> q = new LinkedList<>();
            q.offer(p);

            while(!q.isEmpty()){
                Point current = q.poll();

                Point next = new Point(current.row + ROW, current.col + COL);
                if(next.row >= n || arr[next.row][next.col] == BLOCK || arr[next.row][next.col] == BALL) continue;

                arr[next.row][next.col] = BALL;             // drop
                arr[current.row][current.col] = EMPTY;

                q.offer(new Point(next.row, next.col));
            }
        }

        print(arr);
    }

    private static void print(char[][] arr){
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[i].length; j++){
                sb.append(arr[i][j]);
            }
            sb.append(NEW_LINE);
        }

        System.out.println(sb.toString());
    }
}
