import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj6593 {
	private static final String SPACE = " ";
	private static final String END_LINE = "\n";
	private static final String LOCKED = "Trapped!";
	private static final String ESC_1 = "Escaped in ";
	private static final String ESC_2 = " minute(s).";
	
	private static final char START = 'S';
	private static final char END = 'E';
	private static final char BLOCK = '#';
	
	private static final int[][] DIRECTIONS = {{1, 0, 0}, {-1, 0, 0},  {0, 0, -1}, {0, 0, 1}, {0, 1, 0}, {0, -1, 0}};
	private static final int LEV = 0;
	private static final int ROW = 1;
	private static final int COL = 2;
	
	private static char[][][] top = null;
	private static int[][][] isVisited = null;
	
	private static int L = 0;
	private static int R = 0;
	private static int C = 0;
	private static int result = 0;
	private static Point go = null;
	private static Point des = null;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true){
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			L = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			if(L == 0 && R == 0 && C == 0){
				break;
			}
			
			top = new char[L + 1][R + 1][C + 1];
			
			for(int i = 1; i < L + 1; i++){
				for(int j = 1; j < R + 1; j++){
					String line = br.readLine();
					
					for(int k = 1; k < C + 1; k++){
						top[i][j][k] = line.charAt(k - 1);
						
						if(top[i][j][k] == START){
							go = new Point(i, j, k);
						}
						
						if(top[i][j][k] == END){
							des = new Point(i, j, k);
						}
						
					}
				}
				String waste = br.readLine();
			}
			
			isVisited = new int[L + 1][R + 1][C + 1];
//			result = Integer.MAX_VALUE;
			
			if(isVisited[go.lev][go.row][go.col] == 0){
				isVisited[go.lev][go.row][go.col] = 1;
				
				dfs(new Point(go.lev, go.row, go.col));
			}
			
			
			
			sb.append(isVisited[des.lev][des.row][des.col] == 0 ? LOCKED : ESC_1 + (isVisited[des.lev][des.row][des.col] - 1) + ESC_2).append(END_LINE);
		}
		
		System.out.println(sb.toString());
	}
	
	private static class Point{
		int lev;
		int row;
		int col;
		
		public Point(int lev, int row, int col){
			this.lev = lev;
			this.row = row;
			this.col = col;
		}
	}

	private static void dfs(Point p){
		for(final int[] DIRECTION : DIRECTIONS){
			int nextLev = p.lev + DIRECTION[LEV];
			int nextRow = p.row + DIRECTION[ROW];
			int nextCol = p.col + DIRECTION[COL];
			
			if(nextLev > 0 && nextLev < L + 1 && nextRow > 0 && nextRow < R + 1 && nextCol > 0 && nextCol < C + 1){
				if(isVisited[nextLev][nextRow][nextCol] == 0 && top[nextLev][nextRow][nextCol] != BLOCK){
					isVisited[nextLev][nextRow][nextCol] = isVisited[p.lev][p.row][p.col] + 1;
					
					dfs(new Point(nextLev, nextRow, nextCol));
				}
			}
		}
	}
}
