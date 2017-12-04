package breadth_first_serch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1012 {
	private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0,-1}};
	private static final int ROW = 0;	// 0 index is row
	private static final int COL = 1;	// 1 index is col
	
	public static final String NEW_LINE = "\n";
	public static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		while(T-- > 0){
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			boolean[][] map = new boolean[N][M];
			
			while(K-- > 0){
				st  = new StringTokenizer(br.readLine(), SPACE);
				int col = Integer.parseInt(st.nextToken());
				int row = Integer.parseInt(st.nextToken());
				map[row][col] = true;
			}
			
			int areaCnt = 0;
			
			for(int row = 0; row < N; row++){
				for(int col = 0; col < M; col++){
					//is here cabbage?
					if(map[row][col]){
						map[row][col] = false;	// visited check
						areaCnt++;
						
						Queue<Point> queue = new LinkedList<>();
						queue.offer(new Point(row, col));
						
						while(!queue.isEmpty()){
							Point current = queue.poll();
							
							for(final int[] DIRECTION : DIRECTIONS){
								int nextRow = current.row + DIRECTION[ROW];
								int nextCol = current.col + DIRECTION[COL];
								
								if( 0 <= nextRow && nextRow < N && 0 <= nextCol && nextCol < M){
									if(map[nextRow][nextCol]){	// next is cabbage?
										map[nextRow][nextCol] = false;
										queue.offer(new Point(nextRow, nextCol));
									}
								}
							}
						}
						
					}
				}
			}
			sb.append(areaCnt).append(NEW_LINE);
		}
		br.close();
		
		System.out.println(sb.toString());
	}
	
	// Using 2 Dimension matrix, need class Point
	private static class Point{
		public int row;
		public int col;
		
		public Point(int row, int col){
			this.row = row;
			this.col = col;
		}
	}

}
