package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 13901번: 로봇
 *
 *	@see https://www.acmicpc.net/problem/13901/
 *
 */
public class Boj13901 {
	private static char[][] map = null;
	
	private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	private static final int ROTATE = 4;
	private static final char BLOCK = 'x';
	private static final char EMPTY = '*';
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		for(int i = 0; i < R; i++) Arrays.fill(map[i], EMPTY);
		
		int blocks = Integer.parseInt(br.readLine());
		
		while(blocks-- > 0) {							// 장애물 배치
			st = new StringTokenizer(br.readLine());
			map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = BLOCK;
		}
		
		st = new StringTokenizer(br.readLine());
		Point s = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));	// 시작 정점
		
		int[] way = new int[ROTATE];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < way.length; i++) {			// 움직일 방향을 배열에 저장
			way[i] = Integer.parseInt(st.nextToken());
		}
		
		Point res = bfs(R, C, s, way);					// 너비 우선 탐색을 통해 결과를 정점 변수에 저장 후
		System.out.println(res.row + SPACE + res.col);	// 행, 열 순으로 출력
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
	private static Point bfs(int n, int m, Point start, int[] move) {
		boolean[][] isVisited = new boolean[n][m];
		Point finish = new Point(-1, -1);			// 결과 변수
		int seq = 0;
		int escapeCount = 0;
		
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(start.row, start.col));	// 큐에 시작 정점을 담고 시작 방향을 direct에 가져옴
		isVisited[start.row][start.col] = true;
		int direct = move[seq % ROTATE] - 1;
		
		boolean isBlocked = false;			// 막힌적이 있는가?
		
		while(true) {
			Point current = q.poll();
			
			if(isBlocked) {					// 막힌적이 있다면 다음 이동 방향으로 바꿔줌
				seq += 1;
				direct = move[seq % ROTATE] - 1;
			}
			
			isBlocked = true;
			
			int nextRow = current.row + DIRECTIONS[direct][ROW];
			int nextCol = current.col + DIRECTIONS[direct][COL];
			
			if(nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < m) {
				if(!isVisited[nextRow][nextCol] && map[nextRow][nextCol] != BLOCK) {
					isVisited[nextRow][nextCol] = true;
					
					isBlocked = false;		// 막히지 않고 가는 경우, 탈출 누적 수를 0으로 갱신
					escapeCount = 0;
					
					q.offer(new Point(nextRow, nextCol));	// 큐에 다음 정점 넣고 다시 반복
					continue;
				}
			}
			
			// 길이 막힌 경우
			
			if(escapeCount == ROTATE) {							// 이 정점에서 4번 막혔다면 최종 정점을 저장하고 반복문 종료
				finish = new Point(current.row, current.col);
				break;
			}
			
			escapeCount++;									// 아니면 탈출 누적수 +1, 그리고 다음 방향을 모색하기위해 현 위치를 다시 큐에 담아줌
			q.offer(new Point(current.row, current.col));
		}
		
		return finish;
	}
}
