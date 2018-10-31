package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15530번: Help the Princess!
 *
 *	@see https://www.acmicpc.net/problem/15530/
 *
 */
public class Boj15530 {
	private static final char PRINCESS = '@';
	private static final char SOLDIER = '$';
	private static final char ESCAPE_HATCH = '%';
	private static final char WALL = '#';
	
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1 ,0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	private static final String ESCAPE = "Yes";
	private static final String ARRESTED = "No";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[H][W];
		Point princessPoint = new Point(0, 0);
		LinkedList<Point> soldierPoint = new LinkedList<>();
		int soldierCnt = 0;
		
		for(int i = 0; i < H; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < W; j++) {
				map[i][j] = line.charAt(j);
				
				if(map[i][j] == PRINCESS) princessPoint = new Point(i, j);		// 공주의 위치
				
				if(map[i][j] == SOLDIER) {				// 군인들이 있는 위치
					soldierCnt++;
					soldierPoint.add(new Point(i , j));
				}
			}
		}
		
		String res = ESCAPE;
		
		if(soldierCnt > 0) {
			res = bfs(H, W, map, princessPoint, soldierPoint, soldierCnt);		// 군인의 수가 0보다 많은 경우 실행
		}
		
		System.out.println(res);		// 결과 값 출력
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
	 * 너비 우선 탐색 알고리즘
	 * @param h: 세로
	 * @param w: 가로
	 * @param m: 지도 정보
	 * @param pp: 초기 공주의 위치
	 * @param sp: 초기 군인들의 위치
	 * @param solCnt: 군인 수
	 * @return: 탈출 가능 ? Yes : No;
	 */
	private static String bfs(int h, int w, char[][] m, Point pp, LinkedList<Point> sp, int solCnt) {
		int[][] isVisitedP = new int[h][w];
		int soldiers = Integer.MAX_VALUE;
		
		Point esc = new Point(0, 0);
		
		// 공주의 움직임
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(pp.row, pp.col));
		isVisitedP[pp.row][pp.col] = 1;
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			
			for(final int[] DIRECTION: DIRECTIONS) {
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
				
				if(nextRow >= 0 && nextRow < h && nextCol >= 0 && nextCol < w) {			// 공주 방문 배열에 공주가 탈출하는데 걸린 시간을 저장
					if(m[nextRow][nextCol] != WALL && isVisitedP[nextRow][nextCol] == 0) {
						isVisitedP[nextRow][nextCol] = isVisitedP[current.row][current.col] + 1;
						
						if(m[nextRow][nextCol] == ESCAPE_HATCH) break;		// 탈출구에 도착시 반복문 종료
						
						q.offer(new Point(nextRow, nextCol));
					}
				}
			}
		}
		
		int[][] isVisited = new int[h][w];
		
		Point start = sp.removeFirst();		// 가장 앞의 군인의 움직임 조사
		
		q = new LinkedList<>();
		q.offer(new Point(start.row, start.col));
		isVisited[start.row][start.col] = 1;
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			
			for(final int[] DIRECTION: DIRECTIONS) {
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
				
				if(nextRow >= 0 && nextRow < h && nextCol >= 0 && nextCol < w) {
					if(m[nextRow][nextCol] != WALL && isVisited[nextRow][nextCol] == 0) {		// 방문배열에 해당 군인이 탈출하는데 걸린 시간을 저장
						isVisited[nextRow][nextCol] = isVisited[current.row][current.col] + 1;
						
						if(m[nextRow][nextCol] == ESCAPE_HATCH) {		// 탈출 구 도착시, 탈출지점 저장 및 걸린시간 최솟값을 구함
							esc = new Point(nextRow, nextCol);
							soldiers = Math.min(isVisited[nextRow][nextCol], soldiers);
							break;
						}
						
						q.offer(new Point(nextRow, nextCol));
					}
				}
			}
		}
		
		for(int s = 1; s < solCnt; s++) {		// 뒤에서부터 모든 군인의 움직임을 구함
			start = sp.removeLast();
			
			q = new LinkedList<>();
			q.offer(new Point(start.row, start.col));
			isVisited[start.row][start.col] = 1;
			
			while(!q.isEmpty()) {
				Point current = q.poll();
				
				if(isVisited[current.row][current.col] >= soldiers) break;	// 만약 현재 군인의 움직임이 군인의 최소 움직임보다 크다면 더이상 탐색할 필요 x
				
				for(final int[] DIRECTION: DIRECTIONS) {
					int nextRow = current.row + DIRECTION[ROW];
					int nextCol = current.col + DIRECTION[COL];
					
					if(nextRow >= 0 && nextRow < h && nextCol >= 0 && nextCol < w) {
						if(m[nextRow][nextCol] != WALL && 
								(isVisited[nextRow][nextCol] == 0 || isVisited[nextRow][nextCol] > isVisited[current.row][current.col] + 1)) {
							isVisited[nextRow][nextCol] = isVisited[current.row][current.col] + 1;
							
							if(m[nextRow][nextCol] == ESCAPE_HATCH) {						// 탈출구 도착시, 최솟값을 구함
								soldiers = Math.min(isVisited[nextRow][nextCol], soldiers);
								if(soldiers <= isVisitedP[nextRow][nextCol]) return ARRESTED;	// 그 최솟값이 공주의 움직임 보다 적으면 No 반환
							}
							
							q.offer(new Point(nextRow, nextCol));
						}
					}
				}
			}
		}
		
		return soldiers <= isVisitedP[esc.row][esc.col] ? ARRESTED : ESCAPE;		// 공주와 군인의 움직임 횟수를 비교해 값 반환
	}
}
