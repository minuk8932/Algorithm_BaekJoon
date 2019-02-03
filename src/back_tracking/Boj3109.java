package back_tracking;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 3109번: 빵집
 *
 *	@see https://www.acmicpc.net/problem/3109/
 *
 */
public class Boj3109 {
	private static final int[][] DIRECTIONS = {{-1 ,1}, {0, 1}, {1, 1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	private static boolean[][] isVisited;
	
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
			}
		}
			
		int count = 0;
		isVisited = new boolean[R][C];
		
		for(int i = 0; i < R; i++) {
			if(isVisited[i][0]) continue;
			
			count += backTracking(R, C, map, i, 0);		// 도달 한 경우
		}
		
		System.out.println(count);
	}
	
	private static int backTracking(int r, int c, boolean[][] arr, int row, int col) {
		isVisited[row][col] = true;
		
		if(col == c - 1) return 1;		// 목적지 도착
		
		for(final int[] DIRECTION: DIRECTIONS) {
			int nextRow = row + DIRECTION[ROW];
			int nextCol = col + DIRECTION[COL];
			
			if(nextRow < 0 || nextRow >= r || nextCol < 0 || nextCol >= c) continue;
			if(!arr[nextRow][nextCol] || isVisited[nextRow][nextCol]) continue;
				
			if(backTracking(r, c, arr, nextRow, nextCol) > 0) return 1;		// 목적지는 1개 도착했다면 1반환
		}
		
		return 0;
	}
}
