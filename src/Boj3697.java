import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj3697 {
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1 ,0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	private static final char NEW_LINE = '\n';
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			int[][] map = new int[n][m];
			int[][] diff = new int[n][m];
			int[][] parent = new int[n][m];
			
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < m; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					diff[i][j] = map[i][j] - d;
					parent[i][j] = (m * i) + j;
				}
			}
			
			sb.append(dMinusSummit(n, m, parent, diff, map)).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static class Point{
		int row;
		int col;
		
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	private static int dMinusSummit(int N, int M, int[][] p, int[][] d, int[][] h) {
		boolean[][] isVisited = new boolean[N][M];
		
		for(int row = 0; row < N; row++) {
			for(int col = 0; col < M; col++) {
				if(isVisited[row][col]) continue;
				isVisited[row][col] = true;
				
				Queue<Point> q = new LinkedList<>();
				q.offer(new Point(row, col));
				
				while(!q.isEmpty()) {
					Point current = q.poll();
					
					for(final int[] DIRECTION: DIRECTIONS) {
						int nextRow = current.row + DIRECTION[ROW];
						int nextCol = current.col + DIRECTION[COL];
						
						if(nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < M) {
							if(isVisited[nextRow][nextCol]) continue;
							if(d[current.row][current.col] >= h[nextRow][nextCol]) continue;
							isVisited[nextRow][nextCol] = true;
							
							p[current.row][current.col] = getMaxIdx(p, h, current.row, current.col, nextRow, nextCol);
							q.offer(new Point(nextRow, nextCol));
						}
					}
				}
			}
		}
		
		return getSection(N, M, p);
	}
	
	private static int getMaxIdx(int[][] pIdx, int[][] arr, int row, int col, int nRow, int nCol) {
		return arr[row][col] >= arr[nRow][nCol] ? pIdx[row][col]: pIdx[nRow][nCol];
	}
	
	private static int getSection(int N, int M, int[][] pIdx) {
		int counts = 0;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(find(pIdx[i][j], (M * i) + j)) {
					counts++;
				}
			}
		}
		
		return counts;
	}
	
	private static boolean find(int a, int b) {
		if(a == b) return true;
		else return false;
	}
}
