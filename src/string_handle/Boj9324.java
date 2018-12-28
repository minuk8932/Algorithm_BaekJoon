package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 9324번: 진짜 메세지
 *
 *	@see https://www.acmicpc.net/problem/9324/
 *
 */
public class Boj9324 {
	private static final int ASCII = 65;

	private static final String NEW_LINE = "\n";
	private static final String OK = "OK";
	private static final String NO = "FAKE";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		while(T-- > 0){
			String msg = br.readLine();
			char[] words = msg.toCharArray();
			
			sb.append(realOrFake(words) ? OK: NO).append(NEW_LINE);	 // 진짜니?
		}
		
		System.out.println(sb.toString());
	}
	
	private static boolean realOrFake(char[] arr) {
		int[] alpha = new int[26];
		
		for(int i = 0; i < arr.length; i++){
			int idx = arr[i] - ASCII;
			
			alpha[idx]++;
			
			if(alpha[idx] % 3 == 0) {
				if(i == arr.length - 1) return false;		// 3배수로 채워졌는데 그것이 마지막이다
				if(arr[i] != arr[i + 1]) return false;		// 3배수로 채워졌는데 다음 글자가 지금 글자와 다르다
				
				i++;		// 진짜라면 인덱스 하나 패스
			}
		}
		
		return true;
	}
}
