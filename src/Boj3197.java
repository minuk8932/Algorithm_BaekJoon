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
	private static int[][] dist = null;
	private static int R = 0;
	private static int C = 0;
	private static int minDist = INF;
	
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
		
		dist = new int[R][C];
		bfs();
		
				
		System.out.println(minDist);
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
	
	private static void bfs() {
		boolean once = false;
		
		for(int row = 0; row < R; row++) {
			for(int col = 0; col < C; col++) {
				if(map[row][col] == 'A' && dist[row][col] == 0) {
					int min = Integer.MAX_VALUE;
					dist[row][col] = 1;
					
					Queue<Point> q= new LinkedList<>();
					q.offer(new Point(row, col));
					
					while(!q.isEmpty()) {
						Point current = q.poll();
						
						for(final int[] DIRECTION : DIRECTIONS) {
							int nextRow = current.row + DIRECTION[ROW];
							int nextCol = current.col + DIRECTION[COL];
							
							if(nextRow >= 0 && nextRow < R && nextCol >= 0 && nextCol < C) {
								if(dist[nextRow][nextCol] == 0) {
									if(map[nextRow][nextCol] == ICE) {
										dist[nextRow][nextCol] = dist[current.row][current.col] + 1;
										once = false;
										
										q.offer(new Point(nextRow, nextCol));
									}
									
									else if(map[nextRow][nextCol] == WATER) {
										dist[nextRow][nextCol] = 1;
										
										if(!once) {
											min = Math.min(min, dist[current.row][current.col]);
											once = true;
										}
										
										q.offer(new Point(nextRow, nextCol));
									}
								}
								
								if(map[nextRow][nextCol] == 'B') {
									int tmp = 0;
									
									if(min % 2 == 0) {
										tmp = min / 2;
									}
									else {
										tmp = min / 2 + 1;
									}
									
									once = false;
									minDist = min == 0 ? 0 : Math.min(minDist, tmp);
								}
							}
						}
					}
				}
			}
		}
	}
}
