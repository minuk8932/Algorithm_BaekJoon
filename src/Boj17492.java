import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj17492 {
	private static final int BLOCK = 1;
	private static final int EMPTY = 0;
	private static final int DOT = 2;
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
	private static final int ROW = 0, COL = 1;
	
	private static final String IM = "Impossible";
	private static final String PO = "Possible";
	
	private static int[][][] visit;
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
			}
		}

		System.out.println(bfs(N, go));
	}
	
	private static String bfs(int n, int[][] arr) {
		
		
		return "";
	}
}
