package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2567번: 색종이 - 2
 *
 *	@see https://www.acmicpc.net/problem/2567/
 *
 */
public class Boj2567 {
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0, COL = 1;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		boolean[][] paper = new boolean[100][100];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int col = Integer.parseInt(st.nextToken()) - 1;
			int row = Integer.parseInt(st.nextToken()) - 1;
			
			for(int x = row; x < row + 10; x++) {			// 색종이 덮기
				for(int y = col; y < col + 10; y++) {
					paper[x][y] = true;
				}
			}
		}
		
		System.out.println(getLength(paper));
	}
	
	private static int getLength(boolean[][] p) {
		int total = 0;
		
		for(int i = 0; i < 100; i++) {
			for(int j = 0; j < 100; j++) {
				if(!p[i][j]) continue;
				
				for(final int[] DIRECTION: DIRECTIONS) {		// 현 위치가 색종이이고, 주변이 빈 경우: 둘레에 포함됨
					int nextRow = i + DIRECTION[ROW];
					int nextCol = j + DIRECTION[COL];
					
					if(nextRow < 0 || nextRow > 99 || nextCol < 0 || nextCol > 99 || !p[nextRow][nextCol]) total++;
				}
			}
		}
		
		return total;
	}
}
