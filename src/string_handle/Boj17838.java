package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author exponential-e
 *	백준 17838번: 커맨드
 *
 *	@see https://www.acmicpc.net/problem/17838/
 *
 */
public class Boj17838 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		while(T-- > 0) {
			char[] input = br.readLine().toCharArray();
			
			if(input.length == 7) {
				if(input[0] == input[1] && input[1] == input[4]) {
					if(input[2] == input[3] && input[3] == input[5] && input[5] == input[6] && input[0] != input[2]) sb.append(1).append(NEW_LINE);
					else sb.append(0).append(NEW_LINE);
				}
				else {
					sb.append(0).append(NEW_LINE);
				}
			}
			else {
				sb.append(0).append(NEW_LINE);
			}
		}
		
		System.out.println(sb.toString());
	}
}
