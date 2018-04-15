import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj14503 {
	private static final String SPACE = " ";
	
	private static int[][] map = null;
	private static int N = 0;
	private static int M = 0;
	
	private static final int[] DIRECTIONS = {0, 1, 2, 3};			// 북 동 남 서
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		st = new StringTokenizer(br.readLine(), SPACE);
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < N; i++){
			st = new StringTokenizer(br.readLine(), SPACE);
			
			for(int j = 0; j < M; j++){
				map[i][j] = Integer.parseInt(st.nextToken()); 
			}
		}
		
		bfs(r, c, d);
	}
	
	private static class Point{
		int row;
		int col;
		int dir;
		
		public Point(int row, int col, int dir){
			this.row = row;
			this.col = col;
			this.dir = dir;
		}
	}
	
	public static void bfs(int r, int c, int d){
		
	}
}
