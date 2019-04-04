import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj16000 {
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
	private static final int ROW = 0, COL = 1;
	
	private static final char ISLAND = '#';
	private static final char SAFETY = 'O';
	private static final char DANGER = 'X';
	private static final char EMPTY = '.';
	private static final String NEW_LINE = "\n";
	
	private static int[][] flood;
	private static ArrayList<Point> saved = new ArrayList<>();
	
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
		flood = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		
		floodFill(N, M, map);
		checkIsland(N, M, map);
		System.out.println(getResult(N, M, map));
	}
	
	private static void floodFill(int n, int m, char[][] arr) {
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(0, 0));
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			
			for(final int[] DIRECTION: DIRECTIONS) {
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
				
				if(nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= m) continue;
				if(arr[nextRow][nextCol] != EMPTY || flood[nextRow][nextCol] != 0) continue;
				flood[nextRow][nextCol] = 1;
				
				q.offer(new Point(nextRow, nextCol));
			}
		}
	}
	
	private static void checkIsland(int n, int m, char[][] arr) {
		boolean[][] visit = new boolean[n][m];
		
		for(int row = 0; row < n; row++) {
			for(int col = 0; col < m; col++) {
				if(arr[row][col] != ISLAND || visit[row][col]) continue;
				visit[row][col] = true;
				
				Queue<Point> q = new LinkedList<>();
				q.offer(new Point(row, col));
				
				while(!q.isEmpty()) {
					Point current = q.poll();
					
					for(int dir = 0; dir < 4; dir++) {
						int nextRow = current.row + DIRECTIONS[dir][ROW];
						int nextCol = current.col + DIRECTIONS[dir][COL];
						
						if(arr[current.row][current.col] == ISLAND && flood[nextRow][nextCol] == 1) {
							saved.add(new Point(current.row, current.col));
						}
						
						if(visit[nextRow][nextCol] || arr[nextRow][nextCol] != ISLAND) continue;
						visit[nextRow][nextCol] = true;
						
						q.offer(new Point(nextRow, nextCol));
					}
				}
			}
		}
	}
	
	private static StringBuilder getResult(int n, int m, char[][] arr) {
		StringBuilder sb = new StringBuilder();
		boolean[][] visit = new boolean[n][m];
		
		for(Point start: saved) {
			if(visit[start.row][start.col] || arr[start.row][start.col] != ISLAND) continue;
			visit[start.row][start.col] = true;
			
			arr[start.row][start.col] = SAFETY;
			
			Queue<Point> q = new LinkedList<>();
			q.offer(start);
			
			while(!q.isEmpty()) {
				Point current = q.poll();
				
				for(int dir = 0; dir < 4; dir++) {
					int nextRow = current.row + DIRECTIONS[dir][ROW];
					int nextCol = current.col + DIRECTIONS[dir][COL];
					
					if(visit[nextRow][nextCol] || arr[nextRow][nextCol] != ISLAND) continue;
					visit[nextRow][nextCol] = true;
					arr[nextRow][nextCol] = SAFETY;
					
					q.offer(new Point(nextRow, nextCol));
				}
			}
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(arr[i][j] == ISLAND) sb.append(DANGER);
				else sb.append(arr[i][j]);
			}
			sb.append(NEW_LINE);
		}

		return sb;
	}
}
