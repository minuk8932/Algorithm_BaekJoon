package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 3697번: 정상
 *
 * @see https://www.acmicpc.net/problem/3697
 *
 */
public class Boj3697 {
	private static final String NEW_LINE = "\n";
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	private static int[][] group;
	private static PriorityQueue<Point> height;
	private static int total;
	private static int n, m;
	
	private static class Point implements Comparable<Point>{
		int row;
		int col;
		int d;
		
		public Point(int row, int col, int d) {
			this.row = row;
			this.col = col;
			this.d = d;
		}

		@Override
		public int compareTo(Point p) {
			return p.d - this.d;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			int[][] map = new int[n][m];
			height = new PriorityQueue<>();
			group = new int[n][m];
			
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				
				for(int j = 0; j < m; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					group[i][j] = -1;
					height.offer(new Point(i, j, map[i][j]));
				}
			}

			search(map, d);
			sb.append(total).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
	}

	/**
	 *
	 * BFS, Find summits
	 *
	 * line 97 ~ 114: Group by index having reachable from start (difference d with D is larger than next destination)
	 *
	 * @param map
	 * @param D
	 */
	private static void search(int[][] map, int D) {
		total = 0;

		Queue<Point> q = new LinkedList<>();
		int index = height.size() - 1;

		while(!height.isEmpty()) {
			Point start = height.poll();
			if(group[start.row][start.col] != -1) continue;

			int sum = 0;
			boolean summit = true;

			q.offer(start);

			while(q.size()>0) {
				Point current = q.poll();
				if(group[current.row][current.col] != -1) continue;
				if(map[current.row][current.col] == start.d) sum++;
				group[current.row][current.col] = index;

				for(final int[] DIRECTION: DIRECTIONS) {
					int nextRow = current.row + DIRECTION[ROW];
					int nextCol = current.col + DIRECTION[COL];

					if(outOfRange(nextRow, nextCol)) continue;
					if(map[nextRow][nextCol] <= start.d - D) continue;
					if(group[nextRow][nextCol] > index) summit = false;

					if(group[nextRow][nextCol] != -1) continue;
					q.offer(new Point(nextRow,nextCol, map[nextRow][nextCol]));
				}
			}

			if(summit) total += sum;
			index--;
		}
	}

	private static boolean outOfRange(int row, int col) {
		return row < 0 || row >= n || col < 0 || col >= m;
	}
}
