package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author exponential-e
 *	백준 15897번: 잘못 구현한 에라토스테네스의 체
 *
 *	@see https://www.acmicpc.net/problem/15897/
 *
 */
public class Boj15897 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		System.out.println(counting(N));
	}
	
	private static long counting(long n) {
		if(n == 1) return 1;
		
		long count = n;
		
		for(long i = 2; i < n; i++) {				// n번째 항은 걸러내고 반환시 +1
			long tmp = (n - 1) / i;
			long val = (n - 1) / tmp;
			
			count += (tmp + 1) * (val - i + 1);		// 규칙성 있는 공식에 따라 값 구하기
			i = val;
		}
		
		return count + 1;
	}
}
