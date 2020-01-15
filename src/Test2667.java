import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Test2667 {
	private static final String NEW_LINE = "\n";
	
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0, COL = 1;
	
	private static ArrayList<Point> start = new ArrayList<>();
	private static PriorityQueue<Integer> count = new PriorityQueue<>();
	private static boolean[][] visit;
	private static int val;
	
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
		
		int[][] map = new int[N][N];
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < N; j++) {
				map[i][j] = line.charAt(j) - '0';
				
				if(map[i][j] == 1) start.add(new Point(i, j));
			}
		}
		
		visit = new boolean[N][N];
//		System.out.println(bfs(N, map));
		
		int cluster = 0;
		
		for(Point s: start) {
			if(map[s.row][s.col] == 0 || visit[s.row][s.col]) continue;
			visit[s.row][s.col] = true;
			
			cluster++;
			val = 1;
			dfs(N, s, map);
			count.offer(val);
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(cluster).append(NEW_LINE);
		while(!count.isEmpty()) sb.append(count.poll()).append(NEW_LINE);
		
		System.out.println(sb.toString());
	}
	
	private static void dfs(int n, Point current, int[][] map) {
		for(final int[] DIRECTION: DIRECTIONS) {
			int nextRow = current.row + DIRECTION[ROW];
			int nextCol = current.col + DIRECTION[COL];
			
			if(nextRow < 0 || nextCol < 0 || nextRow >= n || nextCol >= n) continue;
			if(map[nextRow][nextCol] == 0 || visit[nextRow][nextCol]) continue;
			visit[nextRow][nextCol] = true;
			val++;
			
			dfs(n, new Point(nextRow, nextCol), map);
		}
	}
	
	private static String bfs(int n, int[][] map) {
		int cluster = 0;
		
		for(Point s: start) {
			if(map[s.row][s.col] == 0 || visit[s.row][s.col]) continue;
			visit[s.row][s.col] = true;
			
			cluster++;
			val = 1;
			
			Queue<Point> q = new LinkedList<>();
			q.offer(new Point(s.row, s.col));
			
			while(!q.isEmpty()) {
				Point current = q.poll();
				
				for(final int[] DIRECTION: DIRECTIONS) {
					int nextRow = current.row + DIRECTION[ROW];
					int nextCol = current.col + DIRECTION[COL];
					
					if(nextRow < 0 || nextCol < 0 || nextRow >= n || nextCol >= n) continue;
					if(map[nextRow][nextCol] == 0 || visit[nextRow][nextCol]) continue;
					visit[nextRow][nextCol] = true;
					
					val++;
					q.offer(new Point(nextRow, nextCol));
				}
			}
			
			count.offer(val);
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(cluster).append(NEW_LINE);
		
		while(!count.isEmpty()) sb.append(count.poll()).append(NEW_LINE);
		
		return sb.toString();
	}
}
