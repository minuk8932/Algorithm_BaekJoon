package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 16234번: 인구 이동
 *
 *	@see https://www.acmicpc.net/problem/16234/
 *
 */
public class Boj16234 {
	private static final int[][] DIRECTIONS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	private static int[][] map;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int count = 0;
		
		while(bfs(N, L, R)) {
			count++;
		}
		
		System.out.println(count);
	}
	
	private static class Point{
		int row;
		int col;
		
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	private static boolean bfs(int n, int l, int r) {
		int[][] isVisited = new int[n][n];
		int[] total = new int[n * n + 1];
		int[] count = new int[n * n + 1];
		boolean pass = false;
		int seq = 0;
		
		for(int row = 0; row < n; row++) {
			for(int col = 0; col < n; col++) {
				if(isVisited[row][col] != 0) continue;
				seq++;
				
				Queue<Point> q = new LinkedList<>();
				q.offer(new Point(row, col));
				
				isVisited[row][col] = seq;
				total[seq] += map[row][col];
				count[seq]++;
				
				while(!q.isEmpty()) {
					Point current = q.poll();
					
					for(final int[] DIRECTION: DIRECTIONS) {
						int nextRow = current.row + DIRECTION[ROW];
						int nextCol = current.col + DIRECTION[COL];
						
						if(nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n) continue;
						if(isVisited[nextRow][nextCol] == 0 && isUnited(l, r, map[current.row][current.col], map[nextRow][nextCol])) {
							isVisited[nextRow][nextCol] = seq;
							total[seq] += map[nextRow][nextCol];		// 국경이 열리는 나라의 총 인구수
							count[seq]++;								// 그때의 나라수

							pass = true;
							q.offer(new Point(nextRow, nextCol));
						}
					}
				}
			}
		}
		
		if(!pass) return false;								// 국경이 열리지 않았다면
		makeAveragePopulation(n, total, count, isVisited);
		
		return true;
	}
	
	private static boolean isUnited(int lowerBound, int upperBound, int a, int b) {		// 두 나라 인구 차이가 L, R에 포함 되는가?
		int diff = Math.abs(a - b);
		return diff >= lowerBound && diff <= upperBound ? true : false;
	}
	
	private static void makeAveragePopulation(int n, int[] sum, int[] cnt, int[][] isVisited) {		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				int idx = isVisited[i][j];
				map[i][j] = sum[idx] / cnt[idx];
			}
		}
	}
}
