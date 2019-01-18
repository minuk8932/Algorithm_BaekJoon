package binary_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 2417번: 정수 제곱근
 *
 *	@see https://www.acmicpc.net/problem/2417/
 *
 */
public class Boj2417 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long n = Long.parseLong(br.readLine());
		
		System.out.println(binarySearch(n));
	}
	
	private static long binarySearch(long N) {		
		long left = 1, right = N / 2;
		long mid = 0;
		
		while(left <= right) {
			mid = (left + right) / 2;
			
			long target = mid * mid;
			
			if(target > N || target <= 0) right = mid - 1;		// 오버플러우 방지
			else if(target < N) left = mid + 1;
			else break;
		}
		
		if(N > mid * mid) mid++;			// 찾은 제곱근이 실제 입력 값보다 작은경우
		
		return mid;
	}
}
