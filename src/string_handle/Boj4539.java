package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 4539번: 반올림
 *
 *	@see https://www.acmicpc.net/problem/4539/
 *
 */
public class Boj4539 {
	private static final char NEW_LINE = '\n';
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		
		while(n-- > 0) {
			char[] nums = br.readLine().toCharArray();
			boolean carry = false;
			
			sb.append(Process(nums, carry)).append(NEW_LINE);		// 결과 출력
		}
		
		System.out.println(sb);
	}
	private static String Process(char[] arr, boolean c) {
		boolean isTen = false;
		
		for(int i = arr.length - 1; i >= 0; i--) {
			int num = (arr[i] - '0');
			
			if(i == 0) {			// 마지막 수는 반올림 x
				if(c) num += 1;
				if(num == 10) {		// num의 이전 수가 9인 경우
					num = 0;
					isTen = true;
				}
				
				arr[i] = (char)(num + '0');
				break;
			}
			
			c = doRound(c, num);	// 반올림!
			arr[i] = '0';
		}
		
		return getString(arr, isTen);
	}
	
	private static boolean doRound(boolean c, int n) {
		if(c) return n + 1 >= 5 ? true: false;
		else return n >= 5 ? true: false;
	}
	
	private static String getString(char[] arr, boolean carry) {
		String res = carry ? "1" : "";
		
		for(int i = 0; i < arr.length; i++) {
			res += arr[i];
		}
		
		return res;
	}
}
