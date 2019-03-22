import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj16930 {
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	private static final int INF = 1_000_000_000;
	
	private static class Point{
		int row;
		int col;
		int dir;
		int move;
		
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
		
		public Point(int row, int col, int dir, int move) {
			this.row = row;
			this.col = col;
			this.dir = dir;
			this.move = move;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		boolean[][] map = new boolean[N][M];
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j) == '#' ? false : true;
			}
		}
		
		st = new StringTokenizer(br.readLine());
		Point start = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
		Point end = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
		
		System.out.println(bfs(N, M, K, map, start, end));
	}
	
	private static int bfs(int n, int m, int k, boolean[][] arr, Point start, Point end) {
		int[][] isVisited = new int[n][m];
		for(int i = 0; i < n; i++) {
			Arrays.fill(isVisited[i], INF);
		}
		
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(start.row, start.col, -1, 0));
		isVisited[start.row][start.col] = 1;
		
		while(!q.isEmpty()) {
			Point current = q.poll();
				
			for(int dir = 0; dir < 4; dir++) {
				int nextRow = current.row + DIRECTIONS[dir][ROW];
				int nextCol = current.col + DIRECTIONS[dir][COL];
				int nextMove = current.move;
					
				if(nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= m || !arr[nextRow][nextCol]) continue;				
				if(isVisited[nextRow][nextCol] > isVisited[current.row][current.col]) {
					isVisited[nextRow][nextCol] = isVisited[current.row][current.col];
					
					if(nextMove == k || dir != current.dir) {
						nextMove = 0;
						isVisited[nextRow][nextCol]++;
					}

					q.offer(new Point(nextRow, nextCol, dir, nextMove + 1));
				}
			}
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				System.out.print((isVisited[i][j] == INF ? 0 : isVisited[i][j]) + " ");
			}
			System.out.println();
		}
		
		return isVisited[end.row][end.col] == INF ? -1 : isVisited[end.row][end.col] - 1;
	}
}
