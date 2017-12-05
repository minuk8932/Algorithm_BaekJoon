import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 		@author minchoba
 *			백준 5427 : 불
 */

public class Boj5427 {
	private static final String SPACE = " ";
	private static final char WAY = '.';
	private static final char BLOCK = '#';
	private static final char START = '@';
	private static final char FIRE = '*';
	private static final String CANNOT_ESCAPE = "IMPOSSIBLE";
	
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	private static char[][] top = null;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0){
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			int w = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			
			top = new char[h][w];
			for(int i = 0 ; i < h; i++){
				top[i] = br.readLine().toCharArray();
			}
			
			int startH = 0;
			int startW = 0;
			
			for(int i = 0; i < h; i++){
				for(int j = 0; j < w; j++){
					if(top[i][j] == START){
						startH = i;
						startW = j;
					}
				}
			}
			
			int escape = 1;
			int[][] isVisited = new int[h][w];
			
			for(int row = startH; row < h; row++){
				for(int col = startW; col < w; col++){
					if(isVisited[row][col] == 0){
						isVisited[row][col] = 1;
						
						Queue<Point> q = new LinkedList<>();
						q.offer(new Point(row, col));
						
						while(!q.isEmpty()){
							Point current = q.poll();
							
							for(final int[] DIRECTION : DIRECTIONS){
								int nextRow = current.row + DIRECTION[ROW];
								int nextCol = current.col + DIRECTION[COL];
								
								if(nextRow >= 0 && nextRow < h && nextCol >= 0 && nextCol < w){
									if(isVisited[nextRow][nextCol] == 0){
										if(top[nextRow][nextCol] != BLOCK && top[nextRow][nextCol] != FIRE){
											escape += isVisited[nextRow][nextCol];
										
											// TODO : how to check when he cann't escape
										}
										
										q.offer(new Point(nextRow, nextCol));
									}
								}
							}
						}
					}
					spreadFire(row, col);
				}
			}
			System.out.println(escape);
		}
	}
	
	private static class Point{
		int row;
		int col;
		
		public Point(int row, int col){
			this.row = row;
			this.col = col;
		}
	}
	
	public static void spreadFire(int x, int y){
		
		for(final int[] DIRECTION : DIRECTIONS){
			if(top[x][y] == FIRE){
				int nextX = DIRECTION[ROW] + x;
				int nextY = DIRECTION[COL] + y;
				
				top[nextX][nextY] = FIRE;
			}
		}
	}
}
