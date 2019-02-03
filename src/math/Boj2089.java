package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

/**
 * 
 * 	@author minchoba
 *	백준 2089번: -2진수
 *
 *	@see https://www.acmicpc.net/problem/2089/
 *
 */
public class Boj2089 {
	private static final String ONE = "1";
	private static final String ZERO = "0";
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		double N = Double.parseDouble(br.readLine());
		
		System.out.println(N == 0 ? 0 : getBinarySystem(N));
	}
	
	private static StringBuilder getBinarySystem(double num) {
		StringBuilder sb = new StringBuilder();
		ArrayDeque<String> stack = new ArrayDeque<>();		
		
		while(num != 0) {			
			stack.push(pushElement(num % 2));
			num = Math.ceil(num / -2.0);			// -2로 나눈 값을 올림 (기본 2진수: 2로 나눈 값을 내림)
		}
		
		while(!stack.isEmpty()) sb.append(stack.pop());
		
		return sb;
	}
	
	private static String pushElement(double product) {
		return product == 0 ? ZERO : ONE;
	}
}
