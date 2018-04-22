package depth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 * 백준 11403번 : 경로찾기
 * 
 * 	@see https://www.acmicpc.net/problem/11403/
 * 
 */
public class Boj11403 {
	private static final String SPACE = " ";
	private static final String END_LINE = "\n";
	
	private static int N = 0;
	private static int[][] map = null;
	private static boolean[][] isVisited = null;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		for(int i = 0; i < N; i++){
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			
			for(int j = 0; j < N; j++){
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		isVisited = new boolean[N][N];
		
		for(int row = 0; row < N; row++){
			for(int col = 0; col < N; col++){
				if(map[row][col] == 1 && !isVisited[row][col]){	// 경로가 존재하고 방문하지 않은 경우
					dfs(new Point(row, col));					// 깊이 우선 탐색 알고리즘 실행
				}
			}
		}
		
		// 결과 메소드를 통해 결과 출력
		System.out.println(res());
	}
	
	/**
	 * 정점 이너클래스
	 * 	@author minchoba
	 *	
	 */
	private static class Point {
		int row;
		int col;
		
		public Point(int row, int col){
			this.row = row;
			this.col = col;
		}
	}
	
	/**
	 * 깊이 우선 탐색 알고리즘
	 * 	@param p : 탐색할 정점
	 */
	private static void dfs(Point p){
		isVisited[p.row][p.col] = true;				// 해당 정점을 방문한것으로 표시
		
		for(int next = 0; next < N; next++){
			if(map[p.row][p.col] == 1 && map[p.col][next] == 1){		// 현위치(r, c)에 길이 존재하고, 이와 연결된 길(c, next)에도 경로가 존재하면
				if(!isVisited[p.row][next]){
					isVisited[p.row][next] = true;				
					map[p.row][next] = 1;					// 합한 경로(r, next)에도 길이 존재함으로 바꿔줌
					
					dfs(new Point(p.row, next));		// 합 경로를 기준으로 다시 깊이 우선 탐색
				}
			}
		}
	}
	
	/**
	 * 결과 출력 메소드
	 * 	@return : 버퍼에 담아둔 경로의 형태를 문자열 형태로 반환
	 */
	private static String res(){
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				sb.append(map[i][j]).append(SPACE);		// 버퍼를 통한 경로 저장
			}
			sb.append(END_LINE);
		}
		
		return sb.toString();		// 결과 문자열 반환
	}
}
