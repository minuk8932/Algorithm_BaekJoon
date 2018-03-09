package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 3055번 : 탈출
 *
 *	@see https://www.acmicpc.net/problem/3055
 *
 */
public class Boj3055 {
	private static final String SPACE = " ";
	private static final String NO_WAY = "KAKTUS";

	private static final char HEDGEHOG = 'S';
	private static final char CAVE = 'D';
	private static final char WATER = '*';
	private static final char STONE = 'X';

	private static final int[][] DIRECTIONS = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	private static final int ROW = 0;
	private static final int COL = 1;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		int sRow = 0, sCol = 0;									// 시작지 인덱스를 추적할 변수들
		int dRow = 0, dCol = 0;								// 목적지 인덱스를 추적할 변수들
		boolean[][] waterMap = new boolean[R][C];	// 물이 존재하는 위치를 추적할 배열

		char[][] map = new char[R][C];					// 실제 지도를 담을 배열

		for (int i = 0; i < R; i++) {
			String line = br.readLine();

			for (int j = 0; j < C; j++) {
				map[i][j] = line.charAt(j);

				if (map[i][j] == HEDGEHOG) {
					sRow = i;
					sCol = j;
				}
				if (map[i][j] == CAVE) {
					dRow = i;
					dCol = j;
				}
				if (map[i][j] == WATER) {
					waterMap[i][j] = true;
				}
			}
		}
		
		br.close();

		int[][] isVisited = new int[R][C];
		int[][] wVisited = new int[R][C];
		boolean arrived = false;

		Queue<Point> q = new LinkedList<>();

		for (int i = 0; i < R; i++) {				// 물이 어떻게, 몇 회에 걸쳐 퍼지는지 알아보기 위해 먼저 BFS 실행 
			for(int j = 0; j < C; j++){
				if(waterMap[i][j]){
					q.offer(new Point(i, j));
					wVisited[i][j] = 1;
				}
			}
		}

		while (!q.isEmpty()) {

				Point wCurrent = q.poll();
	
				for (final int[] DIRECTION : DIRECTIONS) {
					int nextRow = wCurrent.row + DIRECTION[ROW];
					int nextCol = wCurrent.col + DIRECTION[COL];
	
					if (nextRow >= 0 && nextRow < R && nextCol >= 0 && nextCol < C) {
						if (map[nextRow][nextCol] != STONE && map[nextRow][nextCol] != CAVE) {			// 돌과 목적지는 피하면서
							if (wVisited[nextRow][nextCol] == 0) {															// 아직 물이 찬곳이 아닐경우에
								wVisited[nextRow][nextCol] = wVisited[wCurrent.row][wCurrent.col] + 1;	// 물이 몇번이동만에 해당 칸으로 갔는지에 대해 배열에 값을 담아 구분
	
								q.offer(new Point(nextRow, nextCol));													// 다음으로 물이 이동할 칸을 큐에 담아줌
							}
						}
					}
				}
			}																																// 물의 이동경로 추적 완료!

			q.offer(new Point(sRow, sCol));																					// 다음으로 고슴도치의 이동경로를 시작점부터 BFS 실행
			isVisited[sRow][sCol] = 1;
			
		while(!q.isEmpty()){
			Point current = q.poll();

			for (final int[] DIRECTION : DIRECTIONS) {
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];

				if (nextRow >= 0 && nextRow < R && nextCol >= 0 && nextCol < C) {
					if (map[nextRow][nextCol] != WATER && map[nextRow][nextCol] != STONE) {			// 돌과 물이 찬곳은 피하면서
						if (isVisited[nextRow][nextCol] == 0 && 															// 아직 고슴도치가 방문하지 않았을 때
								(wVisited[nextRow][nextCol] > isVisited[current.row][current.col] + 1 || wVisited[nextRow][nextCol] == 0)) { 
							// 다음 고슴도치가 갈 칸까지 이전에 물이 찬 값 > 현재까지 고슴도치가 이동한 값 + 1 : 다음 칸으로 이동했을때 고슴도치의 최종 이동, 이면
							// 즉 아직 해당 칸에 물이 차지않아 고슴도치가 이동 가능한 경우
							isVisited[nextRow][nextCol] = isVisited[current.row][current.col] + 1;			// 다음칸으로 이동하는데 걸린 횟수를 해당 배열에 담아줌

							if (map[nextRow][nextCol] == CAVE) {															// 만약 위의 코드를 실행하면서 목적지에 도착한 경우
								if (!arrived) {
									arrived = true;																					// 도착값을 true로 변경
								}
							}

							q.offer(new Point(nextRow, nextCol));														// 다음 이동경로를 큐에 담아줌
						}
					}
				}
			}
		}

		System.out.println(arrived ? isVisited[dRow][dCol] - 1 : NO_WAY);										//	목적지에 도착했다면 이동까지 걸린 횟수를, 도착 못했다면 'KAKTUS' 출력
	}
	
	/**
	 * 
	 * 	@author minchoba
	 *	정점 이너 클래스
	 */
	private static class Point {
		int row;
		int col;

		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
}