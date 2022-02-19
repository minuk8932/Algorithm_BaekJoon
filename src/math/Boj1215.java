package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author exponential-e
 * 백준 1215번: 잘못 작성한 요세푸스 코드
 *
 * @see https://www.acmicpc.net/problem/1215
 *
 */
public class Boj1215 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long n = Long.parseLong(st.nextToken());
		long k = Long.parseLong(st.nextToken());
		
		System.out.println(josephs(n, k));
	}
	
	private static long josephs(long N, long K) {
		long sum = N <= K ? 0: (N - K) * K;

		long range = (long) Math.floor(Math.sqrt(K));
		for(long factor = 1; factor < range; factor++) {
			long left = Math.min(K / factor, N);
			long right = K / (factor + 1) + 1;

			if(left < right) continue;
			long section = (left - right + 1);
			sum += K * section - ((factor * (left + right) * section) >> 1);
		}

		long loop = Math.min(N, K / range);
		for(long factor = 1; factor <= loop; factor++) {
			sum += K % factor;
		}
		
		return sum;
	}
}
