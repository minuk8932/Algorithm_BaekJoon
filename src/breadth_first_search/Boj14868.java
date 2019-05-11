package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 14868번: 문명
 *
 *	@see https://www.acmicpc.net/problem/14868/
 *
 */
public class Boj14868 {
	private static int[][] spread;
	
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1 ,0}, {0, -1}};
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
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Queue<Point> civil = new LinkedList<>();
		spread = new int[N][N];
		
		while(K-- > 0) {
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken()) - 1;
			int col = Integer.parseInt(st.nextToken()) - 1;
			
			civil.offer(new Point(row, col));
			spread[row][col] = 1;
		}
		
		int max = init(N, civil);
		System.out.println(binarySearch(N, max + 1));
	}
	
	private static int init(int n, Queue<Point> q) {
		int max = 0;
		
		while(!q.isEmpty()) {
			Point current = q.poll();
				
			for(final int[] DIRECTION: DIRECTIONS) {
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
							
				if(nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n) continue;
				if(spread[nextRow][nextCol] != 0) continue;
				spread[nextRow][nextCol] = spread[current.row][current.col] + 1;		// 문명이 퍼져나가는 햇수를 미리 배열에 저장
					
				if(spread[nextRow][nextCol] > max) max = spread[nextRow][nextCol];		// 최대 해를 저장해서 반환
					
				q.offer(new Point(nextRow, nextCol));
			}
		}
		
		return max;
	}
	
	private static int binarySearch(int n, int end) {
		int start = 1, year = Integer.MAX_VALUE;
		
		while(start <= end) {
			int mid = (start + end) / 2;
			
			boolean united = bfs(n, mid);			// 이분탐색을 통해 기준 mid해에 모든 문명이 합쳐지면 그 해의 최소를 저장
			
			if(united) {
				end = mid - 1;
				year = Math.min(mid, year);
			}
			else {
				start = mid + 1;
			}
		}
		
		return year - 1;
	}
	
	private static boolean bfs(int n, int year) {
		boolean[][] visit = new boolean[n][n];
		boolean flag = false;

		for(int row = 0; row < n; row++) {
			for(int col = 0; col < n; col++) {
				if(visit[row][col] || spread[row][col] > year || spread[row][col] == 0) continue;
				visit[row][col] = true;
				
				Queue<Point> q = new LinkedList<>();
				q.offer(new Point(row, col));
				
				if(flag) return false;			// 문명이 두개 이상인 경우
				flag = true;
				
				while(!q.isEmpty()) {
					Point current = q.poll();
					
					for(final int[] DIRECTION: DIRECTIONS) {
						int nextRow = current.row + DIRECTION[ROW];
						int nextCol = current.col + DIRECTION[COL];
								
						if(nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n) continue;
						if(visit[nextRow][nextCol] || spread[nextRow][nextCol] > year || spread[nextRow][nextCol] == 0) continue;
						visit[nextRow][nextCol] = true;
						
						q.offer(new Point(nextRow, nextCol));
					}
				}
			}
		}
		
		return flag;
	}
	
}
