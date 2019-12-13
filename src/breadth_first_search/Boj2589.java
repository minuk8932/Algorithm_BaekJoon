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
 *	백준 2589번 : 보물섬
 *
 *	@see https://www.acmicpc.net/problem/2589
 *
 */
public class Boj2589 {
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0, COL = 1;
	private static final char LAND = 'L';
	
	private static ArrayList<Point> node = new ArrayList<>();
	
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
		int M = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[N][M];
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);
				
				if(map[i][j] == LAND) node.add(new Point(i, j));				// save start point candidate
			}
		}
		
		System.out.println(bfs(N, M, map));
	}
	
	private static int bfs(int n, int m, char[][] arr) {
		int max = 0;
		
		for(Point start: node) {
			int[][] visit = new int[n][m];
			
			Queue<Point> q = new LinkedList<>();
			q.offer(start);
			
			visit[start.row][start.col] = 1;
			
			while(!q.isEmpty()) {
				Point current = q.poll();
				
				for(final int[] DIRECTION: DIRECTIONS) {
					int nextRow = current.row + DIRECTION[ROW];
					int nextCol = current.col + DIRECTION[COL];
					
					if(nextRow < 0 || nextCol < 0 || nextRow >= n || nextCol >= m) continue;
					if(visit[nextRow][nextCol] != 0 || arr[nextRow][nextCol] != LAND) continue;		// not land can't go
					visit[nextRow][nextCol] = visit[current.row][current.col] + 1;
					
					if(visit[nextRow][nextCol] > max) max = visit[nextRow][nextCol];
					
					q.offer(new Point(nextRow, nextCol));
				}
			}
		}
		
		return max - 1;
	}
}