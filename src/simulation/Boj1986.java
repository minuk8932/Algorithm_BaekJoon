package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1986번: 체스
 *
 *	@see https://www.acmicpc.net/problem/1986/
 *
 */
public class Boj1986 {
	private static final int QUEEN = -1;
	private static final int KNIGHT = -2;
	private static final int PAWN = -3;
	
	private static final int[][][] DIRECTIONS = {{{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {-1, -1}, {-1, 1}, {1, -1}}, 
			{{1, 2}, {2, 1}, {-1, 2}, {2, -1}, {-1, -2}, {-2, -1}, {-2, 1}, {1, -2}}};
	
	private static int[][] chess;
	private static ArrayList<Point>[] list = new ArrayList[2];
	
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
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		chess = new int[n][m];
		input(br.readLine(), QUEEN);
		input(br.readLine(), KNIGHT);
		input(br.readLine(), PAWN);
		
		System.out.println(detected(n, m));
	}
	
	private static void input(String in, int type) {
		StringTokenizer st = new StringTokenizer(in);
		int idx = -(type + 1);
		if(idx != 2) list[idx] = new ArrayList<>();
		
		int count = Integer.parseInt(st.nextToken());
		while(count-- > 0) {
			int row = Integer.parseInt(st.nextToken()) - 1;
			int col = Integer.parseInt(st.nextToken()) - 1;
			
			chess[row][col] = type;
			if(idx != 2) list[idx].add(new Point(row, col));
		}
	}
	
	private static int detected(int N, int M) {							// 이동 경로 > 0, 다른 말이 위치한 경우 < 0
		for(int idx = 0; idx < 2; idx++) {
			for(Point current: list[idx]) {
				if(idx == 0) {											// 퀸의 위치
					for(int i = 0; i < 8; i++) {
						int nextRow = current.row + DIRECTIONS[idx][i][0];
						int nextCol = current.col + DIRECTIONS[idx][i][1];
						
						while(!isOutOfRange(N, M, nextRow, nextCol)) {
							if(chess[nextRow][nextCol] < 0) break;		// 다른 말이 있는 경우 이동 종료
							chess[nextRow][nextCol] = -QUEEN;
							
							nextRow += DIRECTIONS[idx][i][0];			// 퀸이 움직이는 경로에 값을 채워줌
							nextCol += DIRECTIONS[idx][i][1];
						}
					}
				}
				else {
					for(int i = 0; i < 8; i++) {						// 나이트의 위치
						int nextRow = current.row + DIRECTIONS[idx][i][0];
						int nextCol = current.col + DIRECTIONS[idx][i][1];
						
						if(isOutOfRange(N, M, nextRow, nextCol) || chess[nextRow][nextCol] < 0) continue;
						chess[nextRow][nextCol] = -KNIGHT;
					}
				}
			}
		}
		
		return getSafetyArea(N, M);				// 0이 존재하는 칸의 갯수
	}
	
	private static int getSafetyArea(int N, int M) {
		int count = 0;
		
		for(int row = 0; row < N; row++) {
			for(int col = 0; col < M; col++) {
				if(chess[row][col] == 0) count++;
			}
		}
		
		return count;
	}
	
	private static boolean isOutOfRange(int N, int M, int row, int col) {
		return row < 0 || row >= N || col < 0 || col >= M ? true: false;
	}
}
