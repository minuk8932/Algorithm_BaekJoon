package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1743번: 음식물 피하기
 *
 *	@see https://www.acmicpc.net/problem/1743/
 *
 */
public class Boj1743 {
	private static boolean[][] map = null;
	
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		map = new boolean[N + 1][M + 1];
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = true;
		}
		
		System.out.println(bfs(N, M));		// 너비 우선 탐색 메소드를 통한 결과값 출력
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
	 * @param N: 가로
	 * @param M: 세로
	 * @return: 쓰레기더미 최댓값
	 */
	private static int bfs(int N, int M) {
		int max = 0;
		boolean[][] isVisited = new boolean[N + 1][M + 1];
		
		for(int row = 1; row < N + 1; row++) {
			for(int col = 1; col < M + 1; col++) {
				if(map[row][col] && !isVisited[row][col]) {		// 아직 확인하지 않은 쓰레기만 체크하며
					isVisited[row][col] = true;
					
					int waste = 1;
					
					Queue<Point> q = new LinkedList<>();
					q.offer(new Point(row, col));
					
					while(!q.isEmpty()) {
						Point current = q.poll();
						
						for(final int[] DIRECTION : DIRECTIONS) {
							int nextRow = current.row + DIRECTION[ROW];
							int nextCol = current.col + DIRECTION[COL];
							
							if(nextRow > 0 && nextRow < N + 1 && nextCol > 0 && nextCol < M + 1) {	// 인접한 쓰레기 갯수를 센 후
								if(!isVisited[nextRow][nextCol] && map[nextRow][nextCol]) {
									isVisited[nextRow][nextCol] = true;
									
									waste++;
									q.offer(new Point(nextRow, nextCol));
								}
							}
						}
					}
					
					max = Math.max(waste, max);		// 최댓값을 담고
				}
			}
		}
		
		
		return max;		// 반환
	}
}
