package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 16768번: Mooyo Mooyo
 *
 *	@see https://www.acmicpc.net/problem/16768
 *
 */
public class Boj16768 {
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0, COL = 1;
	
	private static final String NEW_LINE = "\n";
	
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
		int K = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][10];
		int max = 0;
		
		for(int i = 0; i < N; i++) {
			String input = br.readLine();
			
			for(int j = 0; j < 10; j++) {
				map[i][j] = input.charAt(j) - '0';
				if(max < map[i][j]) max = map[i][j];
			}
		}
		
		System.out.println(mooyomooyo(N, K, map, max));
	}
	
	private static String mooyomooyo(int n, int k, int[][] map, int size) {
		boolean flag = true;
		
		while(flag) {
			boolean[][] visit = new boolean[n][10];
			ArrayList<Point>[] pop = new ArrayList[size + 1];
			for(int i = 0; i < pop.length; i++) {
				pop[i] = new ArrayList<>();
			}
			
			int[] count = new int[size + 1];
			
			flag = false;
			
			for(int row = 0; row < n; row++) {
				for(int col = 0; col < 10; col++) {
					if(map[row][col] == 0 || visit[row][col]) continue;
					int src = map[row][col];
					int[] check = new int[size + 1];
					
					ArrayList<Point> save = new ArrayList<>();
					Queue<Point> q = new LinkedList<>();
					
					check[src]++;									// count adjacent
					visit[row][col] = true;
					save.add(new Point(row, col));					// save adjacent
					q.offer(new Point(row, col));
					
					while(!q.isEmpty()) {
						Point current = q.poll();
						
						for(final int[] DIRECTION: DIRECTIONS) {
							int nextRow = current.row + DIRECTION[ROW];
							int nextCol = current.col + DIRECTION[COL];
							
							if(nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= 10) continue;
							if(visit[nextRow][nextCol] || map[nextRow][nextCol] != src) continue;
							visit[nextRow][nextCol] = true;

							check[src]++;							
							save.add(new Point(nextRow, nextCol));
							
							q.offer(new Point(nextRow, nextCol));
						}
					}

					if(check[src] >= k) {				// save promisings
						pop[src].addAll(save);
						count[src] += check[src];
					}
				}
			}
			
			for(int i = 1; i < count.length; i++) {
				if(count[i] < k) continue;
				flag = true;

				for(Point p: pop[i]) {
					map[p.row][p.col] = 0;				// erase pop
				}
			}
			
			if(!flag) break;
			
			for(int row = n - 1; row >= 0; row--) {
				for(int col = 0; col < 10; col++) {
					if(map[row][col] == 0) continue;
					int idx = row + 1;
					
					while(idx <= n - 1 && map[idx][col] == 0) {			// drop
						idx++;
					}
					
					if(idx - 1 != row) {
						map[idx - 1][col] = map[row][col];
						map[row][col] = 0;
					}
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < 10; j++) {
				sb.append(map[i][j]);
			}
			sb.append(NEW_LINE);
		}
		
		return sb.toString();
	}
}
