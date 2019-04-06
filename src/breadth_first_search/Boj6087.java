package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 6087번: 레이저 통신
 *
 *	@see https://www.acmicpc.net/problem/6087/
 *
 */
public class Boj6087 {
	private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	private static final int ROW = 0, COL = 1;
	
	private static final char TARGET = 'C';
	private static final char BLOCK = '*';
	
	private static class Point{
		int row;
		int col;
		int dir;
		
		public Point(int row, int col, int dir) {
			this.row = row;
			this.col = col;
			this.dir = dir;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		
		Point[] edge = new Point[2];
		int index = 0;
		
		char[][] map = new char[H][W];
		for(int i = 0; i < H; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < W; j++) {
				map[i][j] = line.charAt(j);
				if(map[i][j] == TARGET) edge[index++] = new Point(i, j, -1);
			}
		}
		
		System.out.println(bfs(H, W, map, edge));
	}
	
	private static int bfs(int h, int w, char[][] room, Point[] p) {		
		int[][] mirror = new int[h][w];
		
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(p[0].row, p[0].col, p[0].dir));
		
		mirror[p[0].row][p[0].col] = 0;
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			
			for(int i = 0; i < 4; i++) {
				int nextRow = current.row + DIRECTIONS[i][ROW];
				int nextCol = current.col + DIRECTIONS[i][COL];
				
				while(nextRow >= 0 && nextRow < h && nextCol >= 0 && nextCol < w && room[nextRow][nextCol] != BLOCK) {		// 직진가능한 경우 계속 달림
					if(mirror[nextRow][nextCol] == 0) {
						mirror[nextRow][nextCol] = mirror[current.row][current.col] + 1;		// 아직 0인 값들은 출발 지점의 값 + 1 (방향이 바뀐 후 반복이므로)
							
						q.offer(new Point(nextRow, nextCol, i));
					}
					
					nextRow += DIRECTIONS[i][ROW];
					nextCol += DIRECTIONS[i][COL];
				}
			}
		}
		
		return mirror[p[1].row][p[1].col] - 1;		// 초기 무조건 방향이 다르게 설정되었으므로 -1
	}
}
