package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
 * 	@author minchoba
 *	백준 11653번: 소인수분해
 *
 *	@see https://www.acmicpc.net/problem/11653/
 *
 */
public class Boj11653 {
	private static final char NEW_LINE = '\n';
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		boolean[] prime = isPrime(N);
		System.out.println(getPrimeFactor(prime, N));		// 소인수 리스트 출력
	}
	
	private static boolean[] isPrime(int n) {		// N까지 소수 찾기
		boolean[] arr = new boolean[n + 1];
		
		Arrays.fill(arr, true);
		arr[0] = arr[1] = false;
		
		for(int i = 2; i < n + 1; i++) {
			if(!arr[i]) continue;
			
			for(int j = i + i; j < n + 1; j += i) {
				arr[j] = false;
			}
		}
		
		return arr;
	}
	
	private static StringBuilder getPrimeFactor(boolean[] arr, int n) {
		StringBuilder sb = new StringBuilder();
		int element = n;
		
		for(int i = 2; i < n + 1; i++) {
			if(!arr[i]) continue;				// 소수 중
			
			while(element % i == 0) {			// 해당 수가 N의 약수인 경우
				element /= i;
				sb.append(i).append(NEW_LINE);
			}
		}
		
		return sb;
	}
}
