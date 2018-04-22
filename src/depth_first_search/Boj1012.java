package depth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1012번 : 유기농 배추
 *
 *	@see https://www.acmicpc.net/problem/1012/
 *
 */
public class Boj1012 {
	private static final String SPACE = " ";
	private static final String END_LINE = "\n";
	
	private static int M = 0;
	private static int N = 0;
	private static int K = 0;
	private static int[][] map = null;
	private static boolean[][] isVisited = null;
	
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	private static final int CABBAGE = 1;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		while(T-- > 0){
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			M = Integer.parseInt(st.nextToken());		// 가로
			N = Integer.parseInt(st.nextToken());		// 세로
			K = Integer.parseInt(st.nextToken());		// 심어진 배추의 갯수
			
			isVisited = new boolean[N][M];
			map = new int[N][M];
			for(int i = 0; i < K; i++){
				st = new StringTokenizer(br.readLine(), SPACE);
				int cabbageCol = Integer.parseInt(st.nextToken());
				int cabbageRow = Integer.parseInt(st.nextToken());
				
				map[cabbageRow][cabbageCol] = CABBAGE;					// 입력에 따라 배추 배치
			}
			
			int larva = 0;
			
			for(int row = 0; row < N; row++){
				for(int col = 0; col < M; col++){
					if(map[row][col] == CABBAGE && !isVisited[row][col]){	// 아직 세지 않은 배추인 경우
						dfs(new Point(row, col));				// 깊이 우선 탐색 알고리즘 실행
						
						larva++;										// 인접한 배추들에 한마리의 애벌레 배치
					}
				}
			}
			sb.append(larva).append(END_LINE);			// 테스트 케이스 당 필요한 애벌레 개수를 버퍼에 담고
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
	}
	
	/**
	 *	정점 이너 클래스
	 * 	@author minchoba
	 *	
	 */
	private static class Point{
		int row;
		int col;
		
		public Point(int row, int col){
			this.row = row;
			this.col = col;
		}
	}
	
	/**
	 * 	깊이 우선 탐색 알고리즘
	 * 	@param p : 배추의 위치를 찾을 정점
	 */
	private static void dfs(Point p){
		isVisited[p.row][p.col] = true;						// 하나 선택된 배추를 체크하고
		
		for(final int[] DIRECTION : DIRECTIONS){		// 인접한 배추가 있는지 확인하는데,
			int nextRow = p.row + DIRECTION[ROW];
			int nextCol = p.col + DIRECTION[COL];
			
			if(nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < M){					// 해당 맵의 영역내에서
				if(!isVisited[nextRow][nextCol] && map[nextRow][nextCol] == CABBAGE){	// 아직 체크되지 않은 배추일 경우
					isVisited[nextRow][nextCol] = true;														// 체크한 것으로 표시 후
					
					dfs(new Point(nextRow, nextCol));						// 다시 그 배추를 기준으로 깊이 우선 탐색
				}
			}
		}
	}
}
