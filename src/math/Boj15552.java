package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 15552번 : 빠른 A+B
 *
 *	@see https://www.acmicpc.net/problem/15552/
 *
 */
public class Boj15552 {
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	public static void main(String[] args) throws Exception{												// 빠른 연산을 위해
																																	//	split -> StringTokenizer
																																	//	System.out.println() -> StringBuilder()
																																	// Scanner -> BufferedReader()
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder(); 
		while(T-- > 0){
			StringTokenizer st = new StringTokenizer(br.readLine(), SPACE);
			
			sb.append(Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken())).append(NEW_LINE);
		}
		
		System.out.println(sb.toString());		// StringBuilder로 System.out.println()의 횟수를 줄여줌
	}
}
