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
 *	백준 2842번: 집배원 한상덕
 *
 *	@see https://www.acmicpc.net/problem/2842/
 *
 */
public class Boj2842 {
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
	private static final int ROW = 0, COL = 1;
	private static final int INF = 1_000_001;
	
	private static final char POST_OFFICE = 'P', HOME = 'K';
	
	private static char[][] map;
	private static int[][] cost;
	
	private static Point s = new Point(-1, -1);
	private static ArrayList<Point> e = new ArrayList<>();
	
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
		int N = Integer.parseInt(br.readLine());
		
		map = new char[N][N];
		
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < N; j++) {
				map[i][j] = line.charAt(j);
				if(map[i][j] == POST_OFFICE) s = new Point(i, j);
				if(map[i][j] == HOME) e.add(new Point(i, j));
			}
		}
		
		cost = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < N; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(binarySearch(N));
	}
	
	private static int binarySearch(int n) {
		int result = INF;
		int size = e.size();
		
		for(int row = 0; row < n; row++) {
			for(int col = 0; col < n; col++) {
				int start = 0, end = INF;
		
				while(start <= end){
					int mid = (start + end) / 2;
					
					int min = cost[row][col], max = cost[row][col] + mid;			// 최소와 최대 고정
					if(cost[s.row][s.col] < min || cost[s.row][s.col] > max) {		// 시작점이 범위를 벗어난 경우
						start = mid + 1;
						continue;
					}
					
					int isDelivered = bfs(n, max, min);			// 모든 집에 배달 가능한가?
		
					if(isDelivered == size) {					// 가능
						end = mid - 1;
						if(result > mid) result = mid;		// 가능시 최소 피로도 저장
					}
					else {										// 불가
						start = mid + 1;
					}
				}
			}
		}
			
		return result;
	}
	
	private static int bfs(int n, int max, int min) {
		boolean[][] isVisited = new boolean[n][n];
		isVisited[s.row][s.col] = true;
		
		Queue<Point> q = new LinkedList<>();
		q.offer(s);
		
		int count = 0;
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			
			for(final int[] DIRECTION: DIRECTIONS) {
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
				
				if(nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n) continue;
				if(isVisited[nextRow][nextCol]|| min > cost[nextRow][nextCol] || cost[nextRow][nextCol] > max) continue;
				isVisited[nextRow][nextCol] = true;
				
				if(map[nextRow][nextCol] == HOME) count++;		// 집마다 배달 완료시
				
				q.offer(new Point(nextRow, nextCol));
			}
		}
		
		return count;
	}
}
