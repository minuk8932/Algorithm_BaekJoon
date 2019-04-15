package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 17142번: 연구소 3
 *
 *	@see https://www.acmicpc.net/problem/17142/
 *
 */
public class Boj17142 {
	private static ArrayList<String> query = new ArrayList<>();
	private static boolean[] visit;
	private static int empty = 0;
	
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0, COL = 1;
	private static final int INF = Integer.MAX_VALUE;
	
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
		
		int[][] lab = new int[N][N];
		LinkedList<Point> tmp = new LinkedList<>();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				lab[i][j] = Integer.parseInt(st.nextToken());
				
				if(lab[i][j] == 2) tmp.add(new Point(i, j));
				if(lab[i][j] == 0) empty++;						// 빈칸
			}
		}
		
		int size = tmp.size();
		int index = 0;
		Point[] virus = new Point[size];
		
		while(!tmp.isEmpty()) {
			Point idx = tmp.remove();
			virus[index++] = new Point(idx.row, idx.col);
		}
		
		for(int i = 0; i < size; i++) {
			visit = new boolean[size];
			backTracking (size, M, i, 0, i + "");		// 확산 바이러스 순서 정하기
		}

		System.out.println(bfs(N, virus, lab));
	}
	
	private static void backTracking(int n, int m, int current, int count, String str) {
		if(count == m - 1) {
			query.add(str);
			return;
		}
		
		if(visit[current]) return;
		visit[current] = true;
		
		for(int next = current + 1; next < n; next++) {
			if(visit[next]) continue;
			
			backTracking(n, m, next, count + 1, str + " " + next);
			visit[next] = false;
		}
	}
	
	private static int bfs(int n, Point[] src, int[][] map) {
		int result = INF;
		
		for(String qu: query) {			
			int[][] isVisited = new int[n][n];
			
			for(int i = 0; i < n; i++) {
				Arrays.fill(isVisited[i], -1);
			}
			
			Queue<Point> q = new LinkedList<>();
			
			StringTokenizer st = new StringTokenizer(qu);
			while(st.hasMoreTokens()) {
				int idx = Integer.parseInt(st.nextToken());
				q.offer(new Point(src[idx].row, src[idx].col));		// 확산 시킬 바이러스부터 큐에 저장
				
				isVisited[src[idx].row][src[idx].col] = 0;
			}
			
			int spread = 0;
			int time = 0;
			
			while(!q.isEmpty()) {
				Point current = q.poll();
					
				for(final int[] DIRECTION: DIRECTIONS) {
					int nextRow = current.row + DIRECTION[ROW];
					int nextCol = current.col + DIRECTION[COL];
							
					if(nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n) continue;
					if(map[nextRow][nextCol] == 1 || isVisited[nextRow][nextCol] != -1) continue;
					isVisited[nextRow][nextCol] = isVisited[current.row][current.col] + 1;
					
					if(map[nextRow][nextCol] == 0) {
						spread++;								// 빈칸 확산량
						time = isVisited[nextRow][nextCol];		// 그때 걸린 시간
					}
					
					q.offer(new Point(nextRow, nextCol));
				}
			}
			
			if(spread == empty) {
				result = Math.min(result, time);
			}
		}
		
		return result == INF ? -1: result;
	}
}
