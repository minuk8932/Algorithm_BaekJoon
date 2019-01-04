package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1981번: 배열에서 이동
 *
 *	@see https://www.acmicpc.net/problem/1981/
 *
 */
public class Boj1981 {
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	private static final int INF = 100_000;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[][] map = new int[n][n];
		int max = 0, min = INF;
		
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] > max) max = map[i][j];
				if(map[i][j] < min) min = map[i][j];
			}
		}
		
		System.out.println(binarySearch(n, map, max, min));		// 결과 출력
	}
	
	private static class Point {
		int row;
		int col;
		
		public Point(int row, int col) {
			this.row = row;
			this.col = col;		
		}
	}

	private static int binarySearch(int n, int[][] arr, int upperBound, int lowerBound) {
		int start = 0, end = upperBound - lowerBound;
		int diff = INF;
		
		while(start <= end) {
			int mid = (start + end) / 2;
			boolean search = bfs(n, arr, mid, upperBound, lowerBound);		// 너비우선 탐색이 완료되었는가?
			
			if(search) {
				end = mid - 1;
				diff = Math.min(mid, diff);
			}
			else {
				start = mid + 1;
			}
		}
		
		return diff;
	}
	
	private static boolean bfs(int N, int[][] arr, int limit, int upper, int lower) {				
		for(int bound = lower; bound < upper - limit + 1; bound++) {
			boolean[][] isVisited = new boolean[N][N];
			Queue<Point> q = new LinkedList<>();
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(arr[i][j] >= bound && arr[i][j] <= bound + limit) isVisited[i][j] = false;		// 차이의 범위가 속하는 경우
					else isVisited[i][j] = true;			// 차이의 범위가 벗어나는 경우
				}
			}
			
			if(isVisited[0][0]) continue;
			isVisited[0][0] = true;
			
			q.offer(new Point(0, 0));
			
			while(!q.isEmpty()) {
				Point current = q.poll();
				
				for(final int[] DIRECTION: DIRECTIONS) {
					int nextRow = current.row + DIRECTION[ROW];
					int nextCol = current.col + DIRECTION[COL];
					
					if(nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < N) {
						if(!isVisited[nextRow][nextCol]) {
							isVisited[nextRow][nextCol] = true;
							
							if(nextRow == N - 1 && nextCol == N - 1) return true;		// 탐색 성공!
							q.offer(new Point(nextRow, nextCol));
						}
					}
				}
			}
		}
		
		return false;
	}
}
