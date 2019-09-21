package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
 * 	@author exponential-e
 *	백준 1730번: 판화
 *
 *	@see https://www.acmicpc.net/problem/1730/
 *
 */
public class Boj1730 {
	private static final String NEW_LINE = "\n";
	
	private static final char EMPTY = '.';
	private static final char UP_DOWN = '|';
	private static final char LEFT_RIGHT = '-';
	private static final char MULTI = '+';
	
	private static final char UP = 'U';
	private static final char DOWN = 'D';
	private static final char LEFT = 'L';
	private static final char RIGHT = 'R';
	
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
		int n = Integer.parseInt(br.readLine());
		
		String line = br.readLine();
		char[] alpha = new char[line.length()];
		
		for(int i = 0; i < alpha.length; i++) {
			alpha[i] = line.charAt(i);
		}
		
		System.out.print(drawing(n, alpha));
	}
	
	private static String drawing(int n, char[] alpha) {
		char[][] paint = new char[n][n];
		for(int i = 0; i < n; i++) Arrays.fill(paint[i], EMPTY);
		
		Point p = new Point(0, 0);
		
		for(int i = 0; i < alpha.length; i++) {
			switch(alpha[i]) {
			case DOWN:
			case UP:
				if(alpha[i] == DOWN && p.row >= n - 1) continue;					// 범위 체크
				if(alpha[i] == UP && p.row < 1) continue;
				
				if(paint[p.row][p.col] == LEFT_RIGHT) paint[p.row][p.col] = MULTI;	// 중첩인 경우
				if(paint[p.row][p.col] == EMPTY) paint[p.row][p.col] = UP_DOWN;
				
				if(alpha[i] == UP) p.row--;
				else p.row++;
				
				if(paint[p.row][p.col] == LEFT_RIGHT) paint[p.row][p.col] = MULTI;
				if(paint[p.row][p.col] == EMPTY) paint[p.row][p.col] = UP_DOWN;
				
				break;
				
			case RIGHT:				
			case LEFT:
				if(alpha[i] == RIGHT && p.col >= n - 1) continue;
				if(alpha[i] == LEFT && p.col < 1) continue;
				
				if(paint[p.row][p.col] == UP_DOWN) paint[p.row][p.col] = MULTI;
				if(paint[p.row][p.col] == EMPTY) paint[p.row][p.col] = LEFT_RIGHT;
				
				if(alpha[i] == LEFT) p.col--;
				else p.col++;
				
				if(paint[p.row][p.col] == UP_DOWN) paint[p.row][p.col] = MULTI;
				if(paint[p.row][p.col] == EMPTY) paint[p.row][p.col] = LEFT_RIGHT;
				break;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				sb.append(paint[i][j]);
			}
			
			sb.append(NEW_LINE);
		}
		
		return sb.toString();
	}
}
