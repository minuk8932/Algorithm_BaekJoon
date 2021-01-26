import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Boj20130 {

    private static ArrayList<Point>[] locks = new ArrayList[26];
    private static boolean[][] visit;
    private static int N, M;
    private static int count;
    private static Point start, end;

    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1;

    private static final String SPACE = " ";
    private static final String NEW_LINE = "\n";
    private static final char BLOCK = '#';
    private static final char START = '@';
    private static final char END = '!';

    private static class Point {
        int row;
        int col;
        int key;

        public Point(int row, int col, int key) {
            this.row = row;
            this.col = col;
            this.key = key;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < locks.length; i++) {
            locks[i] = new ArrayList<>();
        }

        visit = new boolean[N][M];
        char[][] map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();

            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == START) start = new Point(i, j, 0);
                if (map[i][j] == END) end = new Point(i, j, 0);
                if (map[i][j] >= 'A' && map[i][j] <= 'Z') locks[map[i][j] - 'A'].add(new Point(i, j, 0));
            }
        }

        System.out.println(bfs(map));
    }

    private static String bfs(char[][] map) {
        Queue<Point> q = new LinkedList<>();
        StringBuilder sb = new StringBuilder();

        sb.append(start.row + 1).append(SPACE).append(start.col + 1).append(NEW_LINE);
        q.offer(start);

        visit[start.row][start.col] = true;
        count = 1;

        while (!q.isEmpty()) {
            Point current = q.poll();

            if (map[current.row][current.col] >= 'a' && map[current.row][current.col] <= 'z') {
                int target = map[current.row][current.col] - 'a';
                current.key |= 1 << target;

                for (Point p : locks[target]) {
                    for (final int[] DIRECTION : DIRECTIONS) {
                        int nextRow = p.row + DIRECTION[ROW];
                        int nextCol = p.col + DIRECTION[COL];

                        if (outOfRange(nextRow, nextCol)) continue;
                        if (map[nextRow][nextCol] == BLOCK) continue;
                        if (!visit[nextRow][nextCol]) continue;

                        if(!visit[p.row][p.col]) {
                            visit[p.row][p.col] = true;
                            count++;
                            sb.append(p.row + 1).append(SPACE).append(p.col + 1).append(NEW_LINE);
                        }

                        q.offer(new Point(p.row, p.col, current.key));
                    }
                }
            }

            for (final int[] DIRECTION : DIRECTIONS) {
                int nextRow = current.row + DIRECTION[ROW];
                int nextCol = current.col + DIRECTION[COL];

                if (outOfRange(nextRow, nextCol)) continue;
                if (map[nextRow][nextCol] == BLOCK) continue;

                if (map[nextRow][nextCol] >= 'A' && map[nextRow][nextCol] <= 'Z') {
                    int lock = 1 << (map[nextRow][nextCol] - 'A');
                    if ((lock & current.key) != 1) continue;

                    if(!visit[nextRow][nextCol]){
                        visit[nextRow][nextCol] = true;
                        count++;
                        sb.append(nextRow + 1).append(SPACE).append(nextCol + 1).append(NEW_LINE);
                    }

                    q.offer(new Point(nextRow, nextCol, current.key));
                }
                else if (map[nextRow][nextCol] == END) {
                    sb.append(end.row + 1).append(SPACE).append(end.col + 1).append(NEW_LINE);
                    System.out.println(++count);
                    return sb.toString();
                }
                else {
                    if (!visit[nextRow][nextCol]) {
                        visit[nextRow][nextCol] = true;
                        count++;
                        sb.append(nextRow + 1).append(SPACE).append(nextCol + 1).append(NEW_LINE);
                    }

                    q.offer(new Point(nextRow, nextCol, current.key));
                }
            }
        }

        return sb.toString();
    }

    private static boolean outOfRange(int row, int col) {
        return row < 0 || row >= N || col < 0 || col >= M;
    }
}
