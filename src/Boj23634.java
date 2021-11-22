import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Boj23634 {

    private static int[][] visit;
    private static int[] parent;
    private static int[][] village;
    private static int n, m;

    private static List<Point> fires = new ArrayList<>();
    private static List<Integer> groups = new ArrayList<>();

    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1;
    private static final int STONE = 2;

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
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        init();

        village = new int[n][m];
        for(int i = 0; i < n; i++) {
            String line = br.readLine();

            for(int j = 0; j < m; j++) {
                village[i][j] = line.charAt(j) - '0';

                if(village[i][j] != 0) continue;
                fires.add(new Point(i, j));
            }
        }

        mergingZero();
        spread();

        System.out.println(solution());
    }

    private static String solution() {
        int size = groups.size();

        if(size == 0) return size + " " + size;
        if(size == 1) return 0 + " " + -parent[groups.get(0)];



        return "";
    }

    private static void mergingZero() {
        for (Point fire: fires) {
            for (final int[] DIRECTION: DIRECTIONS) {
                int nextRow = fire.row + DIRECTION[ROW];
                int nextCol = fire.col + DIRECTION[COL];

                if (outOfRange(nextRow, nextCol)) continue;
                if (village[nextRow][nextCol] != 0) continue;
                merged(fire.row * m + fire.col, nextRow * m + nextCol);
            }
        }

        for(int i = 0; i < parent.length; i++) {
            if(parent[i] >= 0) continue;
            groups.add(i);
        }
    }

    private static void spread() {
        visit = new int[n][m];
        for(int i = 0; i < n; i++) {
            Arrays.fill(visit[i], -1);
        }

        Queue<Point> q = new ArrayDeque<>();

        for (Point start: fires) {
            if (visit[start.row][start.col] != -1) continue;
            visit[start.row][start.col] = 0;

            q.offer(start);
        }

        while(!q.isEmpty()) {
            Point current = q.poll();

            for (final int[] DIRECTION: DIRECTIONS) {
                int nextRow = current.row + DIRECTION[ROW];
                int nextCol = current.col + DIRECTION[COL];

                if(outOfRange(nextRow, nextCol)) continue;
                if(visit[nextRow][nextCol] != -1) continue;
                if(village[nextRow][nextCol] == STONE) continue;

                visit[nextRow][nextCol] = visit[current.row][current.col] + village[nextRow][nextCol];
                q.offer(new Point(nextRow, nextCol));
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                System.out.print(visit[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static boolean outOfRange(int row, int col) {
        return row < 0 || row >= n || col < 0 || col >= m;
    }

    private static void init() {
        parent = new int[n * m];
        Arrays.fill(parent, -1);
    }

    private static int find(int x) {
        if (parent[x] < 0) return x;
        return parent[x] = find(parent[x]);
    }

    private static boolean merged(int x, int y) {
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

        return 1 != 1;
    }
}
