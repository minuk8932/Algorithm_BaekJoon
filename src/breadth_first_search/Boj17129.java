package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 17129번: 윌리암슨수액빨이딱따구리가 정보섬에 올라온 이유
 *
 *	@see https://www.acmicpc.net/problem/17129/
 *
 */
public class Boj17129 {
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0, COL = 1;
	
	private static final int BLOCK = 1;
	private static final int WOOD_PECKER = 2;
	private static final int BEAN_SOUP = 3;
	private static final int SUSHI = 4;
	private static final int MAC_CHEESE = 5;
	
	private static final String YES = "TAK";
	private static final String NO = "NIE";
	private static final String NEW_LINE = "\n";
	
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
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[][] ndFloor = new int[n][m];
		Point p = new Point(-1, -1);
		
		for(int i = 0; i < n; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < m; j++) {
				ndFloor[i][j] = line.charAt(j) - '0';
				if(ndFloor[i][j] == WOOD_PECKER) p = new Point(i, j);
			}
		}

		System.out.println(bfs(n, m, ndFloor, p));
	}
	
	private static StringBuilder bfs(int N, int M, int[][] map, Point start) {
		StringBuilder sb = new StringBuilder();
		int[][] visit = new int[N][M];
		int min = Integer.MAX_VALUE;
		
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(start.row, start.col));
		
		visit[start.row][start.col] = 1;
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			
			for(final int[] DIRECTION: DIRECTIONS) {
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
				
				if(nextRow < 0 || nextCol < 0 || nextRow >= N || nextCol >= M) continue;
				if(map[nextRow][nextCol] == BLOCK || visit[nextRow][nextCol] != 0) continue;
				visit[nextRow][nextCol] = visit[current.row][current.col] + 1;
				
				if(map[nextRow][nextCol] == BEAN_SOUP || map[nextRow][nextCol] == SUSHI || map[nextRow][nextCol] == MAC_CHEESE) {
					min = Math.min(min, visit[nextRow][nextCol]);		// 최소 음식 거리
				}
				
				q.offer(new Point(nextRow, nextCol));
			}
		}
		
		return min == Integer.MAX_VALUE ? sb.append(NO): sb.append(YES).append(NEW_LINE).append(min - 1);
	}
}
