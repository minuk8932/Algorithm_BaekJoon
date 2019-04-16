package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 6146번: 신아를 만나러
 *
 *	@see https://www.acmicpc.net/problem/6146/
 *
 */
public class Boj6146 {
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0, COL = 1;
	private static final int FORMAT = 500;
	private static final int BLOCK = -1;
	
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
		int X = Integer.parseInt(st.nextToken()) + FORMAT;
		int Y = Integer.parseInt(st.nextToken()) + FORMAT;
		int N = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[FORMAT * 2 + 1][FORMAT * 2 + 1];		// 음수 범위 커버
		Point end = new Point(Y, X);
		
		while(N-- > 0) {
			st = new StringTokenizer(br.readLine());
			int col = Integer.parseInt(st.nextToken()) + 500;
			int row = Integer.parseInt(st.nextToken()) + 500;
			
			map[row][col] = BLOCK;
		}
		
		System.out.println(bfs(map, end));
	}
	
	private static int bfs(int[][] arr, Point e) {
		int size = FORMAT * 2;
		int[][] visit = new int[size + 1][size + 2];
		
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(500, 500));
		
		visit[500][500] = 1;
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			
			if(current.row == e.row && current.col == e.col) break;
			
			for(final int[] DIRECTION: DIRECTIONS) {
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
				
				if(nextRow < 0 || nextCol < 0 || nextRow > size || nextCol > size) continue;
				if(visit[nextRow][nextCol] != 0 || arr[nextRow][nextCol] == BLOCK) continue;
				visit[nextRow][nextCol] = visit[current.row][current.col] + 1;
				
				q.offer(new Point(nextRow, nextCol));
			}
		}
		
		return visit[e.row][e.col] - 1;			// 최단 시간
	}
}
