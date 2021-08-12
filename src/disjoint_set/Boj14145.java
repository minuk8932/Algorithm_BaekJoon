package disjoint_set;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 *
 * @author exponential-e
 * 백준 14145번: Žetva
 *
 * @see https://www.acmicpc.net/problem/14145
 *
 */
public class Boj14145 {

    private static final Function<Integer, int[]> ARRAY = int[]::new;
    private static final String NEW_LINE = "\n";
    private static int[] parent;
    private static int N, M;

    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Function<String, Integer> parse = Integer::parseInt;
        BiFunction<Integer, Integer, boolean[][]> mapped = (n, m) -> new boolean[n][m];

        N = parse.apply(st.nextToken());
        M = parse.apply(st.nextToken());

        init();

        boolean[][] map = mapped.apply(N, M);

        for(int i = 0; i < N; i++) {
            String line = br.readLine();

            for(int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) == '1';
            }
        }

        search(map);
        System.out.println(printer(map));
    }

    /**
     *
     * Printer
     *
     * line 61 ~ 62: comparing & sort by set size
     *
     * @param m
     * @return
     */
    private static String printer(boolean[][] m) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(
                Comparator.comparingInt(x -> -parent[find(x)]));

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(!m[i][j]) continue;

                int index = i * M + j;
                pq.offer(index);
            }
        }

        int[] answer = ARRAY.apply(N * M);
        int head = parent[find(pq.peek())];

        int day = 1;

        while(!pq.isEmpty()) {
            int current = pq.poll();

            if(parent[find(current)] != head) {
                head = parent[find(current)];
                day++;
            }

            answer[current] = day;
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 1; i <= answer.length; i++) {
            sb.append(answer[i - 1]);
            if(i % M == 0) sb.append(NEW_LINE);
        }

        return sb.toString();
    }

    private static void search(boolean[][] map) {
        for(int row = 0; row < N; row++) {
            for(int col = 0; col < M; col++) {
                int start = row * M + col;

                if(!map[row][col]) continue;
                if(parent[find(start)] != -1) continue;

                Queue<Integer> q = new ArrayDeque<>();
                q.offer(start);

                while(!q.isEmpty()) {
                    int current = q.poll();

                    for (final int[] DIRECTION: DIRECTIONS) {
                        int nextRow = current / M + DIRECTION[ROW];
                        int nextCol = current % M + DIRECTION[COL];
                        int next = nextRow * M + nextCol;

                        if(outofRange(nextRow, nextCol)) continue;
                        if(!map[nextRow][nextCol]) continue;
                        if(merged(start, next)) continue;

                        q.offer(next);
                    }
                }
            }
        }
    }

    private static void init() {
        parent = ARRAY.apply(N * M);

        Arrays.fill(parent, -1);
    }

    private static int find(int x) {
        if(parent[x] < 0) return x;
        return parent[x] = find(parent[x]);
    }

    private static boolean merged (int x, int y) {
        x = find(x);
        y = find(y);

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

    private static boolean outofRange(int row, int col) {
        return row < 0 || col < 0 || row >= N || col >= M;
    }
}
