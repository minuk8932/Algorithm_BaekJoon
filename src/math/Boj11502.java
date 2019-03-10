package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
 * 	@author minchoba
 *	백준 11502번: 세개의 소수
 *
 *	@see https://www.acmicpc.net/problem/11502/
 *
 */
public class Boj11502 {
	private static boolean[] isPrime = new boolean[1_001];
	
	private static final String SPACE = " ";
	private static final String NEW_LINE = "\n";
	
	private static class Pair{
		int a;
		int b;
		int c;
		
		public Pair (int a, int b, int c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		getPrime();
		
		while(T-- > 0) {
			int K = Integer.parseInt(br.readLine());
			Pair result = process(K);
			
			if(result == null) sb.append(0).append(NEW_LINE);
			else sb.append(result.a).append(SPACE).append(result.b).append(SPACE).append(result.c).append(NEW_LINE);
		}
		
		System.out.print(sb);
	}
	
	private static void getPrime() {
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;
		
		for(int i = 2; i < isPrime.length; i++) {
			if(!isPrime[i]) continue;
			
			for(int j = i + i; j < isPrime.length; j += i) {
				isPrime[j] = false;
			}
		}
	}
	
	private static Pair process(int target) {		
		for(int i = 2; i < isPrime.length; i++) {
			if(!isPrime[i]) continue;
			
			for(int j = 2; j < isPrime.length; j++) {
				if(!isPrime[j]) continue;
				
				for(int k = 2; k < isPrime.length; k++) {
					if(!isPrime[k]) continue;
					if(i + j + k == target) return sort(new Pair(i, j, k));		// 세개 소수 정렬 후 반환
				}
			}
		}
		
		return null;		// 답 없음
	}
	
	private static Pair sort(Pair p) {
		int[] tmp = {p.a, p.b, p.c};
		Arrays.sort(tmp);
		
		return new Pair(tmp[0], tmp[1], tmp[2]);
	}
}
