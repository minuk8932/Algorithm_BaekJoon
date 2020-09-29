package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author exponential-e
 * 	백준 6109번: Another Cow Number Game
 * 
 * 	@see https://www.acmicpc.net/problem/6109/
 *
 */
public class Boj6190 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		System.out.println(search(N));
	}
	
	private static int search(int n) {
		int count = 0;
		long loop = n;
		
		while(loop != 1) {			
			if(loop % 2 == 0) loop /= 2;
			else loop = loop * 3 + 1;
			
			count++;
		}
		
		return count;
	}
}
