package depth_first_search;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 14920번: 3n+1 수열
 *
 *	@see https://www.acmicpc.net/problem/14920/
 *
 */
public class Boj14920 {
	private static int times = 0;
	
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cn = Integer.parseInt(br.readLine());
		
		System.out.println(recursion(cn));		// 재귀 함수를 통한 결과 값 출력
	}
	
	/**
	 * 재귀 호출 메소드
	 * 	
	 */
	private static int recursion(int num) {
		times++;
		if(num == 1) return times;				// 최초 1이 나온경우 값 반환
		
		if(num % 2 == 0) return recursion(num / 2);		// 짝수인 경우 재귀 호출
		else return recursion(num * 3 + 1);				// 홀수인 경우 재귀 호출
	}
}
