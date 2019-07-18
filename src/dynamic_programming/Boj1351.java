package dynamic_programming;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 1351번: 무한 수열
 *
 *	@see https://www.acmicpc.net/problem/1351/
 *
 */
public class Boj1351 {
	private static HashMap<Long, Long> dp = new HashMap<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long N = Long.parseLong(st.nextToken());
		long P = Long.parseLong(st.nextToken());
		long Q = Long.parseLong(st.nextToken());
		
		dp.put((long) 0, (long) 1);
		System.out.println(recursion(N, P, Q));
	}
	
	private static long recursion(long n, long p, long q) {
		if(dp.containsKey(n)) return dp.get(n);							// 이미 값이 저장되어있으면 바로 반환	
		
		dp.put(n, recursion(n / p, p, q) + recursion(n / q, p, q));		// 값 저장이 안된 경우		
		return dp.get(n);
	}
}
