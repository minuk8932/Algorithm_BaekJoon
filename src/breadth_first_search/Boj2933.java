package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2933번: 미네랄
 *
 *	@see https://www.acmicpc.net/problem/
 *
 */
public class Boj2933 {
	private static final String NEW_LINE = "\n";
	private static final char MINERAL = 'x';
	private static final char EMPTY = '.';
	
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	private static final int INF = 1_000;
	
	private static int[][] isVisited = null;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 계산
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		char[][] mine = new char[R][C];
		for(int i = 0; i < R; i++) {
			mine[i] = br.readLine().toCharArray();
		}
		
		int crash = Integer.parseInt(br.readLine());	// 던지는 막대기의 수
		int[] stk = new int[crash];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < crash; i++) {
			stk[i] = Integer.parseInt(st.nextToken());	// 막대기를 던지는 층 입력
		}
		
		for(int level = 0; level < crash; level++) {
			isVisited = new int[R][C];
			
			if(level % 2 == 0) {						// 왼쪽에서 던지는 경우
				for(int i = 0; i < C; i++) {
					if(mine[R - stk[level]][i] == MINERAL) {
						mine[R - stk[level]][i] = EMPTY;
						break;
					}
				}
			}
			else {						// 오른쪽에서 던지는 경우
				for(int i = C - 1; i >= 0; i--) {
					if(mine[R - stk[level]][i] == MINERAL) {
						mine[R - stk[level]][i] = EMPTY;
						break;
					}
				}
			}
			
			int cluster = bfs(mine, R, C);		// 덩어리가 2개이상인지 너비우선탐색을 통해 구함

			if(cluster > 1) {
				int interval = getMinSpace(mine, R, C);	// 덩어리가 2개 이상이라면, 떨어질 수 있는 최소 크기를 메서드를 통해 구함
				drop(mine, R, C, interval);				// interval을 통해 해당 덩어리를 떨어트리는 메소드
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {		// 최종 결과를 버퍼에 담고
				sb.append(mine[i][j]);
			}
			sb.append(NEW_LINE);
		}
		
		System.out.println(sb.toString());		// 결과 값 한번에 출력
	}
	
	/**
	 * 정점 이너 클래스
	 * @author minchoba
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
	 * 
	 * 너비 우선 탐색을 통한 덩어리 갯수 반환
	 */
	private static int bfs(char[][] map, int N, int M) {
		int cnt = 1;
		
		isVisited = new int[N][M];
		
		for(int row = N - 1; row >= 0; row--) {
			for(int col = 0; col < M; col++) {
				if(map[row][col] == MINERAL && isVisited[row][col] == 0) {	// 미네랄이 있는 방문하지 않은 정점만 검사
					if(row != N - 1) {
						cnt++;
					}
					
					isVisited[row][col] = cnt;
					
					Queue<Point> q = new LinkedList<>();
					q.offer(new Point(row, col));
					
					while(!q.isEmpty()) {
						Point current = q.poll();
						
						for(final int[] DIRECTION : DIRECTIONS) {
							int nextRow = current.row + DIRECTION[ROW];
							int nextCol = current.col + DIRECTION[COL];
							
							if(nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < M) {
								if(isVisited[nextRow][nextCol] == 0 && map[nextRow][nextCol] == MINERAL) {
									isVisited[nextRow][nextCol] = cnt;
									
									q.offer(new Point(nextRow, nextCol));
								}
							}
						}
					}
				}
			}
		}
		
		return cnt;
	}
	
	/**
	 * 
	 * 최소 간격 반환 메소드
	 */
	private static int getMinSpace(char[][] map, int N, int M) {
		int min = INF;
		
		for(int j = 0; j < M; j++) {
			int inter = 0;
			
			for(int i = N - 1; i >= 0; i--) {
				if(isVisited[i][j] == 0) {		// 빈공간인 경우 간격 +1
					inter++;
				}
				
				if(isVisited[i][j] == 1) {		// 바닥에 붙은 미네랄이 나타나면 간격 = 0
					inter = 0;
				}
				
				if(isVisited[i][j] == 2) {		// 떨어져야 할 미네랄이 나타나면, 최솟값을 구해줌
					min = Math.min(min, inter);
					
					break;
				}
			}
		}
		
		return min;		// 최소 간격 반환
	}
	
	/**
	 * 
	 * 간격에 맞춰 덩어리를 떨어트리는 메소드
	 */
	private static void drop(char[][] map, int N, int M, int space) {
		for(int j = 0; j < M; j++) {			
			for(int i = N - 1; i >= 0; i--) {
				if(isVisited[i][j] != 1) {	
					if(map[i][j] == MINERAL) {			// 덩어리가 공중에 떠있는 경우
						map[i][j] = EMPTY;				// 해당 덩어리를 비우고
						map[i + space][j] = MINERAL;	// 간격에 맞추어 덩어리를 밑으로 내려줌
					}
				}
			}
		}
	}
}
