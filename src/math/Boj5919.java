package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author exponential-e
 *	백준 5919번: Hay bale
 *
 *	@see https://www.acmicpc.net/problem/5919/
 *
 */
public class Boj5919 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] hay = new int[N];
		int sum = 0;
		
		for(int i = 0; i < N; i++) {
			hay[i] = Integer.parseInt(br.readLine());
			sum += hay[i];
		}
		
		System.out.println(getResult(hay, sum / N));
	}
	
	private static int getResult(int[] arr, int avg) {
		int result = 0;
	
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] > avg) result += (arr[i] - avg);			// move haies
		}
		
		return result;
	}
}
