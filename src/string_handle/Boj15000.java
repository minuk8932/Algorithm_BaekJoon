package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 15000번: CAPS
 *
 *	@see https://www.acmicpc.net/problem/15000/
 *
 */
public class Boj15000 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for(char c: br.readLine().toCharArray()) {		// ASCII code
			sb.append((char) (c - 32));
		}
		
		System.out.println(sb);
	}
}
