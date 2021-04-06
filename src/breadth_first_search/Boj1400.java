package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 1400번: 화물차
 *
 * @see https://www.acmicpc.net/problem/1400
 *
 */
public class Boj1400 {

    private static final char BLOCK = '.';
    private static final char START = 'A';
    private static final char END = 'B';
    private static final char ctoi = '0';

    private static final String NEW_LINE = "\n";
    private static final String HYPHEN = "-";
    private static final String IM = "impossible";

    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1;
    private static final int SIGN = 15;
    private static final int INF = 1_000_000_000;

    private static Point start, end;
    private static boolean[] flags;
    private static char[][] map;
    private static List<Integer>[] cost;

    private static int N, M;

    private static class Point {
        int row;
        int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    /**
     *
     * Main
     *
     * line 101 ~ 104: save prefix sum of gate time
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if(N == 0 && M == 0) break;

            map = new char[N][M];
            int size = 0;

            for (int i = 0; i < N; i++) {
                String line = br.readLine();

                for (int j = 0; j < M; j++) {
                    map[i][j] = line.charAt(j);

                    if (signPosition(map[i][j])) size++;
                    if (map[i][j] == START) start = new Point(i, j);
                    if (map[i][j] == END) end = new Point(i, j);
                }
            }

            flags = new boolean[size + 1];
            cost = new ArrayList[size + 1];
            int len = N * M;

            for(int i = 0; i <= size; i++) {
                cost[i] = new ArrayList<>();
            }

            while (size-- > 0){
                st = new StringTokenizer(br.readLine());
                int idx = Integer.parseInt(st.nextToken());
                flags[idx] = st.nextToken().equals(HYPHEN);

                int h = Integer.parseInt(st.nextToken());
                int o = Integer.parseInt(st.nextToken());

                int first = flags[idx] ? h: o;
                int second = flags[idx] ? o: h;

                cost[idx].add(0);
                for(int i = 1; i <= len; i++) {
                    int nxt = cost[idx].get(i - 1);
                    cost[idx].add(nxt + (i % 2 == 1 ? first: second));
                }
            }

            sb.append(search()).append(NEW_LINE);
            br.readLine();
        }

        System.out.print(sb.toString());
    }

    /**
     *
     * BFS
     *
     * line 151 ~ 159: check current sign state & add 1 or add next time
     *
     * @return
     */
    private static String search() {
        int[][] visit = new int[N][M];
        for(int i = 0; i < N; i++) {
            Arrays.fill(visit[i], INF);
        }

        Queue<Point> q = new LinkedList<>();
        q.offer(start);

        visit[start.row][start.col] = 0;

        while(!q.isEmpty()) {
            Point current = q.poll();

            for(int d = 0; d < 4; d++) {
                int nextRow = current.row + DIRECTIONS[d][ROW];
                int nextCol = current.col + DIRECTIONS[d][COL];

                if (outOfRange(nextRow, nextCol)) continue;
                if (map[nextRow][nextCol] == BLOCK) continue;
                if (visit[nextRow][nextCol] <= visit[current.row][current.col] + 1) continue;

                if (signPosition(map[nextRow][nextCol])) {
                    int index = map[nextRow][nextCol] - ctoi;
                    int cur = visit[current.row][current.col];
                    int way = 0;
                    int c = 0;

                    int size = cost[index].size();
                    for(int i = 1; i < size; i++) {
                        int from = cost[index].get(i - 1);
                        int to = cost[index].get(i);

                        if(cur < from || cur >= to) continue;
                        way = i;
                        c = to;
                        break;
                    }

                    visit[nextRow][nextCol] = (noCost(index, way, d) ? visit[current.row][current.col]: c) + 1;
                }
                else {
                    visit[nextRow][nextCol] = visit[current.row][current.col] + 1;
                }

                q.offer(new Point(nextRow, nextCol));
            }
        }

        return visit[end.row][end.col] == INF ? IM: visit[end.row][end.col] + "";
    }

    private static boolean outOfRange(int row, int col) {
        return row < 0 || col < 0 || row >= N || col >= M;
    }

    private static boolean signPosition(char sign) {
        int value = sign - ctoi;
        return (SIGN & value) == value;
    }

    private static boolean noCost(int idx, int way, int dir) {
        if(dir % 2 == 1) return flags[idx] ? way % 2 == 1: way % 2 == 0;
        else return !flags[idx] ? way % 2 == 1: way % 2 == 0;
    }
}
