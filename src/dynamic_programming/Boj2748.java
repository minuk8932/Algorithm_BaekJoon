package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 2748번: 피보나치 수2
 *
 *	@see https://www.acmicpc.net/problem/2748/
 *
 */
public class Boj2748 {
	private static final int INF = 92;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		long[] fibo = new long[INF];
		fibo[2] = 1;						// fibo[1] = 0, fibo[2] = 1;
		for(int i = 3; i < n + 2; i++) {
			fibo[i] = fibo[i - 1] + fibo[i - 2];		// 3부터는 피보나치 수열의 성질을 통해 값을 저장
		}
		
		System.out.println(fibo[n + 1]);		// n + 1 번째 인덱스의 값, 피보나치 n번째 수를 출력
	}
}
