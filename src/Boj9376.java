import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj9376 {
	private static final String NEW_LINE = "\n";
	
	private static final char DOOR = '#';
	private static final char BLOCK = '*';
	private static final char PRISONER = '$';
	
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0, COL = 1;
	private static final int INF = 1_000_000;
	
	private static ArrayList<Point> start = new ArrayList<>();
	private static boolean[][] used;
	private static char[][] map;
	
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
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int h = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			map = new char[h][w];
			used = new boolean[h][w];
			
			for(int i = 0; i < h; i++) {
				String line = br.readLine();
				for(int j = 0; j < w; j++) {
					map[i][j] = line.charAt(j);
					
					if(map[i][j] == PRISONER) start.add(new Point(i, j));
					if(map[i][j] == DOOR) used[i][j] = true;
				}
			}
			
			int result = bfs(h, w);
			sb.append(result).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static int bfs(int H, int W) {
		// 완탐으로 첫 죄수 최단 경로 탐색 후 위치로부터 역경로 탐 
		
		return 0;
	}
}
