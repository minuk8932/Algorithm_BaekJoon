import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 7569번 : 토마토
 *
 *	@see https://www.acmicpc.net/problem/7569
 *
 *	비슷한 문제 (토마토 2차원)
 *	@see https://www.acmicpc.net/problem/7576
 *
 */

public class Boj7569 {
	private static final String SPACE = " ";
	
	private static final int[][] DIRECTIONS = {{-1, 0, 0}, {0, 0, 1}, {1, 0, 0}, {0, 0, -1}, {0, 1, 0}, {0, -1, 0}};	// 3차원 6방향
	private static final int ROW = 0;			// x축
	private static final int COL = 1;			// y축
	private static final int DEP = 2;			// z축
	
	private static final int RIPE = 1;
	private static final int UNRIPE = 0;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		
		int[][][] box = new int[H + 1][N + 1][M + 1];
		int[][][] isVisited = new int[H + 1][N + 1][M + 1];
		
		Queue<Point> queue = new LinkedList<>();

		int notRipedCnt = 0;

		for (int dep = 1; dep < H + 1; dep++) {
			for (int row = 1; row < N + 1; row++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int col = 1; col < M + 1; col++) {
					box[dep][row][col] = Integer.parseInt(st.nextToken());

					if (box[dep][row][col] == RIPE) {
						isVisited[dep][row][col] = 1;
						queue.offer(new Point(dep, row, col));	// 탐색을 시작 할 토마토의 위치를 큐에 저장
					}
					else if (box[dep][row][col] == UNRIPE) {
						notRipedCnt++;
					}
				}
			}
		}

		br.close();

		while (!queue.isEmpty()) {
			Point current = queue.poll();

			for (final int[] DIRECTION : DIRECTIONS) {
				int nextDep = current.dep + DIRECTION[DEP];
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];

				if (0 < nextDep && nextDep < H + 1 && 0 < nextRow && nextRow < N + 1 && 0 < nextCol && nextCol < M + 1) {
					if (box[nextDep][nextRow][nextCol] == UNRIPE && isVisited[nextDep][nextRow][nextCol] == 0) {		// 토마토가 안익었고, 방문하지 않은 점일 경우
						isVisited[nextDep][nextRow][nextCol] = isVisited[current.dep][current.row][current.col] + 1;		// 단계별 값 합산 (토마토가 익는 날짜 계산)

						queue.offer(new Point(nextDep, nextRow, nextCol));
						notRipedCnt--;																														// 익지않은 토마토의 갯수를 하나씩 감소
					}
				}
			}
		}

		if (notRipedCnt > 0) {			// 익지 않은 토마토가 남은 경우
			System.out.println(-1);

			return;
		}

		int maxDays = 0;

		for (int dep = 1; dep < H + 1; dep++) {
			for (int row = 1; row < N + 1; row++) {
				for (int col = 1; col < M + 1; col++) {
					maxDays = Math.max(maxDays, isVisited[dep][row][col] - 1);		// 토마토가 익는 날짜 중 가장 긴 날짜, 즉 최종적으로 토마토가 다 익었을때의 기간 계산
				}
			}
		}

		System.out.println(maxDays);		// 결과 출력
	}
	
	private static class Point{		// 탐색을 지정해 줄 클래스
		int dep;
		int row;
		int col;
			
		/**
		 * 
		 * @param dep : 위, 아래
		 * @param row : 앞, 뒤 
		 * @param col : 좌, 우
		 * 
		 */
			public Point(int dep, int row, int col){
				this.dep = dep;
				this.row = row;
				this.col = col;
			}
	}
}
