package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 12755번: 수면 장애
 *
 *	@see https://www.acmicpc.net/problem/12755/
 *
 */
public class Boj12755 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		System.out.println(nthNumber(N));
	}
	
	private static char nthNumber(int nth) {
		int cipher = 1;
		int theNum = 0, tmp = 0, size = 9;
		
		while(theNum < nth) {
			tmp = theNum;
			theNum += (size * cipher);		// 이전 자리의 숫자 갯수까지 구함
			size *= 10;
			
			cipher++;
		}
		
		int value = (int) Math.pow(10, --cipher - 1);		// 해당 자릿수
		int idx = nth - tmp - 1;
		
		value += idx / cipher;				// 그 숫자의 값
		idx %= cipher;						// 숫자에서 idx번째
		
		return String.valueOf(value).charAt(idx);		// 숫자의 해당 번째 값을 반환
	}
}
