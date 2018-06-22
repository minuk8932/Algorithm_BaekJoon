package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 14623번: 감정이입
 *
 *	@see https://www.acmicpc.net/problem/14623/
 *
 */
public class Boj14623 {
	public static void main(String[] args) throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] bin1 = br.readLine().toCharArray();
		char[] bin2 = br.readLine().toCharArray();
		
		long val1 = 0, val2 = 0;
		for(long i = bin1.length - 1; i >= 0; i--) {		// 2진수를 10진수로 계산
			if((bin1[(int) i] - '0') == 1) {
				val1 += Math.pow(2, bin1.length - 1 - i);
			}
		}
		
		for(long i = bin2.length - 1; i >= 0; i--) {
			if((bin2[(int) i] - '0') == 1) {
				val2 += Math.pow(2, bin2.length - 1 - i);
			}
		}		
		
		System.out.println(Long.toBinaryString((val1 * val2)));	// 계산한 값을 2진수로 출력
	}
}
