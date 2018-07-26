import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2842 {
	private static final int INF = 1_000_001;
	
	private static final char POST = 'P';
	private static final char HOME = 'K';
	
	private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1 , 1}, {-1, 1}, {1, - 1}, {-1, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		char[][] map = new char[N][N];
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < N; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		
		int min = INF, max = 0;
		
		int[][] cost = new int[N][N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < N; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] == POST || map[i][j] == HOME) {
					min = Math.min(min, cost[i][j]);
					max = Math.max(max, cost[i][j]);
				}
			}
		}
		
		
		
		
	}
}
