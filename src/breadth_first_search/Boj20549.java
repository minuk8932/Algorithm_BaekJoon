package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 20549번: 인덕이의 고민
 *
 * @see https://www.acmicpc.net/problem/20549
 *
 */
public class Boj20549 {
    private static int N;
    private static Point s;
    private static ArrayList<Point> pos = new ArrayList<>();
    private static ArrayList<Integer> permutation = new ArrayList<>();
    private static boolean[] used;

    private static final int[][] BISHOP_DIRECTIONS = {{1, 1}, {-1, -1}, {-1, 1}, {1, -1}};
    private static final int[][] KNIGHT_DIRECTIONS = {{1, 2}, {-1, -2}, {-1, 2}, {1, -2},
            {2, 1}, {-2, 1}, {-2, -1}, {2, -1}, {1, 1}, {-1, -1}};
    private static final int ROW = 0, COL = 1;
    private static final int INF = 1_000_000_000;

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
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        s = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        int M = Integer.parseInt(br.readLine());
        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            pos.add(new Point(r, c));
        }

        br.close();
        int size = pos.size();

        for (int s = 1; s <= size; s++) {
            used = new boolean[size + 1];
            recursion(s, 1, s, pos.size());     // make sequence of feeding
        }

        System.out.println(bfs(A, B, C));
    }

    private static void recursion(int current, int count, int value, int limit) {
        if(count == limit){
            permutation.add(value);
            return;
        }

        used[current] = true;

        for(int next = 1; next <= limit; next++) {
            if(used[next]) continue;
            recursion(next, count + 1, value * 10 + next, limit);
            used[next] = false;
        }
    }

    private static int bfs(int knight, int bishop, int rook) {
        int result = INF;
        int limit = rook * 2;

        for(int seq: permutation) {                                 // make all cases and comparing
            int[][] visit = new int[N][N];
            Point start = s;
            Point end;

            int loop = seq;
            int count = 0;

            while(loop > 0) {
                int v = limit * 2;                                  // maximum cost of single path

                for (int row = 0; row < N; row++) {
                    for (int col = 0; col < N; col++) {
                        visit[row][col] = INF;
                    }
                }

                end = pos.get(loop % 10 - 1);
                loop /= 10;

                if(start.row == end.row && start.col == end.col) {  // start position has feed
                    start = end;
                    continue;
                }

                Queue<Point> q = new LinkedList<>();
                q.offer(new Point(start.row, start.col));
                visit[start.row][start.col] = 0;

                while (!q.isEmpty()) {
                    Point current = q.poll();

                    for (final int[] DIRECTION : KNIGHT_DIRECTIONS) {
                        int nextRow;
                        int nextCol;
                        int cost = visit[current.row][current.col];

                        if(DIRECTION[ROW] == 1 && DIRECTION[COL] == 1) {            // rook moving
                            Point[] pairs = {new Point(end.row, current.col), new Point(current.row, end.col)};
                            cost += rook;

                            for(Point next: pairs) {
                                nextRow = next.row;
                                nextCol = next.col;

                                if (visit[nextRow][nextCol] <= cost) continue;
                                if (cost > limit) break;
                                visit[nextRow][nextCol] = cost;

                                if (nextRow == end.row && nextCol == end.col) {
                                    v = Math.min(visit[nextRow][nextCol], v);
                                    break;
                                }

                                q.offer(new Point(nextRow, nextCol));
                            }
                        }
                        else if(DIRECTION[ROW] == -1 && DIRECTION[COL] == -1) {         // bishop moving
                            cost += bishop;

                            for(final int[] B_DIRECTION: BISHOP_DIRECTIONS) {
                                nextRow = current.row;
                                nextCol = current.col;

                                while(true) {
                                    nextRow += B_DIRECTION[ROW];
                                    nextCol += B_DIRECTION[COL];

                                    if(outOfRange(nextRow, nextCol)) break;
                                    if(visit[nextRow][nextCol] <= cost) continue;
                                    if(cost > limit) continue;
                                    visit[nextRow][nextCol] = cost;

                                    if (nextRow == end.row && nextCol == end.col) v = Math.min(visit[nextRow][nextCol], v);
                                    q.offer(new Point(nextRow, nextCol));
                                }
                            }
                        }
                        else {                                                          // knight moving
                            nextRow = current.row + DIRECTION[ROW];
                            nextCol = current.col + DIRECTION[COL];
                            cost += knight;

                            if (outOfRange(nextRow, nextCol)) continue;
                            if (visit[nextRow][nextCol] <= cost) continue;
                            if (cost > limit) continue;
                            visit[nextRow][nextCol] = cost;

                            if (nextRow == end.row && nextCol == end.col) v = Math.min(visit[nextRow][nextCol], v);
                            q.offer(new Point(nextRow, nextCol));
                        }
                    }
                }

                count += v;
                start = end;
            }

            result = Math.min(result, count);
        }

        return result;
    }

    private static boolean outOfRange(int row, int col) {
        return row < 0 || col < 0 || row >= N || col >= N;
    }
}
