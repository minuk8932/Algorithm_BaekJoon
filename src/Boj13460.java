import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj13460 {
	private static final String SPACE = " ";
	
	private static final char BLOCK = '#';
	private static final char HOLE = 'O';
	private static final char RED = 'R';
	private static final char BLUE = 'B';
	
	private static final int FAIL = -1;
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	private static char[][] map = null;
	private static int[][][] isVisited = null;
	private static int N = 0;
	private static int M = 0;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		isVisited = new int[N][M][2];
		
		Point[] ballPos = new Point[2];
		Point destHole = new Point(0, 0);
		
		for(int i = 0; i < N; i++){
			String line = br.readLine();
			
			for(int j = 0; j < M; j++){
				map[i][j] = line.charAt(j);
				
				if(map[i][j] == HOLE){
					destHole = new Point(i, j);
				}
				
				if(map[i][j] == RED){
					ballPos[0] = new Point(i, j);
				}
				
				if(map[i][j] == BLUE){
					ballPos[1] = new Point(i, j);
				}
			}
		}
		
		for(int i = 0; i < ballPos.length; i++) {
			bfs(destHole, ballPos, i);
		}
		
//		for(int i = 0; i < N; i++) {
//			for(int j = 0; j < M; j++) {
//				System.out.print(isVisited[i][j][0]);
//			}
//			System.out.println();
//		}
		
		StringBuilder sb = new StringBuilder();
		
		if((isVisited[destHole.row][destHole.col][0] > 0 && isVisited[destHole.row][destHole.col][0] <= 10) &&
				(isVisited[destHole.row][destHole.col][1] == 0 || isVisited[destHole.row][destHole.col][0] < isVisited[destHole.row][destHole.col][1])) {
			sb.append(isVisited[destHole.row][destHole.col][0]);
		}
		else {
			sb.append(FAIL);
		}
		
		System.out.println(sb.toString());
	}
	
	private static class Point{
		int row;
		int col;
				
		public Point(int row, int col){
			this.row = row;
			this.col = col;
		}
	}
	
	private static void bfs(Point end, Point[] start, int idx){
		int rev = idx == 0 ? 1 : 0;
		
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(start[idx].row, start[idx].col));
		isVisited[start[idx].row][start[idx].col][idx] = 0;
		
		int dirX = 0;
		int dirY = 0;
		
		while(!q.isEmpty()){
			Point current = q.poll();
			
			boolean hasWay = false;
			
			for(final int[] DIRECTION : DIRECTIONS) {
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
				
				if(nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < M) {
					if(map[nextRow][nextCol] != BLOCK && dirX == DIRECTION[ROW] && dirY == DIRECTION[COL]) {
						hasWay = true;
					}
				}
			}
			
			if(hasWay) {
				int nextRow = current.row + dirX;
				int nextCol = current.col + dirY;
						
				if(isVisited[nextRow][nextCol][idx] == 0 && map[nextRow][nextCol] != BLOCK) {
					isVisited[nextRow][nextCol][idx] = isVisited[current.row][current.col][idx];
					
					if(nextRow == end.row && nextCol == end.col) return;
					
					q.offer(new Point(nextRow, nextCol));
				}
			}
			else {
				for(final int[] DIRECTION : DIRECTIONS) {
					int nextRow = current.row + DIRECTION[ROW];
					int nextCol = current.col + DIRECTION[COL];
					
					if(nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < M) {
						if(isVisited[nextRow][nextCol][idx] == 0 && map[nextRow][nextCol] != BLOCK) {
							isVisited[nextRow][nextCol][idx] = isVisited[current.row][current.col][idx] + 1;
								
							if(nextRow == end.row && nextCol == end.col) return;
							
							dirX = DIRECTION[ROW];
							dirY = DIRECTION[COL];
								
							q.offer(new Point(nextRow, nextCol));
						}
					}
				}
			}
		}
	}
}
