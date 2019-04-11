import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj14868 {
	private static int[][] visit;
	private static int[] parent;
	private static ArrayList<Point> fix;
	
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1 ,0}, {0, -1}};
	private static final int ROW = 0, COL = 1;
	
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
		
		init(N);
		
		Queue<Point> civil = new LinkedList<>();
		fix = new ArrayList<>();
		int[][] countries = new int[N][N];
		visit = new int[N][N];
		
		int rep = 1;
		
		while(K-- > 0) {
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken()) - 1;
			int col = Integer.parseInt(st.nextToken()) - 1;
			
			fix.add(new Point(row, col));
			civil.offer(new Point(row, col));
			countries[row][col] = rep++;
			visit[row][col] = 1;
		}
		
		System.out.println(bfs(N, civil, countries));
	}
	
	private static void init(int n) {
		parent = new int[n * n];
		for(int i = 0; i < parent.length; i++) {
			parent[i] = -1;
		}
	}
	
	private static int find(int x) {
		if(parent[x] < 0) return x;
		return parent[x] = find(parent[x]);
	}
	
	private static boolean isCycle(int x, int y) {
		x = find(x);
		y = find(y);
		
		return x == y ? true: false;
	}
	
	private static void merge(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x == y) return;
		
		if(parent[x] < parent[y]) {
			parent[x] += parent[y];
			parent[y] = x;
		}
		else {
			parent[y] += parent[x];
			parent[x] = y;
		}
	}
	
	private static int bfs(int n, Queue<Point> q, int[][] country) {
		int year = 0;
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			
			for(final int[] DIRECTION: DIRECTIONS) {
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
					
				if(nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n) continue;
				if(isCycle(current.row * n + current.col, nextRow * n + nextCol)) continue;
				
				if(visit[nextRow][nextCol] == 0) {
					visit[nextRow][nextCol] = visit[current.row][current.col] + 1;
					country[nextRow][nextCol] = country[current.row][current.col];
					
					q.offer(new Point(nextRow, nextCol));
				}
				
				if(visit[nextRow][nextCol] != 0 && country[nextRow][nextCol] != country[current.row][current.col]) {
					merge(current.row * n + current.col, nextRow * n + nextCol);
				}
			}
		}
		
		return year - 1;
	}
}
