package baekjoon_contest;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BSIS_ProblemD {
	private static final int INF = 9_000_001;
	private static boolean[] prime = new boolean[INF];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		
		isPrime();
		
		int cnt = 0, idx = 0;
		
		for(int i = 0; i < INF; i++) {
			if(!prime[i]) {
				cnt++;
				
				if(cnt == K) {
					idx = i;
					break;
				}
			}
		}
		
		System.out.println(idx);
	}
	private static void isPrime() {
		prime[0] = prime[1] = true;
		
		for(int i = 2; i < INF; i++) {
			if(prime[i]) continue;
			
			for(int j = i + i; j < INF; j += i) {
				prime[j] = true;
			}
		}
	}
}

