package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 13565번: 침투
 *
 *	@see https://www.acmicpc.net/problem/13565/
 *
 */
public class Boj13565 {
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	private static final int CURRENT = 0;
	private static final int FIBER = 2;
	
	private static int[][] map = null;
	private static Point[] finish = null;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Point[] start = new Point[M];
		finish = new Point[M];
		int sidx = 0, fidx = 0;
		
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j) - '0';
				
				if(i == 0 && map[i][j] == CURRENT) {		// 시작 지점 저장
					start[sidx++] = new Point(i, j);
				}
				
				if(i == N - 1 && map[i][j] == CURRENT) {	// 종료 지점 저장
					finish[fidx++] = new Point(i, j);
				}
			}
		}
		
		System.out.println(bfs(N, M, start));		// 너비 우선 탐색 메소드를 통한 결과 출력
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
	 * 
	 */
	private static String bfs(int n, int m, Point[] start) {
		boolean[][] isVisited = new boolean[n][m];
		
		for(Point s: start) {
			if(s == null) continue;
			if(isVisited[s.row][s.col]) continue;
			
			Queue<Point> q = new LinkedList<>();
			q.offer(new Point(s.row, s.col));
			
			isVisited[s.row][s.col] = true;
			map[s.row][s.col] = FIBER;
			
			while(!q.isEmpty()) {
				Point current = q.poll();
				
				for(final int[] DIRECTION: DIRECTIONS) {
					int nextRow = DIRECTION[ROW] + current.row;
					int nextCol = DIRECTION[COL] + current.col;
					
					if(nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < m) {
						if(!isVisited[nextRow][nextCol] && map[nextRow][nextCol] == CURRENT) {
							isVisited[nextRow][nextCol] = true;		// 섬유 물질이 흐를 수 있는 곳은 섬유 물질로 채워줌
							map[nextRow][nextCol] = FIBER;
							
							q.offer(new Point(nextRow, nextCol));
						}
					}
				}
			}
		}
		
		return isPercolate(n, m) ? "YES" : "NO";		// 침투가 완료되었으면 YES, 아니면 NO를 반환
	}
	
	/**
	 * 침투 결과 메소드
	 * 
	 */
	private static boolean isPercolate(int n, int m) {
		for(Point f: finish) {
			if(f == null) continue;
			if(map[f.row][f.col] == FIBER) return true;		// 끝 점에 섬유 물질이 존재 할 경우 참
		}
		
		return false;		// 끝 점을 모두 방문 했는데도 물질이 없는 경우 거짓
	}
}
