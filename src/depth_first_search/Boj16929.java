package depth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 16929번: Two Dots
 *
 *	@see https://www.acmicpc.net/problem/16929/
 *
 */
public class Boj16929 {
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	private static boolean[][] isVisited;
	private static boolean result;
	
	private static char[][] map;
	private static int N, M, count = 0;
	
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
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		
		System.out.println(search());
	}
	
	private static String search() {
		isVisited = new boolean[N][M];				// 이미 해당 경로에 사이클이 없다고 확정나면 포함된 경로 모두 x
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				result = false;
				dfs(new Point(i, j), map[i][j]);
				
				if(result) return "Yes";			// 사이클 존재시
			}
		}
		
		return "No";
	}
	
	private static void dfs(Point current, char target) {
		if(isVisited[current.row][current.col]) return;
		isVisited[current.row][current.col] = true;
		
		count = 0;
		LinkedList<Point> list = new LinkedList<>();
		
		for(final int[] DIRECTION: DIRECTIONS) {
			int nextRow = current.row + DIRECTION[ROW];
			int nextCol = current.col + DIRECTION[COL];
			
			if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M) continue;
			if(map[nextRow][nextCol] != target) continue;
			
			if(isVisited[nextRow][nextCol]) {				// 이미 방문한 칸이 2개 이상인 경우 => 사이클
				count++;
				continue;
			}
			
			list.add(new Point(nextRow, nextCol));			// 탐색 할 수 있는 경로 저장
		}
		
		if(count >= 2) {
			result = true;
			return;
		}
		
		for(Point next: list) {
			dfs(next, target);
		}
	}
}
