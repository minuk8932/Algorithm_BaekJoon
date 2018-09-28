package string_handle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
 * 	@author minchoba
 *	백준 1747번: 소수 & 팰린드롬
 *
 *	@see https://www.acmicpc.net/problem/1747/
 *
 */
public class Boj1747 {
	private static final int INF = 2_000_001;
	private static final int SIZE = 80_000;
	
	private static boolean[] prime = new boolean[INF];
	
	public static void main(String[] args)throws Exception{
		// 버퍼를 통한 값 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		isPrime();		// 메소드를 통해 소수를 찾아냄
		
		int idx = 0;
		int[] palin = new int[SIZE];
		
		for(int i = 2; i < INF; i++) {
			if(!prime[i]) continue;		// 소수가 아닌 수의 경우 다음으로
			
			if(i < 10) {			// 10보다 작은 경우 모두 팰린드롬 처리
				palin[idx++] = i;
			}
			else {
				String num = String.valueOf(i);
				int leng = num.length();
				
				boolean isPalindrome = true;
				
				for(int j = 0; j < leng / 2; j++) {					// 10보다 큰 수의 경우 각 자리를 비교해 팰린드롬 여부 판별
					if(num.charAt(j) != num.charAt(leng - 1 - j)) {
						isPalindrome = false;
						break;
					}
				}
				
				if(isPalindrome) palin[idx++] = i;		// 팰린드롬인 경우 배열에 저장
			}
		}
		
		int  res = 0;
		
		for(int i = 0; i < palin.length; i++) {
			if(palin[i] >= N) {					// N보다 크거나 같은 수 중 가장 작은 팰린드롬을 변수에 저장
				res = palin[i];
				break;
			}
		}
		
		System.out.println(res);		// 결과 값 출력
	}
	
	/**
	 * 소수 도출 메소드
	 */
	private static void isPrime() {
		Arrays.fill(prime, true);
		prime[0] = prime[1] = false;
		
		for(int i = 2; i < INF; i++) {			// 에라토스테네스의 체 이용
			if(!prime[i]) continue;
			
			for(int j = i + i; j < INF; j += i) {
				prime[j] = false;
			}
		}
	}
}
