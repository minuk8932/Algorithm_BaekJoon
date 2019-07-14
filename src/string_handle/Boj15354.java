package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author exponential-e
 *	백준 15354번: Aron
 *
 *	@see https://www.acmicpc.net/problem/15354
 *
 */
public class Boj15354 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int count = 0;
		char current = ' ';
		for(int i = 0; i < N; i++) {
			char next = br.readLine().charAt(0);
			
			if(current != next) count++;
			current = next;
		}
		
		System.out.println(count + 1);
	}
}
