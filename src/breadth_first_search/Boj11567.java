package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 11567번: 선진이의 겨울 왕국
 *
 *	@see https://www.acmicpc.net/problem/11567/
 *
 */
public class Boj11567 {
	private static final char VISITED = 'X';
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
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < M; j++) {
				if(line.charAt(j) == VISITED) map[i][j] = 1;
			}
		}
		
		st = new StringTokenizer(br.readLine());
		Point start = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
		st = new StringTokenizer(br.readLine());
		Point end = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
		
		System.out.println(search(N, M, start, end, map));
	}
	
	private static String search(int n, int m, Point s, Point e, int[][] arr) {
		Queue<Point> q = new LinkedList<>();
		q.offer(s);
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			
			for(final int[] DIRECTION: DIRECTIONS) {
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
				
				if(nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= m) continue;
				if(arr[nextRow][nextCol] == 1) {
					if(nextRow != e.row || nextCol != e.col) continue;			// if revisited not end position
					else return "YES";											//escape
				}
				
				arr[nextRow][nextCol]++;
				q.offer(new Point(nextRow, nextCol));
			}
		}
		
		return "NO";
	}
}
