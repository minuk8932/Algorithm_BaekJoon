package depth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 10870번: 피보나치 수 5
 *
 *	@see https://www.acmicpc.net/problem/10870/
 *
 */
public class Boj10870 {	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		System.out.println(fibonacci(N));		// 피보나치 메소드를 통한 결과 출력
	}
	
	/**
	 * 피보나치 함수 메소드
	 * 
	 */
	private static int fibonacci(int num) {
		if(num == 0) return 0;
		if(num == 1) return 1;
		
		return fibonacci(num - 1) + fibonacci(num - 2);		// F(n) = F(n - 1) + F(n - 2);
	}
}
