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
 *	백준 1600번: 말이 되고픈 원숭이
 *
 *	@see https://www.acmicpc.net/problem/1600/
 *
 */
public class Boj1600 {
	private static final int[][] K_DIRECTIONS = {{1, 2}, {2, 1}, {-1, 2}, {2, -1}, {1, -2}, {-2, 1}, {-1, -2}, {-2, -1}};
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	private static final int INF = 100_001;
	
	private static int[][][] isVisited = null;
	private static Queue<Point> q = new LinkedList<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		
		isVisited = new int[H][W][K + 1];
		
		int[][] map = new int[H][W];
		for(int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < W; j++) {
				Arrays.fill(isVisited[i][j], INF);
			}
		}
		
		System.out.println(bfs(H, W, K, map));		// 결과 출력
	}
	
	private static class Point{
		int row;
		int col;
		int jump;
		
		public Point(int row, int col, int jump) {
			this.row = row;
			this.col = col;
			this.jump = jump;
		}
	}
	
	private static int bfs(int N, int M, int kMove, int[][] map) {
		q = new LinkedList<>();
		q.offer(new Point(0, 0, 0));
		
		for(int i = 0; i < kMove; i++) {		// 출발 지점에서 가능한 모든 점프 경우에 1 저장
			isVisited[0][0][i] = 1;
		}
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			
			if(current.row == N - 1 && current.col == M - 1) return isVisited[current.row][current.col][current.jump] - 1;
			
			for(int j = 0; j < kMove; j++) {
				for(final int[] DIRECTION: DIRECTIONS) {		// 원숭이 움직임
					jumping(N, M, map, current.row, current.col, current.jump, 0, DIRECTION);
				}
				
				if(current.jump < kMove) {
					for(final int[] DIRECTION: K_DIRECTIONS) {	// 말 움직임
						jumping(N, M, map, current.row, current.col, current.jump, 1, DIRECTION);
					}
				}
			}
		}
		
		// 목적지에 도착하지 못한 경우
		return -1;
	}
	
	private static void jumping(int N, int M, int[][] map, int row, int col, int jump, int moving, final int[] DIRECTION){	
		int nextRow = row + DIRECTION[ROW];
		int nextCol = col + DIRECTION[COL];
		int nextJump = jump + moving;
			
		if(nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < M) {
			if(map[nextRow][nextCol] != 1) {
				if(isVisited[nextRow][nextCol][nextJump] > isVisited[row][col][jump] + 1) {
					isVisited[nextRow][nextCol][nextJump] = isVisited[row][col][jump] + 1;
					
					q.offer(new Point(nextRow, nextCol, nextJump));
				}
			}
		}
	}
}
