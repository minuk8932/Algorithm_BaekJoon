package depth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1987번 : 알파벳
 *
 *	@see https://www.acmicpc.net/problem/1987
 *
 */
public class Boj1987 {
	private static final String SPACE = " ";
	
	private static int[] isVisited = null;
	private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	private static final int ROW = 0;
	private static final int COL = 1;
	private static int R = 0;
	private static int C = 0;
	private static int res = 0;
	
	private static char[][] alpha= null;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		alpha = new char[R][C];
		isVisited = new int[26];
		
		for(int i = 0; i < R; i++){
			String line = br.readLine();
			
			for(int j = 0; j < C; j++){
				alpha[i] = line.toCharArray();
			}
		}
		
		isVisited[alpha[0][0] - 65] = 1;
		
		dfs(new Point(0, 0));																// DFS 함수 실행
		
		System.out.println(res == 0 ? isVisited[alpha[0][0] - 65]: res);	// 0이 나오는 경우는 없는데, AAA 이런 식의 경우 0이 출력되므로, 그땐 1을 출력, 그외에는 res 출력
	}
	
	private static class Point{
		int row;
		int col;
		
		public Point(int row, int col){
			this.row = row;
			this.col = col;
		}
	}
	
	private static void dfs(Point p){
		
		for (final int[] DIRECTION : DIRECTIONS) {
			int nextRow = p.row + DIRECTION[ROW];
			int nextCol = p.col + DIRECTION[COL];

			if (0 <= nextRow && nextRow < R && 0 <= nextCol && nextCol < C) {
				if (isVisited[alpha[nextRow][nextCol] - 65] == 0) {								// 해당 알파벳의 값이 등장했었는지 체크
					isVisited[alpha[nextRow][nextCol] - 65] = isVisited[alpha[p.row][p.col] - 65] + 1;	// 처음 나온것이라면 이전 값에 +1 해서 경로당 걸린 시간을 구해둠
	
					dfs(new Point(nextRow, nextCol));
					
					res = Math.max(isVisited[alpha[nextRow][nextCol] - 65], res);			// 경로가 막힌경우 현재 경로까지의 최댓값을 뽑고
					isVisited[alpha[nextRow][nextCol] - 65] = 0;										// 0으로 하나씩 초기화 시키며 백트래킹
				}
			}
		}
	}
}
