package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 1284번: 집 주소
 *
 *	@see https://www.acmicpc.net/problem/1284/
 *
 */
public class Boj1284 {
	private static final String NEW_LINE = "\n";
	private static final int[] NUMBER = {4, 2, 3, 3, 3, 3, 3, 3, 3, 3};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			String input = br.readLine();
			if(input.equals("0")) break;
			
			sb.append(getAddress(input)).append(NEW_LINE);
		}
		
		System.out.print(sb);
	}
	
	private static int getAddress(String str) {
		char[] arr = str.toCharArray();
		int size = 1;
		
		for(int i = 0; i < arr.length; i++) {		// 공백 + 주소 번호
			size += NUMBER[arr[i] - '0'] + 1;
		}
		
		return size;
	}
}
