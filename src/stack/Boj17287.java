package stack;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author exponential-e
 *	백준 17287번: The Deeper, The Better
 *
 *	@see https://www.acmicpc.net/problem/17287/
 *
 */
public class Boj17287 {
	private static final char[] OPENED = {'(', '{', '['};
	private static final char[] CLOSED = {')', '}', ']'};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] str = br.readLine().toCharArray();
		
		System.out.println(getCost(str));
	}
	
	private static int getCost(char[] word) {
		int sum = 0, value = 0;
		
		for(char c: word) {
			sum += calculate(OPENED, c);								// 나오는 열린 괄호의 값 더하기
			if(c >= '0' && c <= '9') value = Math.max(value, sum);		// 더한 값 중 최대
			sum -= calculate(CLOSED, c);								// 닫힌 괄호의 값 빼기
		}
		
		return value;
	}
	
	private static int calculate(char[] bracket, char target) {
		for(int i = 0; i < 3; i++) {
			if(target == bracket[i]) return i + 1;
		}
		
		return 0;
	}
}
