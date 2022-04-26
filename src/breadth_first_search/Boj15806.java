package breadth_first_search;

import common.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.function.Predicate;

/**
 *
 * @author exponential-e
 * 백준 15806번: 영우의 기숙사 청소
 *
 * @see https://www.acmicpc.net/problem/15806
 *
 */
public class Boj15806 {

	private static final int[][] DIRECTIONS = {{1, 2}, {-1, 2}, {1, -2}, {-1, -2}
		, {2, 1}, {-2, 1}, {2, -1}, {-2, -1}};
	private static final int ROW = 0, COL = 1;

	private static List<Point<Integer, Integer>> query = new LinkedList<>();
	private static Queue<Point<Integer, Integer>> q;
	private static boolean[][][] dirty;
	private static int N, M;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());		// 방바닥 갯수
		int t = Integer.parseInt(st.nextToken());

		q = new ArrayDeque<>();
		dirty = new boolean[500][N][N];

		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			dirty[0][x][y] = true;

			q.offer(new Point.Builder(x, y).build());
		}

		while(K-- > 0) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;

			query.add(new Point.Builder(x, y).build());
		}

		spread(t);
		System.out.println(supervisorCheck(t));
	}

	private static String supervisorCheck(int timer) {
		while (!query.isEmpty()) {
			Point<Integer, Integer> floor = query.remove(0);
			if(!dirty[timer % 2][floor.getRow()][floor.getCol()]) continue;

			return "YES";
		}

		return "NO";
	}

	private static void spread(int timer) {
		final Predicate<Point<Integer, Integer>> OUT_OF_RANGE = point ->
			point.getRow() < 0 || point.getRow() >= N || point.getCol() < 0 || point.getCol() >= N;

		int count = 0;
		while(timer-- > 0) {
			int size = q.size();

			while (size-- > 0) {
				Point<Integer, Integer> current = q.poll();
				dirty[count][current.getRow()][current.getCol()] = false;

				for (final int[] DIRECTION : DIRECTIONS) {
					int nextRow = current.getRow() + DIRECTION[ROW];
					int nextCol = current.getCol() + DIRECTION[COL];

					if (OUT_OF_RANGE.test(new Point.Builder(nextRow, nextCol).build())) continue;
					Point<Integer, Integer> next = new Point.Builder(nextRow, nextCol).build();

					if (dirty[(count + 1) % 2][next.getRow()][next.getCol()]) continue;
					dirty[(count + 1) % 2][next.getRow()][next.getCol()] = true;

					q.offer(next);
				}
			}

			count++;
		}
	}
}
