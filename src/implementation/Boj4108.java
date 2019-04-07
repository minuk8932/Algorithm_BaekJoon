package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 	
 * 	@author minchoba
 *	백준 4108번: 지뢰찾기
 *
 *	@see https://www.acmicpc.net/problem/4108/
 *
 */
public class Boj4108 {
	private static final char MINE = '*';
	
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, -1}, {-1, 1}, {1, 1}, {-1 ,-1}};
	private static final int ROW = 0, COL = 1;
	
	private static final String NEW_LINE = "\n";
	
	private static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			if(R == 0 && C == 0) break;
			
			int[][] map = new int[R][C];
			for(int i = 0; i < R; i++) {
				String line = br.readLine();
				
				for(int j = 0; j < C; j++) {
					map[i][j] = line.charAt(j) == MINE ? -1: 0;
				}
			}
			
			print(R, C, map);
		}
		
		System.out.println(sb);
	}
	private static void print(int r, int c, int[][] arr) {
		for(int i = 0; i < r; i++) {
			for(int j = 0; j < c; j++) {
				if(arr[i][j] != -1) continue;
				
				for(final int[] DIRECTION: DIRECTIONS) {
					int nextRow = i + DIRECTION[ROW];
					int nextCol = j + DIRECTION[COL];
					
					if(nextRow < 0 || nextRow >= r || nextCol < 0 || nextCol >= c) continue;
					if(arr[nextRow][nextCol] == -1) continue;
					arr[nextRow][nextCol]++;					// 지뢰 찾기
				}
			}
		}
		
		for(int i = 0; i < r; i++) {
			for(int j = 0; j < c; j++) {
				sb.append(arr[i][j] == -1 ? "*": arr[i][j]);
			}
			sb.append(NEW_LINE);
		}
	}
}
