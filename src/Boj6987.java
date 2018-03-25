import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj6987 {
	private static final String SPACE = " ";
	
	private static final char START = 'C';
	private static final char BLOCK = '*';
	private static final char WAY = '.';
	
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	private static final int INF = 100_000;
	
	private static int MIRROR = 0;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[H][W];
		
		int[][] dest = new int[2][2];
		int idx = 0;
		
		for(int i = 0; i < H; i++){
			String line = br.readLine();
			
			for(int j = 0; j < W; j++){
				map[i][j] = line.charAt(j);
				
				if(map[i][j] == START){
					dest[idx][0] = i;
					dest[idx][1] = j;
					
					idx++;
				}
			}
		}
		
		int dist = bfs(map, dest, H, W) - 1;
		System.out.println(dist);
		
	}
	
	private static class Point{
		int row;
		int col;
		int cnt;
		
		public Point(int row, int col, int cnt){
			this.row = row;
			this.col = col;
			this.cnt = cnt;
		}
	}
	
	private static int bfs(char[][] map, int[][] dest, int H, int W){
		int[][] isVisited = new int[H][W];
		
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(dest[0][0], dest[0][1], MIRROR));
		isVisited[dest[0][0]][dest[0][1]] = 1;
		
		while(!q.isEmpty()){
			Point current = q.poll();
			
			for(final int[] DIRECTION : DIRECTIONS){
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
				
				if(nextRow >= 0 && nextRow < H && nextCol >= 0 && nextCol < W){
					if(map[nextRow][nextCol] != BLOCK && isVisited[nextRow][nextCol] == 0){						
						isVisited[nextRow][nextCol] = isVisited[current.row][current.col] + 1;
						
						q.offer(new Point(nextRow, nextCol, MIRROR++));
						
						if(nextRow == dest[1][0] && nextCol == dest[1][1]){
							return isVisited[nextRow][nextCol];
						}
					}
				}
			}
		}
		
		return 0;
	}
}
