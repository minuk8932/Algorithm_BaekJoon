package back_tracking;

import common.Point;
import java.io.*;
import java.util.StringTokenizer;
import java.util.function.Predicate;

/**
 *
 * @author exponential-e
 * 백준 17492번: 바둑알 점프
 *
 * @see https://www.acmicpc.net/problem/17492
 *
 */
public class Boj17492 {
	private static final int EMPTY = 0;
	private static final int DOT = 2;
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}
		, {1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
	private static final int ROW = 0, COL = 1;

	private static final String I = "Impossible";
	private static final String P = "Possible";
	private static boolean flag;
	
	private static int N;
	private static final Predicate<Point<Integer, Integer>> OUT_OF_RANGE = p ->
		p.getRow() < 0 || p.getRow() >= N || p.getCol() < 0 || p.getCol() >= N;
	private static int[][] go;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		go = new int[N][N];

		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				go[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(recursion() ? P: I);
	}

	private static boolean recursion() {
		int count = 0;

		for(int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				if (go[row][col] != DOT) continue;
				count++;

				for (final int[] DIRECTION: DIRECTIONS) {
					Point<Integer, Integer> next = new Point.Builder<Integer, Integer>(
						row + DIRECTION[ROW], col + DIRECTION[COL]).build();

					if (OUT_OF_RANGE.test(next)) continue;
					if (go[next.getRow()][next.getCol()] != DOT) continue;

					Point<Integer, Integer> jump = new Point.Builder<Integer, Integer>(
						next.getRow() + DIRECTION[ROW], next.getCol() + DIRECTION[COL])
						.build();
					if (go[jump.getRow()][jump.getCol()] != EMPTY) continue;

					go[row][col] = EMPTY;
					go[next.getRow()][next.getCol()] = EMPTY;
					go[jump.getRow()][jump.getCol()] = DOT;

					flag |= recursion();
					go[jump.getRow()][jump.getCol()] = EMPTY;
					go[next.getRow()][next.getCol()] = DOT;
					go[row][col] = DOT;
				}
			}
		}

		if (count != 1) return flag;
		return true;
	}
}
