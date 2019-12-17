package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 2985번: 세 수
 *
 *	@see https://www.acmicpc.net/problem/2985/
 *
 */
public class Boj2985 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		System.out.println(formula(a, b, c));
	}
	
	private static String formula(int a, int b, int c) {
		StringBuilder sb = new StringBuilder();
		
		if(a + b == c) sb.append(a).append('+').append(b).append('=').append(c);
		else if(a - b == c) sb.append(a).append('-').append(b).append('=').append(c);
		else if(a * b == c) sb.append(a).append('*').append(b).append('=').append(c);
		else if(a / b == c && a % b == 0) sb.append(a).append('/').append(b).append('=').append(c);
		else if(c + b == a) sb.append(a).append('=').append(b).append('+').append(c);
		else if(c * b == a) sb.append(a).append('=').append(b).append('*').append(c);
		else if(b - c == a) sb.append(a).append('=').append(b).append('-').append(c);
		else sb.append(a).append('=').append(b).append('/').append(c);
		
		return sb.toString();
	}
}
