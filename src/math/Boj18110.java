package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
 * 	@author exponential-e
 *	백준 18110번: solved.ac
 *
 *	@see https://www.acmicpc.net/problem/18110/
 *
 */
public class Boj18110 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[] opi = new int[n];
		for(int i = 0; i < n; i++) {
			opi[i] = Integer.parseInt(br.readLine());
		}
		
		System.out.println(difficulty(n, opi));
	}
	
	private static int difficulty(int n, int[] arr) {
		double per = 3 / 20.0;
		double ex = Math.round(n * per);
		
		Arrays.sort(arr);
		double avg = 0;
		
		for(int i = (int) ex; i < arr.length - ex; i++) {			// make cutting average
			avg += arr[i];
		}
		
		avg /= (arr.length - (ex * 2));
		return (int) Math.round(avg);
	}
}
