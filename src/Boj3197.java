import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj3197 {
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0;
	private static final int COL = 1;
	private static final int INF = 100_000_000;
	private static final char SWAN = 'L';
	private static final char WATER = '.';
	private static final char ICE = 'X';
	
	private static char[][] map = null;
	private static char[] fill = new char[2];
	private static int R = 0;
	private static int C = 0;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		for(int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		fill[0] = 'A';
		fill[1] = 'B';
		
		fillSwan();
		int res = distance();
				
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
	
	private static void fillSwan() {
		boolean[][] isVisited = new boolean[R][C];
		int idx = 0;
		
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(map[i][j] == SWAN && !isVisited[i][j]) {
					isVisited[i][j] = true;
					map[i][j] = fill[idx];
					
					Queue<Point> q = new LinkedList<>();
					q.offer(new Point(i, j));
					
					while(!q.isEmpty()) {
						Point current = q.poll();
						
						for(final int[] DIRECTION : DIRECTIONS) {
							int nextRow = current.row + DIRECTION[ROW];
							int nextCol = current.col + DIRECTION[COL];
							
							if(nextRow >= 0 && nextRow < R && nextCol >= 0 && nextCol < C) {
								if(!isVisited[nextRow][nextCol] && map[nextRow][nextCol] == WATER) {
									map[nextRow][nextCol] = fill[idx];
									isVisited[nextRow][nextCol] = true;
									
									q.offer(new Point(nextRow, nextCol));
								}
							}
						}
					}
					
					idx++;
				}
			}
		}
	}
	
	private static int distance() {
		int maxIce = 0;
		
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(map[i][j] == fill[0]) {					
					int[][] isVisited = new int[R][C];
					isVisited[i][j] = 1;
					
					Queue<Point> q = new LinkedList<>();
					q.offer(new Point(i, j));
					
					while(!q.isEmpty()) {
						Point current = q.poll();
						
						for(final int[] DIRECTION : DIRECTIONS) {
							int nextRow = current.row + DIRECTION[ROW];
							int nextCol = current.col + DIRECTION[COL];
							
							if(nextRow >= 0 && nextRow < R && nextCol >= 0 && nextCol < C) {
								if(isVisited[nextRow][nextCol] == 0) {
									if(map[nextRow][nextCol] == fill[1]) break;
									
									if(map[nextRow][nextCol] != fill[1]) {
										if(map[nextRow][nextCol] == ICE) {
											isVisited[nextRow][nextCol] = isVisited[current.row][current.col] + 1;
										}
										
										if(map[nextRow][nextCol] == WATER) {
											isVisited[nextRow][nextCol] = 1;
											maxIce = Math.max(maxIce, isVisited[current.row][current.col] - 1);
										}
										
										q.offer(new Point(nextRow, nextCol));
									}
								}
							}
						}
					}
				}
			}
		}	
		
		return maxIce % 2 == 0 ? maxIce / 2 : maxIce / 2 + 1;
	}
}
