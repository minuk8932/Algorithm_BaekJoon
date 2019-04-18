package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 	@author minchoba
 *	백준 9421번: 소수상근수
 *
 *	@see https://www.acmicpc.net/problem/9421/
 *
 */
public class Boj9421 {
	private static final String NEW_LINE = "\n";
	private static final int INF = 1_000_000;
	
	private static int[] primes = new int[80_000];
	private static boolean[] visit;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		prime(n);		// 소수 찾기
		System.out.println(getHappyPrimeNumber(n));
	}
	
	private static void prime(int N) {
		boolean[] tmp = new boolean[N + 1];		
		tmp[0] = tmp[1] = true;
		
		for(int i = 2; i < N + 1; i++) {
			if(tmp[i]) continue;
			
			for(int j = i + i; j < N + 1; j += i) {
				tmp[j] = true;
			}
		}
		
		int index = 0;
		for(int i = 0; i < tmp.length; i++) {
			if(tmp[i]) continue;
			primes[index++] = i;
		}
	}
	
	private static StringBuilder getHappyPrimeNumber(int N) {
		StringBuilder sb = new StringBuilder();
		int size = (int) Math.sqrt(INF);
		
		for(int index = 0; index < primes.length; index++) {
			if(primes[index] == 0) break;
			
			visit = new boolean[size];			// 크기 막 잡지 말자
			if(isHappy(primes[index])) sb.append(primes[index]).append(NEW_LINE);		// 소수 상근수인 경우
		}

		return sb;
	}
	
	private static boolean isHappy(int current) {
		while(true) {			
			int next = 0;
			int value = current;
			
			while(value > 0) {
				next += (value % 10) * (value % 10);		// 각자리 제곱합
				value /= 10;
			}
			
			if(next == 1) return true;
			
			current = next;
			
			if(visit[current]) break;			// 이미 방문한 수면 끝
			visit[current] = true;
		}
		
		return false;
	}
}
