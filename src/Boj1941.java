import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Boj1941 {	
	private static final int SIZE = 5;
	private static final int FULL_MEMBER = 7;
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	private static LinkedList<Character> princess = new LinkedList<>();
	private static boolean[][] isVisited = new boolean[SIZE][SIZE];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[][] map = new char[SIZE][SIZE];
		
		for(int i = 0; i < SIZE; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < SIZE; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		
		for(int row = 0; row < SIZE; row++) {
			for(int col = 0; col < SIZE; col++) {
				Point init = new Point(row, col);
				
				isVisited = new boolean[SIZE][SIZE];
				isVisited[init.row][init.col] = true;
				
				princess.add(map[init.row][init.col]);
				dfs(init);
			}
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
	
	private static void dfs(Point start) {
		if(princess.size() == FULL_MEMBER) {
			return;
		}
		
		for(final int[] DIRECTION: DIRECTIONS) {
			int nextRow = start.row + DIRECTION[ROW];
			int nextCol = start.col + DIRECTION[COL];
			
			if(nextRow >= 0 && nextRow < SIZE && nextCol >= 0 && nextCol < SIZE) {
				if(!isVisited[nextRow][nextCol]) {
					isVisited[nextRow][nextCol] = true;
					
					
				}
			}
		}
	}
}
