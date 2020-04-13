package disjoint_set;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 16000번: 섬
 *
 * @see https://www.acmicpc.net/problem/16000/
 *
 */
public class Boj16000 {
    private static final String NEW_LINE = "\n";
    private static final char SAFE = 'O';
    private static final char DANGER = 'X';
    private static final char ISLAND = '#';
    private static final char OCEAN = '.';

    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    private static final int ROW = 0, COL = 1;

    private static int[][] numbered;
    private static int[] ocean;
    private static int[] safeSet;
    private static int count;

    private static int N, M;
    private static ArrayList<Point> adjacent = new ArrayList<>();

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
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        init();

        char[][] map = new char[N][M];
        numbered = new int[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();

            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
                if(map[i][j] == ISLAND) count++;
            }
        }

        numbering(map);
        floodfill(map);
        findSafeIsland(map);
        safeGrouping(map);

        System.out.println(getResult(map));
    }

    private static String getResult(char[][] map) {
        StringBuilder sb = new StringBuilder();

        for(int row = 0; row < N; row++) {
            for(int col = 0; col < M; col++) {
                if(map[row][col] == OCEAN){
                    sb.append(OCEAN);
                }
                else {
                    if(safeSet[find(row * M + col, safeSet)] != -1) sb.append(SAFE);
                    else sb.append(count == 1 ? SAFE: DANGER);                              // just one island size one
                }
            }

            sb.append(NEW_LINE);
        }

        return sb.toString();
    }

    private static void numbering(char[][] map) {
        int count = 0;

        for(int row = 0; row < N; row++) {
            for(int col = 0; col < M; col++) {
                if(map[row][col] != ISLAND || numbered[row][col] != 0) continue;
                Queue<Point> q = new LinkedList<>();
                q.offer(new Point(row, col));

                numbered[row][col] = ++count;

                while(!q.isEmpty()) {
                    Point current = q.poll();

                    for(int i = 0; i < 4; i++) {
                        int nextRow = current.row + DIRECTIONS[i][ROW];
                        int nextCol = current.col + DIRECTIONS[i][COL];

                        if(outOfRange(nextRow, nextCol)) continue;
                        if(numbered[nextRow][nextCol] != 0) continue;
                        if(map[nextRow][nextCol] == OCEAN) continue;

                        numbered[nextRow][nextCol] = count;                 // classify each other
                        q.offer(new Point(nextRow, nextCol));
                    }
                }
            }
        }
    }

    private static void safeGrouping(char[][] map) {
        Point parent = adjacent.get(0);

        for(Point start: adjacent) {
            Queue<Point> q = new LinkedList<>();
            q.offer(start);

            merged(parent.row  * M + parent.col, start.row * M + start.col, safeSet);

            while(!q.isEmpty()) {
                Point current = q.poll();

                for(int i = 0; i < 4; i++) {
                    int nextRow = current.row + DIRECTIONS[i][ROW];
                    int nextCol = current.col + DIRECTIONS[i][COL];

                    if(outOfRange(nextRow, nextCol)) continue;
                    if(map[nextRow][nextCol] == OCEAN) continue;
                    if(merged(parent.row * M + parent.col, nextRow * M + nextCol, safeSet)) continue;

                    q.offer(new Point(nextRow, nextCol));
                }
            }
        }
    }

    private static void findSafeIsland(char[][] map) {
        boolean[][] visit = new boolean[N][M];

        for(int row = 0; row < N; row++) {
            for(int col = 0; col < M; col++) {
                if(map[row][col] != ISLAND || visit[row][col]) continue;
                Queue<Point> q = new LinkedList<>();
                q.offer(new Point(row, col));

                visit[row][col] = true;
                boolean saved = false;

                while(!q.isEmpty()) {
                    Point current = q.poll();

                    for(int i = 0; i < 4; i++) {
                        int nextRow = current.row + DIRECTIONS[i][ROW];
                        int nextCol = current.col + DIRECTIONS[i][COL];

                        if(outOfRange(nextRow, nextCol)) continue;
                        if(map[nextRow][nextCol] == OCEAN) {
                            int size = -ocean[find(nextRow * M + nextCol, ocean)];      // is grouped

                            if(size > 1) {
                                if(!saved) {
                                    adjacent.add(new Point(current.row, current.col));      // maybe safe
                                    saved = true;
                                }
                            }

                            continue;
                        }

                        if(visit[nextRow][nextCol]) continue;
                        visit[nextRow][nextCol] = true;

                        q.offer(new Point(nextRow, nextCol));
                    }
                }
            }
        }
    }

    private static void floodfill(char[][] map) {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(0, 0));

        while(!q.isEmpty()) {
            Point current = q.poll();

            for(int i = 0; i < 8; i++) {
                int nextRow = current.row + DIRECTIONS[i][ROW];
                int nextCol = current.col + DIRECTIONS[i][COL];

                if(outOfRange(nextRow, nextCol)) continue;
                if(map[nextRow][nextCol] != OCEAN) continue;
                if(i > 3 && blocked(current.row, current.col, i)) continue;                 // can spread ?
                if(merged(current.row * M + current.col, nextRow * M + nextCol, ocean)) continue;

                q.offer(new Point(nextRow, nextCol));
            }
        }
    }

    private static boolean blocked (int row, int col, int index) {
        int[] adjRow = {row + DIRECTIONS[index][ROW],row};
        int[] adjCol = {col, col + DIRECTIONS[index][COL]};

        return numbered[adjRow[0]][adjCol[0]] == numbered[adjRow[1]][adjCol[1]];
    }

    private static boolean outOfRange(int row, int col) {
        return row < 0 || row >= N || col < 0 || col >= M;
    }

    private static void init() {
        ocean = new int[N * M];
        safeSet = new int[N * M];

        for(int i = 0; i < ocean.length; i++) {
            ocean[i] = -1;
            safeSet[i] = -1;
        }
    }

    private static int find(int x, int[] parent) {
        if(parent[x] < 0) return x;
        return parent[x] = find(parent[x], parent);
    }

    private static boolean merged(int x, int y, int[] parent) {
        x = find(x, parent);
        y = find(y, parent);

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
