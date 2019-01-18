package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 4564번: 숫자 카드놀이
 *
 *	@see https://www.acmicpc.net/problem/4564/
 *
 */
public class Boj4564 {
	private static final String NEW_LINE = "\n";
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			String line = br.readLine();
			if(line.equals("0")) break;
			
			sb.append(line).append(SPACE);
			
			while(line.length() > 1) {
				line = getNumberMultiple(line.toCharArray());
				sb.append(line).append(SPACE);
			}
			
			sb.append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static String getNumberMultiple(char[] arr) {
		int times = 1;
		
		for(int i = 0; i < arr.length; i++) {			//  자리별 곱셈
			times *= (arr[i] - '0');
		}
		
		return String.valueOf(times);
	}
}
