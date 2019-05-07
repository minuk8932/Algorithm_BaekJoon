import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj15357 {
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0, COL = 1;
	private static final int INF = 10_000_000;
	
	private static final String NO_WAY = "nemoguce";
	private static final char BLOCK = '#';
	private static final char START = 'C';
	private static final char ARRIVE = 'F';
	
	private static Point start = new Point(-1, -1);
	private static Point end = new Point(-1, -1);
	
	private static class Point{
		int row;
		int col;
		
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[N][M];
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);
				if(map[i][j] == START) start = new Point(i, j);
				if(map[i][j] == ARRIVE) end = new Point(i, j);
			}
		}
		
		System.out.println(bfs(N, M, map));
	}
	
	private static int bfs(int n, int m, char[][] arr) {
		int[][] visit = new int[n][m];
		for(int i = 0; i < n; i++) {
			Arrays.fill(visit[i], INF);
		}
		
		Queue<Point> q = new LinkedList<>();
		q.offer(start);
		
		visit[start.row][start.col] = 1;
		while(!q.isEmpty()) {
			Point current = q.poll();
			
			for(final int[] DIRECTION: DIRECTIONS) {
				int nextRow = current.row;
				int nextCol = current.col;
				
				if(breaking(n, m, arr, nextRow + DIRECTION[ROW], nextCol + DIRECTION[COL])) continue;
				
				
				
				Point next = new Point(-1, -1);
				while(!breaking(n, m, arr, nextRow + DIRECTION[ROW], nextCol + DIRECTION[COL])) {
					nextRow += DIRECTION[ROW];
					nextCol += DIRECTION[COL];
						
					if(visit[nextRow][nextCol] > visit[current.row][current.col] + 1) {
						visit[nextRow][nextCol] = visit[current.row][current.col] + 1;
						next = new Point(nextRow, nextCol);
					}
				}
				
				if(next.row == -1) continue;
				q.offer(next);
			}
		}
		
		return 0;
	}
	
	private static boolean breaking(int n, int m, char[][] arr, int row, int col) {
		return row < 0 || col < 0 || row >= n || col >= m || arr[row][col] == BLOCK ? true : false;
	}
}
