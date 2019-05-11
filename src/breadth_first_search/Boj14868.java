package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
	
	private static ArrayList<Point> start = new ArrayList<>();
	
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
			start.add(new Point(row, col));				// 문명 발생지
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
				spread[nextRow][nextCol] = spread[current.row][current.col] + 1;
					
				if(spread[nextRow][nextCol] > max) max = spread[nextRow][nextCol];			// 최대 햇수 저장
				
				q.offer(new Point(nextRow, nextCol));			// 문명 미리 확산
			}
		}
		
		return max;
	}
	
	private static int binarySearch(int n, int end) {
		int start = 1, year = Integer.MAX_VALUE;
		
		while(start <= end) {
			int mid = (start + end) / 2;
			
			boolean united = bfs(n, mid);
			
			if(united) {
				end = mid - 1;
				year = Math.min(mid, year);			// mid해에 문명 확산으로 모든 문명이 통합된 경우 그때의 최소 해 저장
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

        for(Point s: start) {
			if(visit[s.row][s.col] || spread[s.row][s.col] > year) continue;
			visit[s.row][s.col] = true;
				
			Queue<Point> q = new LinkedList<>();
			q.offer(s);
				
			if(flag) return false;
			flag = true;
				
			while(!q.isEmpty()) {
				Point current = q.poll();
					
				for(final int[] DIRECTION: DIRECTIONS) {
					int nextRow = current.row + DIRECTION[ROW];
					int nextCol = current.col + DIRECTION[COL];
							
					if(nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n) continue;
					if(visit[nextRow][nextCol] || spread[nextRow][nextCol] > year) continue;
					visit[nextRow][nextCol] = true;
						
					q.offer(new Point(nextRow, nextCol));
				}
			}
		}
		
		return flag;
	}
	
}