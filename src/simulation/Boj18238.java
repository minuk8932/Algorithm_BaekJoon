package simulation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author exponential-e
 *	백준 18238번: ZOAC 2
 *
 *	@see https://www.acmicpc.net/problem/18238/
 *
 */
public class Boj18238 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] input = br.readLine().toCharArray();
		
		System.out.println(count(input));
	}
	
	private static int count(char[] arr) {
		int count = 0;
		char current = 'A';
		
		for(char c: arr) {
			count += Math.min(find(c, current, 1), find(c, current, -1));
			current = c;
		}
		
		return count;
	}
	
	private static int find(char c, char current, int adder) {
		int cnt = 0;
		
		while(c != current) {
			current += adder;
			if(current > 'Z') current = 'A';
			if(current < 'A') current = 'Z';
			cnt++;
		}
		
		return cnt;
	}
}
