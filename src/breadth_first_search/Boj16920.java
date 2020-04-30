package breadth_first_search;

import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author exponential-e
 * 백준 16920번: 확장 게임
 *
 * @see https://www.acmicpc.net/problem/16920/
 *
 */
public class Boj16920 {
    private static int[] S;
    private static int N, M;
    private static int[] result;
    private static Queue<Point>[] q;

    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1;
    private static final char EMPTY = '.';
    private static final char BLOCK = '#';

    private static final String SPACE = " ";

    private static class Point {
        int row;
        int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        N = in.readInt();
        M = in.readInt();
        int P = in.readInt();

        S = new int[P];
        result = new int[P];
        q = new LinkedList[P];

        for (int i = 0; i < P; i++) {
            S[i] = in.readInt();
            q[i] = new LinkedList<>();
        }

        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String line = in.readString();

            for (int j = 0; j < M; j++) {
                char input = line.charAt(j);
                if (input == EMPTY) continue;;

                if (input == BLOCK) {
                    map[i][j] = -1;
                    continue;
                }

                map[i][j] = input - '0';
                q[map[i][j] - 1].offer(new Point(i, j));
                result[map[i][j] - 1]++;
            }
        }

        System.out.println(bfs(P, map));
    }

    private static String bfs(int p, int[][] arr) {
        LinkedList<Integer> sequence = new LinkedList<>();
        for(int i = 0; i < p; i++) {                        // expand sequence
            sequence.add(i);
        }

        while (!sequence.isEmpty()) {
            int index = sequence.remove();
            int loop = S[index];

            while (loop-- > 0) {
                int size = q[index].size();
                if (size == 0) break;

                while(size-- > 0) {
                    Point current = q[index].poll();

                    for (final int[] DIRECTION : DIRECTIONS) {
                        int nextRow = current.row + DIRECTION[ROW];
                        int nextCol = current.col + DIRECTION[COL];

                        if (outOfRange(nextRow, nextCol) || arr[nextRow][nextCol] != 0) continue;
                        arr[nextRow][nextCol] = (index + 1);
                        result[index]++;

                        q[index].offer(new Point(nextRow, nextCol));
                    }
                }
            }

            if (q[index].size() != 0) sequence.add(index);              // more area
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < p; i++) {
            sb.append(result[i]).append(SPACE);
        }

        return sb.toString();
    }

    private static boolean outOfRange(int row, int col) {
        return row < 0 || row >= N || col < 0 || col >= M;
    }

    private static class InputReader {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;
        private SpaceCharFilter filter;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        public int read() {
            if (numChars == -1) {
                throw new InputMismatchException();
            }
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0) {
                    return -1;
                }
            }
            return buf[curChar++];
        }

        public int readInt() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public String readString() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isSpaceChar(c));
            return res.toString();
        }

        public boolean isSpaceChar(int c) {
            if (filter != null) {
                return filter.isSpaceChar(c);
            }
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        public String next() {
            return readString();
        }

        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);
        }
    }
}
