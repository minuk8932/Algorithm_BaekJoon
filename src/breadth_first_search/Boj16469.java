package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 16469번: 소년 점프
 *
 *	@see https://www.acmicpc.net/problem/16469/
 *
 */
public class Boj16469 {
	private static final String NEW_LINE = "\n";
	
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0, COL = 1;
	private static final int INF = 1_000_000_007;
	
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
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		
		boolean[][] map = new boolean[R][C];
		for(int i = 0; i < R; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < C; j++) {
				map[i][j] = line.charAt(j) == '1' ? true: false;
			}
		}
		
		Point[] evils = new Point[3];
		for(int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			evils[i] = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
		}
		
		bfs(R, C, map, evils);
	}
	
	private static void bfs(int r, int c, boolean[][] arr, Point[] start) {
		int[][][] visit = new int[3][r][c];
		
		for(int s = 0; s < 3; s++) {
			Queue<Point> q = new LinkedList<>();
			q.offer(start[s]);
			
			visit[s][start[s].row][start[s].col] = 1;
			while(!q.isEmpty()) {
				Point current = q.poll();
				
				for(final int[] DIRECTION: DIRECTIONS) {
					int nextRow = current.row + DIRECTION[ROW];
					int nextCol = current.col + DIRECTION[COL];
					
					if(nextRow < 0 || nextRow >= r || nextCol < 0 || nextCol >= c) continue;
					if(visit[s][nextRow][nextCol] != 0 || arr[nextRow][nextCol]) continue;
					visit[s][nextRow][nextCol] = visit[s][current.row][current.col] + 1;			// 각 인원의 최단 경로 채우기
					
					q.offer(new Point(nextRow, nextCol));
				}
			}
		}
		
		System.out.println(getMinCost(r, c, visit));
	}
	
	private static String getMinCost(int r, int c, int[][][] visit) {
		StringBuilder sb = new StringBuilder();
		int min = INF;
		int[][] result = new int[r][c];
		
		for(int i = 0; i < r; i++) {
			for(int j = 0; j < c; j++) {
				if(visit[0][i][j] != 0 && visit[1][i][j] != 0 && visit[2][i][j] != 0) {
					int tmp = 0;
					
					tmp = Math.max(visit[0][i][j] - 1, tmp);			// 지점 (i, j)에 세명 모두 도달 한 경우 그때 가장 늦게온 사람의 시간
					tmp = Math.max(visit[1][i][j] - 1, tmp);
					tmp = Math.max(visit[2][i][j] - 1, tmp);
					
					min = Math.min(tmp, min);							// 그 세명이 모이는 시간의 최솟값
					if(tmp == min) result[i][j] = min;
				}
			}
		}
		
		if(min == INF) return sb.append(-1).toString();					// 세명이 모두 한곳에 모이지 못한 경우
		
		int count = 0;
		for(int i = 0; i < r; i++) {
			for(int j = 0; j < c; j++) {
				if(result[i][j] == min) count++;						// 세명이 모인곳의 최솟값이 여러 곳인경우
			}
		}
		
		return sb.append(min).append(NEW_LINE).append(count).toString();
	}
}
