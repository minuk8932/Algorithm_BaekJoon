import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj16933 {
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0, COL = 1;
	private static final boolean DAY = true;
	
	private static class Point{
		int row;
		int col;
		boolean day;
		int broke;
		
		public Point(int row, int col, boolean day, int broke) {
			this.row = row;
			this.col = col;
			this.day = day;
			this.broke = broke;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}
		
		System.out.println(bfs(N, M, K, map));
	}
	
	private static int bfs(int n, int m, int k, int[][] map) {
		int[][] visit = new int[n][m];
		
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(0, 0, DAY, k));
		
		visit[0][0] = 1;
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			
			for(int[] DIRECTION: DIRECTIONS) {
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
				boolean nextDay = !current.day;
				
				if(nextRow < 0 || nextCol < 0 || nextRow >= n || nextCol >= m) continue;
				if(visit[nextRow][nextCol] != 0) continue;
				
				if(map[nextRow][nextCol] == 1) {
					
				}
				else {
					
				}
			}
		}
		
		return -1;
	}
}
