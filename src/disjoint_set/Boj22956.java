package disjoint_set;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 22956번: 소나기
 *
 * @see https://www.acmicpc.net/problem/22956
 *
 */
public class Boj22956 {

    private static Map<Integer, Record> data = new HashMap<>();
    private static int N, M;

    private static int[] parent;
    private static int[][] H;
    private static int[][] water;

    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1;

    private static final String SPACE = " ";
    private static final String NEW_LINE = "\n";

    private static class Record {
        int row;
        int col;
        int height;
        int day;

        public Record(int row, int col, int height, int day) {
            this.row = row;
            this.col = col;
            this.height = height;
            this.day = day;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        init(N * M);
        H = new int[N][M];
        water = new int[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < M; j++) {
                H[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        StringBuilder sb = new StringBuilder();
        int day = 1;

        while(Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());

            search(a, b, c, day);

            int p = find(a * M + b);
            int row = data.containsKey(p) ? data.get(p).row + 1 : a + 1;
            int col = data.containsKey(p) ? data.get(p).col + 1 : b + 1;

            sb.append(row).append(SPACE).append(col).append(NEW_LINE);
            day++;
        }

        System.out.println(sb.toString());
    }

    /**
     *
     * Rainy
     *
     * line 102 ~ 109: merge each sets
     *
     * @param cur
     * @param nxt
     */
    private static void rainy(int cur, int nxt) {
        cur = setting(cur);
        nxt = setting(nxt);

        if(merged(cur, nxt)) return;
        Record current = data.get(cur);
        Record next = data.get(nxt);

        int newp = find(cur);

        if(current.height == next.height) {
            if(current.day < next.day) data.put(newp, current);
            else data.put(newp, next);
        }
        else {
            if(current.height < next.height) data.put(newp, current);
            else data.put(newp, next);
        }
    }

    /**
     *
     * Setting
     *
     * Set new data or change with new data.
     *
     * @param p
     * @return
     */
    private static int setting(int p) {
        int row = p / M;
        int col = p % M;

        p = find(p);

        if(!data.containsKey(p)) {
            data.put(p, new Record(row, col, H[row][col], water[row][col]));
            return p;
        }

        Record current = data.get(p);

        if(current.height <= H[row][col]) return p;
        data.put(p, new Record(row, col, H[row][col], water[row][col]));

        return p;
    }

    private static void search(int row, int col, int removed, int day) {
        water[row][col] = day;
        H[row][col] -= removed;

        for(final int[] DIRECTION: DIRECTIONS) {
            int nextRow = row + DIRECTION[ROW];
            int nextCol = col + DIRECTION[COL];

            if(outOfRange(nextRow, nextCol)) continue;
            if(water[nextRow][nextCol] == 0) continue;

            rainy(row * M + col, nextRow * M + nextCol);
        }
    }

    private static boolean outOfRange(int row, int col) {
        return row < 0 || col < 0 || row >= N || col >= M;
    }

    private static void init(int n) {
        parent = new int[n];
        Arrays.fill(parent, -1);
    }

    private static int find(int x) {
        if(parent[x] < 0) return x;
        return parent[x] = find(parent[x]);
    }

    private static boolean merged(int x, int y) {
        if(x == y) return true;

        if(parent[x] < parent[y]) {
            parent[x] += parent[y];
            parent[y] = x;
        }
        else {
            parent[y] += parent[x];
            parent[x] = y;
        }

        return false;
    }
}