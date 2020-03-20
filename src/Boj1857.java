import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1857 {
    private static final int[][] DIRECTIONS = {{1, 2}, {2, 1}, {-1, 2}, {1, -2}, {-2, 1}, {2, -1}, {-1, -2}, {-2, -1}};
    private static final int ROW = 0, COL = 1;

    private static Point start, end;

    private static final int ROCK = 2;
    private static final int ALREADY = 1;
    private static final int INF = 1_000_000_000;

    private static int[][] visit;
    private static int distance = INF;
    private static long result = Long.MAX_VALUE;
    private static int n, m;

    private static long[][][][] dp;

    private static class Point{
        int row;
        int col;

        public Point(int row, int col){
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 3) start = new Point(i, j);
                if (map[i][j] == 4) end = new Point(i, j);
            }
        }

        makeResult(map);
    }

    private static void makeResult(int[][] map){
        visit = new int[n][m];
        bfs(map);

        if(distance == INF){
            System.out.println(-1);
            return;
        }

        System.out.println(distance - 2);

//        for(int i = 0; i < n; i++){
//            for(int j = 0; j < m; j++){
//                System.out.print(visit[i][j] + " ");
//            }
//            System.out.println();
//        }



        System.out.println(result);
    }

    private static void bfs(int[][] map){
        Queue<Point> q = new LinkedList<>();
        q.offer(start);

        visit[start.row][start.col] = 1;

        while(!q.isEmpty()){
            int size = q.size();

            while(size-- > 0) {
                Point current = q.poll();

                for (final int[] DIRECTION : DIRECTIONS) {
                    int nextRow = current.row + DIRECTION[ROW];
                    int nextCol = current.col + DIRECTION[COL];

                    if (outOfRange(nextRow, nextCol)) continue;
                    if (map[nextRow][nextCol] == ROCK) continue;
                    if(visit[nextRow][nextCol] == 0) {
                        if (map[nextRow][nextCol] == ALREADY) visit[nextRow][nextCol] = visit[current.row][current.col];
                        else visit[nextRow][nextCol] = visit[current.row][current.col] + 1;

                        if (end.row == nextRow && end.col == nextCol)
                            distance = Math.min(distance, visit[end.row][end.col]);
                        q.offer(new Point(nextRow, nextCol));
                    }
                    else{
                        if (map[nextRow][nextCol] == ALREADY){
                            if(visit[nextRow][nextCol] <= visit[current.row][current.col]) continue;
                            visit[nextRow][nextCol] = visit[current.row][current.col];
                        }
                        else{
                            if(visit[nextRow][nextCol] <= visit[current.row][current.col] + 1) continue;
                            visit[nextRow][nextCol] = visit[current.row][current.col] + 1;
                        }

                        if (end.row == nextRow && end.col == nextCol) distance = Math.min(distance, visit[end.row][end.col]);
                        q.offer(new Point(nextRow, nextCol));
                    }
                }
            }
        }
    }

    private static boolean isPrevPath(int[][] map, Point current, Point prev){
        return (visit[current.row][current.col] == visit[prev.row][prev.col] - 1)
                || (map[prev.row][prev.col] == ALREADY && visit[current.row][current.col] == visit[prev.row][prev.col]);
    }

    private static boolean outOfRange(int row, int col){
        return row < 0 || row >= n || col < 0 || col >= m;
    }
}
