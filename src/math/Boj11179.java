package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * 	@author exponential-e
 *	백준 11179번: 2진수 뒤집기
 *
 *	@see https://www.acmicpc.net/problem/11179/
 *
 */
public class Boj11179 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		System.out.println(makeBinary(N));
	}
	
	private static long makeBinary(int n) {
		Queue<Integer> q = new LinkedList<>();
		
		while(n > 0) {				// make binary
			q.offer(n % 2);
			n /= 2;
		}
		
		return value(q);
	}
	
	private static long value(Queue<Integer> q) {
		long num = 0;
		int idx = q.size() - 1;
		
		while(!q.isEmpty()) {		// reversed
			num += Math.pow(2, idx--) * q.poll();
		}
		
		return num;
	}
}
