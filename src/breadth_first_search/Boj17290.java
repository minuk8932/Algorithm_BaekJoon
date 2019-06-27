package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 17290번: Crazy_aRcade_Good
 *
 *	@see https://www.acmicpc.net/problem/17290/
 *
 */
public class Boj17290 {
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	private static final int ROW = 0, COL = 1;
	private static boolean[][] map;
	
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
		int r = Integer.parseInt(st.nextToken()) - 1;
		int c = Integer.parseInt(st.nextToken()) - 1;
		
		ArrayList<Point> bomb = new ArrayList<>();
		map = new boolean[10][10];
		
		for(int i = 0; i < 10; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < 10; j++) {
				map[i][j] = line.charAt(j) == 'x' ? false: true;
				if(map[i][j]) bomb.add(new Point(i, j));
			}
		}
		
		pop(bomb);									// 폭탄이 터진 상태로 바꿈
		System.out.println(bfs(new Point(r, c)));	// 폭탄이 닿지 않는 위치 최단거리
	}
	
	private static void pop(ArrayList<Point> list) {
		for(Point start: list) {
			int row = start.row;
			while(row + 1 < 10) {
				row++;
				map[row][start.col] = true;
			}
			
			int col = start.col;
			while(col + 1 < 10) {
				col++;
				map[start.row][col] = true;
			}
			
			row = start.row;
			while(row - 1 >= 0) {
				row--;
				map[row][start.col] = true;
			}
			
			col = start.col;
			while(col - 1 >= 0) {
				col--;
				map[start.row][col] = true;
			}
		}
	}
	
	private static int bfs(Point start) {
		if(!map[start.row][start.col]) return 0;
		
		ArrayList<Integer> val = new ArrayList<>();
		int[][] visit = new int[10][10];
		
		Queue<Point> q = new LinkedList<>();
		q.offer(start);
		
		visit[start.row][start.col] = 1;
		
		while(!q.isEmpty()) {
			Point current = q.poll();
			
			for(final int[] DIRECTION: DIRECTIONS) {
				int nextRow = current.row + DIRECTION[ROW];
				int nextCol = current.col + DIRECTION[COL];
				
				if(nextRow < 0 || nextCol < 0 || nextRow >= 10 || nextCol >= 10) continue;
				if(visit[nextRow][nextCol] != 0) continue;
				visit[nextRow][nextCol] = visit[current.row][current.col] + 1;
				
				if(!map[nextRow][nextCol]) val.add(visit[nextRow][nextCol] - 1);
				
				q.offer(new Point(nextRow, nextCol));
			}
		}
		
		Collections.sort(val);
		return val.get(0);
	}
}
