package breadth_first_search;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.BufferedReader;

/**
 * 
 * 	@author minchoba
 *	백준 16940번: 두 동전
 *
 *	@see https://www.acmicpc.net/problem/16940/
 *
 */
public class Boj16197 {
	private static final char BLOCK = '#';
	private static final char COIN = 'o';
	
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[N][M];
		Point[] coin = new Point[2];
		int idx = 0;
		
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);
				
				if(map[i][j] == COIN) {
					coin[idx++] = new Point(i, j);
				}
			}
		}
		
		System.out.println(bfs(N, M, map, coin));
	}
	
	private static class Point{
		int row;
		int col;
		
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	private static class CoinPoint{
		Point p1;
		Point p2;
		
		public CoinPoint(Point p1, Point p2) {
			this.p1 = p1;
			this.p2 = p2;
		}
	}
	
	private static int bfs(int n, int m, char[][] arr, Point[] p) {
		boolean[][][][] isVisited = new boolean[25][25][25][25];
		int move = 1;
		
		Queue<CoinPoint> q = new LinkedList<>();
		
		q.offer(new CoinPoint(new Point(p[0].row, p[0].col), new Point(p[1].row, p[1].col)));
		isVisited[p[0].row][p[0].col][p[1].row][p[1].col] = true;
		
		while(!q.isEmpty()) {
			int size = q.size();
			
			while(size-- > 0) {
				CoinPoint cp = q.poll();
				
				Point current1 = cp.p1;
				Point current2 = cp.p2;
				
				for(final int[] DIRECTION: DIRECTIONS) {
					int nextRow1 = current1.row;
					int nextCol1 = current1.col;
					int nextRow2 = current2.row;
					int nextCol2 = current2.col;
					
					if(isOut(nextRow1 + DIRECTION[ROW], nextCol1 + DIRECTION[COL], n, m)			// 둘다 나가는 경우
							&& isOut(nextRow2 + DIRECTION[ROW], nextCol2 + DIRECTION[COL], n, m)) continue;
					if(!isOut(nextRow1 + DIRECTION[ROW], nextCol1 + DIRECTION[COL], n, m) 			// 둘중 하나만 나가는 경우
							&& isOut(nextRow2 + DIRECTION[ROW], nextCol2 + DIRECTION[COL], n, m)) return move;
					if(isOut(nextRow1 + DIRECTION[ROW], nextCol1 + DIRECTION[COL], n, m) 			// 둘중 하나만 나가는 경우
							&& !isOut(nextRow2 + DIRECTION[ROW], nextCol2 + DIRECTION[COL], n, m)) return move;
					
					if(arr[nextRow1 + DIRECTION[ROW]][nextCol1 + DIRECTION[COL]] == BLOCK			// 둘다 막힌 경우
							&& arr[nextRow2 + DIRECTION[ROW]][nextCol2 + DIRECTION[COL]] == BLOCK) continue;
					
					if(arr[nextRow1 + DIRECTION[ROW]][nextCol1 + DIRECTION[COL]] == BLOCK) { 		// 하나만 막힌 경우
						nextRow2 = current2.row + DIRECTION[ROW];
						nextCol2 = current2.col + DIRECTION[COL];
					}
					
					else if(arr[nextRow2 + DIRECTION[ROW]][nextCol2 + DIRECTION[COL]] == BLOCK) {	// 하나만 막힌 경우
						nextRow1 = current1.row + DIRECTION[ROW];
						nextCol1 = current1.col + DIRECTION[COL];
					}
					else if(arr[nextRow1 + DIRECTION[ROW]][nextCol1 + DIRECTION[COL]] != BLOCK			// 둘다 안막힌 경우
							&& arr[nextRow2 + DIRECTION[ROW]][nextCol2 + DIRECTION[COL]] != BLOCK) {
						nextRow1 = current1.row + DIRECTION[ROW];
						nextCol1 = current1.col + DIRECTION[COL];
						nextRow2 = current2.row + DIRECTION[ROW];
						nextCol2 = current2.col + DIRECTION[COL];
					}
					
					if(!isVisited[nextRow1][nextCol1][nextRow2][nextCol2]) {
						isVisited[nextRow1][nextCol1][nextRow2][nextCol2] = true;
							
						q.offer(new CoinPoint(new Point(nextRow1, nextCol1), new Point(nextRow2, nextCol2)));
					}
				}
			}
			
			move++;						// 움직임 + 1
			if(move > 10) return -1;
		}

		return -1;
	}
	
	private static boolean isOut(int row, int col, int n, int m) {
		return (row < 0 || row >= n || col < 0 || col >= m) ? true : false;
	}
}
