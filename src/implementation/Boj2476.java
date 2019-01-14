package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2476번: 주사위 게임
 *
 *	@see https://www.acmicpc.net/problem/2476/
 *
 */
public class Boj2476 {
	private static final int MONEY = 10_000;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] dice = new int[N][7];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < 3; j++) {
				dice[i][Integer.parseInt(st.nextToken())]++;
			}
		}
		
		System.out.println(getPrize(N, dice));
	}
	
	private static int getPrize(int n, int[][] arr) {
		int max = 0;
		
		for(int i = 0; i < n; i++) {
			int sum = getSum(arr[i]);
			if(sum > max) max = sum;
		}
		
		return max;
	}
	
	private static int getSum(int[] arr) {
		int max = 0;
		
		for(int i = 1; i < arr.length; i++) {			
			int sum = MONEY + i * (MONEY / 10);
			
			if(arr[i] == 3) return sum;				// 동일 숫자 3개
			else if(arr[i] == 2) return sum / 10;	// 동일 숫자 2개
			else {
				if(i > max) max = i;
			}
		}
		
		return max * 100;
	}
}
