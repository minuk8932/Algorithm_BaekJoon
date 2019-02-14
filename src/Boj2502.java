import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj2502 {
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	private static final int INF = 1_000_000;
	
	private static final char POST_OFFICE = 'P';
	private static final char HOME = 'K';
	
	private static class Point{
		int row;
		int col;
		int idx;
		
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
		
		public Point(int row, int col, int idx) {
			this.row = row;
			this.col = col;
			this.idx = idx;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		char[][] map = new char[N][N];
		Point post = new Point(-1, -1); 
		ArrayList<Point> edge = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < N; j++) {
				map[i][j] = line.charAt(j);
				
				if(map[i][j] == POST_OFFICE) post = new Point(i, j);
				else if(map[i][j] == HOME) edge.add(new Point(i, j));
			}
		}
		
		int[][] area = new int[N][N];
		int min = INF, max = 0;
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < N; j++) {
				area[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] == POST_OFFICE || map[i][j] == HOME) {
					min = Math.min(area[i][j], min);
					max = Math.max(area[i][j], max);
				}
			}
		}
		
		System.out.println(binarySearch(N, area, post, edge, max - min));
	}
	
	private static int binarySearch(int n, int[][] cost, Point dest, ArrayList<Point> list, int start) {
		int end = INF, result = INF;

		while(start <= end){
			int mid = (start + end) / 2;
			
			boolean isDelivered = bfs(n, cost, dest, list, mid);
			
			if(isDelivered) {
				end = mid - 1;
				result = Math.min(mid, result);
			}
			else {
				start = mid + 1;
			}
		}
		
		return result;
	}
	
	private static boolean bfs(int n, int[][] cost, Point d, ArrayList<Point> list, int limit) {
		int size = list.size();
		boolean[][][] isVisited = new boolean[n][n][size];
		
		int count = 0, idx = 0;
		int max = 0, min = INF;
		
		Queue<Point> q = new LinkedList<>();
		
		for(Point start: list) {
			isVisited[start.row][start.col][idx] = true;
			q.offer(new Point(start.row, start.col, idx++));
		}
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			
			min = Math.min(min, cost[current.row][current.col]);
			max = Math.max(max, cost[current.row][current.col]);
			
			for(final int[] DIRECTION: DIRECTIONS) {
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
				
				if(nextRow < 0 || nextCol < 0 || nextRow >= n || nextCol >= n) continue;
				int nextMin = Math.min(min, cost[nextRow][nextCol]);
				int nextMax = Math.max(max, cost[nextRow][nextCol]);
				
				if(isVisited[nextRow][nextCol][current.idx] || nextMax - nextMin > limit) continue;
				isVisited[nextRow][nextCol][current.idx] = true;
				
				min = nextMin;
				max = nextMax;
				
				if(nextRow == d.row && nextCol == d.col) count++;
				q.offer(new Point(nextRow, nextCol, current.idx));
			}
		}
		
		return count == size ? true : false;
	}
}
