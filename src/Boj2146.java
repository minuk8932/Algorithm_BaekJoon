import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj2146 {
	private static final int MAX = 101;
	private static final int LAND = 1;
	private static final int OCEAN = 0;
	
	private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	private static final String SPACE = " ";	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N+1][N+1];
		
		for(int i = 1; i < N + 1; i ++){
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			for(int j = 1; j < N + 1; j++){
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int min = Integer.MAX_VALUE;
		int[][] isVisited = new int[N+1][N+1];
		
		for(int row = 1; row < N + 1; row++){
			for(int col = 1; col < N + 1; col++){
				if(isVisited[row][col] == 0){
					
					isVisited[row][col] = 1;
					
					Queue<Point> queue = new LinkedList<>();
					queue.offer(new Point(row, col));
					
					while(!queue.isEmpty()){
						Point current = queue.poll();
						
						for(int[] DIRECTION : DIRECTIONS){
							int nextRow = current.row + DIRECTION[ROW];
							int nextCol = current.col +DIRECTION[COL];
							
							if(nextRow > 0 && nextRow < N + 1 && nextCol > 0 && nextCol < N + 1){
								if(isVisited[nextRow][nextCol] == 0){
									isVisited[nextRow][nextCol] = 1;
									
									
									
									queue.offer(new Point(nextRow, nextCol));
								}
							}
						}
					}
				}
			}
		}
		System.out.println(min);
	}
	
	private static class Point{
		int row;
		int col;
		
		public Point(int row, int col){
			this.row = row;
			this.col = col;
		}
	}
}
