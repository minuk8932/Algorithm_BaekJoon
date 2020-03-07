package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 5022번: 연결
 *
 * @see https://www.acmicpc.net/problem/5022/
 *
 */
public class Boj5022 {
    private static final String IM = "IMPOSSIBLE";
    private static final int INF = 10_000_000;

    private static int[][] aVisit;
    private static int[][] bVisit;

    private static boolean[][] way;

    private static int N, M;
    private static boolean flag;

    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1;

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
        N = Integer.parseInt(st.nextToken()) + 1;
        M = Integer.parseInt(st.nextToken()) + 1;

        Point[] A = new Point[2];
        for(int i = 0; i < 2; i++){
            st = new StringTokenizer(br.readLine());
            A[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Point[] B = new Point[2];
        for(int i = 0; i < 2; i++){
            st = new StringTokenizer(br.readLine());
            B[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        int result = getResult(A, B);
        System.out.println(result >= INF ? IM: result);
    }

    private static int getResult(Point[] a, Point[] b){
        aVisit = new int[N][M];
        bVisit = new int[N][M];
        way = new boolean[N][M];

        Point dist1 = new Point(bfs(a[0], a[1], aVisit, b[0], b[1]), 0);        // A priority
        dfs(a[1], a[0], aVisit, b[0], b[1]);
        dist1.col = nextBfs(b[0], b[1], way, a[0], a[1]);

        if(dist1.row < 0) dist1.row = INF;
        if(dist1.col < 0) dist1.col = INF;

        way = new boolean[N][M];

        Point dist2 = new Point(bfs(b[0], b[1], bVisit, a[0], a[1]), 0);        // B priority
        flag = false;
        dfs(b[1], b[0], bVisit, a[0], a[1]);
        dist2.col = nextBfs(a[0], a[1], way, b[1], b[0]);

        if(dist2.row < 0) dist2.row = INF;
        if(dist2.col < 0) dist2.col = INF;

        int min = Math.min(dist1.row + dist1.col, dist2.row + dist2.col);
        return min;
    }

    private static int bfs(Point start, Point end, int[][] visit, Point nstart, Point nend){
        Queue<Point> q = new LinkedList<>();
        q.offer(start);

        visit[start.row][start.col] = 1;

        while(!q.isEmpty()){
            Point current = q.poll();

            for(final int[] DIRECTION: DIRECTIONS){
                int nextRow = current.row + DIRECTION[ROW];
                int nextCol = current.col + DIRECTION[COL];

                if(outRange(nextRow, nextCol)) continue;
                if(isAnother(nstart, nend, new Point(nextRow, nextCol))) continue;
                if(visit[nextRow][nextCol] != 0) continue;
                visit[nextRow][nextCol] = visit[current.row][current.col] + 1;

                q.offer(new Point(nextRow, nextCol));
            }
        }

        return visit[end.row][end.col] - 1;
    }

    private static void dfs(Point current, Point end, int[][] visit, Point nstart, Point nend){
        if(flag) return;
        way[current.row][current.col] = true;

        for(final int[] DIRECTION: DIRECTIONS){
            int adjRow = current.row + DIRECTION[ROW];
            int adjCol = current.col + DIRECTION[COL];

            if(outRange(adjRow, adjCol)) continue;
            if(isAnother(nstart, nend, new Point(adjRow, adjCol))) continue;
            if(visit[adjRow][adjCol] != visit[current.row][current.col] - 1) continue;      // reverse search

            if(adjRow == end.row && adjCol == end.col){             // if find one, then break all
                way[adjRow][adjCol] = true;
               flag = true;
               return;
            }

            dfs(new Point(adjRow, adjCol), end, visit, nstart, nend);
        }
    }

    private static int nextBfs(Point start, Point end, boolean[][] visit, Point nstart, Point nend){
        int[][] dist = new int[N][M];
        Queue<Point> q = new LinkedList<>();

        q.offer(start);
        dist[start.row][start.col] = 1;

        while(!q.isEmpty()){
            Point current = q.poll();

            for(final int[] DIRECTION: DIRECTIONS){
                int nextRow = current.row + DIRECTION[ROW];
                int nextCol = current.col + DIRECTION[COL];

                if(outRange(nextRow, nextCol)) continue;
                if(isAnother(nstart, nend, new Point(nextRow, nextCol))) continue;
                if(visit[nextRow][nextCol] || dist[nextRow][nextCol] != 0) continue;
                dist[nextRow][nextCol] = dist[current.row][current.col] + 1;            // another distance

                q.offer(new Point(nextRow, nextCol));
            }
        }

        return dist[end.row][end.col] - 1;
    }

    private static boolean outRange(int row, int col){
        return row < 0 || row >= N || col < 0 || col >= M;
    }

    private static boolean isAnother(Point s, Point e, Point p){
        return (s.row == p.row && s.col == p.col) || (e.row == p.row && e.col == p.col);
    }
}
