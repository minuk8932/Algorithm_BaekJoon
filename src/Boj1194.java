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
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[N][M];
		
		int sRow = 0, sCol = 0;
		int dRow = 0, dCol = 0;
		
		for(int i = 0; i < N; i++){
			String line = br.readLine();
			for(int j = 0; j < M; j++){
				map[i][j] = line.charAt(j);
				
				if(map[i][j] == START){
					sRow = i;
					sCol = j;
				}
				
				if(map[i][j] == EXIT){
					dRow = i;
					dCol = j;
				}
			}
		}
		
		
		int[][][] isVisited = new int[N][M][33];
		int esc = 0;
		
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(sRow, sCol, 0));
		
		isVisited[sRow][sCol][0] = 1;
		
LOOP:	while(!q.isEmpty()){
			Point current = q.poll();
			map[current.row][current.col] = WAY;
			
			for(final int[] DIRECTION : DIRECTIONS){
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
				int nextKey = current.key;
				
				
				
				if(nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < M && nextKey >= 0 && nextKey <= 32){	
					if(map[nextRow][nextCol] == BLOCK){
						continue;
					}
					
					if(isVisited[nextRow][nextCol][nextKey] == 0){
						
						if(map[nextRow][nextCol] == WAY){						
							isVisited[nextRow][nextCol][nextKey] = isVisited[current.row][current.col][nextKey] + 1;
							q.offer(new Point(nextRow, nextCol, nextKey));
						}
						else if(map[nextRow][nextCol] >= 'a' && map[nextRow][nextCol] <= 'f'){
							isVisited[nextRow][nextCol][nextKey] = isVisited[current.row][current.col][nextKey] + 1;
							int keyT = (int) Math.pow(2, map[nextRow][nextCol] - 'a');
							
							q.offer(new Point(nextRow, nextCol, keyT));
						}
						else if(map[nextRow][nextCol] >= 'A' && map[nextRow][nextCol] <= 'F'){
							int keyT = (int) Math.pow(2, map[nextRow][nextCol]-'A');
							
							if((nextKey & keyT) == keyT){
								isVisited[nextRow][nextCol][nextKey] = isVisited[current.row][current.col][nextKey] + 1;
								
								q.offer(new Point(nextRow, nextCol, nextKey));
							}
						}
						else if(map[nextRow][nextCol] == EXIT){
							esc++;
							
							break LOOP;
						}
					}
				}
			}
		}
		int toExit = isVisited[dRow][dCol][32] - 1;
		
		System.out.println(esc > 0 ? toExit : NO_WAY);
	}
	private static class Point{
		int row;
		int col;
		int key;
		
		public Point(int row, int col, int key){
			this.row = row;
			this.col = col;
			this.key = key;
		}
	}
}
