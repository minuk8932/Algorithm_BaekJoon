package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 9086번: 문자열
 */
public class Boj9086 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			String line = br.readLine();
			sb.append(line.charAt(0)).append(line.charAt(line.length() - 1)).append(NEW_LINE);
		}
		
		System.out.println(sb); // 결과 출력
	}
}
