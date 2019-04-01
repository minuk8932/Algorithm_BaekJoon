package brute_force;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 2160번: 그림 비교
 *
 *	@see https://www.acmicpc.net/problem/2160/
 *
 */
public class Boj2160 {
	private static final String SPACE = " ";
	private static final char DOT = '.';
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		boolean[][][] arr = new boolean[N][5][7];
		
		for(int i = 0; i < N; i++) {
			for(int x = 0; x < 5; x++) {
				String line = br.readLine();
				
				for(int y = 0; y < 7; y++) {
					if(line.charAt(y) == DOT) arr[i][x][y] = true;
					else arr[i][x][y] = false;
				}
			}
		}
		
		comparison(N, arr);
	}
	
	private static void comparison(int n, boolean[][][] paint) {
		int x = -1, y = -1;
		int min = Integer.MAX_VALUE;
		
		for(int i = 0; i < n; i++) {
			for(int j = i + 1; j < n; j++) {
				int diff = getPair(paint[i], paint[j]);
				
				if(diff < min) {
					min = diff;
					x = i + 1;
					y = j + 1;
				}
			}
		}
		
		System.out.println(x + SPACE + y);
	}
	
	private static int getPair(boolean[][] p1, boolean[][] p2) {
		int count = 0;
		
		for(int row = 0; row < 5; row++) {
			for(int col = 0; col < 7; col++) {
				if(p1[row][col] ^ p2[row][col]) count++;		// 그림이 다른 경우
			}
		}
		
		return count;
	}
}
