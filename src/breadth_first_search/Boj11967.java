package breadth_first_search;

import common.Point;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Predicate;

/**
 * 
 * 	@author minchoba
 *	백준 11967번: 불켜기
 *
 *	@see https://www.acmicpc.net/problem/11967/
 *
 */
public class Boj11967 {
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0, COL = 1;

	private static boolean[][] on;
	private static List<Point>[] light;
	private static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		light = new ArrayList[N * N];
		for(int i = 0; i < light.length; i++){
			light[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;

			light[indexing(x, y)].add(new Point.Builder(a, b).build());
		}

		bfs(new Point.Builder(0, 0).build());
		System.out.println(countRooms());
	}

	private static void bfs(Point start) {
		on = new boolean[N][N];
		boolean[][] visit = new boolean[N][N];
		boolean[][] adjacent = new boolean[N][N];

		Queue<Point> q = new ArrayDeque<>();
		q.offer(start);

		on[start.getRow()][start.getCol()] = true;
		visit[start.getRow()][start.getCol()] = true;

		while (!q.isEmpty()) {
			Point current = q.poll();

			for(Point bulb: light[indexing(current.getRow(), current.getCol())]) {
				if(on[bulb.getRow()][bulb.getCol()]) continue;
				on[bulb.getRow()][bulb.getCol()] = true;
			}

			for(final int[] DIRECTION: DIRECTIONS) {
				int nextRow = current.getRow() + DIRECTION[ROW];
				int nextCol = current.getCol() + DIRECTION[COL];

				Point next = new Point.Builder(nextRow, nextCol).build();

				if(OUT_OF_RANGE.test(next)) continue;
				adjacent[next.getRow()][next.getCol()] = true;
			}

			for(int row = 0; row < N; row++) {
				for(int col = 0; col < N; col++) {
					if(!on[row][col] || !adjacent[row][col] || visit[row][col]) continue;
					visit[row][col] = true;

					q.add(new Point.Builder(row, col).build());
				}
			}
		}
	}

	private static final Predicate<Point> OUT_OF_RANGE = p ->
			p.getRow() < 0 || p.getCol() < 0 || p.getRow() >= N || p.getCol() >= N;

	private static int indexing(int a, int b) {
		return N * a + b;
	}

	private static int countRooms() {
		int count = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (on[i][j]) count++;
			}
		}

		return count;
	}
}
