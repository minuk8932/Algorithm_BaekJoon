package stack;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 2908번: 상수
 *
 *	@see https://www.acmicpc.net/problem/2908/
 *
 */
public class Boj2908 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		System.out.println(getConstant(st.nextToken(), st.nextToken()));
	}	
	
	private static int getConstant(String str1, String str2) {		
		int num1 = Integer.parseInt(getIn(str1));
		int num2 = Integer.parseInt(getIn(str2));
		
		return num1 > num2 ? num1 : num2;
	}
	
	private static String getIn(String str){				// 역순으로
		ArrayDeque<Character> stack = new ArrayDeque<>();
		StringBuilder sb = new StringBuilder();
		
		for(char c: str.toCharArray()) {
			stack.push(c);
		}
		
		while(!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		
		return sb.toString();
	}
}
