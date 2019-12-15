package back_tracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 14502번: 연구소
 *
 *	@see https://www.acmicpc.net/problem/14502/
 *
 */
public class Boj14502 {
	private static final int BLOCK = 1;
	private static final int VIRUS = 2;
	private static final int SAFE = 0;
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0, COL = 1;
	
	private static int N, M;
	private static boolean[][][] visit;
	private static boolean[] used;
	private static int[] list = new int[3];
	
	private static int result = 0;
	
	private static class Point{
		int row;
		int col;
		
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		visit = new boolean[N * M][N * M][N * M];
		used = new boolean[N * M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < N * M; i++) {
			if(map[i / M][i % M] != SAFE) continue;
			backTracking(map, 0, i);					// located blocks
		}
		
		System.out.println(result);
	}
	
	private static void backTracking(int[][] arr, int count, int current) {
		if(used[current]) return;
		used[current] = true;
		list[count] = current;
		
		if(count == 2) {
			if(visit[list[0]][list[1]][list[2]]) return;												// remove duplicated
			visit[list[0]][list[1]][list[2]] = visit[list[0]][list[2]][list[1]]
					= visit[list[1]][list[2]][list[0]] = visit[list[1]][list[0]][list[2]]
					= visit[list[2]][list[1]][list[0]] = visit[list[2]][list[0]][list[1]] = true;
			
			int area = bfs(arr);																		// find safe area
			if(area > result) result = area;
			
			return;
		}
		
		for(int next = 0; next < N * M; next++) {
			if(used[next] || arr[next / M][next % M] != SAFE) continue;
			
			backTracking(arr, count + 1, next);
			used[next] = false;
		}
	}
	
	private static int bfs(int[][] arr) {
		Queue<Point> q = new LinkedList<>();
		int[][] lab = new int[N][M];
		
		for(int row = 0; row < N; row++) {
			for(int col = 0; col < M; col++) {
				if(arr[row][col] == VIRUS) q.offer(new Point(row, col));
				lab[row][col] = arr[row][col];
			}
		}
		
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			
			for(final int[] DIRECTION: DIRECTIONS) {
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
				
				if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M || isBoundary(nextRow, nextCol)) continue;
				if(lab[nextRow][nextCol] == BLOCK || lab[nextRow][nextCol] == VIRUS) continue;
				lab[nextRow][nextCol] = VIRUS;
				
				q.offer(new Point(nextRow, nextCol));
			}
		}
		
		int count = 0;
		
		for(int row = 0; row < N; row++) {
			for(int col = 0; col < M; col++) {
				if(lab[row][col] == SAFE && !isBoundary(row, col)) count++;
			}
		}
		
		return count;
	}
	
	private static boolean isBoundary(int row, int col) {
		for(int idx: list) {
			if(idx / M == row && idx % M == col) return true;
		}
		
		return false;
	}
}
