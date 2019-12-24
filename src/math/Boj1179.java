package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 	@author exponential-e
 *	백준 1179번: 마지막 조세퍼스 문제
 *
 *	@see https://www.acmicpc.net/problem/1179/
 *
 */
public class Boj1179 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long N = Long.parseLong(st.nextToken());
		long K = Long.parseLong(st.nextToken());
		
		System.out.println(joseph(N, K) + 1);
	}
	
	private static long joseph (long n, long k) {
		if (n == 1) return 0;
	    if (k == 1) return n - 1;
	    if (k > n) return (joseph(n - 1, k) + k) % n;
	    
	    long cnt = n / k;					// except
	    long res = joseph(n - cnt, k);
	    
	    res -= n % k;						// remove spare
	    
	    if (res < 0) res += n;
	    else res += res / (k - 1);
	    
	    return res;
	}
}
