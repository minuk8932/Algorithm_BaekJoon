import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj15530 {
	private static final char PRINCESS = '@';
	private static final char SOLDIER = '$';
	private static final char ESCAPE_HATCH = '%';
	private static final char WALL = '#';
	
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1 ,0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	private static final String ESCAPE = "YES";
	private static final String ARRESTED = "NO";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[H][W];
		Point princessPoint = new Point(0, 0);
		LinkedList<Point> soldierPoint = new LinkedList<>();
		int soldierCnt = 0;
		
		for(int i = 0; i < H; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < W; j++) {
				map[i][j] = line.charAt(j);
				
				if(map[i][j] == PRINCESS) princessPoint = new Point(i, j);
				
				if(map[i][j] == SOLDIER) {
					soldierCnt++;
					soldierPoint.add(new Point(i , j));
				}
			}
		}
		
		String res = ESCAPE;
		
		if(soldierCnt > 0) {
			res = bfs(H, W, map, princessPoint, soldierPoint, soldierCnt);
		}
		
		System.out.println(res);
	}
	
	private static class Point{
		int row;
		int col;
		
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	private static String bfs(int h, int w, char[][] m, Point pp, LinkedList<Point> sp, int solCnt) {
		int[][] isVisitedP = new int[h][w];
		int soldiers = Integer.MAX_VALUE;
		
		Point esc = new Point(0, 0);
		
		// princess move
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(pp.row, pp.col));
		isVisitedP[pp.row][pp.col] = 1;
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			
			for(final int[] DIRECTION: DIRECTIONS) {
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
				
				if(nextRow >= 0 && nextRow < h && nextCol >= 0 && nextCol < w) {
					if(m[nextRow][nextCol] != WALL && isVisitedP[nextRow][nextCol] == 0) {
						isVisitedP[nextRow][nextCol] = isVisitedP[current.row][current.col] + 1;
						
						if(m[nextRow][nextCol] == ESCAPE_HATCH) break;
						
						q.offer(new Point(nextRow, nextCol));
					}
				}
			}
		}
		
		int[][] isVisited = new int[h][w];
		
		Point start = sp.removeFirst();
		
		q = new LinkedList<>();
		q.offer(new Point(start.row, start.col));
		isVisited[start.row][start.col] = 1;
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			
			for(final int[] DIRECTION: DIRECTIONS) {
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
				
				if(nextRow >= 0 && nextRow < h && nextCol >= 0 && nextCol < w) {
					if(m[nextRow][nextCol] != WALL && isVisited[nextRow][nextCol] == 0) {
						isVisited[nextRow][nextCol] = isVisited[current.row][current.col] + 1;
						
						if(m[nextRow][nextCol] == ESCAPE_HATCH) {
							esc = new Point(nextRow, nextCol);
							soldiers = Math.min(isVisited[nextRow][nextCol], soldiers);
							break;
						}
						
						q.offer(new Point(nextRow, nextCol));
					}
				}
			}
		}
		
		for(int s = 1; s < solCnt; s++) {
			start = sp.removeLast();
			
			q = new LinkedList<>();
			q.offer(new Point(start.row, start.col));
			isVisited[start.row][start.col] = 1;
			
			while(!q.isEmpty()) {
				Point current = q.poll();
				
				if(isVisited[current.row][current.col] >= soldiers) break;
				
				for(final int[] DIRECTION: DIRECTIONS) {
					int nextRow = current.row + DIRECTION[ROW];
					int nextCol = current.col + DIRECTION[COL];
					
					if(nextRow >= 0 && nextRow < h && nextCol >= 0 && nextCol < w) {
						if(m[nextRow][nextCol] != WALL && 
								(isVisited[nextRow][nextCol] == 0 || isVisited[nextRow][nextCol] > isVisited[current.row][current.col] + 1)) {
							isVisited[nextRow][nextCol] = isVisited[current.row][current.col] + 1;
							
							if(m[nextRow][nextCol] == ESCAPE_HATCH) {
								soldiers = Math.min(isVisited[nextRow][nextCol], soldiers);
								if(soldiers <= isVisitedP[nextRow][nextCol]) return ARRESTED;
							}
							
							q.offer(new Point(nextRow, nextCol));
						}
					}
				}
			}
		}
		
		return soldiers <= isVisitedP[esc.row][esc.col] ? ARRESTED : ESCAPE;
	}
}
