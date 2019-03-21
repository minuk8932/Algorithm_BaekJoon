package breadth_first_search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 * 	백준 14503번: 로봇 청소기
 * 
 * 	@see https://www.acmicpc.net/problem/14503/
 *	
 */
public class Boj14503 {
	private static final int[][] DIRECTIONS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	private static final int CLEANED = 2;
	private static final int BLOCK = 1;
	
	private static class Point{
		int row;
		int col;
		
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < N; i++){
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < M; j++){
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(bfs(N, M, map, new Point(r, c), d));
	}
	
	private static int bfs(int n, int m, int[][] arr, Point start, int dir) {
		int result = 1;
		
		Queue<Point> q = new LinkedList<>();
		q.offer(start);	
		
		arr[start.row][start.col] = CLEANED;
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			int nextDir = (dir + 3) % 4;							// 현재 움직인 방향에서 왼쪽 보기
			int nextRow = current.row + DIRECTIONS[nextDir][ROW];
			int nextCol = current.col + DIRECTIONS[nextDir][COL];
			
			int loop = 0;
			
			while(arr[nextRow][nextCol] == BLOCK || arr[nextRow][nextCol] == CLEANED) {		// 사방 검사
				if(loop == 4) break;
				
				nextDir = (nextDir + 3) % 4;
				nextRow = current.row + DIRECTIONS[nextDir][ROW];
				nextCol = current.col + DIRECTIONS[nextDir][COL];
				
				loop++;
			}
			
			if(loop == 4) {											// 사방이 막힌 경우
				nextRow = current.row + DIRECTIONS[dir][ROW] * -1;	// 뒷길 확인
				nextCol = current.col + DIRECTIONS[dir][COL] * -1;

				if(arr[nextRow][nextCol] == BLOCK) break;
				q.offer(new Point(nextRow, nextCol));				// 뒷길이 벽으로 막혀있지 않으면 후진
			}
			else {
				arr[nextRow][nextCol] = CLEANED;		// 청소 안한 경우
				dir = nextDir;							// 움직인 방향으로 방향 갱신
				result++;
				
				q.offer(new Point(nextRow, nextCol));
			}
		}
		
		return result;
	}
}