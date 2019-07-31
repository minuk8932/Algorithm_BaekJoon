package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;

/**
 * 
 * 	@author exponential-e
 *	백준 4150번: 피보나치 수
 *
 *	@see https://www.acmicpc.net/problem/4150/
 *
 */
public class Boj4150 {
	private static LinkedList<Integer> a1;
	private static LinkedList<Integer> a2;
	private static ArrayDeque<Integer> stack;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		System.out.println(N == 0 ? 0: getFibonacci(N));
	}
	
	private static String getFibonacci(int n) {
		StringBuilder sb = new StringBuilder();
		if(n < 3) return sb.append(1).toString();
		
		long[] dp = new long[93];
		dp[1] = dp[2] = 1;
		
		for(int i = 3; i < dp.length; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}
		
		if(n <= dp.length - 1) return sb.append(dp[n]).toString();
		
		stack = new ArrayDeque<>();
		a1 = makeIntList(dp[91]);							// 91, 92번째 피보나치
		a2 = makeIntList(dp[92]);
		
		while(n-- > dp.length - 1){			
			int loop = Math.max(a1.size(), a2.size());
			int[] size = {a1.size() - 1, a2.size() - 1};	// 숫자의 길이
			int[] idx = {0, 0};
			int carry = 0;
			
			while(loop-- > 0) {
				int[] diff = {size[0] - idx[0]++, size[1] - idx[1]++};		// 뒷자리부터 인덱스 접근
				int sum = 0;
				
				if(diff[0] >= 0) sum += a1.get(diff[0]);
				if(diff[1] >= 0) sum += a2.get(diff[1]);

				if(carry == 1) {			// 이전 합에서 carry 옮김
					sum += 1;
					carry = 0;
				}
				
				if(sum >= 10) {				// 현재 합에서 carry 발생
					carry = 1;
					sum %= 10;
				}
				
				stack.push(sum);
			}
			
			if(carry == 1) stack.push(1);
			
			a1 = new LinkedList<>();
			while(!a2.isEmpty()) a1.add(a2.remove());		// 다음 피보나치로		
			while(!stack.isEmpty()) a2.add(stack.pop());
		}
		
		for(int fibo: a2) sb.append(fibo);		
		return sb.toString();
	}
	
	private static LinkedList<Integer> makeIntList(long num) {
		LinkedList<Integer> list = new LinkedList<>();
		
		for(char c: (num + "").toCharArray()) {
			list.add(c - '0');
		}
		
		return list;
	}
}
