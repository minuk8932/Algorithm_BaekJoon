import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj4991 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";

	private static final char START = 'o';
	private static final char DIRT = '*';
	private static final char BLOCK = 'x';
	private static final char CLEAN = '.';

	private static final int[][] DIRECTIONS = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	private static final int ROW = 0;
	private static final int COL = 1;
	private static final int MAX = 30;
	
	private static Point start = null;
	private static Point[] dust = new Point[10];
	private static char[][] map = null;
	private static int w = 0;
	private static int h = 0;

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());

			if (h == 0 && w == 0) {
				break;
			}

			int dustCnt = 0;
			map = new char[MAX][MAX];

			for (int i = 0; i < h; i++) {
				String line = br.readLine();
				for (int j = 0; j < w; j++) {
					map[i][j] = line.charAt(j);

					if (map[i][j] == START) {
						start = new Point(i, j);
					}
					
					if(map[i][j] == DIRT) {
						dust[dustCnt] = new Point(i, j);
						dustCnt++;
					}
				}
			}
			
			
			
			sb.append(0).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());
	}
	
	private static class Point{
		int row;
		int col;
		
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	private static void bfsStart() {
		int[][] isVisited = new int[MAX][MAX];
		
		isVisited[start.row][start.col] = 1;
		Queue<Point> q = new LinkedList<>();
		
		q.offer(new Point(start.row, start.col));
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			
			for(final int[] DIRECTION : DIRECTIONS) {
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
				
				if(nextRow >= 0 && nextRow < h && nextCol >= 0 && nextCol < w) {
					if(isVisited[nextRow][nextCol] == 0 && map[nextRow][nextCol] != BLOCK) {
						isVisited[nextRow][nextCol] = isVisited[current.row][current.col] + 1;
						
						if(map[nextRow][nextCol] == DIRT) {
							
						}
						
						q.offer(new Point(nextRow, nextCol));
					}
				}
			}
		}
	}
}
