package search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 15705번: 단어 찾기
 *
 * @see https://www.acmicpc.net/problem/15705/
 *
 */
public class Boj15705 {
    private static ArrayList<Point> list = new ArrayList<>();

    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {-1, 1}, {1, -1}, {1, 1}, {-1, -1}};
    private static final int ROW = 0, COL = 1;

    private static class Point{
        int row;
        int col;
        int idx;

        public Point(int row, int col, int idx){
            this.row = row;
            this.col = col;
            this.idx = idx;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] S = br.readLine().toCharArray();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char[][] map = new char[N][M];
        for(int i = 0; i < N; i++){
            String line = br.readLine();

            for(int j = 0; j < M; j++){
                map[i][j] = line.charAt(j);
                if(S[0] == map[i][j]) list.add(new Point(i, j, 1));
            }
        }

        System.out.println(search(N, M, S, map));
    }

    private static int search(int n, int m, char[] src, char[][] snk){
        if(src.length == 1 && list.size() > 0) return 1;

        for(Point current: list){
            for(final int[] DIRECTION: DIRECTIONS){
                int nextRow = current.row + DIRECTION[ROW];
                int nextCol = current.col + DIRECTION[COL];
                int nextIdx = current.idx + 1;

                while(nextRow >= 0 && nextCol >= 0 && nextRow < n && nextCol < m){
                    if(snk[nextRow][nextCol] != src[nextIdx - 1]) break;

                    if(nextIdx == src.length) return 1;     // find

                    nextRow += DIRECTION[ROW];              // move one way
                    nextCol += DIRECTION[COL];
                    nextIdx += 1;
                }
            }
        }

        return 0;
    }
}
