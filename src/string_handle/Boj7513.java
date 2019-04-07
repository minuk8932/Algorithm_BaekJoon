package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 7513번: 준살 프로그래밍 대회
 *
 *	@see https://www.acmicpc.net/problem/7513/
 *
 */
public class Boj7513 {
	private static final String NEW_LINE = "\n";
	private static final String S = "Scenario #";
	private static final String COLON = ":";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t < T + 1; t++) {
			int m = Integer.parseInt(br.readLine());
			String[] pw = new String[m];
			
			for(int i = 0; i < m; i++) {
				pw[i] = br.readLine();
			}
			
			sb.append(S).append(t).append(COLON).append(NEW_LINE);
			
			int n = Integer.parseInt(br.readLine());
			while(n-- > 0) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int k = Integer.parseInt(st.nextToken());
				
				while(k-- > 0) {
					sb.append(pw[Integer.parseInt(st.nextToken())]);
				}
				
				sb.append(NEW_LINE);
			}
			sb.append(NEW_LINE);
		}
		
		System.out.print(sb);
	}
}
