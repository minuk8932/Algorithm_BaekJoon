package disjoint_set;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 14466번: 소가 길을 건너간 이유 6
 *
 *	@see https://www.acmicpc.net/problem/14466/
 *
 */
public class Boj14466 {
	private static final int[][] DIRECTIONS = {{2, 0}, {0, 2}, {-2, 0}, {0, -2}};
	private static final int ROW = 0, COL = 1;
	private static final int CROSSED = -1;
	private static final int COW = 1;
	
	private static int[] parent;
	
	private static ArrayList<Point> start = new ArrayList<>();
	
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
		int R = Integer.parseInt(st.nextToken());
		
		int length = N * 2 - 1;
		
		int[][] map = new int[length][length];
		init(length);
		
		while(R-- > 0) {
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken()) - 1;
			int col = Integer.parseInt(st.nextToken()) - 1;
			row += Integer.parseInt(st.nextToken()) - 1;
			col += Integer.parseInt(st.nextToken()) - 1;
			
			map[row][col] = CROSSED;					// 길 막음
		}
		
		int loop = K;
		while(loop-- > 0) {
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken()) - 1;
			int col = Integer.parseInt(st.nextToken()) - 1;
			
			map[row * 2][col * 2] = COW;				// 소 위치 저장
			start.add(new Point(row * 2, col * 2));
		}
		
		System.out.println(bfs(N, K, map));
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
	
	private static int bfs(int n, int k, int[][] arr) {
		int size = n * 2 - 1;
		
		for(Point s: start) {			
			Queue<Point> q = new LinkedList<>();
			q.offer(new Point(s.row, s.col));
			
			boolean[][] isVisited = new boolean[size][size];
			isVisited[s.row][s.col] = true;
			
			while(!q.isEmpty()) {
				Point current = q.poll();
				
				for(final int[] DIRECTION: DIRECTIONS) {
					int nextRow = current.row + DIRECTION[ROW];
					int nextCol = current.col + DIRECTION[COL];
					int adjRow = current.row + DIRECTION[ROW] / 2;		// 길막 있는지 확인
					int adjCol = current.col + DIRECTION[COL] / 2;
					
					if(nextRow < 0 || nextRow >= size || nextCol < 0 || nextCol >= size) continue;
					if(arr[adjRow][adjCol] == CROSSED || isVisited[nextRow][nextCol]) continue;
					isVisited[nextRow][nextCol] = true;
					
					if(arr[nextRow][nextCol] == COW) {							// 두 소가 만나면 같은 집합
						merge(s.row * size + s.col, nextRow * size + nextCol);
					}
					
					q.offer(new Point(nextRow, nextCol));
				}
			}
		}
		
		ArrayList<Integer> list = new ArrayList<>();
		
		for(int i = 0; i < parent.length; i++) {
			if(parent[i] < -1) {
				k += parent[i];
				list.add(-parent[i]);			// 그룹 별 소의 마릿수
			}
		}
		
		list.add(k);							// 혼자 떨어진 소의 마릿수
		int result = (k - 1) * k / 2;
		int leng = list.size();
		
		for(int i = 0; i < leng; i++) {			
			for(int j = i + 1; j < leng; j++) {				// 경우의 수
				result += (list.get(i) * list.get(j));
			}
		}
		
		return result;
	}
}
