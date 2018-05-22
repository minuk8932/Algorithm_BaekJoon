import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1194 {
	private static final String SPACE = " ";
	
	private static final char START = '0';
	private static final char BLOCK = '#';
	private static final char WAY = '.';
	private static final char EXIT = '1';
	
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	private static final int NO_WAY = -1;
	
	private static char[][] map = null;
	private static boolean[] hasKey = null;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		hasKey = new boolean[6];
		
		int sRow = 0, sCol = 0;
		
		for(int i = 0; i < N; i++){
			String line = br.readLine();
			for(int j = 0; j < M; j++){
				map[i][j] = line.charAt(j);
				
				if(map[i][j] == START){
					sRow = i;
					sCol = j;
				}
				
				if(map[i][j] >= 'a' && map[i][j] <= 'f'){
					hasKey[map[i][j] - 97] = true;
				}
			}
		}
		
		bfs(N, M, sRow, sCol);
		
	}
	private static class Point{
		int row;
		int col;
		
		public Point(int row, int col){
			this.row = row;
			this.col = col;
		}
	}
	
	private static void bfs(int row, int col, int startRow, int startCol){
		
	}
}
