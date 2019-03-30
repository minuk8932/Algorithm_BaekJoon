package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 16948번: 데스 나이트
 *
 *	@see https://www.acmicpc.net/problem/16948/
 *
 */
public class Boj16948 {
	private static final int[][] DIRECTIONS = {{-2, -1}, {-2, 1}, {0, -2}, {0, 2}, {2, -1}, {2, 1}};
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
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		Point start = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		Point end = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		
		System.out.println(bfs(N, start, end));
	}
	
	private static int bfs(int n, Point s, Point e) {
		int[][] isVisited = new int[n][n];
		int min = Integer.MAX_VALUE;
		
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(s.row, s.col));
		
		isVisited[s.row][s.col] = 1;
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			
			for(final int[] DIRECTION: DIRECTIONS) {
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
				
				if(nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n) continue;
				if(isVisited[nextRow][nextCol] != 0) continue;
				isVisited[nextRow][nextCol] = isVisited[current.row][current.col] + 1;
				
				if(nextRow == e.row && nextCol == e.col) min = Math.min(min, isVisited[nextRow][nextCol]);
				
				q.offer(new Point(nextRow, nextCol));
			}
		}
		
		return min == Integer.MAX_VALUE ? -1: min - 1;		// 죽음의 나이트가 도달 못한다면 -1
	}
}
