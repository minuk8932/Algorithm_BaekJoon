package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 1550번: 16진수
 *
 *	@see https://www.acmicpc.net/problem/1550/
 *
 */
public class Boj1550 {
	private static final int HEX = 16;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] word = br.readLine().toCharArray();
		
		System.out.println(hexToDex(word));		// 결과 출력
	}
	
	private static int hexToDex(char[] w) {
		int res = 0;
		
		for(int i = w.length - 1; i >= 0; i--) {
			int num = w[i] - '0';
			if(w[i] >= 'A' && w[i] <= 'F') {
				num = (w[i] - 'A') + 10;
			}
			
			res += Math.pow(HEX, (w.length - 1 - i)) * num;	// 자리 별 16진수 -> 10진수
		}
		
		return res;
	}
}
