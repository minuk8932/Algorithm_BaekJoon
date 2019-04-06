package brute_force;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1895번: 필터
 *
 *	@see https://www.acmicpc.net/problem/1895/
 *
 */
public class Boj1895 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[R][C];
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int T = Integer.parseInt(br.readLine());
		System.out.println(biggerCount(R, C, map, T));
	}
	
	private static int biggerCount(int r, int c, int[][] img, int target) {
		int count = 0;
		
		for(int i = 0; i < r - 2; i++) {
			for(int j = 0; j < c - 2; j++) {
				if(filtering(i, j, img) >= target) count++;
			}
		}
		
		return count;
	}
	
	private static int filtering(int x, int y, int[][] img) {
		ArrayList<Integer> values = new ArrayList<>();
		
		for(int row = x; row < x + 3; row++) {
			for(int col = y; col < y + 3; col++) {
				values.add(img[row][col]);
			}
		}
		
		Collections.sort(values);
		return values.get(4);
	}
}
