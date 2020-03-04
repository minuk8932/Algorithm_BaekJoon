import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj2186 {
    private static int N, M, K;

    private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static final int ROW = 0, COL = 1;

    private static class Point{
        int row;
        int col;
        int jum;
        int idx;

        public Point(int row, int col, int jum, int idx){
            this.row = row;
            this.col = col;
            this.jum = jum;
            this.idx = idx;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        char[][] map = new char[N][M];
        for(int i = 0; i < N; i++){
            String line = br.readLine();

            for(int j = 0; j < M; j++){
                map[i][j] = line.charAt(j);
            }
        }

        char[] key = br.readLine().toCharArray();


        System.out.println(bfs(map, key));
    }

    private static int bfs(char[][] arr, char[] key){
        Queue<Point> q = new LinkedList<>();
        int[][][][] visit = new int[N][M][K][key.length];

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(arr[i][j] != key[0]) continue;
                q.offer(new Point(i, j, 0, 0));
                visit[i][j][0][0] = 1;
            }
        }

        while(!q.isEmpty()){
            Point current = q.poll();

            for(final int[] DIRECTION: DIRECTIONS){
                for(int k = 1; k <= K; k++){
                    int nextRow = current.row + DIRECTION[ROW] * k;
                    int nextCol = current.col + DIRECTION[COL] * k;
                    int nextJum = k - 1;
                    int nextIdx = current.idx + 1;

                    if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M || current.idx == key.length - 1) continue;
                    if(key[nextIdx] != arr[nextRow][nextCol]) continue;

                    if(visit[nextRow][nextCol][nextJum][nextIdx] == 0)
                        visit[nextRow][nextCol][nextJum][nextIdx] = visit[current.row][current.col][current.jum][current.idx];
                    else
                        visit[nextRow][nextCol][nextJum][nextIdx] += visit[current.row][current.col][current.jum][current.idx];

                    q.offer(new Point(nextRow, nextCol, nextJum, nextIdx));
                }
            }
        }

        int sum = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                for(int k = 0; k < K; k++) {
                    if (arr[i][j] != key[key.length - 1]) continue;
                    sum += visit[i][j][k][key.length - 1];
                }
            }
        }

        return sum;
    }
}
