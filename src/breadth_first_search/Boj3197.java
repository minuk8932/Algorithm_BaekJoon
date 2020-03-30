package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 3197번: 백조의 호수
 *
 *	@see https://www.acmicpc.net/problem/3197/
 *
 */
public class Boj3197 {
	private static final int[][] DIRECTIONS = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	private static final int ROW = 0;
	private static final int COL = 1;
	private static final char SWAN = 'L';
	private static final char OCEAN = '.';

	private static int R, C;
	private static int[][] melt;

	private static class Point {
		int row;
		int col;

		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		Point[] start = new Point[2];
		int idx = 0;
		
		melt = new int[R][C];
		char[][] map = new char[R][C];
		
		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for(int j = 0; j < C; j++) {
				map[i][j] = line.charAt(j);
				
				if(map[i][j] == SWAN) {
					start[idx] = new Point(i, j);
					idx++;
				}
			}
		}
		
		init(map);												// ice melting time
		System.out.println(binarySearch(start));
	}

	private static boolean outOfRange (int row, int col) {
		return row < 0 || row >= R || col < 0 || col >= C;
	}
	
	private static void init(char[][] graph){
		int counts = 1;
		boolean[][] isVisited = new boolean[R][C];
		
		Queue<Point> q = new LinkedList<>();
		
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(graph[i][j] != SWAN && graph[i][j] != OCEAN) continue;
				q.offer(new Point(i, j));
				isVisited[i][j] = true;
			}
		}
		
		while(!q.isEmpty()) {
			int size = q.size();
			
			for(int i = 0; i < size; i++) {												// melting time preset
				Point current = q.poll();
				
				for(final int[] DIRECTION: DIRECTIONS) {
					int nextRow = current.row + DIRECTION[ROW];
					int nextCol = current.col + DIRECTION[COL];
					
					if(outOfRange(nextRow, nextCol)) continue;
					if(isVisited[nextRow][nextCol] || graph[nextRow][nextCol] == SWAN) continue;
					isVisited[nextRow][nextCol] = true;

					melt[nextRow][nextCol] = counts;
					q.offer(new Point(nextRow, nextCol));
				}
			}
			
			counts++;
		}
	}

	private static int binarySearch(Point[] start){
		int left = 0, right = 0, min = Integer.MAX_VALUE;

		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(melt[i][j] > right) right = melt[i][j];
			}
		}

		while(left <= right) {
			int mid = (left + right) / 2;
			boolean attaced = canMeet(mid, start);

			if(!attaced) {												// can not meet
				left = mid + 1;
			}
			else {														// if can meet, take min time
				if(min > mid) min = mid;
				right = mid - 1;
			}
		}

		return min;
	}
	
	private static boolean canMeet(int limits, Point[] start) {
		boolean[][] isVisited = new boolean[R][C];

		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(start[0].row, start[0].col));
		
		isVisited[start[0].row][start[0].col] = true;
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			
			for(final int[] DIRECTION: DIRECTIONS) {
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
				
				if(outOfRange(nextRow, nextCol)) continue;
				if(isVisited[nextRow][nextCol] || melt[nextRow][nextCol] > limits) continue;
				isVisited[nextRow][nextCol] = true;
						
				if(nextRow == start[1].row && nextCol == start[1].col) return true;
				q.offer(new Point(nextRow, nextCol));
			}
		}
		
		return false;
	}
}
