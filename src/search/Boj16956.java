package search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author minchoba
 * 백준 16956번: 늑대와 양
 * 
 * @see https://www.acmicpc.net/problem/16956/
 * 
 */
public class Boj16956 {
	private static final String NEW_LINE = "\n";
	
	private static final char SHEEP = 'S';
	private static final char WOLF = 'W';
	private static final char FENCE = 'D';
	private static final char EMPTY = '.';
	
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0, COL = 1;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[R][C];
		for(int i = 0; i < R; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < C; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		
		System.out.println(search(R, C, map));
	}
	
	private static StringBuilder search(int r, int c, char[][] arr) {
		StringBuilder sb = new StringBuilder();
		
		for(int row = 0; row < r; row++) {
			for(int col = 0; col < c; col++) {
				if(arr[row][col] != SHEEP) continue;
				
				for(final int[] DIRECTION: DIRECTIONS) {
					int nextRow = row + DIRECTION[ROW];
					int nextCol = col + DIRECTION[COL];
					
					if(nextRow < 0 || nextCol < 0 || nextRow >= r || nextCol >= c) continue;
					if(arr[nextRow][nextCol] == WOLF) return sb.append(0);			// 늑대가 인접한 경우
				}
			}
		}
		
		sb.append(1).append(NEW_LINE);
		for(int row = 0; row < r; row++) {			// 인접한 늑대가 없으면 되는대로 울타리를 쳐줌
			for(int col = 0; col < c; col++) {
				sb.append(arr[row][col] == EMPTY ? FENCE: arr[row][col]);
			}
			
			sb.append(NEW_LINE);
		}
		
		return sb;
	}
}
