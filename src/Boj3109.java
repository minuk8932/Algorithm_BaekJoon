import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj3109 {
	private static final int[][] DIRECTIONS = {{-1, 1}, {0, 1}, {1, 1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	private static boolean[][] isVisited;
	private static int count;
	private static boolean arrived = false;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		boolean[][] map = new boolean[R][C];
		
		for(int i = 0; i < R; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < C; j++) {
				char spot = line.charAt(j);
				
				if(spot == '.') map[i][j] = true;
				else map[i][j] = false;
			}
		}
			
		isVisited = new boolean[R][C];
		int res = 0;
		
		for(int i = 0; i < C; i++) {
			arrived = false;
			count = 0;
			backTracking(R, C, map, 0, i);
			
			res = Math.max(count, res);
		}
		
		System.out.println(res);
	}
	
	private static void backTracking(int r, int c, boolean[][] arr, int row, int col) {
		if(isVisited[row][col] || !arr[row][col]) return;
		if(col == c - 1) {
			count++;
			arrived = true;
			return;
		}
		
		if(arrived) return;
		
		for(final int[] DIRECTION: DIRECTIONS) {
			int nextRow = row + DIRECTION[ROW];
			int nextCol = col + DIRECTION[COL];
			
			if(nextRow < 0 || nextRow >= r || nextCol < 0 || nextCol >= c) continue;
			System.out.println(row + " " + col + " " + nextRow + " " + nextCol);
				
			backTracking(r, c, arr, nextRow, nextCol);
			isVisited[nextRow][nextCol] = false;
		}
	}
}
