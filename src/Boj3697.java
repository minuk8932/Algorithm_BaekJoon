import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj3697 {
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1 ,0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	private static final char NEW_LINE = '\n';
	
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
			int[][] diff = new int[n][m];
			
			
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
			
				for(int j = 0; j < m; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					diff[i][j] = map[i][j] - d;
				
				}
			}
			
			
		}
		
		System.out.println(sb);
	}
	
	private static class Point{
		int row;
		int col;
		
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
}
