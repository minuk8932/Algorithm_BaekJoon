package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1926번: 그림
 *
 *	@see https://www.acmicpc.net/problem/1926/
 *
 */
public class Boj1926 {
	private static StringBuilder sb = new StringBuilder();
	private static boolean[][] map = null;
	
	private static final String PAINT = "1";
	private static final String NEW_LINE = "\n";
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		map = new boolean[n][m];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < m; j++) {
				if(PAINT.equals(st.nextToken())) {		// 숫자 1이 들어온 경우만 해당 배열을 true로 변경
					map[i][j] = true;
				}
			}
		}
		
		System.out.println(bfs(n, m));		// 너비 우선 탐색 메서드를 이용한 결과값 출력
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
	 * @param N: 세로
	 * @param M: 가로
	 * @return: 그림의 갯수와 그림의 최대 크기
	 */
	private static String bfs(int N, int M) {
		int paintCnt = 0, maxSize = 0;
		boolean[][] isVisited = new boolean[N][M];
		
		for(int row = 0; row < N; row++) {
			for(int col = 0; col < M; col++) {
				if(!isVisited[row][col] && map[row][col]) {		// 아직 탐색x, 배열에 그림이 존재하는 경우
					isVisited[row][col] = true;
					
					int tmp = 1;	// 그림의 최대 크기를 구할 변수
					paintCnt++;		// 그림의 갯수 +1
					
					Queue<Point> q = new LinkedList<>();
					q.offer(new Point(row, col));
					
					while(!q.isEmpty()) {
						Point current = q.poll();
						
						for(final int[] DIRECTION : DIRECTIONS) {
							int nextRow = current.row + DIRECTION[ROW];
							int nextCol = current.col + DIRECTION[COL];
							
							if(nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < M) {
								if(!isVisited[nextRow][nextCol] && map[nextRow][nextCol]) {
									isVisited[nextRow][nextCol] = true;
									
									tmp++;
									q.offer(new Point(nextRow, nextCol));
								}
							}
						}
					}
					
					maxSize = Math.max(maxSize, tmp);		// 최종적으로 가장 큰 그림의 크기 값을 가져옴
				}
			}
		}
		
		sb.append(paintCnt).append(NEW_LINE).append(maxSize);	// 그림의 수와 크기를 버퍼에 담고
		return sb.toString();			// 문자열로 반환
	}
}
