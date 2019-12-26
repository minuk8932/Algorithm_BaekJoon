package greedy;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author exponential-e
 *	백준 17615번: 볼 모으기
 *
 *	@see https://www.amicpc.net/problem/17615/
 *
 */
public class Boj17615 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		System.out.println(calculation(N, br.readLine()));
	}
	
	private static int calculation(int n, String balls) {
		char[] ball = balls.toCharArray();
		
		int[] total = {0, 0};
		for(int i = 0; i < n; i++) {
			if(ball[i] == 'B') total[1]++;
			else total[0]++;
		}
		
		return Math.min(Math.min(reversed('B', ball, total[0]), origin('B', ball, total[0])),	// find minimum from each sides 
				Math.min(reversed('R', ball, total[1]), origin('R', ball, total[1])));
	}
	
	private static int reversed(char color, char[] arr, int total) {
		int count = 0;
		
		for(int i = arr.length - 1; i >= 0; i--) {
			if(arr[i] == color) break;
			count++;
		}
		
		return total - count;
	}
	
	private static int origin(char color, char[] arr, int total) {
		int count = 0;
		
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] == color) break;
			count++;
		}
		
		return total - count;
	}
}
