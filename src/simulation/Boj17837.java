package simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 17837번: 새로운 게임 2
 *
 * @see https://www.acmicpc.net/problem/17837
 *
 */
public class Boj17837 {
    private static final int WHITE = 0, BLUE = 2;

    private static Point[] piece;
    private static LinkedList<Integer>[] status;
    private static int[][] map;
    private static int N;

    private static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    private static final int[] swap = {1, 0, 3, 2};
    private static final int ROW = 0, COL = 1;

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

    public static void main(String[] args ) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        status = new LinkedList[N * N];
        map = new int[N][N];
        piece = new Point[K];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                status[maker(i, j)] = new LinkedList<>();
            }
        }

        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            piece[i] = new Point(Integer.parseInt(st.nextToken()) - 1
                    , Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
            status[maker(piece[i].row, piece[i].col)].add(i);
        }

        System.out.println(newGame2(K));
    }

    private static int newGame2(int k) {
        int count = 1;

        while(count < 1000) {
            for(int seq = 0; seq < k; seq++) {
                Point p = piece[seq];
                Point next = new Point(p.row + DIRECTIONS[p.dir][ROW], p.col + DIRECTIONS[p.dir][COL], p.dir);

                if(outOfRange(next.row, next.col) || map[next.row][next.col] == BLUE) {
                    next.dir = swap[p.dir];
                    next = new Point(p.row + DIRECTIONS[next.dir][ROW]
                            , p.col + DIRECTIONS[next.dir][COL], next.dir);
                    piece[seq].dir = next.dir;                                  // save current prior piece's dir

                    if(outOfRange(next.row, next.col) || map[next.row][next.col] == BLUE) continue;

                    if(map[next.row][next.col] == WHITE) whiteMove(p, next, seq);
                    else redMove(p, next, seq);
                }
                else if(map[next.row][next.col] == WHITE) {
                    whiteMove(p, next, seq);
                }
                else {
                    redMove(p, next, seq);
                }

                for(int row = 0; row < N; row++) {
                    for(int col = 0; col < N; col++) {
                        if(status[maker(row, col)].size() >= 4) return count;       // gathered four pieces
                    }
                }
            }

            count++;
        }

        return -1;
    }

    private static boolean outOfRange(int row, int col) {
        return row < 0 || row >= N || col < 0 || col >= N;
    }

    private static void redMove(Point current, Point next, int seq) {
        int loop = status[maker(current.row, current.col)].size();

        while(loop-- > 0) {
            int idx = status[maker(current.row, current.col)].removeLast();
            status[maker(next.row, next.col)].add(idx);
            piece[idx] = new Point(next.row, next.col, piece[idx].dir);     // keep dir another !

            if(idx == seq) break;
        }
    }

    private static void whiteMove(Point current, Point next, int seq) {
        int[] arr = new int[status[maker(current.row, current.col)].size()];
        Arrays.fill(arr, -1);

        for(int i = arr.length - 1; i >= 0; i--) {
            int idx = status[maker(current.row, current.col)].removeLast();
            arr[i] = idx;

            if(idx == seq) break;
        }

        for(int a: arr) {
            if(a == -1) continue;

            status[maker(next.row, next.col)].add(a);
            piece[a] = new Point(next.row, next.col, piece[a].dir);
        }
    }

    private static int maker(int row, int col) {
        return row * N + col;
    }
}
