package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 2588번 : 곱셈
 *
 *	@see https://www.acmicpc.net/problem/2588/
 *
 */
public class Boj2588 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int a = Integer.parseInt(br.readLine());
		int b = Integer.parseInt(br.readLine());
		
		int[] res = new int[3];
		
		System.out.println(res[0] = a * (b % 10));						// 숫자 b의 1의 자리수와 a를 곱한 값을 출력하며 결과 배열 첫번째에 담음
		System.out.println(res[1] = a * ((b % 100) / 10));				// 숫자 b의 10의 자리수와 a를 곱한 값을 출력하며 결과 배열 두번째에 담음
		System.out.println(res[2] = a * (b / 100));						// 숫자 b의 100의 자리수와 a를 곱한 값을 출력하며 결과 배열 세번째에 담음
		System.out.println(res[0] + res[1] * 10 + res[2] * 100);		// 각 결과 배열의 값에서 곱셈하듯이 10^0, 10^1, 10^2을 각각 곱한 후 더한 값을 출력
	}
}
