package back_tracking;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 1799번: 비숍
 *
 *	@see https://www.acmicpc.net/problem/1799/
 *
 */
public class Boj1799 {
	private static int N = 0;
	private static int[] result = new int[2];
	
	private static boolean[][] blocked;
	private static boolean[] bishop;
	
	private static final int[][] DIRECTIONS = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
	private static final int ROW = 0, COL = 1;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		blocked = new boolean[N][N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < N; j++) {
				int val = Integer.parseInt(st.nextToken());
				if(val == 0) blocked[i][j] = true;
			}
		}
		
		int len = N * N;
		
		bishop = new boolean[len];
		backTracking(0, 0, 0);			// black
		backTracking(1, 1, 0);			// white
		
		System.out.println(result[0] + result[1]);
	}

	private static void backTracking(int start, int current, int count){
		if(count > result[start]) result[start] = count;				// get max
		
		if(current >= bishop.length) return;
		if(bishop[current]) return;
		
		int adder = make(current, start);								// if num is even convert adder
		
		if(isPossible(current) && !blocked[current / N][current % N]) {
			bishop[current] = true;
			
			backTracking(start, current + 2 + adder, count + 1);
			bishop[current] = false;
		}
		
		backTracking(start, current + 2 + adder, count);
	}
	
	private static boolean isPossible(int p){							// compare current location
		for(final int[] DIRECTION: DIRECTIONS) {
			int nextRow = p / N + DIRECTION[ROW];
			int nextCol = (p % N) + DIRECTION[COL];
			
			for(int t = 0; t < N; t++) {
				if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N) continue;
				if(bishop[nextRow * N + nextCol]) return false;
				
				nextRow += DIRECTION[ROW];
				nextCol += DIRECTION[COL];
			}
		}
		
		return true;
	}
	
	private static int make(int c, int s) {
		if(N % 2 == 0) {
			if((c / N) % 2 == 0) {
				if(s == 0 && (c + 2) % N == 0) return 1;					// even row last col black
				if(s == 1 && (c + 1) % N == 0) return -1;					// even row last col white
			}
			else {
				if(s == 0 && (c + 1) % N == 0) return -1;					// odd row last col black
				if(s == 1 && (c + 2) % N == 0) return 1;					// odd row last col white
			}
		}
		
		return 0;
	}
}
