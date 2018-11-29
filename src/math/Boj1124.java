package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1124번: 언더프라임
 *
 *	@see https://www.acmicpc.net/problem/1124/
 *
 */
public class Boj1124 {
	private static final int INF = 100_001;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		boolean[] prime = isPrime();		
		System.out.println(underPrime(A, B, prime));		// 결과 출력
	}
	
	private static int underPrime(int from, int to, boolean[] primeList) {
		int res = 0;
		
		for(int i = from; i < to + 1; i++) {
			int idx = getPrimeListCounts(i, primeList);
			if(primeList[idx]) res++;					// 약수의 개수를 소수로 갖는 수의 개수 (자신, 1 제외)
		}
		
		return res;
	}
	
	private static int getPrimeListCounts(int num, boolean[] arr) {
		int counts = 0, div = 2;
		
		while(num > 1) {
			if(num % div == 0) {
				counts++;
				num /= div;
			}
			else {				
				while(true) {			// 나눌 수가 소수가 될때까지 더함
					div++;
					if(arr[div]) break;
				}
			}
		}
		
		return counts;
	}
	
	private static boolean[] isPrime() {
		boolean[] arr = new boolean[INF];
		
		Arrays.fill(arr, true);
		arr[0] = arr[1] = false;
		
		for(int i = 2; i < INF; i++) {
			for(int j = i + i; j < INF; j += i) {
				if(!arr[j]) continue;
				arr[j] = false;
			}
		}
		
		return arr;
	}
}
