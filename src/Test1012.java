import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Test1012 {
	private static final String NEW_LINE = "\n";
	private static boolean[][] visit;
	
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0, COL = 1;
	
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
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			boolean[][] placed = new boolean[N][M];
			
			while(K-- > 0) {
				st = new StringTokenizer(br.readLine());
				int col = Integer.parseInt(st.nextToken());
				int row = Integer.parseInt(st.nextToken());
				
				placed[row][col] = true;
			}
			
			visit = new boolean[N][M];
//			sb.append(bfs(N, M, placed)).append(NEW_LINE);
			
			int result = 0;
			for(int row = 0; row < N; row++) {
				for(int col = 0; col < M; col++) {
					if(visit[row][col] || !placed[row][col]) continue;
					result++;
					
					dfs(N, M, placed, row, col);
				}
			}
			
			sb.append(result).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
	}
	
	private static void dfs(int n, int m, boolean[][] placed, int currentRow, int currentCol) {
		if(visit[currentRow][currentCol] || !placed[currentRow][currentCol]) return;
		visit[currentRow][currentCol] = true;
		
		for(final int[] DIRECTION: DIRECTIONS) {
			int nextRow = currentRow + DIRECTION[ROW];
			int nextCol = currentCol + DIRECTION[COL];
			
			if(nextRow < 0 || nextCol < 0 || nextRow >= n || nextCol >= m) continue;
			if(!placed[nextRow][nextCol] || visit[nextRow][nextCol]) continue;
			
			dfs(n, m, placed, nextRow, nextCol);
		}
	}
	
	private static int bfs(int n, int m, boolean[][] placed) {
		int count = 0;
		
		for(int row = 0; row < n; row++) {
			for(int col = 0; col < m; col++) {
				if(!placed[row][col] || visit[row][col]) continue;
				visit[row][col] = true;
				
				Queue<Point> q = new LinkedList<>();
				q.offer(new Point(row, col));
				count++;
				
				while(!q.isEmpty()) {
					Point current = q.poll();
					
					for(final int[] DIRECTION: DIRECTIONS) {
						int nextRow = current.row + DIRECTION[ROW];
						int nextCol = current.col + DIRECTION[COL];
						
						if(nextRow < 0 || nextCol < 0 || nextRow >= n || nextCol >= m) continue;
						if(!placed[nextRow][nextCol] || visit[nextRow][nextCol]) continue;
						visit[nextRow][nextCol] = true;
						
						q.offer(new Point(nextRow, nextCol));
					}
				}
			}
		}
		
		return count;
	}
}
