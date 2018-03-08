package breadth_first_serch;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 6592번 : 상범 빌딩
 *
 *	@see https://www.acmicpc.net/problem/6593/
 *
 */
public class Boj6593 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	private static final String NO_WAY = "Trapped!";
	private static final String ESCAPE_1 = "Escaped in ";
	private static final String ESCAPE_2 = " minute(s).";
	
	private static final char BLOCKED = '#';
	private static final char START = 'S';
	private static final char END = 'E';
	
	private static final int[][] DIRECTIONS = {{0, 0, 1}, {0, 0, -1}, {0, 1, 0}, {0, -1, 0}, {1, 0, 0}, {-1, 0, 0}};
	private static final int LEVEL = 2;
	private static final int ROW = 0;
	private static final int COL = 1;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true){
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			int L = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			if(L == 0 && R == 0 && C == 0){
				break;
			}
			
			char[][][] build = new char[L][R + 1][C];				// R + 1 : 공백 입력 처리를 위한 여유 공간
			
			for(int i = 0; i < L; i++){
				for(int j = 0; j < R + 1; j++){
					build[i][j] = br.readLine().toCharArray();
				}
			}
			
			int sRow = 0, sCol = 0, sLevel = 0;							// S의 위치
			int eRow = 0, eCol = 0, eLevel = 0;						// E의 위치
			
			for(int i = 0; i < L; i++){
				for(int j = 0; j < R; j++){
					for(int k = 0; k < C; k++){
						if(build[i][j][k] == START){
							sLevel = i;
							sRow = j;
							sCol = k;
						}
						if(build[i][j][k] == END){
							eLevel = i;
							eRow = j;
							eCol = k;
						}
					}
				}
			}
			
			int[][][] isVisited = new int[L][R][C];
			Queue<Move> q = new LinkedList<>();
			
			q.offer(new Move(sLevel, sRow, sCol));
			isVisited[sLevel][sRow][sCol] = 1;
			
			while(!q.isEmpty()){
				Move current = q.poll();
				
				for(int[] DIRECTION : DIRECTIONS){												// 완전탐색(BFS) 실행, 6방향
					int nextLevel = current.level + DIRECTION[LEVEL];
					int nextRow = current.row + DIRECTION[ROW];
					int nextCol = current.col + DIRECTION[COL];
					
					if(nextLevel >= 0 && nextLevel < L && nextRow >= 0 && nextRow < R && nextCol >= 0 && nextCol < C){
						if(isVisited[nextLevel][nextRow][nextCol] == 0 && build[nextLevel][nextRow][nextCol] != BLOCKED){		// 아직 방문 안했고, 벽으로 막히지 않은 경우
							isVisited[nextLevel][nextRow][nextCol] = isVisited[current.level][current.row][current.col] + 1;		// 이동값을 다음 방문 배열에 추가
							
							q.offer(new Move(nextLevel, nextRow, nextCol));
						}
					}
				}
			}
			
			if(isVisited[eLevel][eRow][eCol] == 0){					// 0 일땐 상범이가 접근하지 못한것이므로, Trapped!
				sb.append(NO_WAY).append(NEW_LINE);
			}
			else{																	// 이외에는 시작지점 방문 값이 1이므로 최종 방문 값 - 1
				sb.append(ESCAPE_1).append(isVisited[eLevel][eRow][eCol] - 1).append(ESCAPE_2).append(NEW_LINE);
			}
		}
		
		System.out.println(sb.toString());							// 결과 값 한번에 출력
	}
	
	/**
	 * 
	 * 	@author minchoba
	 *	이동 이너 클래스
	 *
	 */
	private static class Move{
		int level;
		int row;
		int col;
		
		/**
		 * 
		 * @param level : 층간 이동
		 * @param row : 행 이동
		 * @param col : 열 이동
		 *
		 */
		public Move(int level, int row, int col){
			this.level = level;
			this.row = row;
			this.col = col;
		}
	}
}
