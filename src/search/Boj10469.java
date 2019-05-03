package search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * 
 * 	@author minchoba
 *	백준 10469번: 사이 나쁜 여왕들
 *
 *	@see https://www.acmicpc.net/problem/10469/
 *
 */
public class Boj10469 {
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
	private static final int ROW = 0, COL = 1;
	
	private static final char QUEEN = '*';
	
	private static ArrayList<Point> point = new ArrayList<>();
	
	private static class Point{
		int x;
		int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[][] chess = new char[8][8];
		
		for(int i = 0; i < 8; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < 8; j++) {
				chess[i][j] = line.charAt(j);
				if(chess[i][j] == QUEEN) point.add(new Point(i, j));
			}
		}
		
		System.out.println(search(chess));
	}
	
	private static String search(char[][] arr) {
		if(point.size() != 8) return "invalid";
		
		for(Point start: point) {			
			for(final int[] DIRECTION: DIRECTIONS) {
				int nextX = start.x + DIRECTION[ROW];
				int nextY = start.y + DIRECTION[COL];
				
				while(nextX >= 0 && nextX < 8 && nextY >= 0 && nextY < 8) {				
					if(arr[nextX][nextY] == QUEEN) return "invalid";			// 여왕의 길목에 다른 여왕이 있는 경우
					
					nextX += DIRECTION[ROW];
					nextY += DIRECTION[COL];
				}
			}
		}
		
		return "valid";
	}
}
