package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
 * 	@author exponential-e
 *	백준 14715번: 전생했더니 슬라임 연구자였던 건에 대하여 (Easy) 
 *
 *	@see https://www.acmicpc.net/problem/14715/
 *
 */
public class Boj14715 {
	private static boolean[] prime = new boolean[1_000_001];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		isPrime();
		
		System.out.println(getDivide(K));
	}
	
	private static void isPrime() {
		Arrays.fill(prime, true);
		prime[0] = prime[1] = false;
		
		for(int i = 2; i < 1_001; i++) {
			if(!prime[i]) continue;
			
			for(int j = i + i; j < prime.length; j += i) {
				prime[j] = false;
			}
		}
	}
	
	private static int getDivide(int k) {
		if(prime[k]) return 0;						// 소수인 경우
		int count = 0;
		
		for(int i = 2; i < prime.length; i++) {			
			while(k % i == 0) {						// 소수인 약수의 갯수
				k /= i;
				count++;
			}
			
			if(k == 1) break;
		}
		
		int result = 0, pow = 1;
		while(count > pow) {						// 트리 가지뻗기
			pow *= 2;
			result++;
		}
		
		return result;
	}
}
