package math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 	@author minchoba
 *	백준 1990번: 소수인 팰린드롬
 *
 *	@see https://www.acmicpc.net/problem/1990/
 *
 */
public class Boj1990 {
	private static final int INF = 10_000_001;		// 1천 ~ 1억 까진 소수인 팰린드롬이 존재하지 않으므로 배제하고 고려
	private static final int SIZE = 6_000_001;
	
	private static final char NEW_LINE = '\n';
	
	private static boolean[] prime = new boolean[INF];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		br.close();
		
		isPrime();		// 메소드를 통해 소수인 수를 구함
		
		int idx = 0;
		int[] palin = new int[SIZE];
		
		for(int i = 0; i < INF; i++) {
			if(!prime[i]) continue;
			
			if(i < 10) {
				palin[idx++] = i;			// 10 미만의 수는 배열에 바로 담아줌
			}
			else {
				String num = String.valueOf(i);
				int leng = num.length();
				
				boolean isPalindrome = true;
				
				for(int j = 0; j < leng / 2; j++) {		// 10 이상의 수는 팰린드롬인지 판별 후 배열에 저장
					if(num.charAt(j) != num.charAt(leng - 1 - j)) {
						isPalindrome = false;
						break;
					}
				}
				
				if(isPalindrome) palin[idx++] = i;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < SIZE; i++) {
			if(palin[i] >= a && palin[i] <= b) {		// a ~ b 사이의 소수인 팰림드롬을 버퍼에 담음
				sb.append(palin[i]).append(NEW_LINE);
			}
		}
		
		sb.append(-1);			// 마지막 열 출력 조건
		
		System.out.println(sb.toString());		// 결과 값 한번에 출력
	}
	
	/**
	 * 소수 도출 메소드
	 */
	private static void isPrime() {
		Arrays.fill(prime, true);
		prime[0] = prime[1] = false;
		
		for(int i = 2; i < INF; i++) {				// 에라토스테네스의 체 이용
			if(!prime[i]) continue;
			
			for(int j = i + i; j < INF; j += i) {
				prime[j] = false;
			}
		}
	}
}
