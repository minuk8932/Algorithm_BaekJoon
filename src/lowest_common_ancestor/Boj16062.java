package lowest_common_ancestor;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 16062번: Attack on Alpha-Zet
 *
 * @see https://www.acmicpc.net/problem/16062
 *
 */
public class Boj16062 {

    private static int size;
    private static int n, m;
    private static int[][] parent;
    private static int[] depth;

    private static boolean[][] map;

    private static List<Integer>[] path;
    private static HashMap<Integer, Integer> index = new HashMap<>();

    private static final int[][] DIRECTIONS = {{2, 0}, {0, 2}, {-2, 0}, {0, -2}};
    private static final int ROW = 0, COL = 1;

    private static final char FLOOR = '_';
    private static final char EMPTY = ' ';

    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        int N = in.readInt();
        int M = in.readInt();

        n = (N << 1) + 1;
        m = (M << 1) + 1;

        map = new boolean[n][m];
        int add = 0;

        for(int i = 0; i <= N; i++) {
            for(int j = 0; j < map[i].length; j++) {
                char a = (char) in.read();
                if(a == EMPTY) continue;

                map[a == FLOOR ? (i << 1): (i + add)][j] = true;
            }

            in.read();
            if(i != 0) add++;
        }

        filler();

        init();
        remodeling();
        connecting();

        int K = in.readInt();
        int a = ((in.readInt() - 1) << 1) + 1;
        int b = ((in.readInt() - 1) << 1) + 1;

        int x = a * m + b;
        long result = 0L;

        while(K-- > 1) {
            a = ((in.readInt() - 1) << 1) + 1;
            b = ((in.readInt() - 1) << 1) + 1;

            int y = a * m + b;

            int[] v = {index.get(x), index.get(y)};
            result += depth[v[0]] + depth[v[1]] - depth[LCA(v[0], v[1])] * 2;

            x = y;
        }

        System.out.println(result);
    }

    private static int LCA(int x, int y) {
        if(depth[x] > depth[y]) {
            int tmp = x;
            x = y;
            y = tmp;
        }

        for(int i = 20; i >= 0; i--) {
            int jump = 1 << i;

            if(depth[y] - depth[x] < jump) continue;
            y = parent[y][i];
        }

        if(x == y) return x;

        for(int i = 20; i >= 0; i--) {
            if(parent[x][i] == parent[y][i]) continue;
            x = parent[x][i];
            y = parent[y][i];
        }

        return parent[x][0];
    }

    private static void connecting() {
        for(int p = 1; p < 21; p++){
            for (int cur = 0; cur < size; cur++) {
                parent[cur][p] = parent[parent[cur][p - 1]][p - 1];
            }
        }
    }

    private static void init() {

        parent = new int[size][21];
        depth = new int[size];
        path = new ArrayList[size];

        for(int i = 0; i < size; i++) {
            depth[i] = -1;
            path[i] = new ArrayList<>();
        }
    }

    /**
     *
     * Remodeling
     *
     * line 166: Declare depth & cost, graph topology by bfs.
     *
     */
    private static void remodeling() {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(m + 1);

        int idx = 0;
        depth[0] = 0;
        index.put(m + 1, idx++);

        while(!q.isEmpty()) {
            int current = q.poll();

            int row = current / m;
            int col = current % m;

            for(final int[] DIRECTION: DIRECTIONS) {
                int nextRow = row + DIRECTION[ROW];
                int nextCol = col + DIRECTION[COL];

                int viaRow = row + (DIRECTION[ROW] >> 1);
                int viaCol = col + (DIRECTION[COL] >> 1);

                int c = index.get(current);

                if(outOfRange(nextRow, nextCol)) continue;
                if(map[viaRow][viaCol]) continue;
                if(map[nextRow][nextCol]) continue;

                int next = nextRow * m + nextCol;
                if(!index.containsKey(next)) index.put(next, idx++);
                if(depth[index.get(next)] != -1) continue;

                int p = index.get(next);
                depth[p] = depth[c] + 1;

                path[c].add(p);
                path[p].add(c);

                parent[p][0] = c;
                q.offer(next);
            }
        }
    }

    private static boolean outOfRange(int row, int col) {
        return row < 0 || col < 0 || row >= n || col >= m;
    }

    /**
     *
     * Filter
     *
     * line 196 ~ 204: Data compression
     *
     */
    private static void filler() {
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[i].length; j++) {
                if(i % 2 != 0 || j % 2 != 0) continue;
                map[i][j] = true;
            }
        }

        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[i].length; j++) {
                if(map[i][j]) continue;
                if(i % 2 != 1 || j % 2 != 1) continue;

                size++;
            }
        }
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
            boolean isSpaceChar(int ch);
        }
    }
}
