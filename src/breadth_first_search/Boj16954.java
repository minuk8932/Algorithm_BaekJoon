package breadth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * 	@author minchoba
 *	백준 16954번: 움직이는 미로 탈출
 *
 *	@see https://www.acmicpc.net/problem/16954/
 *
 */
public class Boj16954 {
	private static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {-1, -1}, {1, -1}, {-1, 1}, {1, 1}, {0, 0}};
	private static final int ROW = 0, COL = 1;
	
	private static boolean[][] map = new boolean[8][8];
	private static ArrayList<Point> save;
	
	private static class Point{
		int row, col;
		
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i = 0; i < 8; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < 8; j++) {
				map[i][j] = line.charAt(j) == '.' ? true: false;
			}
		}
		
		System.out.println(bfs());
	}
	
	private static int bfs() {
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(7, 0));
		
		while(!q.isEmpty()) {
			int size = q.size();
			init();				// 움직여야 할 벽의 데이터를 저장
			
			while(size-- > 0) {
				Point current = q.poll();
				
				for(final int[] DIRECTION: DIRECTIONS) {
					int nextRow = current.row + DIRECTION[ROW];
					int nextCol = current.col + DIRECTION[COL];
					
					if(nextRow < 0 || nextRow > 7 || nextCol < 0 || nextCol > 7) continue;
					if(!map[nextRow][nextCol] || dead(new Point(nextRow, nextCol))) continue;		// 깔려죽거나, 다음 경로가 벽인경우
					
					if(nextRow == 0 && nextCol == 7) return 1;		// 도착
					q.offer(new Point(nextRow, nextCol));
				}
			}
			
			remake();		// 벽을 1칸씩 아래로 내리기
		}
		
		return 0;
	}
	
	private static void init() {
		save = new ArrayList<>();
		
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				int next = i + 1;
				if(next > 7) continue;
				
				if(!map[i][j]) save.add(new Point(next, j));
			}
		}
	}
	
	private static boolean dead(Point p) {		
		boolean flag = false;
		
		for(Point renew: save) {
			if(renew.row == p.row && renew.col == p.col) flag = true;
		}

		return flag;
	}
	
	private static void remake() {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				map[i][j] = true;
			}
		}
		
		for(Point remake: save) {
			map[remake.row][remake.col] = false;
		}
	}
}
