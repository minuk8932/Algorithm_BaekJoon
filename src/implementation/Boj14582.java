package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 14582번: 오늘도 졌다.
 *
 *	@see https://www.acmicpc.net/problem/14582/
 *
 */
public class Boj14582 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] play = new int[9][2];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 9; i++) {
			play[i][0] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 9; i++) {
			play[i][1] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(lose(play) ? "Yes" : "No");
	}
	
	private static boolean lose(int[][] arr) {
		boolean isWin = false;
		int[] result = {0, 0};
		
		for(int i = 0; i < 9; i++) {
			result[0] += arr[i][0];					// i회 초
			if(result[0] > result[1]) isWin = true;
			
			result[1] += arr[i][1];					// i회 말
		}
		
		return isWin && result[0] < result[1] ? true : false;
	}
}
