import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj12793 {
	private static final char EMPTY = 'O';
	private static final char BLOCK = 'B';
	private static final char V_BLOCK = '|';
	private static final char H_BLOCK = '-';
	private static final char LINKED = '.';
	private static final char PLUS = '+';
	
	private static final int[][] DIAGONALS = {{-1, -1}, {-1, 1}, {1, 1}, {1, -1}};
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0, COL = 1;
	
	private static ArrayList<Point> path = new ArrayList<>();
	private static boolean[][] broke;
	private static Point start;
	
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
		int M = Integer.parseInt(st.nextToken()) * 2 + 1;
		int N = Integer.parseInt(st.nextToken()) * 2 + 1;
		int K = (int) (Double.parseDouble(st.nextToken()) * 2);
		
		char[][] map = new char[N][M];
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		
		System.out.println(getScore(N, M, K, map));
	}
	
	private static int getScore(int n, int m, int k, char[][] map) {
		start = new Point(n - 1, k);
		for(int i = 0; i < 4; i++) {
			makePath(n, m, k, map, i);
		}
		
		broke = new boolean[n][m];
		int count = 0;
		
		for(Point p: path) {
			if((map[p.row][p.col] != BLOCK && map[p.row][p.col] != LINKED)|| broke[p.row][p.col]) continue;
			System.out.println(p.row + " " + p.col);
			
			bfs(n, m, map, p);
			count++;
		}
		
		return count;
	}
	
	private static void bfs(int n, int m, char[][] map, Point s) {
		Queue<Point> q = new LinkedList<>();
		q.offer(s);
		
		broke[s.row][s.col] = true;
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			
			for(final int[] DIRECTION: DIRECTIONS) {
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
				
				if(!inRange(n, m, nextRow, nextCol)) continue;
				if(map[nextRow][nextCol] == V_BLOCK || map[nextRow][nextCol] == H_BLOCK || map[nextRow][nextCol] == EMPTY) continue;
				if(broke[nextRow][nextCol]) continue;
				broke[nextRow][nextCol] = true;
				
				q.offer(new Point(nextRow, nextCol));
			}
		}
	}
	
	private static void makePath(int n, int m, int k, char[][] map, int index) {
		boolean[][] visit = new boolean[n][m];
		
		path.add(start);
		
		Queue<Point> q = new LinkedList<>();
		q.offer(start);
		
		visit[start.row][start.col] = true;
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			
			int nextRow = current.row + DIAGONALS[index][ROW];
			int nextCol = current.col + DIAGONALS[index][COL];
				
			while(inRange(n, m, nextRow,nextCol)) {
				if(visit[nextRow][nextCol]) break;
				visit[nextRow][nextCol] = true;
					
				path.add(new Point(nextRow, nextCol));
				if(map[nextRow][nextCol] != PLUS) {
					path.add(new Point(current.row, nextCol));
					path.add(new Point(nextRow, current.col));
				}
					
				start = new Point(nextRow, nextCol);
					
				nextRow += DIAGONALS[index][ROW];
				nextCol += DIAGONALS[index][COL];
			}
				
			if(!inRange(n, m, nextRow, nextCol)) break;
			q.offer(new Point(nextRow, nextCol));
		}
	}
	
	private static boolean inRange(int n, int m, int row, int col) {
		return !(row < 0 || col < 0 || row >= n || col >= m);
	}
}
