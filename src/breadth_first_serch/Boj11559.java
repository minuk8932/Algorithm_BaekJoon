package breadth_first_serch;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * 	@author minchoba
 *	백준 11559번: puyo puyo
 *
 *	@see https://www.acmicpc.net/problem/11559/
 *
 */
public class Boj11559 {
	private static final char EMPTY = '.';
	
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	private static final int R = 12;
	private static final int C = 6;
	
	private static char[][] map = null;
	private static int[][] isVisited = null;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[R][C];
		
		int dotNums = 0;				// 맵 안의 점의 갯수를 구함
		
		for(int i = 0; i < R; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < C; j++) {
				map[i][j] = line.charAt(j);
				
				if(map[i][j] == EMPTY) {		// '.'인 경우 dotNums +1
					dotNums++;
				}
			}
		}
		
		int isBoomed = 0;
		
		while(dotNums != 72) {			// map내에 블럭이 하나라도 존재 할 경우 반복문 돌지 않음
			int res = bfs();			// 너비 우선탐색 알고리즘의 결과를 res 변수에 담아줌
			
			if(res == 0) {			// 만약 res가 0이면 더이상 터질 블럭이 없으므로 반복문 종료
				break;
			}
			else {
				isBoomed += res;	// res가 0이 아닐경우 블럭이 터졌으므로, 해당 값을 isBoomed에 더해줌
			}
			
			remake();				// 블럭밑에 빈칸이 있는경우 아래로 끌어 내리는 메소드
		}
		
		System.out.println(isBoomed);		// 최종 몇번 터졌는지 결과를 출력
	}
	
	/**
	 * 	정점 이너 클래스
	 * 	@author minchoba
	 *
	 */
	private static class Point{
		int row;
		int col;
		
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	/**
	 * 너비 우선 탐색 알고리즘
	 * @return
	 */
	private static int bfs() {
		boolean isProcessed = false;					// pop()이 실행 되었는가?
		
		for(int row = 0; row < R; row++) {
			for(int col = 0; col < C; col++) {
				if(map[row][col] == EMPTY) continue;	// '.' 인 경우 탐색을 실행하지 않음
				isVisited = new int[R][C];
				
				isVisited[row][col] = 1;
				
				Queue<Point> q = new LinkedList<>();
				q.offer(new Point(row, col));
				
				while(!q.isEmpty()) {
					Point current = q.poll();
					
					for(final int[] DIRECTION : DIRECTIONS) {
						int nextRow = current.row + DIRECTION[ROW];
						int nextCol = current.col + DIRECTION[COL];
						
						// 맵의 범위 내에서
						if(nextRow >= 0 && nextRow < R && nextCol >= 0 && nextCol < C) {
							// 다음칸: 아직 방문하지 않음, 현재 칸과 다음칸의 모양이 같으면서, 다음칸이 '.'이 아닌경우
							if(isVisited[nextRow][nextCol] == 0 && map[nextRow][nextCol] != EMPTY && 
									map[current.row][current.col] == map[nextRow][nextCol]) {
								isVisited[nextRow][nextCol] = 1;	// 방문한 배열은 방문함(1)으로 설정
								isVisited[row][col]++;				// 탐색을 시작한 인덱스의 방문 배열값에 지속적으로 +1
																
								q.offer(new Point(nextRow, nextCol));		// 다음 인덱스를 큐에 담아줌
							}
						}
					}
				}
				
				// 탐색 시작한 배열의 값이 4이상 -> 인접한 블럭의 갯수가 4개 이상
				if(isVisited[row][col] >= 4) {
					puyoPop();			// 블럭을 터트리는 메소드
					isProcessed = true;	// puyo가 터졌다고 표시
				}
			}
		}
		
		return isProcessed ? 1 : 0;	// puyoPop()가 동작했으면 1을 아니면 0을 반환
	}
	
	private static void puyoPop() {			// 블럭을 터트리는 메소드
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(isVisited[i][j] != 0) {	// 방문배열이 0이 아닌경우
					map[i][j] = EMPTY;		// '.'로 초기화
				}
			}
		}
	}
	
	private static void remake() {				// 맵을 재구성하는 메소드
		for(int i = 0; i < C; i++) {
			for(int j = R -1; j >= 0; j--) {
				if(map[j][i] != EMPTY) {			// 맵의 각 열마다, 행의 역순으로 탐색하여 '.'가 아닌 경우에만
					int move = 1;
					
					while(j + move < R) {					// 반복문을 조건에 맞춰 돌림: 기준 인덱스 아래에 '.'가 존재할 경우 반복적으로 실행
						if(map[j + move][i] == EMPTY) {		// 만약 현재 알파벳이 있는 인덱스의 아래 값이 '.'인 경우
							// 두 배열 내의 값을 바꾸어줌: swap
							char tmp = map[j + move][i];
							map[j + move][i] = map[j + move - 1][i];
							map[j + move - 1][i] = tmp;
						}
						
						move++;		// 다음 인덱스를 검사하기위해 move + 1;
					}
				}
			}
		}
	}
}
