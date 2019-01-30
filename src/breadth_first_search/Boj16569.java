package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 16569번: 화산쇄설류
 *
 *	@see https://www.acmicpc.net/problem/16569/
 *
 */
public class Boj16569 {
	private static boolean [][] isRed;
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	private static final String SPACE = " ";
	
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
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int V = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		Point s = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		
		int[][] map = new int[M + 1][N + 1];
		
		for(int i = 1; i < M + 1; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j < N + 1; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		ArrayList<Point>[] volca = new ArrayList[201];
		for(int i = 0; i < 201; i++){
			volca[i] = new ArrayList<>();
		}
		
		isRed = new boolean[M + 1][N + 1];
		for(int i = 0; i < V; i++) {
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			int timer = Integer.parseInt(st.nextToken());
			
			volca[timer].add(new Point(row, col));
			isRed[row][col] = true;						// 화산 분출구 위치
		}
		
		System.out.println(bfs(M, N, V, map, volca, s));
	}
	
	private static StringBuilder bfs(int n, int m, int v, int[][] arr, ArrayList<Point>[] hole, Point start) {
		StringBuilder sb = new StringBuilder();
		int[][] isVisited = new int[n + 1][m + 1];
		int max = arr[start.row][start.col], minTime = 0;
		
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(start.row, start.col));
		isVisited[start.row][start.col] = 1;
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			if(isVisited[current.row][current.col] < 201) covering(n, m, v, hole, isVisited[current.row][current.col]);
			
			for(final int[] DIRECTION: DIRECTIONS) {
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
				
				if(nextRow < 1 || nextRow > n || nextCol < 1 || nextCol > m) continue;
				if(isVisited[nextRow][nextCol] != 0 || isRed[nextRow][nextCol]) continue;		// 화산 쇄설류가 덮쳤거나, 이미 방문한 경우
				isVisited[nextRow][nextCol] = isVisited[current.row][current.col] + 1;
				
				if(arr[nextRow][nextCol] > max) max = arr[nextRow][nextCol];				
				q.offer(new Point(nextRow, nextCol));
			}
		}
		
		minTime = getMinTime(n, m, arr, isVisited, max);
		
		sb.append(max).append(SPACE).append(minTime == Integer.MAX_VALUE ? 0 : minTime);
		return sb;
	}
	
	private static int getMinTime(int n, int m, int[][] arr, int[][] visit, int maxPos) {
		int min = Integer.MAX_VALUE;
		
		for(int i = 1; i < n + 1; i++) {
			for(int j = 1; j < m + 1; j++) {
				if(arr[i][j] == maxPos) {
					if(visit[i][j] != 0 && visit[i][j] - 1 < min) min = visit[i][j] - 1;	// 방문한 곳에서 최대 높이를 갖는 지역의 최소 시간
				}
			}
		}
		
		return min;
	}
	
	private static boolean isCovered(int holeRow, int holeCol, int row, int col, int time) {
		return time >= Math.abs(holeRow - row) + Math.abs(holeCol - col) ? true: false;
	}
	
	private static void covering(int n, int m, int v, ArrayList<Point>[] hole, int timer) {
		for(int t = 0; t < timer; t++) {
			for(Point pair: hole[t]) {
				for(int row = 1; row < n + 1; row++) {			// 시간대 별로 각 지도의 위치에 맞춰 화산을 덮을지 말지 결정
					for(int col = 1; col < m + 1; col++) {
						if(isRed[row][col]) continue;
						isRed[row][col] = isCovered(row, col, pair.row, pair.col, timer - t);
					}
				}
			}
		}
	}
}
