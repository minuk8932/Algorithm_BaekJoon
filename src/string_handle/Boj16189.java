package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 16189번: Repetitive Palindrome
 *
 *	@see https://www.acmicpc.net/problem/16189/
 *
 */
public class Boj16189 {
	private static final String TRUE = "YES";
	private static final String FALSE = "NO";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] sChar = br.readLine().toCharArray();
		long floor = Long.parseLong(br.readLine());			// 입력이 int 범위를 넘어가므로 long 또는 String으로 받는다.
		
		System.out.println(isPalindrome(sChar));
	}
	
	private static String isPalindrome(char[] arr) {		// 팰린드롬인지 아닌지 확인
		int leng = arr.length / 2;
		
		for(int i = 0; i <= leng; i++) {
			if(arr[i] != arr[arr.length - 1 - i]) return FALSE;
		}
		
		return TRUE;
	}
}
