package depth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author minchoba
 * 백준 14503번 : 로봇청소기
 *
 *	@see https://www.acmicpc.net/problem/14503/
 *
 */
public class Boj14503 {
	private static final String SPACE = " ";
	
	private static int[][] map = null;
	private static int N = 0;
	private static int M = 0;
	private static int clear = 1;
	
	private static final int[][] MOVE = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	private static final int DIRT = 0;
	private static final int BLOCK = 1;
	private static final int WIPED = 2;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		st = new StringTokenizer(br.readLine(), SPACE);
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < N; i++){
			st = new StringTokenizer(br.readLine(), SPACE);
			
			for(int j = 0; j < M; j++){
				map[i][j] = Integer.parseInt(st.nextToken()); 
			}
		}
		
		dfs(new Point(r, c, d));				// dfs 알고리즘 실행
		System.out.println(clear);			// 청소된 지역의 수를 출력
	}
	
	/**
	 * 
	 * 	@author minchoba
	 *	현재 위치와 바라보는 방향을 저장 할 정점 이너 클래스
	 *
	 */
	private static class Point{
		int row;
		int col;
		int dir;
		
		public Point(int row, int col, int dir){
			this.row = row;
			this.col = col;
			this.dir = dir;
		}
	}
	
	/**
	 * 
	 * 	깊이 우선 탐색 알고리즘
	 * 	@param p : 위치와 방향을 받아둠
	 * 
	 */
	private static void dfs(Point p){
		map[p.row][p.col] = WIPED;			// 현 위치를 청소함으로 처리 : clear는 초기값이 1이므로 건드릴 필요 없음
		
		int nextRow = 0, nextCol = 0;		// 다음 위치 선언
		
		for(int i = 0; i < 4; i++){
			p.dir = (p.dir + 3) % 4;			// 현재 바라보는 방향이 1, 2, 3이면 -1 씩,  0이면 3으로 바꿔줘야 하므로 해당 연산을 이용
			
			nextRow = p.row + MOVE[p.dir][ROW];		// MOVE 상수 배열에 각 북 동 남 서 순으로 저장되어 있으므로,  해당 방향에 맞는 행 또는 열을 가져와서 현재 행과 열에 더해줌 
			nextCol = p.col + MOVE[p.dir][COL];
			
			if(nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < M && map[nextRow][nextCol] == DIRT){	// 범위 내에서 아직 방문하지 않은 곳일 경우
				clear++;														// 청소한 지역으로 표시
				dfs(new Point(nextRow, nextCol, p.dir));		// 다음 위치와 방향을 넣고 재귀 호출
				
				return;														// 현 함수 종료
			}
		}
		
		int beforeDir = (p.dir + 2) % 4;							// 이전 방향 저장, 즉 본래 북쪽을 보고 있었다면, 현재는 서쪽을 보고있고 해당 변수에는 동쪽이 저장됨.
																				// 결국 현 위치로 오기 전에 있었던 위치와 방향을 받아오게됨
		int beforeRow = p.row + MOVE[beforeDir][ROW];
		int beforeCol = p.col + MOVE[beforeDir][COL];
		
		if (beforeRow >= 0 && beforeRow < N && beforeCol >= 0 && beforeCol < M && map[beforeRow][beforeCol] != BLOCK) {	// 뒤로 간 그 위치가 움직일 수 있는 곳이면
			dfs(new Point(beforeRow, beforeCol, p.dir));			// 해당 위치를 받아와서 재귀 호출
        }
		
	}
}
