package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 16944번: 강력한 비밀번호
 *
 *	@see https://www.acmicpc.net/problem/16944/
 *
 */
public class Boj16944 {
	private static final char[] WORD = {'!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '+'};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[] password = br.readLine().toCharArray();
		
		System.out.println(add(N, password));
	}
	
	private static int add(int n, char[] arr) {
		int count = 0;
		
		count += hasKeyword(arr, WORD);		// 우선 구성요소 확인
		count += hasValue(arr, 'A', 'Z');
		count += hasValue(arr, '0', '9');
		count += hasValue(arr, 'a', 'z');
		
		int length = arr.length;
		while(length + count < 6) {			// 구성요소를 채웠으니, 길이를 맞춤
			count++;
		}
		
		return count;
	}
	
	private static int hasKeyword(char[] arr, char[] comp) {
		for(int i = 0; i < arr.length; i++) {
			for(char c: comp) {
				if(arr[i] == c) return 0;
			}
		}
		
		return 1;
	}
	
	private static int hasValue(char[] arr, char start, char end) {
		for(char c: arr) {
			if(start <= c && end >= c) return 0;
		}
		
		return 1;
	}
}
