package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 10711번: 모래성
 *
 *	@see https://www.acmicpc.net/problem/10711/
 *
 */
public class Boj10711 {
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, 
			{1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		
		char[][] sand = new char[H][W];
		for(int i = 0; i < H; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < W; j++) {
				sand[i][j] = line.charAt(j);
			}
		}
		
		bfs(H, W, sand);
	}
	private static class Point{
		int row;
		int col;
		
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	private static void bfs(int h, int w, char[][] map) {		
		Queue<Point> q = new LinkedList<>();
		int[][] isVisited = new int[h][w];
		
		for(int row = 0; row < h; row++) {
			for(int col = 0; col < w; col++) {			
				int tmp = 0;
				
				if(map[row][col] != '.' && map[row][col] != '9') {
					for(final int[] DIRECTION: DIRECTIONS) {
						int nextRow = row + DIRECTION[ROW];
						int nextCol = col + DIRECTION[COL];
							
						if(map[nextRow][nextCol] == '.') tmp++;
					}
					
					if(tmp >= (map[row][col] - '0')) {		// 시작점, 첫 파도에 없어질 모래 높이
						q.offer(new Point(row, col));
						isVisited[row][col] = 1;
					}
				}
			}
		}
		
		while(!q.isEmpty()) {
			Point current = q.poll();				
			
			map[current.row][current.col] = '.';			// 모래 무너짐
				
			for(final int[] DIRECTION: DIRECTIONS) {
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
					
				if(!(nextRow >= 0 && nextRow < h && nextCol >= 0 && nextCol < w)) continue;
				if(map[nextRow][nextCol] != '.' && isVisited[nextRow][nextCol] == 0) {		// 인접한곳에 모래가 존재하는 곳 마다
					int counts = 0;
					
					for(final int[] N_DIRECTION: DIRECTIONS) {
						int nnextRow = nextRow + N_DIRECTION[ROW];
						int nnextCol = nextCol + N_DIRECTION[COL];
						
						if(nnextRow >= 0 && nnextRow < h && nnextCol >= 0 && nnextCol < w) {		// 주변에 빈공간의 갯수 카운트
							if(map[nnextRow][nnextCol] == '.') counts++;
						}
					}
					
					if(counts >= map[nextRow][nextCol] - '0') {
						isVisited[nextRow][nextCol] = isVisited[current.row][current.col] + 1;		// 무너질 수 있는 경우
						q.offer(new Point(nextRow, nextCol));
					}
				}
			}
		}
		
		printMax(h, w, isVisited);
	}
	
	private static void printMax(int h, int w, int[][] arr) {
		int max = 0;
		
		for(int i = 0; i < h; i++) {
			for(int j = 0; j < w; j++) {
				if(arr[i][j] > max) max = arr[i][j];
			}
		}
		
		System.out.println(max);
	}
}
