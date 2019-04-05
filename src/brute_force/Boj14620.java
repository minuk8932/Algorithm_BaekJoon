package brute_force;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 14620번: 꽃길
 *
 *	@see https://www.acmicpc.net/problem/14620/
 *
 */
public class Boj14620 {
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0, COL = 1;
	
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
		int N = Integer.parseInt(br.readLine());
		
		int[][] map = new int[N][N];
		for(int i = 0; i < N ; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(getMinCost(N, map));
	}
	
	private static int getMinCost(int n, int[][] arr) {		
		int min = Integer.MAX_VALUE;
		
		for(int row = 0; row < n; row++) {				// 1번 꽃
			for(int col = 0; col < n; col++) {
				if(outlines(n, row, col)) continue;
				
				for(int row1 = 0; row1 < n; row1++) {			// 2번 꽃
					for(int col1 = 0; col1 < n; col1++) {	
						if(outlines(n, row1, col1) || isAdjacent(row, col, row1, col1)) continue;
						
						for(int row2 = 0; row2 < n; row2++) {			// 3번 꽃						
							for(int col2 = 0; col2 < n; col2++) {		// 각 꽃은 가장자리에 나지 않으며 꽃의 중앙 정점간 맨하튼 거리차가 2보다 커야함
								if(outlines(n, row2, col2) || isAdjacent(row, col, row2, col2) || isAdjacent(row1, col1, row2, col2)) continue;		
								int cost = getCost(new Point(row, col), arr) + 
										getCost(new Point(row1, col1), arr) + getCost(new Point(row2, col2), arr);
								
								min = Math.min(cost, min);				// 그때의 꽃을 심는 최소 비용
							}
						}
					}
				}
			}
		}
		
		
		return min;
	}
	
	private static boolean outlines(int n, int row, int col) {
		return row == 0 || col == 0 || row == n - 1 || col == n - 1 ? true: false;
	}
	
	private static boolean isAdjacent(int row, int col, int x, int y) {
		return Math.abs(row - x) + Math.abs(col - y) <= 2 ? true: false;
	}
	
	private static int getCost(Point p, int[][] arr) {
		int cost = arr[p.row][p.col];
			
		for(final int[] DIRECTION: DIRECTIONS) {
			int nextRow = p.row + DIRECTION[ROW];
			int nextCol = p.col + DIRECTION[COL];
				
			cost += arr[nextRow][nextCol];
		}
		
		return cost;
	}
}
