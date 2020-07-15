package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * @author exponential-e
 * 백준 9376번: 탈옥
 *
 * @see https://www.acmicpc.net/problem/9376
 *
 */
public class Boj9376 {
	private static final String NEW_LINE = "\n";
	
	private static final char DOOR = '#';
	private static final char BLOCK = '*';
	private static final char PRISONER = '$';
	private static final char EMPTY = '.';
	
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0, COL = 1;
	private static final int INF = 1_000_000_000;
	
	private static ArrayList<Point> start;
	private static int[][][] cost;
	private static char[][] map;

	private static int h, w;
	
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
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			
			map = new char[h + 2][w + 2];
			cost = new int[3][h + 2][w + 2];

			for(int i = 0; i < h + 2; i++) {
				Arrays.fill(map[i], EMPTY);
			}

			start = new ArrayList<>();
			start.add(new Point(0, 0));			// out of prison
			
			for(int i = 1; i <= h; i++) {
				String line = br.readLine();

				for(int j = 1; j <= w; j++) {
					map[i][j] = line.charAt(j - 1);
					
					if(map[i][j] == PRISONER){				// from prisoner
						start.add(new Point(i, j));
						map[i][j] = EMPTY;
					}
				}
			}

			bfs(map);
			int result = INF;

			for (int i = 0; i < h + 2; i++) {
				for (int j = 0; j < w + 2; j++) {
					if (map[i][j] == BLOCK) continue;
					int k = cost[0][i][j] + cost[1][i][j] + cost[2][i][j] - 3;		// find min cost

					if (map[i][j] == DOOR) k -= 2;
					if (result > k) result = k;
				}
			}

			sb.append(result).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
	}
	
	private static void bfs(char[][] map) {
		int index = -1;

		for (Point s: start) {
			Deque<Point> dq = new LinkedList<>();
			dq.offer(s);

			cost[++index][s.row][s.col] = 1;

			while (!dq.isEmpty()) {
				Point current = dq.poll();

				for(int[] DIRECTION: DIRECTIONS) {
					int nextRow = current.row + DIRECTION[ROW];
					int nextCol = current.col + DIRECTION[COL];

					if(outOfRange(nextRow, nextCol)) continue;
					if (cost[index][nextRow][nextCol] != 0 || map[nextRow][nextCol] == BLOCK) continue;

					if (map[nextRow][nextCol] == EMPTY) {
						cost[index][nextRow][nextCol] = cost[index][current.row][current.col];
						dq.offerFirst(new Point(nextRow, nextCol));								// search first current level
					}
					else {
						cost[index][nextRow][nextCol] = cost[index][current.row][current.col] + 1;
						dq.offer(new Point(nextRow, nextCol));
					}
				}
			}
		}
	}

	private static boolean outOfRange(int row, int col) {
		return  row < 0 || row > h + 1 || col < 0 || col > w + 1;
	}
}
