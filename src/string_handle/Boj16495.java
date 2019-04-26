package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 16495번: 열 순서
 *
 *	@see https://www.acmicpc.net/problem/16495/
 *
 */
public class Boj16495 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] alpha = br.readLine().toCharArray();
		System.out.println(getSequence(alpha));
	}
	
	private static long getSequence(char[] arr) {
		long count = 0;
		
		for(int i = 0; i < arr.length; i++) {
			count += Math.pow(26, arr.length - 1 - i) * (arr[i] - 'A' + 1);		// 열 순서 공식
		}
		
		return count;
	}
}
