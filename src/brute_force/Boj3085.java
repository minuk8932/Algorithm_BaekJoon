package brute_force;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * 
 * 	@author minchoba
 *	백준 3085번: 사탕 게임
 *
 *	@see https://www.acmicpc.net/problem/3085/
 *
 */
public class Boj3085 {
	private static ArrayList<Pair> set = new ArrayList<>();
	private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}};
	private static final int ROW = 0, COL = 1;
	
	private static boolean[][] isVisited;
	private static char[][] fix;
	
	private static class Point{
		int row, col;
		
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	private static class Pair{
		Point p1, p2;
		
		public Pair(Point p1, Point p2) {
			this.p1 = p1;
			this.p2 = p2;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		char[][] candy = new char[N][N];
		fix = new char[N][N];
		
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < N; j++) {
				candy[i][j] = line.charAt(j);
				fix[i][j] = candy[i][j];
			}
		}
		
		init(N, candy);							// 인접 배열끼리 리스트에 저장
		System.out.println(getMax(N, candy));
	}
	
	private static void init(int n, char[][] arr) {
		for(int row = 0; row < n; row++) {
			for(int col = 0; col < n; col++) {
				for(final int[] DIRECTION: DIRECTIONS) {
					int nextRow = row + DIRECTION[ROW];
					int nextCol = col + DIRECTION[COL];
					
					if(nextRow >= n || nextCol >= n || arr[row][col] == arr[nextRow][nextCol]) continue;
					set.add(new Pair(new Point(row, col), new Point(nextRow, nextCol)));
				}
			}
		}
	}
	
	private static int getMax(int n, char[][] arr) {
		int max = 0;
		
		for(Pair p: set) {
			int value = process(n, arr, p);
			if(value > max) max = value;		// 최대 사탕 갯수
		}
		
		return max;
	}
	
	private static int process(int n, char[][] arr, Pair p) {
		isVisited = new boolean[n][n];
		int max = 0;
		
		Point p1 = p.p1;
		Point p2 = p.p2;
		
		char tmp = arr[p1.row][p1.col];					// 사탕 위치 바꾸기
		arr[p1.row][p1.col] = arr[p2.row][p2.col];
		arr[p2.row][p2.col] = tmp;
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(isVisited[i][j]) continue;
				isVisited[i][j] = true;
				
				int cost = search(n, arr, new Point(i, j), true);		// 행을 기준으로 같은 사탕의 갯수
				if(cost > max) max = cost;
				
				cost = search(n, arr, new Point(i, j), false);			// 열을 기준으로 같은 사탕의 갯수
				if(cost > max) max = cost;
			}
		}
		
		for(int i = 0; i < n; i++) {				// 탐색 완료 후 배열 초기화
			for(int j = 0; j < n; j++) {
				arr[i][j] = fix[i][j];
			}
		}
		
		return max;
	}
	
	private static int search(int n, char[][] arr, Point current, boolean d) {
		Point move = new Point(d ? current.row + 1 : current.row, d ? current.col: current.col + 1);
		int count = 1;
		
		while(move.row < n && move.col < n && arr[move.row][move.col] == arr[current.row][current.col]) {
			isVisited[move.row][move.col] = true;
			count++;
			
			if(d) move.row++;
			else move.col++;
		}
		
		move = new Point(d ? current.row - 1 : current.row, d ? current.col: current.col - 1);
		
		while(move.row >= 0 && move.col >= 0 && arr[move.row][move.col] == arr[current.row][current.col]) {
			isVisited[move.row][move.col] = true;
			count++;
			
			if(d) move.row--;
			else move.col--;
		}
		
		return count;
	}
}
