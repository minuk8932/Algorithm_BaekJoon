package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 5355번: 화성 수학
 *
 *	@see https://www.acmicpc.net/problem/5355/
 *
 */
public class Boj5355 {
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			double result = Double.parseDouble(st.nextToken());
			
			while(st.hasMoreTokens()) {
				switch (st.nextToken()) {
				case "@":
					result *= 3;
					break;
					
				case "%":
					result += 5;
					break;
					
				case "#":
					result -= 7;
					break;
				}
			}
			
			sb.append(String.format("%.2f", result)).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
}
