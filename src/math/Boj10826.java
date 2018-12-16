package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 
 * 	@author minchoba
 *	백준 10826번: 피보나치 수 4
 *
 *	@see https://www.acmicpc.net/problem/10826/
 *
 */
public class Boj10826 {
	private static final int INF = 19;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		System.out.println(finbonacci(n));		// 결과 출력
	}
	
	private static String finbonacci(int N) {
		String[] dp = new String[N + 1];
		dp[0] = "0";
		
		if(N > 0) dp[1] = "1";
		for(int i = 2; i < N + 1; i++) {			
			if(dp[i - 1].length() < INF) {
				dp[i] = String.valueOf(Long.parseLong(dp[i - 2]) + Long.parseLong(dp[i - 1]));
			}
			else {
				dp[i] = getBigNumSum(dp[i - 2], dp[i - 1]);		// Long 범위를 넘어가는 경우
			}
		}
		
		return dp[N];
	}
	
	private static String getBigNumSum(String n1, String n2) {
		int leng1 = n1.length();
		int leng2 = n2.length();
		int leng = 0;
		
		char[] num1 = null;
		char[] num2 = null;
		
		if(leng1 > leng2) {
			num1 = n1.toCharArray();
			leng = leng1;
			
			num2 = stringToCharArr(n2, leng2, leng1);
		}
		else {
			num2 = n2.toCharArray();
			leng = leng2;
			
			num1 = stringToCharArr(n1, leng1, leng2);
		}
		
		return makeStringNumber(leng, num1, num2);
	}
	
	private static char[] stringToCharArr(String A, int leng1, int leng2) {
		char[] arr = new char[leng2];
		int idx = 0;
		
		for(int i = 0; i < leng2; i++) {
			if(i < leng2 - leng1) {
				arr[i] = '0';
			}
			else {
				arr[i] = A.charAt(idx++);
			}
		}
		
		return arr;
	}
	
	private static String makeStringNumber(int length, char[] num1, char[] num2) {		// 숫자를 문자열로 변환해 저장
		Deque<Integer> stack = new ArrayDeque<>();
		int carry = 0;
		
		for(int i = length - 1; i >= 0; i--) {
			int tmp = (num1[i] - '0') + (num2[i] - '0') + carry;
			
			if(tmp > 9) {
				tmp -= 10;
				stack.push(tmp);
				
				carry = 1;
			}
			else {
				stack.push(tmp);
				carry = 0;
			}
		}
		
		if(carry == 1) stack.push(1);
		
		StringBuilder sb = new StringBuilder();
		while(!stack.isEmpty()) sb.append(stack.pop());
		
		return sb.toString();
	}
}
