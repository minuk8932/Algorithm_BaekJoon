package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 2744번: 대소문자 바꾸기
 *
 *	@see https://www.acmicpc.net/problem/2744/
 *
 */
public class Boj2744 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for(char c: br.readLine().toCharArray()) {
			if(c >= 'A' && c <= 'Z') sb.append((char) (c + 32));		// ref: ASCII code
			else sb.append((char) (c - 32));
		}
		
		System.out.println(sb);
	}
}
