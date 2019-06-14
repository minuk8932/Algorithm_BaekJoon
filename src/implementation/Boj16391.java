package implementation;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 16391번: Zebras and Ocelots
 *
 *	@see https://www.acmicpc.net/problem/16391/
 *
 */
public class Boj16391 {
	private static final String OCELOT = "O";
	private static long[] pow = new long[60];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		init();
		
		boolean[] bin = new boolean[N];
		
		for(int i = 0; i < N; i++) {
			if(br.readLine().equals(OCELOT)) continue;
			bin[i] = true;								// 지브라인 경우 true
		}
		
		System.out.println(rotate(N, bin));
	}
	
	private static void init() {
		long value = 1;
		
		for(int i = 0; i < pow.length; i++) {			// 60번째까지 제곱수
			pow[i] = value;
			value *= 2;
		}
	}
	
	private static long rotate(int n, boolean[] flag) {			
		long from = 0, to = 0;
		
		for(int i = 0; i < n; i++) {
			to += pow[n - 1 - i];			// 목적 값
			
			if(!flag[i]) continue;
			from += pow[n - 1 - i];			// 시작 값
		}
		
		return to - from;
	}
}
