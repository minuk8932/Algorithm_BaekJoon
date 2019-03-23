package brute_force;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2578번: 빙고
 *
 *	@see https://www.acmicpc.net/problem/2578/
 *
 */
public class Boj2578 {
	private static int[][] map;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map = new int[5][5];
		for(int i = 0; i < 5; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < 5; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] call = new int[5][5];
		for(int i = 0; i < 5; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < 5; j++) {
				call[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(bingo(call));
	}
	
	private static int bingo(int[][] call) {
		int count = 0;
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				int target = call[i][j];
				remove(target);					// 해당 숫자를 하나씩 지워줌
				count++;
				
				if(bingoCount() >= 3) return count;		// 3 빙고인 경우
			}
		}
		
		
		return count;
	}
	
	private static void remove(int t){
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				if(t == map[i][j]) map[i][j] = -1;
			}
		}
	}
	
	private static int bingoCount() {
		int count = 0;
		
		count += diagonal(0, 1);			// 대각선 2개
		count += diagonal(4, -1);
		
		for(int i = 0; i < 5; i++) {			// 가로 빙고
			boolean isBingo = true;
			
			for(int j = 0; j < 5; j++) {
				if(map[i][j] != -1) isBingo = false;
			}
			
			if(isBingo) count++;
		}
		
		for(int i = 0; i < 5; i++) {			// 세로 빙고
			boolean isBingo = true;
			
			for(int j = 0; j < 5; j++) {
				if(map[j][i] != -1) isBingo = false;
			}
			
			if(isBingo) count++;
		}
		
		return count;
	}
	
	private static int diagonal(int rev, int adder) {
		boolean isBingo = true;
		
		for(int i = 0; i < 5; i++) {
			if(map[rev + adder * i][i] != -1) isBingo = false;
		}
		
		return isBingo ? 1: 0;
	}
}
