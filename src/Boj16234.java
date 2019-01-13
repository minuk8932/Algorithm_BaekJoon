import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj16234 {
	private static final int[][] DIRECTIONS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	private static Country[][] map;
	private static int count = 0;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		map = new Country[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < N; j++) {
				map[i][j] = new Country(false, false, false, false, Integer.parseInt(st.nextToken()));
			}
		}
		
		boolean isMoved = false;
		
		while(!isMoved) {
			isMoved = bfs(N, L, R);
		}
		
		System.out.println(count);
	}
	
	private static class Country{
		boolean N;
		boolean E;
		boolean S;
		boolean W;
		int pop;
		
		public Country(boolean N, boolean E, boolean S, boolean W, int pop) {
			this.N = N;
			this.E = E;
			this.S = S;
			this.W = W;
			this.pop = pop;
		}
	}
	
	private static class Point{
		int row;
		int col;
		
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	private static boolean bfs(int n, int l, int r) {
		openBorder(n, l, r);
		
		boolean[][] isVisited = new boolean[n][n];
		LinkedList<Point> change = new LinkedList<>();
		
		for(int row = 0; row < n; row++) {
			for(int col = 0; col < n; col++) {
				if(isVisited[row][col]) continue;
				isVisited[row][col] = true;
				
				Queue<Point> q = new LinkedList<>();
				q.offer(new Point(row, col));
				
				while(!q.isEmpty()) {
					
				}
			}
		}
		
		return false;
	}
	
	private static void openBorder(int n, int left, int right) {
		for(int row = 0; row < n; row++) {
			for(int col = 0; col < n; col++) {
				for(int i = 0; i < 4; i++) {
					int adjRow = row + DIRECTIONS[i][ROW];
					int adjCol = col + DIRECTIONS[i][COL];
					
					if(adjRow >= 0 && adjRow < n && adjCol >= 0 && adjCol < n) {
						int diff = Math.abs(map[row][col].pop - map[adjRow][adjCol].pop);
						
						if(diff <= right && diff >= left) {
							switch (i) {
							case 0:
								map[row][col].N = true;
								map[adjRow][adjCol].S = true;
								break;
								
							case 1:
								map[row][col].E = true;
								map[adjRow][adjCol].W = true;
								break;
								
							case 2:
								map[row][col].S = true;
								map[adjRow][adjCol].N = true;
								break;
								
							case 3:
								map[row][col].W = true;
								map[adjRow][adjCol].E = true;
								break;
							}
						}
					}
				}
			}
		}
	}
}
