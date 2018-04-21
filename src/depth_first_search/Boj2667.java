package depth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * 
 * 	@author minchoba
 *	백준 2667번 : 단지번호 구하기
 *
 *	@see https://www.acmicpc.net/problem/2667/
 *
 */
public class Boj2667 {
	private static final String END_LINE = "\n";
	
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	private static final char HOUSE = '1';
	
	private static int N = 0;
	private static int res = 1;
	private static int[][] isVisited = null;
	private static char[][] map = null;	
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new char[N][N];
		for(int i = 0; i < N; i++){
			map[i] = br.readLine().toCharArray();
		}
		
		ArrayList<Integer> homes = new ArrayList<>();		// 단지 별 집의 수를 담을 배열리스트
		
		isVisited = new int[N][N];
		int subd = 0;
		
		for(int row = 0; row < N; row++){
			for(int col = 0; col < N; col++){
				if(isVisited[row][col] == 0 && map[row][col] == HOUSE){		// 방문 전인 집인경우
					
					subd++;								// 단지수 +1
					dfs(new Point(row, col));	// dfs 알고리즘 실행
					homes.add(res);				// 임의의 한 단지내의 가구수
					
					res = 1;								// 가구수 초기화
				}
			}
		}
		
		Collections.sort(homes);						// 가구수 정렬
		
		StringBuilder sb = new StringBuilder();
		sb.append(subd).append(END_LINE);		// 버퍼에 단지의 갯수를 담고
		
		for(int i = 0; i < subd; i++){
			sb.append(homes.get(i)).append(END_LINE);	// 정렬된 가구수 또한 반복문을 통해 버퍼에 담음
		}
		
		System.out.println(sb.toString());			// 결과값 한번에 출력
	}
	
	/**
	 * 
	 * 	@author minchoba
	 *	정점 이너 클래스
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
	 * 깊이 우선 탐색 알고리즘
	 * 	@param p : 탐색을 시작 할 정점
	 */
	private static void dfs(Point p){
		isVisited[p.row][p.col] = 1;								// 방문함으로 표시
		
		for(final int[] DIRECTION : DIRECTIONS){
			int nextRow = p.row + DIRECTION[ROW];
			int nextCol = p.col + DIRECTION[COL];
			
			if(nextRow >= 0 && nextCol >= 0 && nextRow < N && nextCol < N){					// 범위내에서, 방문 안한 집인경우
				if(isVisited[nextRow][nextCol] == 0 && map[nextRow][nextCol] == HOUSE){
					isVisited[nextRow][nextCol] = 1;																// 방문함으로 표시
					
					res += isVisited[nextRow][nextCol];						// 가구수를 누적으로 구함, 깊이 우선 탐색이므로 하나씩 더해주면 단계별 갯수 측정 가능
					dfs(new Point(nextRow, nextCol));				// 다음 정점을 다시 깊이 우선 탐색
				}
			}
		}
	}
}
