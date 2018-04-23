package depth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2468번 : 안전 영역
 *
 *	@https://www.acmicpc.net/problem/2468/
 *
 */
public class Boj2468 {
	private static final String SPACE = " ";
	
	private static int[][] map = null;
	private static boolean[][] isVisited = null;
	
	private static int maxVal = 0;
	private static int N = 0;
	
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		for(int i = 0; i < N; i++){
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			for(int j = 0; j < N; j++){
				map[i][j]= Integer.parseInt(st.nextToken());
				
				if(map[i][j] > maxVal){				// 안전영역의 최대 높이를 구함
					maxVal = map[i][j];
				}
			}
		}
		
		int safety = 0;
		
		for(int start = 0; start < maxVal; start++){		// 비가 오지 않을 경우부터 최대 영역의 높이까지 비가올 경우를 반복문으로 계산
			isVisited = new boolean[N][N];
			int tmp = 0;
			
			for(int row = 0; row < N; row++){
				for(int col = 0; col < N; col++){
					if(!isVisited[row][col] && map[row][col] > start){	// 빗물이 찬 높이보다 높은 경우가 안전 영역임
						dfs(new Point(row ,col), start);							// 깊이 우선 탐색 알고리즘 실행
						
						tmp++;							// 안전영역의 갯수 +1
					}
				}
			}
			
			if(tmp > safety){			// 안전 영역 갯수중 최댓값을 저장
				safety = tmp;
			}
		}
		
		System.out.println(safety);	// 안전 영역의 갯수 출력
	}
	
	/**
	 * 정점 이너 클래스
	 * @author minchoba
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
	 * 깊이 우선 탐색 메소드
	 * @param p	: 탐색할 안전영역
	 * @param limit : 비가 찬 정도
	 */
	private static void dfs(Point p, int limit){
		isVisited[p.row][p.col] = true;
		
		for(final int[] DIRECTION : DIRECTIONS){
			int nextRow = p.row + DIRECTION[ROW];
			int nextCol = p.col + DIRECTION[COL];
			
			if(nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < N){
				if(!isVisited[nextRow][nextCol] && map[nextRow][nextCol] > limit){		// 방문하지 않은 것 중 안전영역 인것을 골라줌
					isVisited[nextRow][nextCol] = true;
					
					dfs(new Point(nextRow, nextCol), limit);
				}
			}
		}
	}
}
