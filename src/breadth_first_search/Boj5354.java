package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * 	@author minchoba
 *	백준 5354번: J박스
 *
 *	@see https://www.acmicpc.net/problem/5354/
 *
 */
public class Boj5354 {
	private static final String NEW_LINE = "\n";
	
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	private static final char SHARP = '#';
	private static final char J = 'J';
	
	private static boolean[][] isVisited = null;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			int n = Integer.parseInt(br.readLine());
			
			isVisited = new boolean[n][n];
			bfs(n);
			
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					if(isVisited[i][j]) {		// 진리 배열 값이 참이면 J 아니면 #을 버퍼에 담음
						sb.append(J);
					}
					else {
						sb.append(SHARP);
					}
				}
				sb.append(NEW_LINE);
			}
			
			sb.append(NEW_LINE);
		}
		
		System.out.println(sb.toString());		// 결과값 한번에 출력
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
	 * 너비 우선 탐색 메소드
	 * @param N
	 */
	private static void bfs(int N) {
		for(int row = 1; row < N - 1; row++) {
			for(int col = 1; col < N - 1; col++) {
				if(!isVisited[row][col]) {
					isVisited[row][col] = true;			// 가장자리를 제외한 내부 점 중 하나를 참으로
					
					Queue<Point> q = new LinkedList<>();
					q.offer(new Point(row, col));
					
					while(!q.isEmpty()) {
						Point current = q.poll();
						
						for(final int[] DIRECTION : DIRECTIONS) {
							int nextRow = current.row + DIRECTION[ROW];
							int nextCol = current.col + DIRECTION[COL];
							
							if(nextRow > 0 && nextRow < N - 1 && nextCol > 0 && nextCol < N - 1) {
								if(!isVisited[nextRow][nextCol]) {		// 너비 우선 탐색하며 다음 정점 또한 가장자리를 제외하고 참으로 변경
									isVisited[nextRow][nextCol] = true;
									
									q.offer(new Point(nextRow, nextCol));
								}
							}
						}
					}
				}
			}
		}
	}
}
