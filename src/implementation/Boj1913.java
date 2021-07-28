package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 1913번: 달팽이
 *
 *	@see https://www.acmicpc.net/problem/1913/
 *
 */
public class Boj1913 {
	private static final int[][] DIRECTIONS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	private static final char SPACE = ' ';
	private static final char NEW_LINE = '\n';

	private static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int num = Integer.parseInt(br.readLine());
		
		Point result = getRes(N, num);
		sb.append(result.row + 1).append(SPACE).append(result.col + 1);

		System.out.println(sb.toString());
	}
	
	private static class Point{
		int row;
		int col;
		
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	private static Point getRes(int n, int target) {

		int[][] arr = new int[n][n];
		
		int loop = n * n;
		int num = 1;
		int move = 1;
		int repeat = 0;

		Point start = new Point(n / 2, n / 2);
		Point res = start;

		arr[start.row][start.col] = num++;
		
OUTER:	while(true) {
			for(final int[] DIRECTION: DIRECTIONS) {
				for(int j = 0; j < move; j++) {
					int nextRow = start.row + DIRECTION[ROW];
					int nextCol = start.col + DIRECTION[COL];
					
					if(num > loop) break OUTER;
					arr[nextRow][nextCol] = num++;
					
					if(arr[nextRow][nextCol] == target) res = new Point(nextRow, nextCol);
					
					start = new Point(nextRow, nextCol);
				}
				
				repeat++;
				
				if(repeat == 2) {
					move++;
					repeat = 0;
				}
			}
		}
		
		sb.append(getSnail(n, arr));
		return res;
	}
	
	private static StringBuilder getSnail(int n, int[][] arr) {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				sb.append(arr[i][j]).append(SPACE);
			}
			sb.append(NEW_LINE);
		}
		
		return sb;
	}
}
