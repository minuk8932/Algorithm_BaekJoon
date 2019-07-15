package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 13701번: 중복 제거
 *
 *	@see https://www.acmicpc.net/problem/13701/
 *
 */
public class Boj13701 {
	private static final String SPACE = " ";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		boolean[] pass = new boolean[33554432];
		while(st.hasMoreTokens()) {
			int cur = Integer.parseInt(st.nextToken());
			if(pass[cur]) continue;
			
			pass[cur] = true;
			sb.append(cur).append(SPACE);
		}
		
		System.out.println(sb.toString());
	}
}
