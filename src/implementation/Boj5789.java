package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 5789번: 한다 안한다.
 *
 *	@see https://www.acmicpc.net/problem/5789/
 *
 */
public class Boj5789 {
	private static final String YES = "Do-it\n";
	private static final String NO = "Do-it-Not\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		while(N-- > 0) {
			char[] arr = br.readLine().toCharArray();
			int half = arr.length / 2;
			
			sb.append(arr[half] == arr[half - 1] ? YES: NO);
		}
		
		System.out.print(sb);
	}
}
