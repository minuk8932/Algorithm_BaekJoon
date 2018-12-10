package dijkstra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj4184 {
	private static final int[][] DIRECTIONS = {{ -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 } };
	private static final int ROW = 0;
	private static final int COL = 1;
	private static final int INF = 1_000_001;

	private static final char[] BEARING = {'0', '1', '2', '3', '4', '5', '6', '7'};
	private static final char NEW_LINE = '\n';

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		char[][] map = new char[r + 1][c + 1];
		for (int i = 1; i < r + 1; i++) {
			String line = br.readLine();

			for (int j = 0; j < c; j++) {
				map[i][j + 1] = line.charAt(j);
			}
		}

		int n = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());

			int startRow = Integer.parseInt(st.nextToken());
			int startCol = Integer.parseInt(st.nextToken());
			int endRow = Integer.parseInt(st.nextToken());
			int endCol = Integer.parseInt(st.nextToken());

			int res = dijkstra(r, c, startRow, startCol, endRow, endCol, map);
			sb.append(res).append(NEW_LINE);
		}

		System.out.println(sb);
	}
	
	private static class Point implements Comparable<Point> {
		int row;
		int col;
		int cost;

		public Point(int row, int col, int cost) {
			this.row = row;
			this.col = col;
			this.cost = cost;
		}

		@Override
		public int compareTo(Point p) {
			if(this.cost < p.cost) return -1;
			else if(this.cost > p.cost) return 1;
			else return 0;
		}
	}
	
	private static int dijkstra(int n, int m, int sRow, int sCol, int eRow, int eCol, char[][] map) {
        int[][] cost = costInit(n, m);
		cost[sRow][sCol] = 0;

		PriorityQueue<Point> pq = new PriorityQueue<>();
		pq.offer(new Point(sRow, sCol, cost[sRow][sCol]));

		while (!pq.isEmpty()) {
			Point current = pq.poll();

			for (int i = 0; i < 8; i++) {
				int nextRow = current.row + DIRECTIONS[i][ROW];
				int nextCol = current.col + DIRECTIONS[i][COL];
				int nextCost = 0;

				if (nextRow > 0 && nextRow < n + 1 && nextCol > 0 && nextCol < m + 1) {
					if(map[current.row][current.col] != BEARING[i]) nextCost = 1;

					if (cost[nextRow][nextCol] > cost[current.row][current.col] + nextCost) {	
						cost[nextRow][nextCol] = cost[current.row][current.col] + nextCost;
						
						pq.offer(new Point(nextRow, nextCol, cost[nextRow][nextCol]));
					}
				}
			}
		}
        
        return cost[eRow][eCol];
	}
    
	private static int[][] costInit(int r, int c) {
		int[][] arr = new int[r + 1][c + 1];

		for (int i = 0; i < r + 1; i++) {
			Arrays.fill(arr[i], INF);
		}
        
        return arr;
	}
}
