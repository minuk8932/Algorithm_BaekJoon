package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 16236번: 아기 상어
 *
 *	@see https://www.acmicpc.net/problem/16236/
 *
 */
public class Boj16236 {
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	private static Point fish = new Point(-1, -1);
	private static Point start = new Point(-1, -1);
	private static int[][] map;
	private static int result, count;
	private static int limit = 2;
	
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
		
		map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] == 9) {
					start = new Point(i, j);
					map[i][j] = 0;
				}
			}
		}
			
		while(getClosestFish(N, map)) {
			if(fish.row == -1 && fish.col == -1) break;		// 다음에 먹을 물고기가 없는 경우
			int cost = bfs(N, fish);
				
			if(count == limit) {			// 나이만큼 물고기를 먹은 경우
				limit++;
				count = 0;
			}
				
			result += cost;					// 이동 거리 +
			fish = new Point(-1, -1);
		}
		
		System.out.println(result);
	}
	
	private static boolean getClosestFish(int n, int[][] arr) {
		int min = Integer.MAX_VALUE;
		boolean isFeed = false;
		
		int[][] isVisited = new int[n][n];
		Queue<Point> q = new LinkedList<>();
		
		q.offer(start);
		isVisited[start.row][start.col] = 1;
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			
			for(final int[] DIRECTION: DIRECTIONS) {
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
				
				if(nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n) continue;
				if(map[nextRow][nextCol] > limit || isVisited[nextRow][nextCol] != 0) continue;
				isVisited[nextRow][nextCol] = isVisited[current.row][current.col] + 1;
				
				if(limit > map[nextRow][nextCol] && map[nextRow][nextCol] > 0) {
					min = Math.min(isVisited[nextRow][nextCol], min);			// 가장 가까운 물고기의 거리 저장
					isFeed = true;
				}
				
				q.offer(new Point(nextRow, nextCol));
			}
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(isVisited[i][j] == min && map[i][j] > 0 && limit > map[i][j]) {
					fish = new Point(i, j);			// 가장 가까운 물고기 중 좌측 상단에 가까운 물고기 부터
					return isFeed;
				}
			}
		}

		return isFeed;			// 물고기가 없는 경우
	}
	
	private static int bfs(int n, Point target) {
		int[][] isVisited = new int[n][n];
		
		Queue<Point> q = new LinkedList<>();
		q.offer(start);
		
		isVisited[start.row][start.col] = 1;
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			
			for(final int[] DIRECTION: DIRECTIONS) {
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
				
				if(nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n) continue;
				if(map[nextRow][nextCol] > limit || isVisited[nextRow][nextCol] != 0) continue;
				isVisited[nextRow][nextCol] = isVisited[current.row][current.col] + 1;
				
				if(nextRow == target.row && nextCol == target.col && limit > map[nextRow][nextCol]) {
					map[nextRow][nextCol] = 0;
					start = new Point(nextRow, nextCol);		// 자신보다 작은 물고기를 먹는 경우, 먹힌 물고기 위치부터 다시 시작
					count++;
					
					return isVisited[nextRow][nextCol] - 1;		// 먹은 물고기까지의 거리
				}
				
				q.offer(new Point(nextRow, nextCol));
			}
		}
		
		return -1;			// 먹을게 없더라..
	}
}
