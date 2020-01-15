package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author exponential-e
 *	백준 17530번: Buffoon
 *
 *	@see https://www.acmicpc.net/problem/17530/
 *
 */
public class Boj17530 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int value = -1;
		int max = 0;
		
		for(int i = 0; i < N; i++) {
			int count = Integer.parseInt(br.readLine());
			if(value == -1) value = count;
			if(max < count) max = count;
		}
		
		System.out.println(max == value ? 'S': 'N');
	}
}
