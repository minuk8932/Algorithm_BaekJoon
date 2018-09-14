package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 11772번: POT
 *
 *	@see https://www.acmicpc.net/problem/11772/
 *
 */
public class Boj11772 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int sum = 0;
		while(N-- > 0) {
			String num = br.readLine();
			
			int cutter = num.length() - 1;
			int under = Integer.parseInt(num.substring(0, cutter));		// 아랫수
			int upper = num.charAt(cutter) - '0';					// 지수 승
			
			sum += (int) Math.pow(under, upper);		// 원래식으로 고쳐서 합
		}
		
		System.out.println(sum);		// 결과 값 출력
	}
}
