import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj14868 {
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
		int[][] isVisited = new int[N][N];
		
		while(K-- > 0) {
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken()) - 1;
			int col = Integer.parseInt(st.nextToken()) - 1;
			
			fix.add(new Point(row, col));
			civil.offer(new Point(row, col));
			isVisited[row][col] = 1;
		}
		
		System.out.println(bfs(N, civil, isVisited));
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
	
	private static boolean merge(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x == y) return false;;
		
		if(parent[x] < parent[y]) {
			parent[x] += parent[y];
			parent[y] = x;
		}
		else {
			parent[y] += parent[x];
			parent[x] = y;
		}
		
		return true;
	}
	
	private static int bfs(int n, Queue<Point> q, int[][] visit) {
		int year = 0;
		
		while(!q.isEmpty()) {
			Point current = q.poll();
				
			for(final int[] DIRECTION: DIRECTIONS) {
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
					
				if(nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n) continue;
				if(!merge(current.row * n + current.col, nextRow * n + nextCol)) continue;
				visit[nextRow][nextCol] = visit[current.row][current.col] + 1;
				year = visit[nextRow][nextCol];
					
				q.offer(new Point(nextRow, nextCol));
			}
			
		}
		
		return year - 1;
	}
}
