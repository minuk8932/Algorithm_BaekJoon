import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj17489 {
	private static ArrayList<Point> start = new ArrayList<>();
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0, COL = 1;
	private static final int INF = 1_000_000;
	
	private static final String NEW_LINE = "\n";
	private static final String SPACE = " ";
	
	private static int[] parent;
	
	private static class Point{
		int row;
		int col;
		int idx;
		
		public Point(int row, int col, int idx) {
			this.row = row;
			this.col = col;
			this.idx = idx;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		char[] S = br.readLine().toCharArray();
		init(N, M);
		
		char[][] map = new char[N][M];
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);
				
				if(map[i][j] == S[0]) start.add(new Point(i, j, 0));
			}
		}
		
		System.out.println(search(N, M, L, S, map));
	}
	
	private static void init(int n, int m) {
		parent = new int[n * m];
		for(int i = 0; i < parent.length; i++) {
			parent[i] = -1;
		}
	}
	
	private static int find(int x) {
		if(parent[x] < 0) return x;
		else return parent[x] = find(parent[x]);
	}
	
	private static void merge(int x, int y) {
		if(parent[x] < parent[y]) {
			parent[x] += parent[y];
			parent[y] = x;
		}
		else {
			parent[y] += parent[x];
			parent[x] = y;
		}
	}
	
	private static String search(int n, int m, int len, char[] key, char[][] arr) {
		int[][] visit = new int[n][m];
		int row = 0, col = 0;
		int max = 0;
		
		for(Point s: start) {
			if(visit[s.row][s.col] != 0) continue;
			visit[s.row][s.col] = 1;
			
			Queue<Point> q = new LinkedList<>();
			q.offer(s);
			
			while(!q.isEmpty()) {
				Point current = q.poll();
				
				for(final int[] DIRECTION: DIRECTIONS) {
					int nextRow = current.row + DIRECTION[ROW];
					int nextCol = current.col + DIRECTION[COL];
					int nextIdx = (current.idx + 1) % len;
					
					if(nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= m) continue;
					if(arr[nextRow][nextCol] != key[nextIdx]) continue;
					if(visit[nextRow][nextCol] != 0) {
						int x = current.row * m + current.col;
						int y = nextRow * m + nextCol;
						
						if(x == y) continue;
						merge(x, y);
						
						continue;
					}

					visit[nextRow][nextCol] = visit[current.row][current.col] + 1;
					
					if(arr[nextRow][nextCol] == key[len - 1]) {
						if(visit[nextRow][nextCol] > max) {
							max = visit[nextRow][nextCol];
							row = nextRow;
							col = nextCol;
						}
					}
					
					q.offer(new Point(nextRow, nextCol, nextIdx));
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		if(max == 0 || max == INF) return sb.append(-1).toString();
		return sb.append(max / len).append(NEW_LINE).append(row + 1).append(SPACE).append(col + 1).toString();
	}
}
