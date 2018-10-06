package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 13460번: 구슬 탈출 2
 *
 *	@see https://www.acmicpc.net/problem/13460/
 *
 */
public class Boj13460 {
	private static final char BLOCK = '#';
	private static final char WAY = '.';
	private static final char HOLE = 'O';
	private static final char RED = 'R';
	private static final char BLUE = 'B';

	private static final int FAIL = -1;
	private static final int[][] DIRECTIONS = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	private static final int ROW = 0;
	private static final int COL = 1;

	private static char[][] map = null;
	private static boolean[][][][] isVisited = null;

	public static void main(String[] args) throws Exception {
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		Point[] start = new Point[2];

		isVisited = new boolean[N][M][N][M];

		for (int i = 0; i < N; i++) {
			String line = br.readLine();

			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);

				if (map[i][j] == RED) {				// 빨간공 위치
					start[0] = new Point(i, j);
					map[i][j] = WAY;
				}

				if (map[i][j] == BLUE) {			// 파란 공 위치
					start[1] = new Point(i, j);
					map[i][j] = WAY;
				}
			}
		}
		
		System.out.println(bfs(N, M, start));		// 너비 우선 탐색 메소드를 통한 결과 값 출력
	}
	
	/**
	 * 정점 이너 클래스
	 * @author minchoba
	 *
	 */
	private static class Point {
		int row;
		int col;

		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	/**
	 * 각 공의 위치 정점 클래스
	 * @author minchoba
	 *
	 */
	private static class BallPos{
		Point rP;
		Point bP;
		
		public BallPos(Point rP, Point bP) {
			this.rP = rP;
			this.bP = bP;
		}
	}

	/**
	 * 너비 우선 탐색 메소드
	 * 
	 */
	private static int bfs(int R, int C, Point[] s) {
		Queue<BallPos> q = new LinkedList<>();
		q.offer(new BallPos(new Point(s[0].row, s[0].col), new Point(s[1].row, s[1].col)));		// 큐에 두 공의 위치를 모두 넣어줌
		
		isVisited[s[0].row][s[0].col][s[1].row][s[1].col] = true;			// 두 공의 위치를 모두 방문한 것으로 처리
		
		int res = 0;

		while (!q.isEmpty()) {
			int qSize = q.size();
			
			while(qSize-- > 0) {
				BallPos current = q.poll();
				
				int rRow = current.rP.row;		// 현재 빨간공과 파란공의 정점을 모두 뽑아와서
				int rCol = current.rP.col;
				int bRow = current.bP.row;
				int bCol = current.bP.col;
				
				if(map[rRow][rCol] == HOLE && map[rRow][rCol] != map[bRow][bCol]) return res;	// 만약 빨간공만 구멍에 도달한 경우면 그때까지의 횟수를 반환
	
				for (final int[] DIRECTION : DIRECTIONS) {	// 아직 도달 못한 경우 각 공마다 4방위로 탐색
					int nextRedRow = rRow;
					int nextRedCol = rCol;
					int nextBlueRow = bRow;
					int nextBlueCol = bCol;
					
					// 빨간공 움직임 -> 막힐때까지
					while (map[nextRedRow + DIRECTION[ROW]][nextRedCol + DIRECTION[COL]] != BLOCK && map[nextRedRow][nextRedCol] != HOLE) {
	                    nextRedRow += DIRECTION[ROW];
	                    nextRedCol += DIRECTION[COL];
					}
					
					// 파란공 움직임 -> 막힐때까지
					while (map[nextBlueRow + DIRECTION[ROW]][nextBlueCol + DIRECTION[COL]] != BLOCK && map[nextBlueRow][nextBlueCol] != HOLE) {
						nextBlueRow += DIRECTION[ROW];
						nextBlueCol += DIRECTION[COL];
					}
					
					if(nextRedRow == nextBlueRow && nextRedCol == nextBlueCol) { // 두 공이 같은 위치에 있을때
						if(map[nextRedRow][nextRedCol] == HOLE) continue;		// 둘 다 구멍에 들어간 경우 다음으로 넘어감, 정답이 될 수 없는 케이스
						
						// 빨간공이 파란공 보다 덜 움직인 경우, 파란공을 바로 전 칸으로 옮김
						if(Math.abs(nextRedRow - rRow) + Math.abs(nextRedCol - rCol) < Math.abs(nextBlueRow - bRow) + Math.abs(nextBlueCol - bCol)) {
							nextBlueRow -= DIRECTION[ROW];
							nextBlueCol -= DIRECTION[COL];
						}
						else {			// 반대의 경우 빨간공을 한칸 뒤로
							nextRedRow -= DIRECTION[ROW];
							nextRedCol -= DIRECTION[COL];
						}
					}
					
					if(isVisited[nextRedRow][nextRedCol][nextBlueRow][nextBlueCol]) continue;		// 공의 위치가 결정 되었을때 이미 방문한 위치면 다음으로
					isVisited[nextRedRow][nextRedCol][nextBlueRow][nextBlueCol] = true;
					
					// 아직 방문하지 않은 칸인 경우 큐에 다음 위치로 저장
					q.offer(new BallPos(new Point(nextRedRow, nextRedCol), new Point(nextBlueRow, nextBlueCol)));
				}
			}
			
			if(res > 9) return FAIL;		// 이동 횟수가 9회가 넘어가는 경우 -1 반환
			res++;						// 정상적으로 한번 수행 후 결과 + 1
		}
		
		return FAIL;			// 가능한 이동을 모두 완료했는데 공이 도달하지 못한 경우
	}
}
