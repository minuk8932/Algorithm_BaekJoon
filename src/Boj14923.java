import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj14923 {
	private static final String SPACE = " ";
	private static final int WAY = 0;
	private static final int BLOCK = 1;
	
	private static final int INF = 1_000_001;
	private static final int NO_WAY = -1;
	private static final int ROW = 0;
	private static final int COL = 1;
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	
	private static int N = 0;
	private static int M = 0;
	
	private static int[][] map = null;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		Point start = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);
		st = new StringTokenizer(br.readLine());
		Point end = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);
		
		map = new int[N + 1][M + 1];
		for(int i = 1; i < N + 1; i++){
			st = new StringTokenizer(br.readLine());
					
			for(int j = 1; j < M + 1; j++){
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][][] isVisited = new int[N + 1][M + 1][2];
		for(int i = 1 ; i < N + 1; i++){
			for(int j = 1; j < M + 1; j++){
				Arrays.fill(isVisited[i][j], INF);
			}
		}
		
		breakingBfs(isVisited, start);
		
		System.out.println(isVisited[end.row][end.col][1] == INF && isVisited[end.row][end.col][0] == INF ? 
				NO_WAY : Math.min(isVisited[end.row][end.col][1], isVisited[end.row][end.col][0]) - 1);
	}
	
	private static class Point{
		int row;
		int col;
		int isBroken;
		
		public Point(int row, int col, int isBroken){
			this.row = row;
			this.col = col;
			this.isBroken = isBroken;
		}
	}
	
	private static void breakingBfs(int[][][] isVisited, Point s){
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(s.row, s.col, s.isBroken));
		isVisited[1][1][0] = 1;
		
		while(!q.isEmpty()){
			Point current = q.poll();
			
			for(final int[] DIRECTION : DIRECTIONS){
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
				int isBreaking = current.isBroken;
				
				if(nextRow > 0 && nextRow < N + 1 && nextCol > 0 && nextCol < M + 1){
					if(map[nextRow][nextCol] == WAY && 
							isVisited[nextRow][nextCol][isBreaking] > isVisited[current.row][current.col][isBreaking] + 1){
						
						isVisited[nextRow][nextCol][isBreaking] = isVisited[current.row][current.col][isBreaking] + 1;
						q.offer(new Point(nextRow, nextCol, isBreaking));
					}
					else if(map[nextRow][nextCol] == BLOCK && isBreaking == 0 &&
							isVisited[nextRow][nextCol][isBreaking] > isVisited[current.row][current.col][isBreaking] + 1){
						
						isVisited[nextRow][nextCol][1] = isVisited[current.row][current.col][0] + 1;
						q.offer(new Point(nextRow, nextCol, 1));
					}
				}
			}
		}
	}
}
