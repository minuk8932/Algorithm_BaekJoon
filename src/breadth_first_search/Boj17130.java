package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 17130번: 토끼가 정보섬에 올라온 이유
 *
 *	@see https://www.acmicpc.net/problem/17130/
 *
 */
public class Boj17130 {
	private static final int[][] DIRECTIONS = { { 0, 1 }, { 1, 1 }, { -1, 1 } };
	private static final int ROW = 0, COL = 1;

	private static final char RABBIT = 'R';
	private static final char BLOCK = '#';
	private static final char DOOR = 'O';
	private static final char CARROT = 'C';

	private static class Point {
		int row;
		int col;

		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		char[][] map = new char[N][M];
		ArrayList<Point> doors = new ArrayList<>();
		Point s = new Point(-1, -1);

		for (int i = 0; i < N; i++) {
			String line = br.readLine();

			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);

				if (map[i][j] == RABBIT) s = new Point(i, j);
				if (map[i][j] == DOOR) doors.add(new Point(i, j));
			}
		}

		System.out.println(getMaxCarrot(N, M, map, s, doors));
	}

	private static int getMaxCarrot(int n, int m, char[][] map, Point start, ArrayList<Point> end) {
		boolean isEscape = false;

		int[][] carrots = new int[n][m];
		for (int i = 0; i < n; i++) {
			Arrays.fill(carrots[i], -1);
		}

		Queue<Point> q = new LinkedList<>();
		q.offer(start);

		carrots[start.row][start.col] = 0;

		while (!q.isEmpty()) {
			Point current = q.poll();

			for (final int[] DIRECTION : DIRECTIONS) {
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];

				if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= m) continue;
				if (map[nextRow][nextCol] == BLOCK)	continue;
				
				if(map[nextRow][nextCol] == CARROT) {			// 현재 위치에 당근이 존재하는 경우
					if (carrots[nextRow][nextCol] < carrots[current.row][current.col] + 1) {	// 추가 값이 큰 경우에만
						carrots[nextRow][nextCol] = carrots[current.row][current.col] + 1;
						
						q.offer(new Point(nextRow, nextCol));
					}
				}
				else {
					if (carrots[nextRow][nextCol] < carrots[current.row][current.col]) {		// 현재 값이 다음 값보다 크면 갱신
						carrots[nextRow][nextCol] = carrots[current.row][current.col];
						if (map[nextRow][nextCol] == DOOR) isEscape = true;
						
						q.offer(new Point(nextRow, nextCol));
					}
				}
			}
		}

		int max = 0;
		for(Point p: end){									// 종점의 값 중 최댓값
			max = Math.max(max, carrots[p.row][p.col]);
		}
	
		return isEscape ? max: -1;
	}
}
