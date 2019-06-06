package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 17267번: 상남자
 *
 *	@see https://www.acmicpc.net/problem/17267/
 *
 */
public class Boj17267 {
	private static boolean[][] move;
	private static final int[] DIRECTIONS = {1, -1};
	
	private static class Point{
		int left;
		int right;
		int row;
		int col;
		
		public Point(int row, int col, int left, int right) {
			this.row = row;
			this.col = col;
			this.left = left;
			this.right = right;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int L = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		Point start = new Point(0, 0, 0, 0);
		
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j) - '0';
				
				if(map[i][j] == 2) start = new Point(i, j, L, R);
			}
		}
		
		bfs(N, M, map, start);
	}
	
	private static void bfs(int n, int m, int[][] arr, Point s) {
		move = new boolean[n][m];
		
		Queue<Point> q = new LinkedList<>();
		q.offer(s);
		
		move[s.row][s.col] = true;
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			int nextRow = current.row;
			int nextCol = current.col;
			
			for(final int DIRECTION: DIRECTIONS) {		// 상/하 움직임
				nextRow = current.row + DIRECTION;
				
				while(nextRow >= 0 && nextRow < n && !move[nextRow][nextCol] && arr[nextRow][nextCol] == 0) {
					move[nextRow][nextCol] = true;
					q.offer(new Point(nextRow, nextCol, current.left, current.right));
					
					nextRow += DIRECTION;
				}
			}
			
			if(current.left > 0) {				// 왼쪽 움직일 수 있는 경우
				nextRow = current.row;
				nextCol = current.col - 1;
				
				if(nextCol >= 0 && !move[nextRow][nextCol] && arr[nextRow][nextCol] == 0) {
					move[nextRow][nextCol] = true;
					
					q.offer(new Point(nextRow, nextCol, current.left - 1, current.right));
				}
			}
			
			if(current.right > 0) {				// 오른쪽 움직일 수 있는 경우
				nextRow = current.row;
				nextCol = current.col + 1;
				
				if(nextCol < m && !move[nextRow][nextCol] && arr[nextRow][nextCol] == 0) {
					move[nextRow][nextCol] = true;
					
					q.offer(new Point(nextRow, nextCol, current.left, current.right - 1));
				}
			}
		}
		
		System.out.println(getArea(n, m));		// 이동 가능한 경로 수
	}
	
	private static int getArea(int n, int m) {
		int area = 0;
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(move[i][j]) area++;
			}
		}
		
		return area;
	}
}
