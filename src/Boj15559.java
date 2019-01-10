import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj15559 {
	private static final HashMap<Character, Point> hm = new HashMap<>();
	
	private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	private static final int INF = 1_000_001;
	
	private static final char NORTH = 'N';
	private static final char SOUTH = 'S';
	private static final char EAST = 'E';
	private static final char WEST = 'W';
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		init();
		
		char[][] map = new char[N][M];
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		
		System.out.println(blockCount(N, M, map));
	}
	
	private static void init() {
		hm.put(NORTH, new Point(-1, 0));
		hm.put(SOUTH, new Point(1, 0));
		hm.put(EAST, new Point(0, 1));
		hm.put(WEST, new Point(0, -1));
	}
	
	private static class Point{
		int row;
		int col;
		
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	private static int blockCount(int n, int m, char[][] arr) {
		int[][] isVisited = new int[n][m];
		
		for(int i = 0; i < n; i++) {
			Arrays.fill(isVisited[i], INF);
		}
		
		for(int row = 0; row < n; row++) {
			for(int col = 0; col < m; col++) {
				if(isVisited[row][col] != INF) continue;
				
				Queue<Point> q = new LinkedList<>();
				q.offer(new Point(row, col));
				
				isVisited[row][col] = 1;
				
				while(!q.isEmpty()) {
					Point current = q.poll();
					
					for(final int[] DIRECTION: DIRECTIONS) {
						
					}
				}
			}
		}
		
		return 0;
	}
}
