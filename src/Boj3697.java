import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj3697 {
	private static final String NEW_LINE = "\n";
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	private static ArrayList<Point> summits = new ArrayList<>();
	
	private static int[] parent;
	private static int total;
	
	private static class Point implements Comparable<Point>{
		int row;
		int col;
		int d;
		
		public Point(int row, int col, int d) {
			this.row = row;
			this.col = col;
			this.d = d;
		}

		@Override
		public int compareTo(Point p) {
			return this.d > p.d ? -1: 1;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			int[][] map = new int[n][m];
			parent = new int[n * m];
			
			int max = 0;
			
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				
				for(int j = 0; j < m; j++) {
					parent[i * m + j] = -1;
					map[i][j] = Integer.parseInt(st.nextToken());
					if(max < map[i][j]) max = map[i][j];
					
					summits.add(new Point(i, j, map[i][j]));
				}
			}
			
			search(n, m, map, max, d);
			sb.append(total).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
	}
	
	private static int find(int x) {
		if(parent[x] < 0) return x;
		else return parent[x] = find(parent[x]);
	}
	
	private static boolean merged(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x == y) return true;
		
		if(parent[x] < parent[y]) {
			parent[x] += parent[y];
			parent[y] = x;
		}
		else {
			parent[y] += parent[x];
			parent[x] = y;
		}
		
		return false;
	}
	
	private static void search(int n, int m, int[][] map, int max, int high) {
		Collections.sort(summits);
		
		for(Point p: summits) {
			if(parent[p.row * m + p.col] != -1) continue;
			
			Queue<Point> q = new LinkedList<>();
			q.offer(p);
			
			int val = p.d;
			total++;
			
			while(!q.isEmpty()) {
				Point current = q.poll();
				
				for(final int[] DIRECTION: DIRECTIONS) {
					int nextRow = current.row + DIRECTION[ROW];
					int nextCol = current.col + DIRECTION[COL];
					
					if(nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= m) continue;
					if(val - high + 1 < 0 || merged(current.row * m + current.col, nextRow * m + nextCol)) continue;
					
					if(map[nextRow][nextCol] == max) total++;
					q.offer(new Point(nextRow, nextCol, map[nextRow][nextCol]));
				}
			}
		}
	}
}
