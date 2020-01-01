package search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 18242번: 네모네모 시력검사
 *
 *	@see https://www.acmicpc.net/problem/18242/
 *
 */
public class Boj18242 {
	private static final char COLORED = '#';	
	private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	private static final int ROW = 0, COL = 1;
	private static final String[] TEST = {"UP", "LEFT", "DOWN", "RIGHT"};
	
	private static int N, M;
	
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
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[N][M];
		Point min = new Point(101, 101);
		Point max = new Point(-1, -1);
		
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < M ; j++) {
				map[i][j] = line.charAt(j);
				
				if(map[i][j] == COLORED) {
					min = new Point(Math.min(i, min.row), Math.min(j, min.col));			// left up
					max = new Point(Math.max(i, max.row), Math.max(j, max.col));			// right down
				}
			}
		}
		
		System.out.println(finder(map, min, max));
	}
	
	private static String finder(char[][] arr, Point pm, Point pM) {
		int pos = -1, min = 100;
		
		for(int idx = 0; idx < 4; idx++) {
			int count = 0;
			
			if(idx < 2) count = checker(pm, arr, idx);			// find empty space
			else count = checker(pM, arr, idx);
			
			if(count < min) {
				min = count;
				pos = idx;
			}
		}
		
		return TEST[pos];
	}
	
	private static int checker(Point src, char[][] arr, int i) {
		int count = 0;
		int row = src.row;
		int col = src.col;
		
		char pos = arr[row][col];
		int drow = DIRECTIONS[i][ROW];
		int dcol = DIRECTIONS[i][COL];
		
		
		while(true) {			
			row += drow;
			col += dcol;
			if(row < 0 || row >= N || col < 0 || col >= M) break;
			
			pos = arr[row][col];
			if(pos != COLORED) break;
			
			count++;
		}
		
		return count;
	}
}
