package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 3036번: 링
 *
 *	@see https://www.acmicpc.net/problem/3036/
 *
 */
public class Boj3036 {
	private static final String SLASH = "/";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		br.readLine();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int origin = Integer.parseInt(st.nextToken());
		
		while(st.hasMoreTokens()) {
			int target = Integer.parseInt(st.nextToken());
			int divider = gcd(origin, target);
			
			sb.append(origin / divider).append(SLASH).append(target / divider).append(NEW_LINE);
		}
		
		System.out.println(sb);
	}
	
	private static int gcd(int a, int b) {
		if(b == 0) return a;
		else return gcd(b, a % b);
	}
}
