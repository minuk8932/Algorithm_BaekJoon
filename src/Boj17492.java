import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj17492 {
	private static final int BLOCK = 1;
	private static final int EMPTY = 0;
	private static final int DOT = 2;
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
	private static final int ROW = 0, COL = 1;
	
	private static final String IM = "Impossible";
	private static final String PO = "Possible";
	
	private static int[][][] map;
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
		int N = Integer.parseInt(br.readLine());
		
		int[][] go = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				go[i][j] = Integer.parseInt(st.nextToken());
				
				if(go[i][j] == 2) start.add(new Point(i, j));
				
				for(int x = 0; x < map.length; x++) {
					map[x][i][j] = go[i][j];
				}
			}
		}
		
		map = new int[start.size()][N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				for(int x = 0; x < map.length; x++) {
					map[x][i][j] = go[i][j];
				}
			}
		}
		
		String res = IM;
		for(Point s: start) {
			if(isPossible(N, go, s)) {
				res = PO;
				break;
			}
		}
		
		System.out.println(res);
	}
	
	private static boolean isPossible(int n, int[][] arr, Point p){
		Queue<Point> q = new LinkedList<>();
		q.offer(p);
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			
			for(int d = 0; d < DIRECTIONS.length; d++) {
				
			}
		}
		
		return false;
	}
}
