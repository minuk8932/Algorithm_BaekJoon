package math;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedReader;

/**
 * 
 * 	@author minchoba
 *	백준 2475번: 검증수
 *
 *	@see https://www.acmicpc.net/problem/2475/
 *
 */
public class Boj2475 {
	private static final int MOD = 10;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int sum = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 5; i++) {
			int tmp = Integer.parseInt(st.nextToken());
			sum += (tmp * tmp);			// 제곱수의 합
		}
		
		System.out.println(sum % MOD);	// 나머지 연산을 통한 검증수 출력
	}
}
