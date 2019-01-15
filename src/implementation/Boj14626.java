package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 14626번: ISBN
 *
 *	@see https://www.acmicpc.net/problem/14626/
 *
 */
public class Boj14626 {
	private static final char ERASED = '*';
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] ISBN = br.readLine().toCharArray();
		
		System.out.println(getCode(ISBN));
	}
	
	private static int getCode(char[] arr) {
		int convert = 1;
		int checkSign = arr[arr.length - 1] - '0';
		
		int total = 0;
		
		for(int i = 0; i < arr.length - 1; i++) {
			if(ERASED == arr[i]) {
				if(i % 2 != 0) convert = 3;			// 지워진 코드의 자릿수가 짝수번째인 경우
				continue;
			}
			
			if(i % 2 == 0) total += (arr[i] - '0');
			else total += (arr[i] - '0') * 3;
		}
		
		int diff = 10 - checkSign;
		checkSign = diff == 10 ? 0 : diff;		// 끝자리가 10으로 계산되면 이는 0
		
		for(int i = 0; i < 10; i++) {
			int value = (total + i * convert) % 10;
			if(checkSign == value) return i;
		}
		
		return 0;
	}
}
